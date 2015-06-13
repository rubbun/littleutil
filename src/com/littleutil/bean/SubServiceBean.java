package com.littleutil.bean;

public class SubServiceBean {

	public String id,name,image_path;

	public SubServiceBean(String id, String name, String image_path) {
		super();
		this.id = id;
		this.name = name;
		this.image_path = image_path;
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

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
}
