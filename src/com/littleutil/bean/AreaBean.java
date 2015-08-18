package com.littleutil.bean;

public class AreaBean {

	public String id, name, pincode, city_id;

	public AreaBean(String id, String name, String pincode, String city_id) {
		super();
		this.id = id;
		this.name = name;
		this.pincode = pincode;
		this.city_id = city_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
}
