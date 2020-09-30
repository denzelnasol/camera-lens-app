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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_CODE_CALCULATE_DOF = 30;
    public static final String EXTRA_LENS_INDEX = "lens index";

    private String make;
    private double aperture;
    private int focalLength;

    private LensManager manager;
    private static ArrayAdapter<Lens> adapter;

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

   /* @Override
    protected void onResume() {
        super.onResume();
        //adapter.addAll(manager.lens);
        populateListView();
    }*/

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
        if (requestCode == 1) {
            make = data.getStringExtra(LensActivity.EXTRA_MAKE);
            aperture = data.getDoubleExtra(LensActivity.EXTRA_APERTURE, 0);
            focalLength = data.getIntExtra(LensActivity.EXTRA_FOCAL_LENGTH, 0);
            manager.add(new Lens(make, aperture, focalLength));
            /*if (!manager.lens.isEmpty()) {
                TextView emptyList = (TextView) findViewById(R.id.empty);
                emptyList.setText("");
            }*/
            adapter.notifyDataSetChanged();
        }
        if (resultCode == CalculateDepthOfFieldActivity.RESULT_CODE_DELETE_LENS) {
            int lensIndex = data.getIntExtra(EXTRA_LENS_INDEX, 0);
            manager.lens.remove(lensIndex);
            adapter.notifyDataSetChanged();
            /*if (manager.lens.isEmpty()) {
                TextView emptyList = (TextView) findViewById(R.id.empty);
                emptyList.setText("Your lens list is currently Empty. To add more lenses, click the add button on the bottom right");
            }*/
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
        list.setEmptyView(findViewById(R.id.empty));
        list.setAdapter(adapter);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        View empty = findViewById(R.id.empty);
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setEmptyView(empty);
    }

    public class MyListAdapter extends ArrayAdapter<Lens> {
        public MyListAdapter() {
            super(MainActivity.this, R.layout.lens_list, manager.lens);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Check that a view is given
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.lens_list, parent, false);
            }

            // Find lens to use
            Lens currentLens = manager.getLens(position);

            // Fill the text
            TextView makeText = (TextView) itemView.findViewById(R.id.text_lens);
            makeText.setText(currentLens.getMake() + " " + currentLens.getFocalLength() + "mm F" + currentLens.getMaximumAperture());

            return itemView;
        }
    }
    // Go to Calculate DOF Activity for the lens clicked
    private void registerLensClickCallBack() {
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Lens clickedLens = manager.getLens(position);
                Intent intent = CalculateDepthOfFieldActivity.makeIntent(MainActivity.this);
                intent.putExtra(EXTRA_LENS_INDEX, position);
                startActivityForResult(intent, RESULT_CODE_CALCULATE_DOF);
            }
        });
    }
}