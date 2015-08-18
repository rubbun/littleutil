package com.littleutil.screens;

import java.util.ArrayList;

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
import com.littleutil.bean.ServiceBean;
import com.littleutil.bean.SubServiceBean;
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
		if (hasConnection()) {
			new FetchSubServices().execute();
		} else {
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
				obj.put("type", "android");
				String response = HttpClient.SendHttpPost(Constants.FETCH_SUBERVICES, obj.toString());
				if (response != null) {
					createList(response);
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

	private void createList(String response) {
		try {
			JSONArray arr = new JSONArray(response);
			serviceList.clear();
			allServiceList.clear();
			ArrayList<SubServiceBean> list1 = new ArrayList<SubServiceBean>();
			list1.add(new SubServiceBean("1", "Track Your Status", ""));
			list1.add(new SubServiceBean("2", "Track Service Rates", ""));
			serviceList.add(new ServiceBean("Settings", "0", list1));
			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = arr.getJSONObject(i);
				ArrayList<SubServiceBean> list = new ArrayList<SubServiceBean>();
				JSONArray jarr;

				jarr = obj.getJSONArray("sub_services");
				for (int j = 0; j < jarr.length(); j++) {
					JSONObject object = jarr.getJSONObject(j);
					String path;
					if (object.has("path")) {
						path = object.getString("path");
					} else {
						path = "";
					}
					list.add(new SubServiceBean(object.getString("id"), object.getString("name"), path));
				}
				serviceList.add(new ServiceBean(obj.getString("name"), obj.getString("id"), list));
				allServiceList.add(new ServiceBean(obj.getString("name"), obj.getString("id"), list));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
