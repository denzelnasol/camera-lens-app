package sfu.packages.cmpt276a2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_CODE_CALCULATE_DOF = 30;
    public static final String EXTRA_LENS_INDEX = "lens index";

    private LensManager manager;
    private ArrayAdapter<Lens> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = LensManager.getInstance();


        populateLensList();
        populateListView();
        registerLensClickCallBack();

        setupFAB();
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LensActivity.makeIntent(MainActivity.this);
                intent.putExtra("test", 0);
                startActivityForResult(intent, 1);

            }
        });
    }

    // Gets called when activity started, finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }

        switch (requestCode) {
            case 1:
                String make = data.getStringExtra(LensActivity.EXTRA_MAKE);
                double aperture = data.getDoubleExtra(LensActivity.EXTRA_APERTURE, 0);
                int focalLength = data.getIntExtra(LensActivity.EXTRA_FOCAL_LENGTH, 0);
                manager.add(new Lens(make, aperture, focalLength));
                //updateList();
                this.adapter.notifyDataSetChanged();
                break;
        }
    }

    private void populateLensList() {
        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4, 200));
    }

    private void populateListView() {
        adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(adapter);
    }

/*
 private void updateList() {
        List<Lens> newLens = manager.lens;
        adapter.clear();
        adapter.addAll(newLens);
        populateListView();
    }
 */

    private void registerLensClickCallBack() {
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Lens clickedLens = manager.getLens(position);
                String message = "You clicked position " + position + " which is lens make " + clickedLens.getMake();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                Intent intent = CalculateDepthOfFieldActivity.makeIntent(MainActivity.this);
                intent.putExtra(EXTRA_LENS_INDEX, position);
                startActivityForResult(intent, RESULT_CODE_CALCULATE_DOF);
            }
        });
    }

    public class MyListAdapter extends ArrayAdapter<Lens> {
        public MyListAdapter() {
            super(MainActivity.this, R.layout.lens_list, manager.lens);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.lens_list, parent, false);
            }

            Lens currentLens = manager.getLens(position);

            TextView makeText = (TextView) itemView.findViewById(R.id.text_lens);
            makeText.setText(currentLens.getMake() + " " + currentLens.getFocalLength() + "mm F" + currentLens.getMaximumAperture());

            return itemView;
        }
    }
}