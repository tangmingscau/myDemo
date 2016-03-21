package com.example.tonydemo.windowmanager.type_point;

import android.content.Context;
import android.view.View;

/**
 * Created by xinlyun on 16-3-8.
 */
public class MDialog {
    Context context;
    FloatWindowsView floatWindowsView;
    public MDialog(Context context){
        this.context = context;
        this.floatWindowsView = new FloatWindowsView(context);
    }
    public View setContentView(int layoutId){
        floatWindowsView.createParames();
        return floatWindowsView.createFloatView(layoutId);
    }
    public void setContentView(View view){
        floatWindowsView.createParames();
        floatWindowsView.createFloatView(view);
    }
    public void show(){
        floatWindowsView.show();
    }
    public void dismiss(){
        floatWindowsView.remove();
    }


}
