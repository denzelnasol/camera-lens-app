package sfu.packages.cmpt276a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class CalculateDepthOfFieldActivity extends AppCompatActivity {

    private int lensIndex;
    private LensManager manager = LensManager.getInstance();
    private Lens currentLens;

    double circleOfConfusion;
    double distanceToSubject;
    double selectedAperture;

    EditText circleOfConfusionInput;
    EditText distanceToSubjectInput;
    EditText selectedApertureInput;

    double hyperfocalDistance;
    double nearfocalPoint;
    double farfocalPoint;
    double depthOfField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_depth_of_field);
        setTitle("Calculate Depth of Field");

        Intent intent = getIntent();
        lensIndex = intent.getIntExtra(MainActivity.EXTRA_LENS_INDEX, 0);

        currentLens = manager.getLens(lensIndex);

        TextView displayCamera = (TextView) findViewById(R.id.cameraInfoText);
        displayCamera.setText("" + currentLens.getMake() + " " + currentLens.getFocalLength() + "mm F" + currentLens.getMaximumAperture());

        setupCalculateButton();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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
        inflater.inflate(R.menu.calculate_activity_menu, menu);
        return true;
    }

    private void setupCalculateButton() {
        Button btn = (Button) findViewById(R.id.calculateBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DepthOfFieldCalculator calculator = new DepthOfFieldCalculator();
                circleOfConfusionInput = (EditText) findViewById(R.id.editCircleOfConfusionText);
                distanceToSubjectInput = (EditText) findViewById(R.id.editDistanceToSubjectText);
                selectedApertureInput = (EditText) findViewById(R.id.editSelectedApertureText);

                circleOfConfusion = Double.parseDouble(circleOfConfusionInput.getText().toString());
                distanceToSubject = Double.parseDouble(distanceToSubjectInput.getText().toString());
                selectedAperture = Double.parseDouble(selectedApertureInput.getText().toString());

                if (selectedAperture < currentLens.getMaximumAperture()) {
                    TextView hyperfocalDistanceText = (TextView) findViewById(R.id.hyperfocalDistanceText);
                    hyperfocalDistanceText.setText("Invalid Aperture");

                    TextView nearfocalPointText = (TextView) findViewById(R.id.nearFocalDistanceText);
                    nearfocalPointText.setText("Invalid Aperture");

                    TextView farfocalPointText = (TextView) findViewById(R.id.farFocalDistanceText);
                    farfocalPointText.setText("Invalid Aperture");

                    TextView depthOfFieldText = (TextView) findViewById(R.id.depthOfFieldText);
                    depthOfFieldText.setText("Invalid Aperture");
                }
                else {
                    hyperfocalDistance = calculator.hyperFocalDistance(selectedAperture, circleOfConfusion, currentLens.getFocalLength());
                    TextView hyperfocalDistanceText = (TextView) findViewById(R.id.hyperfocalDistanceText);
                    hyperfocalDistanceText.setText("" + formatDouble(hyperfocalDistance/1000) + "m");

                    nearfocalPoint = calculator.nearFocalPoint(hyperfocalDistance, distanceToSubject, currentLens.getFocalLength());
                    TextView nearfocalPointText = (TextView) findViewById(R.id.nearFocalDistanceText);
                    nearfocalPointText.setText("" + formatDouble(nearfocalPoint) + "m");

                    farfocalPoint = calculator.farFocalPoint(hyperfocalDistance, distanceToSubject, currentLens.getFocalLength());
                    TextView farfocalPointText = (TextView) findViewById(R.id.farFocalDistanceText);
                    farfocalPointText.setText("" + formatDouble(farfocalPoint) + "m");

                    depthOfField = calculator.depthOfField(farfocalPoint, nearfocalPoint);
                    TextView depthOfFieldText = (TextView) findViewById(R.id.depthOfFieldText);
                    depthOfFieldText.setText("" + formatDouble(depthOfField) + "m");
                }
            }
        });

    }

    double formatDouble(double distance)
    {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(distance));
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, CalculateDepthOfFieldActivity.class);
    }
}