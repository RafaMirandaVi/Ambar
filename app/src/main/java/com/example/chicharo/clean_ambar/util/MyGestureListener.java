package com.example.chicharo.clean_ambar.util;

import android.app.Activity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by chicharo on 11/12/14.
 */
public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_MIN_DISTANCE = 10;
    private static final int SWIPE_MAX_OFF_PATH = 10;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    private Activity ourActivity;

    public MyGestureListener(Activity activity){
        this.ourActivity = activity;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        try {
        /*int actionMask = e1.getActionMasked();
        Log.d("OnFling",String.valueOf(actionMask));*/

            float pressure = e1.getPressure();
            Log.d("onFling", " Pressure: " + String.valueOf(pressure));
            float dX = e2.getX() - e1.getX();
            Log.d("onFling", " X: " + String.valueOf(dX));

            float dY = e1.getY() - e2.getY();
            Log.d("onFling", " Y: " + String.valueOf(dY));

            float rawX = e1.getRawX();
            Log.d("onFling", " rawX : " + String.valueOf(rawX));
        /*if (Math.abs(dY)<SWIPE_MAX_OFF_PATH &

        Math.abs(velocityX)>=SWIPE_THRESHOLD_VELOCITY &

        Math.abs(dX)>=SWIPE_MIN_DISTANCE){

            if (dX>0) {

                Toast.makeText(ourActivity.getApplicationContext(), "Right Swipe", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(ourActivity.getApplicationContext(), "Left Swipe", Toast.LENGTH_SHORT).show();

            }

            return true;

        } else {

            if (Math.abs(dX) < SWIPE_MAX_OFF_PATH &

                    Math.abs(velocityY) >= SWIPE_THRESHOLD_VELOCITY &

                    Math.abs(dY) >= SWIPE_MIN_DISTANCE) {

                if (dY > 0) {

                    Toast.makeText(ourActivity.getApplicationContext(), "Up Swipe", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(ourActivity.getApplicationContext(), "Down Swipe", Toast.LENGTH_SHORT).show();
                }

                return true;
            }

        return false;
    }*/
        } catch (Exception e){
            Log.e("MyGestureListener error: ", e.toString());
        }
        return true;

    }
}
