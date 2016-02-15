package com.example.tonydemo.animation;

import android.animation.ValueAnimator;

/**
 * Created by tony on 16-1-13.
 */
public class myValueAnimation {
    public myValueAnimation(){
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 1f);
        animation.setDuration(1000);
        animation.start();
    }
}
