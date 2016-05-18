package com.example.tonydemo.customView.keyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tonydemo.R;


/**
 * Created by tony on 16-5-13.
 */
public class KeyBoardView extends LinearLayout implements View.OnClickListener {
    private final static int width = 360;
    private final static int height = 180;
    public final static String KEY0 = "0", KEY1 = "1", KEY2 = "2", KEY3 = "3",
            KEY4 = "4", KEY5 = "5", KEY6 = "6", KEY7 = "7", KEY8 = "8", KEY9 = "9", KEYNULL = "", BACK = "BACK";
    public int textColor = 0;
    public float textSize = 0;

    TextView text0, text1, text2, text3, text4, text5, text6, text7, text8, text9, textnull;
    ImageView back = null;
    KeyboardClick listener = null;

    public KeyBoardView(Context context) {
        super(context);
        init();
    }

    public KeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textColor = android.support.v4.content.ContextCompat.getColor(getContext(), R.color.myBlackText);
        textSize = getResources().getDimensionPixelSize(R.dimen.keyboard_textsize);

        LinearLayout line1 = new LinearLayout(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        line1.setOrientation(HORIZONTAL);
        line1.setLayoutParams(layoutParams);
        text1 = new TextView(getContext());
        text1.setWidth(width);
        text1.setHeight(height);
        text1.setText(KEY1);
        text1.setGravity(Gravity.CENTER);
        text1.setTextColor(textColor);
        text1.setTextSize(textSize);
        text2 = new TextView(getContext());
        text2.setWidth(width);
        text2.setHeight(height);
        text2.setText(KEY2);
        text2.setGravity(Gravity.CENTER);
        text2.setTextColor(textColor);
        text2.setTextSize(textSize);
        text3 = new TextView(getContext());
        text3.setWidth(width);
        text3.setHeight(height);
        text3.setText(KEY3);
        text3.setGravity(Gravity.CENTER);
        text3.setTextColor(textColor);
        text3.setTextSize(textSize);
        line1.addView(text1);
        line1.addView(text2);
        line1.addView(text3);

        LinearLayout line2 = new LinearLayout(getContext());
        line2.setLayoutParams(layoutParams);
        text4 = new TextView(getContext());
        text4.setWidth(width);
        text4.setHeight(height);
        text4.setText(KEY4);
        text4.setGravity(Gravity.CENTER);
        text4.setTextColor(textColor);
        text4.setTextSize(textSize);
        text5 = new TextView(getContext());
        text5.setWidth(width);
        text5.setHeight(height);
        text5.setText(KEY5);
        text5.setGravity(Gravity.CENTER);
        text5.setTextColor(textColor);
        text5.setTextSize(textSize);
        text6 = new TextView(getContext());
        text6.setWidth(width);
        text6.setHeight(height);
        text6.setText(KEY6);
        text6.setGravity(Gravity.CENTER);
        text6.setTextColor(textColor);
        text6.setTextSize(textSize);
        line2.addView(text4);
        line2.addView(text5);
        line2.addView(text6);

        LinearLayout line3 = new LinearLayout(getContext());
        line3.setLayoutParams(layoutParams);
        text7 = new TextView(getContext());
        text7.setWidth(width);
        text7.setHeight(height);
        text7.setText(KEY7);
        text7.setGravity(Gravity.CENTER);
        text7.setTextColor(textColor);
        text7.setTextSize(textSize);
        text8 = new TextView(getContext());
        text8.setWidth(width);
        text8.setHeight(height);
        text8.setText(KEY8);
        text8.setGravity(Gravity.CENTER);
        text8.setTextColor(textColor);
        text8.setTextSize(textSize);
        text9 = new TextView(getContext());
        text9.setWidth(width);
        text9.setHeight(height);
        text9.setText(KEY9);
        text9.setGravity(Gravity.CENTER);
        text9.setTextColor(textColor);
        text9.setTextSize(textSize);
        line3.addView(text7);
        line3.addView(text8);
        line3.addView(text9);

        LinearLayout line4 = new LinearLayout(getContext());
        line4.setLayoutParams(layoutParams);
        textnull = new TextView(getContext());
        textnull.setWidth(width);
        textnull.setHeight(height);
        textnull.setText(KEYNULL);
        textnull.setGravity(Gravity.CENTER);
        textnull.setTextColor(textColor);
        textnull.setTextSize(textSize);
        text0 = new TextView(getContext());
        text0.setWidth(width);
        text0.setHeight(height);
        text0.setText(KEY0);
        text0.setGravity(Gravity.CENTER);
        text0.setTextColor(textColor);
        text0.setTextSize(textSize);
        back = new ImageView(getContext());
        line4.addView(textnull);
        line4.addView(text0);
        line4.addView(back);

        this.addView(line1);
        this.addView(line2);
        this.addView(line3);
        this.addView(line4);

        text0.setTag(KEY0);
        text0.setOnClickListener(this);
        text1.setTag(KEY1);
        text1.setOnClickListener(this);
        text2.setTag(KEY2);
        text2.setOnClickListener(this);
        text3.setTag(KEY3);
        text3.setOnClickListener(this);
        text4.setTag(KEY4);
        text4.setOnClickListener(this);
        text5.setTag(KEY5);
        text5.setOnClickListener(this);
        text6.setTag(KEY6);
        text6.setOnClickListener(this);
        text7.setText(KEY7);
        text7.setOnClickListener(this);
        text8.setTag(KEY8);
        text8.setOnClickListener(this);
        text9.setTag(KEY9);
        text9.setOnClickListener(this);
        back.setTag(BACK);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        listener.clickKey(tag);
    }

    public void setKeyboardClickListener(KeyboardClick keyboardClick) {
        listener = keyboardClick;
    }

    public interface KeyboardClick {
        void clickKey(String tag);
    }
}
