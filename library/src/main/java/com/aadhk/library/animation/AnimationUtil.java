package com.aadhk.library.animation;

import android.animation.Animator;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by jack on 29/11/2016.
 */

public class AnimationUtil {

    public static void start(View view){
        ScaleInAnimation animation = new ScaleInAnimation();
        Interpolator mInterpolator = new LinearInterpolator();
        int mDuration = 300;
        for (Animator anim : animation.getAnimators(view)) {
            anim.setDuration(mDuration).start();
            anim.setInterpolator(mInterpolator);
        }
    }
}
