package com.littleutil.bean;

import java.util.ArrayList;

public class ServiceBean {

	public String service_name,id;
	public ArrayList<SubServiceBean> list = new ArrayList<SubServiceBean>();
	
	public ServiceBean(String service_name, String id, ArrayList<SubServiceBean> list) {
		super();
		this.service_name = service_name;
		this.id = id;
		this.list = list;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<SubServiceBean> getList() {
		return list;
	}
	public void setList(ArrayList<SubServiceBean> list) {
		this.list = list;
	}
}
