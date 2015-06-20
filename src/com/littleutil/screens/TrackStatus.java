package com.littleutil.screens;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.constant.Constants;
import com.littleutil.network.HttpClientGet;

public class TrackStatus extends BaseActivity{
	
	private ListView ll_booking_list;
	private LinearLayout ll_header;
	private LayoutInflater inflater;
	private TextView textView1;
	public String name = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.track_status);
		
		name = getIntent().getExtras().getString("name");
		
		ll_header = (LinearLayout)findViewById(R.id.ll_header);
		ll_booking_list = (ListView)findViewById(R.id.ll_booking_list);
		textView1 = (TextView)findViewById(R.id.textView1);
		textView1.setText(name);
	
		View v = getLayoutInflater().inflate(R.layout.bookin_req_row, null);
		ll_header.addView(v);
	}
	
	public class getAllBookingInfo extends AsyncTask<Void, Void, Void>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			doShowLoading();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			String response = HttpClientGet.SendHttpGet(Constants.ALL_BOOKINGINFO_REQUEST);
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			doRemoveLoading();
		}
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.ll_header:
			
			break;
		}
	}
}
