package com.littleutil.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.littleutil.BaseActivity;
import com.littleutil.R;

public class DialogAddress {
	
	public interface OnAddressSetListener{
		public void onAddressSet(String address);
	}
	
	private EditText etAddress;
	
	public DialogAddress(final BaseActivity baseActivity,final OnAddressSetListener listener, String address) {
		
		View view = View.inflate(baseActivity, R.layout.custom_address, null);
		etAddress = (EditText)view.findViewById(R.id.etAddress);
		etAddress.setText(address);
		new AlertDialog.Builder(baseActivity)
		.setTitle("Provide Your Address")
		.setCancelable(false)
		.setView(view)
		.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				listener.onAddressSet(etAddress.getText().toString().trim());
			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				//listener.onAddressSet(etAddress.getText().toString().trim());
			}
		})
		.show();
		etAddress.setSelection(address.length());
	}

}
