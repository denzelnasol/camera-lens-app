package sfu.packages.cmpt276a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LensActivity extends AppCompatActivity {

     private String make;
     private double aperture;
     private int focalLength;

     EditText makeInput;
     EditText apertureInput;
     EditText focalLengthInput;

     public static final String EXTRA_MAKE = "the make";
     public static final String EXTRA_APERTURE = "the aperture";
     public static final String EXTRA_FOCAL_LENGTH = "the focal length";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lens2);
        setTitle("Lens Details");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lens_save:
                makeInput = (EditText) findViewById(R.id.editMakeText);
                apertureInput = (EditText) findViewById(R.id.editApertureText);
                focalLengthInput = (EditText) findViewById(R.id.editFocalLengthText);

                make = makeInput.getText().toString();
                aperture = Double.parseDouble(apertureInput.getText().toString());
                focalLength = Integer.parseInt(focalLengthInput.getText().toString());

                Intent intent = new Intent();
                intent.putExtra(EXTRA_MAKE, make);
                intent.putExtra(EXTRA_APERTURE, aperture);
                intent.putExtra(EXTRA_FOCAL_LENGTH, focalLength);
                setResult(Activity.RESULT_OK, intent);

                finish();
                return true;

            case R.id.lens_back:
                Intent intentCancel = new Intent();
                setResult(Activity.RESULT_CANCELED, intentCancel);
                finish();
                return true;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_lens_menu, menu);
        return true;
    }





    public static Intent makeIntent(Context context) {
        return new Intent(context, LensActivity.class);
    }
}