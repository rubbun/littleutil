package com.littleutil.screens;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.littleutil.R;
import com.littleutil.BaseActivity;
import com.littleutil.constant.Constants;
import com.littleutil.network.HttpClient;

public class SplashScreen extends BaseActivity{
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				mIntent = new Intent(SplashScreen.this,DashBoard.class);
				startActivity(mIntent);
			}
		}, 3000);
	}
	
	public class FetchSubServices extends AsyncTask<Void, Void, Void>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			try {
				JSONObject obj = new JSONObject();
				obj.put("id", "1");
				String response = HttpClient.SendHttpPost(Constants.FETCH_SUBERVICES, obj.toString());
				if(response != null){
					
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
		}
	}
}
