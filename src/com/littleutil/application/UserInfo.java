package com.littleutil.application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.littleutil.constant.Constants;

public class UserInfo {
	public String user_id = null;
	public String name = null;
	public String email = null;
	public String mobile_no = null;
	public String password = null;
	public String area = null;
	public String city = null;
	public String country = null;
	public String pincode = null;
	
	public boolean session = false;
	public SharedPreferences preference = null;
	
	public UserInfo(Context ctx){
		
		preference = ctx.getSharedPreferences(Constants.values.USRINFO.name(), Context.MODE_PRIVATE);
		user_id = preference.getString(Constants.values.USERID.name(), null);
		name = preference.getString(Constants.values.NAME.name(), null);
		email = preference.getString(Constants.values.EMAIL.name(), null);
		mobile_no = preference.getString(Constants.values.MOBILE_NO.name(), null);		
		password = preference.getString(Constants.values.PASSWORD.name(), null);
		area = preference.getString(Constants.values.AREA.name(), null);
		city = preference.getString(Constants.values.CITY.name(), null);
		country = preference.getString(Constants.values.COUNTRY.name(), null);
		pincode = preference.getString(Constants.values.PINCODE.name(), null);
		
		session =  preference.getBoolean(Constants.values.SESSION.name(), false);
		
	}

	public void SetUserInfo(String user_id,String name, String email,String mobile_no,String password,String area,String city,String country,String pincode,boolean session) {
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.mobile_no = mobile_no;
		this.password = password;
		this.area = area;
		this.city = city;
		this.country = country;
		this.pincode = pincode;
		
		this.session = session;
		
		Editor edit = preference.edit();
		edit.putString(Constants.values.USERID.name(), user_id);
		edit.putString(Constants.values.NAME.name(), name);
		edit.putString(Constants.values.EMAIL.name(), email);
		edit.putString(Constants.values.MOBILE_NO.name(), mobile_no);
		edit.putString(Constants.values.PASSWORD.name(), password);
		edit.putString(Constants.values.AREA.name(), area);
		edit.putString(Constants.values.CITY.name(), city);
		edit.putString(Constants.values.COUNTRY.name(), country);
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
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}

	public boolean isSession() {
		return session;
	}
	
}
