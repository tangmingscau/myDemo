package com.example.tonydemo.customView.switchViewDemo;

import android.view.View;

/**
 * An interface which is serverd as a callback event listeners for
 * FanragFloatingLabelView.java
 * 
 * @author Hardik Trivedi
 * 
 */
public interface OnFloatingLableFocusChangeListener {

	public void onFocusChange(View v, boolean hasFocus);
}