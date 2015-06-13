package com.littleutil.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.littleutil.R;
import com.littleutil.BaseActivity;

public class SplashScreen extends BaseActivity{
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				mIntent = new Intent(SplashScreen.this,DashBoard.class);
				startActivity(mIntent);
			}
		}, 3000);
	}
}
