package com.littleutil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
	 
	 public static String getCurrentTimeStamp()
		{
			Calendar c = Calendar.getInstance();
			int mHour = c.get(Calendar.HOUR);
			int mMinute = c.get(Calendar.MINUTE);
			int mSeconds = c.get(Calendar.SECOND);
			
			return mHour +":"+ mMinute +":"+ mSeconds;
		}



	public static String getCurrentDate()
		{
			Calendar c = Calendar.getInstance();
			System.out.println("Current time => " + c.getTime());

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			String formattedDate = df.format(c.getTime());
			System.out.println("Date ::" + formattedDate);
			
			return formattedDate;
		}
		
		public static String getCurrentDate_DDMMYYYY()
		{
			Calendar c = Calendar.getInstance();
			System.out.println("Current time => " + c.getTime());

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
			String formattedDate = df.format(c.getTime());
			System.out.println("Date ::" + formattedDate);
			
			return formattedDate;
		}
		
		public static boolean compareDates(String strFrmDate, String strToDate) 
		{
			try{
				 
	    		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        	Date date1 = sdf.parse(strFrmDate);
	        	Date date2 = sdf.parse(strToDate);
	 
	        	System.out.println(sdf.format(date1));
	        	System.out.println(sdf.format(date2));
	 
	        	if(date1.compareTo(date2)>0){
	        		System.out.println("Date1 is after Date2");
	        		return false; 
	        	}else if(date1.compareTo(date2)<0){
	        		System.out.println("Date1 is before Date2");
	        		return true; 
	        	}else if(date1.compareTo(date2)==0){
	        		System.out.println("Date1 is equal to Date2");
	        		return true; 
	        	}else{
	        		System.out.println("How to get here?");
	        		return false; 
	        	}
	 
	    	}catch(ParseException ex){
	    		ex.printStackTrace();
	    	}
			return false; 
		}
}
