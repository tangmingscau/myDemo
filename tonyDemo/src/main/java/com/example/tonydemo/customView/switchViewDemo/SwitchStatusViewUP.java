package com.example.tonydemo.customView.switchViewDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewAnimator;

import com.example.tonydemo.R;

/**
 * Created by tony on 16-1-20.
 */
public class SwitchStatusViewUP extends ViewAnimator {
    public SwitchStatusViewUP(Context context) {
        super(context);
        init();
    }

    public SwitchStatusViewUP(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        setInAnimation(getContext(), R.anim.push_up_in);
        setOutAnimation(getContext(),R.anim.push_up_out);
    }
}
