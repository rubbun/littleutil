package com.littleutil.screens;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.constant.Constants;
import com.littleutil.network.HttpClient;

public class SplashScreen extends BaseActivity{
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		serviceList.clear();
		new FetchSubServices().execute();
		
		/*new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				mIntent = new Intent(SplashScreen.this,DashBoard.class);
				startActivity(mIntent);
				finish();
			}
		}, 6000);*/
	}
	
	public class FetchSubServices extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				JSONObject obj = new JSONObject();
				obj.put("id", "1");
				JSONObject obj1 = new JSONObject();
				obj1.put("id", "2");
				JSONObject obj2 = new JSONObject();
				obj2.put("id", "3");
				String response = HttpClient.SendHttpPost(Constants.FETCH_SUBERVICES, obj.toString());
				String response1 = HttpClient.SendHttpPost(Constants.FETCH_SUBERVICES, obj1.toString());
				String response2 = HttpClient.SendHttpPost(Constants.FETCH_SUBERVICES, obj2.toString());
				if(response != null){
					JSONArray jarr = new JSONArray(response);
					arr[0] = jarr.toString();
				}
				if(response1 != null){
					JSONArray jarr = new JSONArray(response1);
					arr[1] = jarr.toString();
				}
				if(response2 != null){
					JSONArray jarr = new JSONArray(response2);
					arr[2] = jarr.toString();
				}
				return true;
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if(result){
				mIntent = new Intent(SplashScreen.this,DashBoard.class);
				startActivity(mIntent);
				finish();
			}
		}
	}
}
