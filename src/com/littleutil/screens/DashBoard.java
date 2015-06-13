package com.littleutil.screens;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.adapter.ExpandableListAdapter;
import com.littleutil.bean.ServiceBean;
import com.littleutil.bean.SubServiceBean;

public class DashBoard extends BaseActivity implements OnClickListener{

	private Intent mIntent;
	private int lastExpandedPosition = -1;
	private ExpandableListView expandableListView1;
	private ExpandableListAdapter adapter;
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
		
		expandableListView1 = (ExpandableListView)findViewById(R.id.expandableListView1);
		expandableListView1.setGroupIndicator(null);
		expandableListView1.setOnGroupExpandListener(new OnGroupExpandListener() {

		    @Override
		    public void onGroupExpand(int groupPosition) {
		            if (lastExpandedPosition != -1
		                    && groupPosition != lastExpandedPosition) {
		            	expandableListView1.collapseGroup(lastExpandedPosition);
		            }
		            lastExpandedPosition = groupPosition;
		    }
		});
		
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
	
		System.out.println("!!size1:"+arr[0]);
		System.out.println("!!size2:"+arr[1]);
		System.out.println("!!size3:"+arr[2]);
		createList();
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
			mIntent = new Intent(DashBoard.this,InnerServices.class);
			mIntent.putExtra("id", 1);
			startActivity(mIntent);
			break;
		
		case R.id.ll_general_service:
			mIntent = new Intent(DashBoard.this,InnerServices.class);
			mIntent.putExtra("id", 2);
			startActivity(mIntent);
			break;
			
		case R.id.ll_billing_service:
			mIntent = new Intent(DashBoard.this,InnerServices.class);
			mIntent.putExtra("id", 3);
			startActivity(mIntent);
			break;
		}
	}
	
	private void createList() {
		ArrayList<SubServiceBean> list1 = new ArrayList<SubServiceBean>();
		list1.add(new SubServiceBean("1", "Track Your status", ""));
		list1.add(new SubServiceBean("2", "Change your password", ""));
		serviceList.add(new ServiceBean("Settings", "0", list1));
		for(int i= 0 ; i <arr.length; i++){
			ArrayList<SubServiceBean> list = new ArrayList<SubServiceBean>();
			JSONArray jarr;
			try {
				jarr = new JSONArray(arr[i]);
				for(int j = 0; j < jarr.length(); j++){
					JSONObject object = jarr.getJSONObject(j);
					list.add(new SubServiceBean(object.getString("id"),
							object.getString("name"), 
							object.getString("path")));
				}
				if(i == 0){
					serviceList.add(new ServiceBean("Hotel Service", "1", list));
				}else if(i == 1){
					serviceList.add(new ServiceBean("General Service", "2", list));
				}else if(i == 2){
					serviceList.add(new ServiceBean("Billing Service", "3", list));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		adapter = new ExpandableListAdapter(DashBoard.this, serviceList);
		expandableListView1.setAdapter(adapter);
	}
}
