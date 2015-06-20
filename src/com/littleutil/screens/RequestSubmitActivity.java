package com.littleutil.screens;

import java.util.Calendar;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.littleutil.BaseActivity;
import com.littleutil.R;
import com.littleutil.constant.Constants;
import com.littleutil.dialog.DialogAddress;
import com.littleutil.dialog.DialogAddress.OnAddressSetListener;
import com.littleutil.dialog.DialogDescription;
import com.littleutil.dialog.DialogDescription.OnDescSetListener;
import com.littleutil.network.HttpClient;

public class RequestSubmitActivity extends BaseActivity {

	private EditText etName, etEmail, etPhone,etTime, etPassword, etAddress, etCity, etZipCode, etDate,etArea,etDescription;
	private ImageView ivBack;
	private TextView tv_service_name;
	private Button btnConfirm,btnContinue;
	private DatePicker datePicker;
	private Calendar calendar;
	private int year, month, day;
	private String date, time,name,service_id;
	private LinearLayout ll_part1,ll_part2;
	public boolean flag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requestsubmit);

		tv_service_name = (TextView)findViewById(R.id.tv_service_name);
		etName = (EditText) findViewById(R.id.etName);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etPhone = (EditText) findViewById(R.id.etPhone);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etAddress = (EditText) findViewById(R.id.etAddress);
		etArea  = (EditText) findViewById(R.id.etArea);
		etCity = (EditText) findViewById(R.id.etCity);
		etZipCode = (EditText) findViewById(R.id.etZipCode);
		etDate = (EditText) findViewById(R.id.etDate);
		etTime = (EditText)findViewById(R.id.etTime);
		etDescription = (EditText) findViewById(R.id.etDescription);
		ivBack = (ImageView) findViewById(R.id.ivBack);
		btnConfirm = (Button) findViewById(R.id.btnConfirm);
		btnContinue  = (Button) findViewById(R.id.btnContinue);
		ll_part1 = (LinearLayout)findViewById(R.id.ll_part1);
		ll_part2 = (LinearLayout)findViewById(R.id.ll_part2);
		calendar = Calendar.getInstance();

		name = getIntent().getExtras().getString("name");
		service_id = getIntent().getExtras().getString("id");
		
		tv_service_name.setText(name);
		btnContinue.setOnClickListener(this);
		btnConfirm.setOnClickListener(this);
		etDate.setKeyListener(null);
		etAddress.setKeyListener(null);
		etCity.setKeyListener(null);
		
		/*etDate.setEnabled(false);
		etAddress.setEnabled(false);
		etCity.setEnabled(false);*/
		
		etAddress.setOnClickListener(this);
		etCity.setOnClickListener(this);
		etDate.setOnClickListener(this);
		ivBack.setOnClickListener(this);
		etDescription.setOnClickListener(this);
		etTime.setOnClickListener(this);
		
		ll_part1.setVisibility(View.VISIBLE);
		ll_part2.setVisibility(View.GONE);
		
		setCurrentDate();
	}
	private void setCurrentDate() {
		 final Calendar c = Calendar.getInstance();
	        year  = c.get(Calendar.YEAR);
	        month = c.get(Calendar.MONTH);
	        day   = c.get(Calendar.DAY_OF_MONTH);
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
			
		case R.id.etTime:
			registerForContextMenu(v);
			openContextMenu(v);
			unregisterForContextMenu(v);
			break;
		case R.id.ivBack:
			finish();
			break;
			
		case R.id.btnConfirm:
			if(isValid()){
				new ReqAsynctask().execute(createReq());
			}
			break;
			
		case R.id.btnContinue:
			ll_part1.setVisibility(View.GONE);
			ll_part2.setVisibility(View.VISIBLE);
			break;
			
		case R.id.etDescription:
			new DialogDescription(this, new OnDescSetListener() {
				
				@Override
				public void onAddressSet(String desc) {
					etDescription.setText(desc);
				}
			}, etDescription.getText().toString().trim());
			break;
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		if(v == etCity){
			menu.setHeaderTitle("SELECT CITY");
			menu.add(0, 1, 0, "Kolkata");
			menu.add(0, 1, 0, "Bangalore");
			menu.add(0, 1, 0, "Delhi");
			menu.add(0, 1, 0, "Chennai");
			menu.add(0, 1, 0, "Hyderabad");
			menu.add(0, 1, 0, "Mumbai");
		}else if(v == etTime){
			menu.setHeaderTitle("SELECT TIME");
			menu.add(1, 1, 0, "12AM - 2 AM");
			menu.add(1, 1, 0, "2AM - 4 AM");
			menu.add(1, 1, 0, "4AM - 6 AM");
			menu.add(1, 1, 0, "6AM - 8 AM");
			menu.add(1, 1, 0, "10AM - 12 PM");
			menu.add(1, 1, 0, "12PM - 2 PM");
			menu.add(1, 1, 0, "2PM - 4 PM");
			menu.add(1, 1, 0, "4PM - 6 PM");
			menu.add(1, 1, 0, "8PM - 10 AM");
			menu.add(1, 1, 0, "10AM - 12 AM");
		}
		
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if(item.getGroupId() == 0){
			etCity.setText(item.getTitle());
		}else if(item.getGroupId() == 1){
			etTime.setText(item.getTitle());
		}else{
			return false;
		}
		return true;
	}

	private void showDate(int year, int month, int day) {
		date = new StringBuilder().append(day).append("/").append(month).append("/").append(year).toString();
		etDate.setText( date );
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == 999) {
			if(flag){
				flag = false;
			DatePickerDialog datePickerDialog = new DatePickerDialog(this, myDateListener, year, month, day);
			datePickerDialog.setTitle("SET DATE");
			return datePickerDialog;
			}
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
			
			String response = HttpClient.SendHttpPost(Constants.SUBMIT_REQUEST, params[0].toString());
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
			if (result) {
				if(etPassword.getText().toString().length()>0){
					app.getUserinfo().SetUserInfo(etName.getText().toString().trim(), etEmail.getText().toString().trim(), etPhone.getText().toString().trim(), etPassword.getText().toString().trim(), etArea.getText().toString().trim(), etCity.getText().toString().trim(), etZipCode.getText().toString().trim(), true);					
				}else{
					app.getUserinfo().SetUserInfo(etName.getText().toString().trim(), etEmail.getText().toString().trim(), etPhone.getText().toString().trim(), etPassword.getText().toString().trim(), etArea.getText().toString().trim(), etCity.getText().toString().trim(), etZipCode.getText().toString().trim(), false);
				}
				Toast.makeText(getApplicationContext(), "Request Successfully submitted.", Toast.LENGTH_LONG).show();
				finish();
			}else{
				Toast.makeText(getApplicationContext(), "Some error occured.", Toast.LENGTH_LONG).show();
			}
		}
	}
	@Override
	public void onBackPressed() {
		if(ll_part2.getVisibility() == View.VISIBLE){
			ll_part1.setVisibility(View.VISIBLE);
			ll_part2.setVisibility(View.GONE);
		}else if(ll_part1.getVisibility() == View.VISIBLE){
			finish();
		}
	}
}
