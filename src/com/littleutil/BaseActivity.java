package com.littleutil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.littleutil.application.Appsettings;
import com.littleutil.application.UserInfo;

public class BaseActivity extends Activity {

	public ProgressDialog dialog;
	public Appsettings app = null;

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
}
