package com.littleutil.constant;


public class Constants {

	public static String BASE_URL = "http://devlpconsole.com/lu/index.php/";
	public static String FETCH_SUBERVICES = BASE_URL + "api/findServiceNameImageByID";
	public static String SUBMIT_REQUEST = BASE_URL + "api/addRequest";
	public static String ALL_BOOKINGINFO_REQUEST = BASE_URL + "api/findTrackServicesByContactNo";
	
	public enum values {
		USRINFO, USERID, NAME, EMAIL,DESCRIPTION, MOBILE_NO, PASSWORD, AREA, CITY, COUNTRY, PINCODE,ADDRESS, SESSION
	}
}
