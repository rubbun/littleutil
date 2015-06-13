package com.littleutil.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.littleutil.BaseActivity;
import com.littleutil.R;

public class DashBoard extends BaseActivity implements OnClickListener{

	private Intent mIntent;
	public SlidingMenu slidingMenu;
	private ImageView iv_menu,iv_search,iv_whatsapp,iv_call;
	private LinearLayout ll_home_service,ll_general_service,ll_billing_service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		
		slidingMenu = new SlidingMenu(DashBoard.this);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.attachToActivity(DashBoard.this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.dashboard_slider);
		
		ll_home_service = (LinearLayout)findViewById(R.id.ll_home_service);
		ll_general_service = (LinearLayout)findViewById(R.id.ll_general_service);
		ll_billing_service = (LinearLayout)findViewById(R.id.ll_billing_service);
		
		iv_menu = (ImageView)findViewById(R.id.iv_menu);
		iv_search = (ImageView)findViewById(R.id.iv_search);
		iv_whatsapp = (ImageView)findViewById(R.id.iv_whatsapp);
		iv_call = (ImageView)findViewById(R.id.iv_call);
		
		iv_menu.setOnClickListener(this);
		iv_search.setOnClickListener(this);
		iv_whatsapp.setOnClickListener(this);
		iv_call.setOnClickListener(this);
		
		ll_billing_service = (LinearLayout)findViewById(R.id.ll_billing_service);
		ll_general_service = (LinearLayout)findViewById(R.id.ll_general_service);
		ll_home_service = (LinearLayout)findViewById(R.id.ll_home_service);
		
		ll_billing_service.setOnClickListener(this);
		ll_general_service.setOnClickListener(this);
		ll_home_service.setOnClickListener(this);
	
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.iv_menu:
			slidingMenu.toggle();
			break;
		
		case R.id.iv_whatsapp:
			mIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");;
			startActivity(mIntent);
			break;
			
		case R.id.iv_search:
			slidingMenu.toggle();
			break;
		
		case R.id.iv_call:
			mIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9674448739" ));
			startActivity(mIntent);
			break;
			
		case R.id.ll_home_service:
			break;
		
		case R.id.ll_general_service:
			break;
			
		case R.id.ll_billing_service:
			break;
		}
	}
}
