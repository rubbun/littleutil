package com.littleutil.application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.littleutil.constant.Constants;

public class UserInfo {
	public String name = "";
	public String email = "";
	public String mobile_no = "";
	public String password = "";
	public String area = "";
	public String city = "";
	public String address = "";
	public String pincode = "";
	public String description = "";	
	public boolean session = false;
	
	public SharedPreferences preference;
	
	public UserInfo(Context ctx){
		
		preference = ctx.getSharedPreferences(Constants.values.USRINFO.name(), Context.MODE_PRIVATE);
		name = preference.getString(Constants.values.NAME.name(), "");
		email = preference.getString(Constants.values.EMAIL.name(), "");
		mobile_no = preference.getString(Constants.values.MOBILE_NO.name(), "");		
		password = preference.getString(Constants.values.PASSWORD.name(), "");
		area = preference.getString(Constants.values.AREA.name(), "");
		city = preference.getString(Constants.values.CITY.name(), "");
		pincode = preference.getString(Constants.values.PINCODE.name(), "");
		description = preference.getString(Constants.values.DESCRIPTION.name(), "");
		session =  preference.getBoolean(Constants.values.SESSION.name(), false);
		
	}

	public void SetUserInfo(String name, String email,String mobile_no,String password,String area,String city,String pincode,boolean session) {
		this.name = name;
		this.email = email;
		this.mobile_no = mobile_no;
		this.password = password;
		this.area = area;
		this.city = city;
		this.pincode = pincode;
		
		this.session = session;
		
		Editor edit = preference.edit();
		edit.putString(Constants.values.NAME.name(), name);
		edit.putString(Constants.values.EMAIL.name(), email);
		edit.putString(Constants.values.MOBILE_NO.name(), mobile_no);
		edit.putString(Constants.values.PASSWORD.name(), password);
		edit.putString(Constants.values.AREA.name(), area);
		edit.putString(Constants.values.CITY.name(), city);
		edit.putString(Constants.values.PINCODE.name(), pincode);
		
		edit.putBoolean(Constants.values.SESSION.name(), session);
		edit.commit();
	}
	
	public void setSession(boolean session) {
		this.session = session;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}

	public String getUser_id() {
		return preference.getString(Constants.values.USERID.name(), "");
	}

	public void setUser_id(String user_id) {
		Editor edit = preference.edit();
		edit.putString(Constants.values.USERID.name(), user_id);		
		edit.commit();
	}

	public String getName() {
		return preference.getString(Constants.values.NAME.name(), "");
	}

	public void setName(String name) {
		this.name = name;
		Editor edit = preference.edit();
		edit.putString(Constants.values.NAME.name(), name);		
		edit.commit();
	}

	public String getEmail() {
		return preference.getString(Constants.values.EMAIL.name(), "");
	}

	public void setEmail(String email) {
		this.email = email;
		Editor edit = preference.edit();
		edit.putString(Constants.values.EMAIL.name(), email);		
		edit.commit();
	}

	public String getMobile_no() {
		return preference.getString(Constants.values.MOBILE_NO.name(), "");
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
		Editor edit = preference.edit();
		edit.putString(Constants.values.MOBILE_NO.name(), mobile_no);		
		edit.commit();
	}

	public String getPassword() {
		return preference.getString(Constants.values.PASSWORD.name(), "");
	}

	public void setPassword(String password) {
		this.password = password;
		Editor edit = preference.edit();
		edit.putString(Constants.values.PASSWORD.name(), password);		
		edit.commit();
	}

	public String getArea() {
		return preference.getString(Constants.values.AREA.name(), "");
	}

	public void setArea(String area) {
		this.area = area;
		Editor edit = preference.edit();
		edit.putString(Constants.values.AREA.name(), area);		
		edit.commit();
	}

	public String getCity() {
		return preference.getString(Constants.values.CITY.name(), "");
	}

	public void setCity(String city) {
		this.city = city;
		Editor edit = preference.edit();
		edit.putString(Constants.values.CITY.name(), city);		
		edit.commit();
	}

	

	public String getPincode() {
		return preference.getString(Constants.values.PINCODE.name(), "");
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
		Editor edit = preference.edit();
		edit.putString(Constants.values.PINCODE.name(), pincode);		
		edit.commit();
	}

	public boolean isSession() {
		return session;
	}
	
}
