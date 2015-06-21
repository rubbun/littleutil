package com.littleutil.screens;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.adapter.BookingListAdapter;
import com.littleutil.bean.BookingReqBean;
import com.littleutil.constant.Constants;
import com.littleutil.network.HttpClient;
import com.littleutil.network.HttpClientGet;

public class TrackStatus extends BaseActivity{
	
	private ListView ll_booking_list;
	private LinearLayout ll_header;
	private LayoutInflater inflater;
	private TextView textView1;
	private ImageView ivBack;
	public String name = "";
	
	private ArrayList<BookingReqBean> list = new ArrayList<BookingReqBean>();
	private BookingListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.track_status);
		
		name = getIntent().getExtras().getString("name");
		
		ll_header = (LinearLayout)findViewById(R.id.ll_header);
		ll_booking_list = (ListView)findViewById(R.id.ll_booking_list);
		textView1 = (TextView)findViewById(R.id.textView1);
		textView1.setText(name);
	
		View v = getLayoutInflater().inflate(R.layout.bookin_req_header, null);
		ll_header.addView(v);
		
		ivBack = (ImageView)findViewById(R.id.ivBack);
		ivBack.setOnClickListener(this);
		
		new getAllBookingInfo().execute();
	}
	
	public class getAllBookingInfo extends AsyncTask<Void, Void, Void>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			doShowLoading();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			JSONObject ob = new JSONObject();
			try {
				ob.put("phone_number", app.getUserinfo().getMobile_no());
				String response = HttpClient.SendHttpPost(Constants.ALL_BOOKINGINFO_REQUEST, ob.toString());
				if (response != null) {
					JSONObject object = new JSONObject(response);
					JSONArray arr = object.getJSONArray("track");
					for(int i = 0; i < arr.length(); i++){
						JSONObject obj = arr.getJSONObject(i);
						list.add(new BookingReqBean(obj.getString("ID"),
								obj.getString("REQUEST_DATE"),
								obj.getString("APPOINTMENT_TIME"),
								obj.getString("STATUS"),
								obj.getString("AGENT_NAME"),
								obj.getString("Agent_ID"), obj.getString("CUS_DESC")));
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			doRemoveLoading();
			adapter = new BookingListAdapter(TrackStatus.this, R.layout.bookin_req_row, list);
			ll_booking_list.setAdapter(adapter);
			
		}
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.ivBack:
			finish();
			break;
		}
	}
}
