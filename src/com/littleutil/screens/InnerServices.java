package com.littleutil.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.littleutil.BaseActivity;
import com.littleutil.R;

public class InnerServices extends BaseActivity{
	
	private Intent mIntent;
	private ImageView iv_search,iv_whatsapp,ivBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.innser_services);
		
		iv_search = (ImageView)findViewById(R.id.iv_search);
		iv_whatsapp = (ImageView)findViewById(R.id.iv_whatsapp);
		ivBack = (ImageView)findViewById(R.id.ivBack);
		
		iv_search.setOnClickListener(this);
		iv_whatsapp.setOnClickListener(this);
		ivBack.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.iv_whatsapp:
			mIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");;
			startActivity(mIntent);
			break;
			
		case R.id.iv_search:
			break;
			
		case R.id.ivBack:
			finish();
			break;
		}
	}
}
