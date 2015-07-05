package com.littleutil;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.littleutil.application.Appsettings;
import com.littleutil.application.UserInfo;
import com.littleutil.bean.ServiceBean;

public class BaseActivity extends Activity implements OnClickListener{

	public ProgressDialog dialog;
	public Appsettings app = null;
	public static ArrayList<ServiceBean> serviceList = new ArrayList<ServiceBean>();
	public static String[] arr = new String[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (Appsettings)getApplication();
		if(!app.init){
			app.init = true;
			app.setUserinfo(new UserInfo(this));
		}
	}

	public void doShowLoading() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				dialog = new ProgressDialog(BaseActivity.this);
				dialog.setMessage("Please wait..........");
				dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				dialog.setIndeterminate(true);
				dialog.setCancelable(true);
				dialog.show();
			}
		});
	}

	public void doRemoveLoading() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				dialog.cancel();
			}
		});
	}

	@Override
	public void onClick(View v) {}

	 public boolean hasConnection() {
		 ConnectivityManager cm =
			        (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
			
		    NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		    if (wifiNetwork != null && wifiNetwork.isConnected()) {
		      return true;
		    }

		    NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		    if (mobileNetwork != null && mobileNetwork.isConnected()) {
		      return true;
		    }

		    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		    if (activeNetwork != null && activeNetwork.isConnected()) {
		      return true;
		    }
		    return false;
		  }
}
