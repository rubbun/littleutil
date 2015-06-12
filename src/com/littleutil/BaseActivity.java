package com.littleutil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

public class BaseActivity extends Activity {

	public ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
