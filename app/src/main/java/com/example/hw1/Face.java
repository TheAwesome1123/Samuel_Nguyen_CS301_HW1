//@author Samuel Nguyen
/**
 * Notes:
 * The package name is different; I renamed the project.
 *
 * This project only works when it is in portrait mode.
 *
 * The SeekBar progress values are not reflected accurately when a RadioButton is selected.
 * Additionally, to get a desired color, it may be necessary to adjust some or all SeekBars.
 *
 * The Spinner value is not reflected accurately at startup and when "Randomize Face" is clicked.
 */

package com.example.hw1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import java.util.Random;

public class Face extends SurfaceView implements SeekBar.OnSeekBarChangeListener,
        AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener,
        View.OnClickListener{
    // For setting up colors
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;
    private Paint skinPaint = new Paint();
    private Paint eyePaint = new Paint();
    private Paint hairPaint = new Paint();

    // RGB values
    int skinRed;
    int skinGreen;
    int skinBlue;

    int eyeRed;
    int eyeGreen;
    int eyeBlue;

    int hairRed;
    int hairGreen;
    int hairBlue;

    int newRed;
    int newGreen;
    int newBlue;

    // 1 for Hair button checked, 2 for Eyes, 3 for Skin
    int checked;

    /** External Citation
     * Date: September 28, 2019
     * Problem: I have a black background when I want a white one.
     * Resource: Cake Project from CS371
     * Solution: I used setBackgroundColor.
     */
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setBackgroundColor(Color.WHITE);
        randomize();
    }

    /** External Citation
     * Date: September 28, 2019
     * Problem: I didn't know how to get colors using one int.
     * Resource: https://stackoverflow.com/questions/5280367/android-generate-random-color-on-click
     * Solution: I implemented the general idea shown.
     */

    /** External Citation
     * Date: September 28, 2019
     * Problem: Face always drew in black (except where one eye paint is white).
     * Resource: Stephen Nguyen
     * Solution: Initialize Paint instance variables and use setColor in randomize().
     */

    /**
     * Sets values randomly.
     */
    public void randomize() {
        Random generator = new Random();

        skinRed = generator.nextInt(256);
        skinGreen = generator.nextInt(256);
        skinBlue = generator.nextInt(256);
        this.skinColor = Color.argb(255, skinRed, skinGreen, skinBlue);
        this.skinPaint.setColor(skinColor);

        eyeRed = generator.nextInt(256);
        eyeGreen = generator.nextInt(256);
        eyeBlue = generator.nextInt(256);
        this.eyeColor = Color.argb(255, eyeRed, eyeGreen, eyeBlue);
        this.eyePaint.setColor(eyeColor);

        hairRed = generator.nextInt(256);
        hairGreen = generator.nextInt(256);
        hairBlue = generator.nextInt(256);
        this.hairColor = Color.argb(255, hairRed, hairGreen, hairBlue);
        this.hairPaint.setColor(hairColor);

        int style = generator.nextInt(3);
        this.hairStyle = style;
    }

    /**
     * Draws a complete face.
     *
     * @param canvas: what is being drawn on
     */
    public void onDraw(Canvas canvas) {
        /**Draws face, eyes, and hair
         * Since one hairstyle is a large circle, the face needs to be drawn over it.
         * This only shows the top.
         */
        drawHair(canvas, this.hairPaint);
        canvas.drawCircle(600, 600, 300, this.skinPaint);
        drawEyes(canvas, this.eyePaint);
    }

    /**
     * Draws the eyes.
     *
     * @param canvas: what is being drawn on
     * @param paint:  what color to use
     */
    public void drawEyes(Canvas canvas, Paint paint) {
        // Draws white parts
        Paint white = new Paint();
        white.setColor(Color.WHITE);
        canvas.drawCircle(450, 550, 80, white);
        canvas.drawCircle(750, 550, 80, white);

        // Draws colored parts
        canvas.drawCircle(450, 550, 30, paint);
        canvas.drawCircle(750, 550, 30, paint);
    }

    /**
     * Draws the hair.
     *
     * @param canvas: what is being drawn on
     * @param paint: what paint to use
     */
    public void drawHair(Canvas canvas, Paint paint) {
        // "Normal"
        if(this.hairStyle == 0) {
            canvas.drawCircle(600, 550, 300, paint);
        }
        // Short
        else if(this.hairStyle == 1) {
            canvas.drawRect(450, 250, 750, 300, paint);

        }
        // Mohawk
        else if(this.hairStyle == 2){
            canvas.drawRect(550, 120, 650, 300, paint);
        }
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
                newRed = progress;
                break;
            // Modifies green values
            case R.id.greenValue:
                newGreen = progress;
                break;
            // Modifies blue values
            case R.id.blueValue:
                newBlue = progress;
                break;
        }
        int newColor = Color.argb(255, newRed, newGreen, newBlue);
        if(checked == 1) {
            this.hairPaint.setColor(newColor);
        }
        else if(checked == 2) {
            this.eyePaint.setColor(newColor);
        }
        else if(checked == 3) {
            this.skinPaint.setColor(newColor);
        }
        invalidate();
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
    public void onItemSelected (AdapterView < ? > parent, View view, int position, long id) {
        if(parent.getItemAtPosition(position).equals("\"Normal\"")) {
            // Normal
            this.hairStyle = 0;
        }
        else if(parent.getItemAtPosition(position).equals("Short")) {
            // Short
            this.hairStyle = 1;
        }
        else if(parent.getItemAtPosition(position).equals("Mohawk")){
            // Mohawk
            this.hairStyle = 2;
        }
        invalidate();
    }

    public void onNothingSelected (AdapterView < ? > parent) {

    }

    /** External Citation
     * Date: September 27, 2019
     * Problem: I needed to implement OnClickListener.
     * Resource: https://developer.android.com/reference/android/view/View.OnClickListener
     * Solution: I implemented the methods given.
     */

    /**
     * Recalls randomize to set new colors.
     *
     * @param v: the View being drawn on
     */
    public void onClick (View v) {
        randomize();
        invalidate();
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
        RadioButton rb = (RadioButton) group.findViewById(checkedId);
        if(rb.getText().equals("Hair")) {
            checked = 1;
        }
        else if(rb.getText().equals("Eyes")) {
            checked = 2;
        }
        else if(rb.getText().equals("Skin")) {
            checked = 3;
        }
        invalidate();
    }
    // End of interface methods
} // End of Face class