package com.littleutil.screens;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.adapter.MemberAdapter;
import com.littleutil.adapter.SubServiceAdapter;
import com.littleutil.bean.SubServiceBean;

public class InnerServices extends BaseActivity{
	
	private Intent mIntent;
	private Dialog dialog;
	private AutoCompleteTextView ll_dialog_search;
	private MemberAdapter memberadapter;
	private int id;
	private ImageView iv_search,iv_whatsapp,ivBack;
	private ListView ll_service_list;
	private SubServiceAdapter adapter;
	private ArrayList<SubServiceBean> listItem = new ArrayList<SubServiceBean>();
	private ArrayList<SubServiceBean> list = new ArrayList<SubServiceBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.innser_services);
		
		id = getIntent().getExtras().getInt("id");
		
		iv_search = (ImageView)findViewById(R.id.iv_search);
		iv_whatsapp = (ImageView)findViewById(R.id.iv_whatsapp);
		ivBack = (ImageView)findViewById(R.id.ivBack);
		
		iv_search.setOnClickListener(this);
		iv_whatsapp.setOnClickListener(this);
		ivBack.setOnClickListener(this);
		
		ll_service_list = (ListView)findViewById(R.id.ll_service_list);
		setInnerServiceList(id);
		
		ll_service_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				String id = list.get(pos).getId();
				String name = list.get(pos).getName();
				mIntent = new Intent(InnerServices.this,RequestSubmitActivity.class);
				mIntent.putExtra("name", name);
				mIntent.putExtra("id", id);
				startActivity(mIntent);
			}
		});
	}
	
	private void setInnerServiceList(int id2) {
		list = serviceList.get(id2).getList();
		adapter = new SubServiceAdapter(InnerServices.this, R.layout.sub_service_row, list);
		ll_service_list.setAdapter(adapter);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.iv_whatsapp:
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
			
		case R.id.ivBack:
			finish();
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
	
	public void openSearchpage() {
		dialog = new Dialog(InnerServices.this);
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
					memberadapter = new MemberAdapter(InnerServices.this, R.layout.row_member, listItem);
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
					mIntent = new Intent(InnerServices.this,RequestSubmitActivity.class);
					mIntent.putExtra("name", name);
					mIntent.putExtra("id", id);
					startActivity(mIntent);
					dialog.cancel();
				}
		});
		dialog.show();
	}
}
