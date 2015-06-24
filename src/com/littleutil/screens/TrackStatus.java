package com.littleutil.screens;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.adapter.BookingListAdapter;
import com.littleutil.bean.BookingReqBean;
import com.littleutil.constant.Constants;
import com.littleutil.network.HttpClient;

public class TrackStatus extends BaseActivity {
	
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
		
		ll_booking_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				ShowDetaildialog(list.get(arg2));
			}
		});
		
		if(hasConnection()){
			new getAllBookingInfo().execute();
		}else{
			Toast.makeText(getApplicationContext(), "Please check your internet conncetion..", Toast.LENGTH_LONG).show();
		}
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
						list.add(new BookingReqBean(obj.getString("CUS_ID"),
								obj.getString("NAME"),
								obj.getString("EMAIL"),
								obj.getString("MOBILE"),
								obj.getString("ADDRESS1"),
								obj.getString("AREA1"),
								obj.getString("CITY1"),
								obj.getString("STATE1"),
								
								obj.getString("ADDRESS2"),
								obj.getString("AREA2"),
								obj.getString("CITY2"),
								obj.getString("STATE2"),
								obj.getString("PIN"),
								obj.getString("PASSWORD"),
								obj.getString("ID"),
								obj.getString("SERVICE_NO"),
								obj.getString("STATUS"),
								obj.getString("REQUEST_DATE"),
								obj.getString("CLOSE_DATE"),
								obj.getString("Agent_ID"),
								obj.getString("COST"),
								obj.getString("VENDOR_SERCHARGE"),
								
								obj.getString("CUS_FEEDBACK"),
								obj.getString("CUS_DESC"),
								obj.getString("LABOR_COST"),
								obj.getString("MATERIAL_COST"),
								obj.getString("COMMISION"),
								obj.getString("INTER_FEEDBACK"),
								obj.getString("APPOINTMENT_DATE"),
								obj.getString("APPOINTMENT_TIME"),
								obj.getString("AGENT_NAME"),
								obj.getString("Service_name")
								
								));
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
	
	public void ShowDetaildialog(BookingReqBean bookingReqBean){
		final Dialog dialog = new Dialog(TrackStatus.this);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_version);
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
	    lp.copyFrom(dialog.getWindow().getAttributes());
	    lp.width = LayoutParams.MATCH_PARENT;
	    lp.height = LayoutParams.MATCH_PARENT;
	    dialog.getWindow().setAttributes(lp);
		
		TextView tv_order_id = (TextView)dialog.findViewById(R.id.tv_order_id);
		tv_order_id.setText(bookingReqBean.getID());
		
		TextView tv_order_date = (TextView)dialog.findViewById(R.id.tv_order_date);
		tv_order_date.setText(bookingReqBean.getREQUEST_DATE());
		
		TextView tv_service_name = (TextView)dialog.findViewById(R.id.tv_service_name);
		tv_service_name.setText(bookingReqBean.getService_name());
				
		TextView tv_agent_name = (TextView)dialog.findViewById(R.id.tv_agent_name);
		tv_agent_name.setText(bookingReqBean.getAGENT_NAME());
		
		TextView tv_apponit_date = (TextView)dialog.findViewById(R.id.tv_apponit_date);
		tv_apponit_date.setText(bookingReqBean.getAPPOINTMENT_DATE());
		
		TextView tv_apponit_time = (TextView)dialog.findViewById(R.id.tv_apponit_time);
		tv_apponit_time.setText(bookingReqBean.getAPPOINTMENT_TIME());
		
		TextView tv_status = (TextView)dialog.findViewById(R.id.tv_status);
		tv_status.setText(bookingReqBean.getSTATUS());
		
		Button btn_ok = (Button)dialog.findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.cancel();
			}
		});
		
		dialog.show();
	}
}
