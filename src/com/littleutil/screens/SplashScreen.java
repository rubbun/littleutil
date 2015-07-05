package com.littleutil.screens;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.constant.Constants;
import com.littleutil.network.HttpClient;

public class SplashScreen extends BaseActivity {
	private Intent mIntent;
	private Animation animationFadeIn;
	private ImageView imageView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		imageView1 = (ImageView) findViewById(R.id.imageView1);

		animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
		imageView1.startAnimation(animationFadeIn);

		serviceList.clear();
		if(hasConnection()){
			new FetchSubServices().execute();
		}else{
			Toast.makeText(getApplicationContext(), "Please check your internet conncetion..", Toast.LENGTH_LONG).show();
		}
	}

	public class FetchSubServices extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				JSONObject obj = new JSONObject();
				obj.put("id", "1");
				obj.put("type", "android");
				JSONObject obj1 = new JSONObject();
				obj1.put("id", "2");
				obj1.put("type", "android");
				JSONObject obj2 = new JSONObject();
				obj2.put("id", "3");
				obj2.put("type", "android");
				JSONObject obj3 = new JSONObject();
				obj3.put("id", "4");
				obj3.put("type", "android");
				JSONObject obj4 = new JSONObject();
				obj4.put("id", "5");
				obj4.put("type", "android");
				
				String response = HttpClient.SendHttpPost(Constants.FETCH_SUBERVICES, obj.toString());
				String response1 = HttpClient.SendHttpPost(Constants.FETCH_SUBERVICES, obj1.toString());
				String response2 = HttpClient.SendHttpPost(Constants.FETCH_SUBERVICES, obj2.toString());
				String response3 = HttpClient.SendHttpPost(Constants.FETCH_SUBERVICES, obj3.toString());
				String response4 = HttpClient.SendHttpPost(Constants.FETCH_SUBERVICES, obj4.toString());
				if (response != null) {
					JSONArray jarr = new JSONArray(response);
					arr[0] = jarr.toString();
					if (response1 != null) {
						JSONArray jarr1 = new JSONArray(response1);
						arr[1] = jarr1.toString();
						if (response2 != null) {
							JSONArray jarr2 = new JSONArray(response2);
							arr[2] = jarr2.toString();
							if (response3 != null) {
								JSONArray jarr3 = new JSONArray(response3);
								arr[3] = jarr3.toString();
								if (response4 != null) {
									JSONArray jarr4 = new JSONArray(response4);
									arr[4] = jarr4.toString();
								}
							}
						}
					}
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
			if (result) {
				mIntent = new Intent(SplashScreen.this, DashBoard.class);
				startActivity(mIntent);
				finish();
			}
		}
	}
}
