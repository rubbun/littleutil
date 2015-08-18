package com.littleutil.bean;

public class ServiceRatesBean {
	private String id;
	private String service_id;
	private String rates;
	private String special_rates;
	private String name;

	public ServiceRatesBean(String id, String service_id, String rates, String special_rates, String name) {
		super();
		this.id = id;
		this.service_id = service_id;
		this.rates = rates;
		this.special_rates = special_rates;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getRates() {
		return rates;
	}

	public void setRates(String rates) {
		this.rates = rates;
	}

	public String getSpecial_rates() {
		return special_rates;
	}

	public void setSpecial_rates(String special_rates) {
		this.special_rates = special_rates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
