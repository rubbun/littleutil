package com.littleutil.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.littleutil.R;
import com.littleutil.BaseActivity;
import com.littleutil.dialog.DialogAddress;
import com.littleutil.dialog.DialogAddress.OnAddressSetListener;

public class RequestSubmitActivity extends BaseActivity{
	
	private EditText etName,etEmail,etPhone,etPassword,etAddress,etCity,etZipCode,etDate;
	private ImageView ivBack;
	private Button btnConfirm;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requestsubmit);
		
		etName = (EditText)findViewById(R.id.etName);
		etEmail = (EditText)findViewById(R.id.etEmail);
		etPhone = (EditText)findViewById(R.id.etPhone);
		etPassword = (EditText)findViewById(R.id.etPassword);
		etAddress = (EditText)findViewById(R.id.etAddress);
		etCity = (EditText)findViewById(R.id.etCity);
		etZipCode = (EditText)findViewById(R.id.etZipCode);
		etDate = (EditText)findViewById(R.id.etDate);
		ivBack = (ImageView)findViewById(R.id.ivBack);
		btnConfirm = (Button)findViewById(R.id.btnConfirm);
		
		etAddress.setOnClickListener(this);
	}
	
	public void OnConfirmClick(){
		
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
			break;

		
		}
	}
	
	
	

}
