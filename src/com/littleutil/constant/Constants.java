package com.littleutil.constant;

import java.util.ArrayList;

import com.littleutil.bean.ServiceBean;

public class Constants {

	public static String BASE_URL = "http://devlpconsole.com/lu/index.php/";
	public static String FETCH_SUBERVICES = "api/findServiceNameImageByID";
	public static ArrayList<ServiceBean> serviceList = new ArrayList<ServiceBean>();
	
	public enum values {
		USRINFO, USERID, NAME, EMAIL, MOBILE_NO, PASSWORD, AREA, CITY, COUNTRY, PINCODE, SESSION
	}
}
