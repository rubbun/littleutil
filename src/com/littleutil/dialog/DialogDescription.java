package com.littleutil.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import com.littleutil.BaseActivity;
import com.littleutil.R;

public class DialogDescription {
	
	public interface OnDescSetListener{
		public void onAddressSet(String desc);
	}
	
	private EditText editText;
	
	public DialogDescription(final BaseActivity baseActivity,final OnDescSetListener listener, String desc) {
		
		View view = View.inflate(baseActivity, R.layout.custom_desctiption, null);
		editText = (EditText)view.findViewById(R.id.etDesc);
		editText.setText(desc);
		new AlertDialog.Builder(baseActivity)
		.setTitle("Enter Desription")
		.setCancelable(false)
		.setView(view)
		.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				listener.onAddressSet(editText.getText().toString().trim());
				
				
				
			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				listener.onAddressSet(editText.getText().toString().trim());
				
			}
		})
		.show();
		
	}

}
