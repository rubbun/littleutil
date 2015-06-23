package com.littleutil.screens;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.adapter.ExpandableListAdapter;
import com.littleutil.adapter.MemberAdapter;
import com.littleutil.bean.ServiceBean;
import com.littleutil.bean.SubServiceBean;

public class DashBoard extends BaseActivity implements OnClickListener{

	private Intent mIntent;
	private Dialog dialog;
	private MemberAdapter memberadapter;
	private int lastExpandedPosition = -1;
	private ExpandableListView expandableListView1;
	private ExpandableListAdapter adapter;
	public SlidingMenu slidingMenu;
	private AutoCompleteTextView ll_dialog_search;
	private ImageView iv_menu,iv_search,iv_whatsapp,iv_call;
	private LinearLayout ll_home_service,ll_general_service,ll_billing_service;
	private ArrayList<SubServiceBean> listItem = new ArrayList<SubServiceBean>();
	
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
		
		expandableListView1.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView arg0, View arg1, int grouppos, int childpos, long arg4) {
				
				String grp_name = serviceList.get(grouppos).service_name;
				if(!grp_name.equalsIgnoreCase("Settings")){
					String id = serviceList.get(grouppos).getList().get(childpos).id;
					String name = serviceList.get(grouppos).getList().get(childpos).name;
					mIntent = new Intent(DashBoard.this,RequestSubmitActivity.class);
					mIntent.putExtra("name", name);
					mIntent.putExtra("id", id);
					startActivity(mIntent);
					slidingMenu.toggle();
				}else if(serviceList.get(grouppos).getList().get(childpos).name.equalsIgnoreCase("Track Your status")){
					if(app.getUserinfo().getMobile_no().length() >0){
						slidingMenu.toggle();
						String child_name = serviceList.get(grouppos).getList().get(childpos).name;
						mIntent = new Intent(DashBoard.this,TrackStatus.class);
						mIntent.putExtra("name", child_name);
						startActivity(mIntent);
					}else{
						Toast.makeText(getApplicationContext(), "You don't have make any request till now..", Toast.LENGTH_LONG).show();
					}
					
				}
				return false;
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
	
		createList();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.iv_menu:
			slidingMenu.toggle();
			break;
		
		case R.id.iv_whatsapp:
			/*mIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");;
			startActivity(mIntent);*/
			//openWhatsApp("9674216123@s.whatsapp.net");
			mIntent = new Intent();
			mIntent.setAction(Intent.ACTION_SEND);
			mIntent.putExtra(Intent.EXTRA_TEXT, "Hello, i want to Book a service.");
			mIntent.setType("text/plain");
			mIntent.setPackage("com.whatsapp");
			startActivity(mIntent);
			break;
			
		case R.id.iv_search:
			openSearchpage();
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
					serviceList.add(new ServiceBean("Home Service", "1", list));
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
	
	public void openSearchpage() {
		dialog = new Dialog(DashBoard.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialog_member);
		dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

		final ListView lv_dialog_members = (ListView) dialog.findViewById(R.id.lv_dialog_members);
		ll_dialog_search = (AutoCompleteTextView) dialog.findViewById(R.id.ll_dialog_search);

		ll_dialog_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

				// search(cs.toString());
				String searchString = ll_dialog_search.getText().toString();
				if (searchString.length() > 0) {
					int textLength = searchString.length();
					listItem.clear();
					for (int i = 1; i < serviceList.size(); i++) {
						for(int j = 0 ; j < serviceList.get(i).getList().size(); j ++){
							String retailerName = serviceList.get(i).getList().get(j).getName();
							if (textLength <= retailerName.length()) {
								if (retailerName.toLowerCase().contains(searchString.toLowerCase())) {
									listItem.add(serviceList.get(i).getList().get(j));								
								}
							}
						}
					}
					memberadapter = new MemberAdapter(DashBoard.this, R.layout.row_member, listItem);
					lv_dialog_members.setAdapter(memberadapter);
					lv_dialog_members.setFastScrollEnabled(false);
				} else {
					lv_dialog_members.setAdapter(null);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

			}
		});

		lv_dialog_members.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

					String id = listItem.get(arg2).id;
					String name = listItem.get(arg2).name;
					mIntent = new Intent(DashBoard.this,RequestSubmitActivity.class);
					mIntent.putExtra("name", name);
					mIntent.putExtra("id", id);
					startActivity(mIntent);
					dialog.cancel();
				}
		});
		dialog.show();
	}
	
	private void openWhatsApp(String id) {

		Cursor c = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
		        new String[] { ContactsContract.Contacts.Data._ID }, ContactsContract.Data.DATA1 + "=?",
		        new String[] { id }, null);
		c.moveToFirst();
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("content://com.android.contacts/data/" + c.getString(0)));
		startActivity(i);
		c.close();
		
		}
}
