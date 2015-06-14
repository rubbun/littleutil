package com.littleutil.screens;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.adapter.SubServiceAdapter;
import com.littleutil.bean.SubServiceBean;

public class InnerServices extends BaseActivity{
	
	private Intent mIntent;
	private int id;
	private ImageView iv_search,iv_whatsapp,ivBack;
	private GridView gridView1;
	private SubServiceAdapter adapter;
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
		
		gridView1 = (GridView)findViewById(R.id.gridView1);
		setInnerServiceList(id);
		
		gridView1.setOnItemClickListener(new OnItemClickListener() {

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
		gridView1.setAdapter(adapter);
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
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
}
