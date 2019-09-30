//@author Samuel Nguyen
package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    // See citation on implementing a Spinner
    String[] hairArray = {"\"Normal\"", "Short", "Mohawk"};

    /** External Citation
     * Date: September 28, 2019
     * Problem: Error with setting up Face
     * Resource: Stephen Nguyen
     * Solution: Use the object in the Project tab in activity_main.xml
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Face face = (Face) findViewById(R.id.faceView);

        SeekBar redValue = (SeekBar) findViewById(R.id.redValue);
        redValue.setOnSeekBarChangeListener(face);

        SeekBar greenValue = (SeekBar) findViewById(R.id.greenValue);
        greenValue.setOnSeekBarChangeListener(face);

        SeekBar blueValue = (SeekBar) findViewById(R.id.blueValue);
        blueValue.setOnSeekBarChangeListener(face);

        /** External Citation
         * Date: September 24, 2019
         * Problem: I needed to learn how to use RadioButtons.
         * Resource: https://developer.android.com/guide/topics/ui/controls/radiobutton.html#java
         * Solution: I used a RadioGroup.
         */

        RadioGroup features = (RadioGroup) findViewById(R.id.features);
        features.setOnCheckedChangeListener(face);
        RadioButton hair = (RadioButton) findViewById(R.id.hair);
        RadioButton eyes = (RadioButton) findViewById(R.id.eyes);
        RadioButton skin = (RadioButton) findViewById(R.id.skin);

        /** External Citation
         * Date: September 28, 2019
         * Problem: I needed to implement Spinner.
         * Resource: https://www.javatpoint.com/android-spinner-example
         * Solution: I used their steps of setting one up.
         */

        Spinner hairstyle = (Spinner) findViewById(R.id.hairstyle);
        hairstyle.setOnItemSelectedListener(face);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                hairArray);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairstyle.setAdapter(aa);


        Button randomFace = (Button) findViewById(R.id.randomFace);
        randomFace.setOnClickListener(face);
    }
}
