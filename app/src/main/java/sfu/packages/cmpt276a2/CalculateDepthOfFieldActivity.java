package sfu.packages.cmpt276a2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CalculateDepthOfFieldActivity extends AppCompatActivity {

    private int lensIndex;
    private LensManager manager = LensManager.getInstance();
    private DepthOfFieldCalculator calculator;
    private Lens currentLens;

    double circleOfConfusion;
    double distanceToSubject;
    double selectedAperture;

    EditText circleOfConfusionInput;
    EditText distanceToSubjectInput;
    EditText selectedApertureInput;

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


    }

    private void setupCalculateButton() {
        Button btn = (Button) findViewById(R.id.calculateBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleOfConfusionInput = (EditText) findViewById(R.id.editCircleOfConfusionText);
                distanceToSubjectInput = (EditText) findViewById(R.id.editDistanceToSubjectText);
                selectedApertureInput = (EditText) findViewById(R.id.editSelectedApertureText);

                circleOfConfusion = Double.parseDouble(circleOfConfusionInput.getText().toString());
                distanceToSubject
            }
        });

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, CalculateDepthOfFieldActivity.class);
    }
}