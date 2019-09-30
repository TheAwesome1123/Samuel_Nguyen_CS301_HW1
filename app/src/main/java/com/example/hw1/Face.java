//@author Samuel Nguyen
// Note: This only works on a portrait view.

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

public class Face extends SurfaceView {
    // For setting up colors
    int skinColor;
    int eyeColor;
    int hairColor;
    int hairStyle;
    Paint skinPaint = new Paint();
    Paint eyePaint = new Paint();
    Paint hairPaint = new Paint();

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
} // End of Face class