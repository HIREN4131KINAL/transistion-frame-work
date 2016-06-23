package com.hometech.transitionframworkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.transition.TransitionManager;

public class TransitionsActivity extends AppCompatActivity {

    //scenes to transition
    private Scene scene1, scene2;
    //transition to move between scenes
    private TransitionSet transition;
    //flag to swap between scenes
    private boolean start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        //get the layout ID
        RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.base);

        //first scene
        ViewGroup startViews = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.start_layout, baseLayout, false);

        //second scene
        ViewGroup endViews = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.end_layout, baseLayout, false);

        /*First we define the base scene, which is the ID we gave the containing layout in both scene layout files. Next we define the two scenes we are transitioning between, specifying their layout file names and the containing base scene. This will tell Android we want to transition the views within the two scenes, treating any view with the same ID in both scenes as the same object, so that it animates the change from one scene to the other.
*/

        //create the two scenes
        scene1 = new Scene(baseLayout, startViews);
        scene2 = new Scene(baseLayout, endViews);

        //create transition, set properties
        transition = new TransitionSet();
        transition.setDuration(2000);
        transition.addTransition(new ChangeBounds());
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

        //initialize flag
        start = true;

    }


    public void changeScene(View v) {

        //check flag
        if (start) {
            TransitionManager.go(scene2, transition);
            start = false;
        } else {
            TransitionManager.go(scene1, transition);
            start = true;
        }
    }


}
