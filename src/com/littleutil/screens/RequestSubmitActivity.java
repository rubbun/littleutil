package com.littleutil.screens;

import java.util.Calendar;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.dialog.DialogAddress;
import com.littleutil.dialog.DialogAddress.OnAddressSetListener;
import com.littleutil.network.HttpClient;

public class RequestSubmitActivity extends BaseActivity {

	private EditText etName, etEmail, etPhone, etPassword, etAddress, etCity, etZipCode, etDate,etArea;
	private ImageView ivBack;
	private Button btnConfirm;
	private DatePicker datePicker;
	private Calendar calendar;
	private int year, month, day;
	private String date, time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requestsubmit);

		etName = (EditText) findViewById(R.id.etName);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etPhone = (EditText) findViewById(R.id.etPhone);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etAddress = (EditText) findViewById(R.id.etAddress);
		etArea  = (EditText) findViewById(R.id.etArea);
		etCity = (EditText) findViewById(R.id.etCity);
		etZipCode = (EditText) findViewById(R.id.etZipCode);
		etDate = (EditText) findViewById(R.id.etDate);
		ivBack = (ImageView) findViewById(R.id.ivBack);
		btnConfirm = (Button) findViewById(R.id.btnConfirm);
		calendar = Calendar.getInstance();

		etAddress.setOnClickListener(this);
	}

	public void OnConfirmClick() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.etAddress:
			new DialogAddress(this, new OnAddressSetListener() {

				@Override
				public void onAddressSet(String address) {
					etAddress.setText(address);

				}
			}, etAddress.getText().toString().trim());
			break;

		case R.id.etCity:
			registerForContextMenu(v);
			openContextMenu(v);
			unregisterForContextMenu(v);
			break;

		case R.id.etDate:
			showDialog(999);
			break;
			
		case R.id.btnConfirm:
			if(isValid()){
				new ReqAsynctask().execute(createReq());
			}
			break;

		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("SELECT CITY");
		menu.add(0, 1, 0, "Kolkata");
		menu.add(0, 1, 0, "Bangalore");
		menu.add(0, 1, 0, "Delhi");
		menu.add(0, 1, 0, "Chennai");
		menu.add(0, 1, 0, "Hyderabad");
		menu.add(0, 1, 0, "Mumbai");

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		etCity.setText(item.getTitle());
		return super.onContextItemSelected(item);
	}

	private void showDate(int year, int month, int day) {
		//etDate.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
		date = new StringBuilder().append(day).append("/").append(month).append("/").append(year).toString();
		Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(RequestSubmitActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
            	time = selectedHour + ":" + selectedMinute;
            	
            	
            	if(selectedHour>11){
            		etDate.setText( date + "  " + time+" PM");
            	}else{
            		etDate.setText( date + "  " + time+" AM");
            	}
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == 999) {
			return new DatePickerDialog(this, myDateListener, year, month, day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			
			showDate(arg1, arg2 + 1, arg3);
		}
	};
	
	public boolean isValid(){
		boolean flag = true;
		if(!(etPhone.getText().toString().trim().length()>=10)){
			flag = false;
			new AlertDialog.Builder(RequestSubmitActivity.this)
			.setCancelable(false)
			.setTitle("Error")
			.setMessage("Please enter a valid phone number")
			
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					
				}
			})
			.show();
		}
		return flag;
		
	}
	
	public JSONObject createReq(){
		JSONObject jsonObject = new JSONObject();
		try{
		jsonObject.put("name", etName.getText().toString().trim());
		jsonObject.put("email", etEmail.getText().toString().trim());
		jsonObject.put("mobile", etPhone.getText().toString().trim());
		jsonObject.put("password", etPassword.getText().toString().trim());
		jsonObject.put("address", etAddress.getText().toString().trim());
		jsonObject.put("area", etArea.getText().toString().trim());
		jsonObject.put("city", etCity.getText().toString().trim());
		jsonObject.put("appointment_date", etDate.getText().toString().trim());
		jsonObject.put("service_id", "");
		return jsonObject;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public class ReqAsynctask extends AsyncTask<JSONObject, Void, Boolean>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();			
			doShowLoading();
		}

		@Override
		protected Boolean doInBackground(JSONObject... params) {
			try{
			
			String response = HttpClient.SendHttpPost("", params[0].toString());
					if(response!= null){
						JSONObject jsonObject = new JSONObject(response);
						return jsonObject.getBoolean("status");
					}
			}catch(Exception e){
				
			}
			
			return false;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			doRemoveLoading();
		}
		
	}

}
