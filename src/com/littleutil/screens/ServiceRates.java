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
import android.widget.Toast;

import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.adapter.ServiceRateListAdapter;
import com.littleutil.bean.ServiceRatesBean;
import com.littleutil.constant.Constants;
import com.littleutil.network.HttpClientGet;

public class ServiceRates extends BaseActivity {

	private ListView ll_service_rate_list;
	private LinearLayout ll_header;
	private LayoutInflater inflater;
	private TextView textView1;
	private ImageView ivBack;
	private ServiceRateListAdapter adapter;

	private ArrayList<ServiceRatesBean> list = new ArrayList<ServiceRatesBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_rates);

		ll_header = (LinearLayout) findViewById(R.id.ll_header);
		ll_service_rate_list = (ListView) findViewById(R.id.ll_service_rate_list);

		textView1 = (TextView) findViewById(R.id.textView1);
		textView1.setText("Service Rates");

		View v = getLayoutInflater().inflate(R.layout.service_rate_header, null);
		ll_header.addView(v);

		ivBack = (ImageView) findViewById(R.id.ivBack);
		ivBack.setOnClickListener(this);

		if (hasConnection()) {
			new getAllServiceRates().execute();
		} else {
			Toast.makeText(getApplicationContext(), "Please check your internet conncetion..", Toast.LENGTH_LONG).show();
		}
	}

	public class getAllServiceRates extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			doShowLoading();
		}

		@Override
		protected Void doInBackground(Void... params) {
			JSONObject ob = new JSONObject();
			try {
				String response = HttpClientGet.SendHttpGet(Constants.ALL_SERVICE_RATES);
				if (response != null) {
					JSONObject object = new JSONObject(response);
					JSONArray arr = object.getJSONArray("rates");
					for (int i = 0; i < arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						list.add(new ServiceRatesBean(obj.getString("id"), obj.getString("service_id"), obj.getString("rates"), obj.getString("special_rates"), obj.getString("name")));
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
			adapter = new ServiceRateListAdapter(ServiceRates.this, R.layout.service_rate_row, list);
			ll_service_rate_list.setAdapter(adapter);
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
