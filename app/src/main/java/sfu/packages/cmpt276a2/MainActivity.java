package sfu.packages.cmpt276a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LensManager manager = new LensManager();
    private DepthOfFieldCalculator calculator = new DepthOfFieldCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());
        populateLensList();
        populateListView();
    }

    private void populateLensList() {
        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4, 200));
    }

    private void populateListView() {
        String [] myItems = {"blue", "green"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.lens_list, R.id.test, myItems);
        //ArrayAdapter<Lens> adapter = new ArrayAdapter<Lens>(this, R.layout.lens_list, manager.getLens(0));

        ListView list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(adapter);
    }

}