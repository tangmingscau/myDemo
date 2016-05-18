package com.example.tonydemo.customView.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by tony on 16-4-19.
 */
public class SignalDialog extends Dialog{
    public SignalDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SignalDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
