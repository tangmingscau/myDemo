package com.example.tonydemo.customView.switchViewDemo;

import android.app.Activity;
import android.os.Bundle;

import com.example.tonydemo.R;

public class LibActivity extends Activity {
	FloatingLabelView label1, label2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.float_view);

		label1 = (FloatingLabelView) findViewById(R.id.label1);
		// label2 = (FloatingLabelView) findViewById(R.id.label2);

	}

}
