package sfu.packages.cmpt276a2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LensManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = LensManager.getInstance();
        populateLensList();
        populateListView();
        registerClickCallBack();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());
    }

    private void populateLensList() {
        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4, 200));
    }

    private void populateListView() {
        ArrayAdapter<Lens> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(adapter);
    }

    private void registerClickCallBack() {
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Lens clickedLens = manager.getLens(position);
                String message = "You clicked position " + position + " which is lens make " + clickedLens.getMake();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                // Launch lens activity
                Intent intent = new Intent();
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<Lens> {
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