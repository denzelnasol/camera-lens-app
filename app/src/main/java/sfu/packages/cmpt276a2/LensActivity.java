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
    private int iconID;
    private boolean imageChosen = false;

    EditText makeInput;
    EditText apertureInput;
    EditText focalLengthInput;


    LensManager manager = LensManager.getInstance();

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

                if (make.length() < 0) {
                    Toast.makeText(LensActivity.this, "There must be a make", Toast.LENGTH_SHORT).show();
                }
                else if (aperture < 1.4) {
                    Toast.makeText(LensActivity.this, "The selected aperture must be greater than or equal to 1.4", Toast.LENGTH_SHORT).show();
                }
                else if (focalLength < 0) {
                    Toast.makeText(LensActivity.this, "The selected focal length must be greater than 0", Toast.LENGTH_SHORT).show();
                }
                else if (imageChosen == false) {
                    Toast.makeText(LensActivity.this, "You must select an image", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent();
                    manager.add(new Lens(make, aperture, focalLength, iconID));
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
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

    public void setImage1(View view) {
        iconID = R.drawable.lensimage1;
        imageChosen = true;
        Toast.makeText(LensActivity.this, "You chose lens image 1", Toast.LENGTH_SHORT).show();
    }

    public void setImage2(View view) {
        iconID = R.drawable.lensimage2;
        imageChosen = true;
        Toast.makeText(LensActivity.this, "You chose lens image 2", Toast.LENGTH_SHORT).show();
    }

    public void setImage3(View view) {
        iconID = R.drawable.lensimage3;
        imageChosen = true;
        Toast.makeText(LensActivity.this, "You chose lens image 3", Toast.LENGTH_SHORT).show();
    }

    public void setImage4(View view) {
        iconID = R.drawable.lensimage4;
        imageChosen = true;
        Toast.makeText(LensActivity.this, "You chose lens image 4", Toast.LENGTH_SHORT).show();
    }

    public void setImage5(View view) {
        iconID = R.drawable.lensimage5;
        imageChosen = true;
        Toast.makeText(LensActivity.this, "You chose lens image 5", Toast.LENGTH_SHORT).show();
    }
}
