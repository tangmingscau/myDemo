package com.example.tonydemo.customView.floatButton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tonydemo.R;

public class FloatActivity extends AppCompatActivity
{  
  
    @Override  
    public void onCreate(Bundle savedInstanceState)
    {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.float_main);
        //获取启动按钮  
        Button start = (Button)findViewById(R.id.start);
        //获取移除按钮  
        Button remove = (Button)findViewById(R.id.stop);
        //绑定监听  
        start.setOnClickListener(new View.OnClickListener()
        {  
              
            @Override  
            public void onClick(View v)   
            {  
                // TODO Auto-generated method stub  
                Intent intent = new Intent(FloatActivity.this, FloatService.class);
                //启动FxService  
                startService(intent);  
                finish();  
            }  
        });  
          
        remove.setOnClickListener(new View.OnClickListener()
        {  
              
            @Override  
            public void onClick(View v)   
            {  
                //uninstallApp("com.phicomm.hu");  
                Intent intent = new Intent(FloatActivity.this, FloatService.class);
                //终止FxService  
                stopService(intent);  
            }  
        });  
          
    }  
}  