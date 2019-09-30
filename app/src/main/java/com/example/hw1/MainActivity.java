//@author Samuel Nguyen
//Note: The package name is different from the project name due to renaming.

package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

/** External Citation
 * Date: September 29, 2019
 * Problem: For some reason, when attempting to use setProgress() to update SeekBars,
 * the program kept crashing when selecting a RadioButton.
 * Resource: Stephen Nguyen
 * Solution: Have MainActivity implement the interfaces; Face now only extends SurfaceView.
 * This apparently fixed the problem.
 */

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
        AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener,
        View.OnClickListener{
    // See citation on implementing a Spinner
    String[] hairArray = {"\"Normal\"", "Short", "Mohawk"};

    /** External Citation
     * Date: September 28, 2019
     * Problem: Error with setting up Face
     * Resource: Stephen Nguyen
     * Solution: Use the object in the Project tab in activity_main.xml
     */
    Face face;

    // For updating colors
    int finalR;
    int finalG;
    int finalB;

    // 1 for Hair button checked, 2 for Eyes, 3 for Skin
    int checked = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        face = (Face) findViewById(R.id.faceView);

        SeekBar redValue = (SeekBar) findViewById(R.id.redValue);
        redValue.setOnSeekBarChangeListener(this);

        SeekBar greenValue = (SeekBar) findViewById(R.id.greenValue);
        greenValue.setOnSeekBarChangeListener(this);

        SeekBar blueValue = (SeekBar) findViewById(R.id.blueValue);
        blueValue.setOnSeekBarChangeListener(this);

        /** External Citation
         * Date: September 24, 2019
         * Problem: I needed to learn how to use RadioButtons.
         * Resource: https://developer.android.com/guide/topics/ui/controls/radiobutton.html#java
         * Solution: I used a RadioGroup.
         */

        RadioGroup features = (RadioGroup) findViewById(R.id.features);
        features.setOnCheckedChangeListener(this);
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
        hairstyle.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                hairArray);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairstyle.setAdapter(aa);

        Button randomFace = (Button) findViewById(R.id.randomFace);
        randomFace.setOnClickListener(this);
    }
    //Interface methods are below

    /** External Citation
     * Date: September 24, 2019
     * Problem: I needed to implement SeekBar.
     * Resource:
     * https://developer.android.com/reference/android/widget/SeekBar.OnSeekBarChangeListener.html
     * Solution: I implemented the methods given.
     */

    /** External Citation
     * Date: September 28, 2019
     * Problem: I needed to know how to determine which SeekBar is being changed.
     * Resource: https://stackoverflow.com/questions/8719632/multiple-seekbars-listener
     * Solution: I used the switch case idea.
     */

    /** External Citation
     * Date: September 29, 2019
     * Problem: Original method generally worked (checks SeekBar, then checks RadioButton,
     * then adjusts the appropriate feature's color), but it could be "cleaner".
     * Resource: Stephen Nguyen
     * Solution: Create "new" instance variables, set those to SeekBar values,
     * create a new color with them, and set it as the Color for the appropriate Paint.
     * Also, the SeekBar values need to retain changes between RadioButton switches.
     */

    /**
     * Modifies the RGB values of the features based on which RadioButton is selected.
     * Afterwards, a new Color is made, and the Paint is set to that Color.
     *
     * @param seekBar: the SeekBar being touched
     * @param progress: the value of the progress bar
     * @param fromUser
     */
    public void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser) {
        // Determines which SeekBar is being touched
        switch(seekBar.getId()) {
            // Modifies red values
            case R.id.redValue:
                finalR = progress;
                // Keeps values updated between switching RadioButtons
                if(checked == 1) {
                    face.hairRed = progress;
                }
                else if(checked == 2) {
                    face.eyeRed = progress;
                }
                else if(checked == 3) {
                    face.skinRed = progress;
                }
                break;
            // Modifies green values
            case R.id.greenValue:
                finalG = progress;
                // Keeps values updated between switching RadioButtons
                if(checked == 1) {
                    face.hairGreen = progress;
                }
                else if(checked == 2) {
                    face.eyeGreen = progress;
                }
                else if(checked == 3) {
                    face.skinGreen = progress;
                }
                break;
            // Modifies blue values
            case R.id.blueValue:
                finalB = progress;
                // Keeps values updated between switching RadioButtons
                if(checked == 1) {
                    face.hairBlue = progress;
                }
                else if(checked == 2) {
                    face.eyeBlue = progress;
                }
                else if(checked == 3) {
                    face.skinBlue = progress;
                }
                break;
        }
        int newColor = Color.argb(255, finalR, finalG, finalB);
        if(checked == 1) {
            face.hairPaint.setColor(newColor);
        }
        else if(checked == 2) {
            face.eyePaint.setColor(newColor);
        }
        else if(checked == 3) {
            face.skinPaint.setColor(newColor);
        }
        face.invalidate();
    }

    public void onStartTrackingTouch (SeekBar seekBar) {

    }

    public void onStopTrackingTouch (SeekBar seekBar) {

    }

    /** External Citation
     * Date: September 24, 2019
     * Problem: I needed to implement Spinner.
     * Resource:
     * https://developer.android.com/reference/android/widget/AdapterView.OnItemSelectedListener.html
     * Solution: I implemented the methods given.
     */

    /** External Citation
     * Date: September 28, 2019
     * Problem: I needed to know how to determine which Spinner selection is currently selected.
     * Resource: https://developer.android.com/guide/topics/ui/controls/spinner
     * Solution: I used parent.getItemAtPosition(position).
     */

    /**
     * Sets hairStyle parameter accordingly.
     * @param parent: the Spinner
     * @param view
     * @param position: where each item is stored
     * @param id
     */
    public void onItemSelected (AdapterView< ? > parent, View view, int position, long id) {
        if(parent.getItemAtPosition(position).equals("\"Normal\"")) {
            // Normal
            face.hairStyle = 0;
        }
        else if(parent.getItemAtPosition(position).equals("Short")) {
            // Short
            face.hairStyle = 1;
        }
        else if(parent.getItemAtPosition(position).equals("Mohawk")){
            // Mohawk
            face.hairStyle = 2;
        }
        face.invalidate();
    }

    public void onNothingSelected (AdapterView < ? > parent) {

    }

    /** External Citation
     * Date: September 27, 2019
     * Problem: I needed to implement OnClickListener.
     * Resource: https://developer.android.com/reference/android/view/View.OnClickListener
     * Solution: I implemented the methods given.
     */

    /** External Citation
     * Date: September 30, 2019
     * Problem: I need to use onClick for updating SeekBar values.
     * Source: Stephen Nguyen
     * Solution: Use a similar process in onCheckedChanged().
     */
    /**
     * Recalls randomize to set new colors.
     * Also updates SeekBars and Spinner accordingly.
     *
     * @param v: the View being drawn on
     */
    public void onClick (View v) {
        SeekBar r = (SeekBar) findViewById(R.id.redValue);
        SeekBar g = (SeekBar) findViewById(R.id.greenValue);
        SeekBar b = (SeekBar) findViewById(R.id.blueValue);
        Spinner spin = (Spinner) findViewById(R.id.hairstyle);
        face.randomize();

        if(checked == 1) {
            r.setProgress(face.hairRed);
            g.setProgress(face.hairGreen);
            b.setProgress(face.hairBlue);
        }
        else if(checked == 2) {

            r.setProgress(face.eyeRed);
            g.setProgress(face.eyeGreen);
            b.setProgress(face.eyeBlue);
        }
        else if(checked == 3) {

            r.setProgress(face.skinRed);
            g.setProgress(face.skinGreen);
            b.setProgress(face.skinBlue);
        }

        // Spinner
        if(face.hairStyle == 0) {
            spin.setSelection(0);
        }
        else if(face.hairStyle == 1) {
            spin.setSelection(1);
        }
        else if(face.hairStyle == 2) {
            spin.setSelection(2);
        }
        face.invalidate();
    }

    /** External Citation
     * Date: September 27, 2019
     * Problem: I needed to implement the RadioGroup interface.
     * Resource:
     * https://developer.android.com/reference/android/widget/RadioGroup.OnCheckedChangeListener.html
     * Solution: I implemented the method given.
     */

    /** External Citation
     * Date: September 29, 2019
     * Problem: I needed to determine which RadioButton is selected.
     * Resource:
     * https://stackoverflow.com/questions/8323778/how-to-set-onclicklistener-on-a-radiobutton-in-android
     * Solution: I used one example given.
     */

    /**
     * Changes the value of "checked" based on which RadioButton is selected.
     *
     * @param group: the RadioGroup with the RadioButtons
     * @param checkedId: the ID of the RadioButton
     */
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        SeekBar r = (SeekBar) findViewById(R.id.redValue);
        SeekBar g = (SeekBar) findViewById(R.id.greenValue);
        SeekBar b = (SeekBar) findViewById(R.id.blueValue);
        RadioButton rb = (RadioButton) group.findViewById(checkedId);
        if(rb.getText().equals("Hair")) {
            checked = 1;
            r.setProgress(face.hairRed);
            g.setProgress(face.hairGreen);
            b.setProgress(face.hairBlue);
        }
        else if(rb.getText().equals("Eyes")) {
            checked = 2;
            r.setProgress(face.eyeRed);
            g.setProgress(face.eyeGreen);
            b.setProgress(face.eyeBlue);
        }
        else if(rb.getText().equals("Skin")) {
            checked = 3;
            r.setProgress(face.skinRed);
            g.setProgress(face.skinGreen);
            b.setProgress(face.skinBlue);
        }
        face.invalidate();
    }
}
