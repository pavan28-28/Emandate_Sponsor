package com.EmanSponser.model;

public class Details_status {

	private CustDetails_Banker details;
	private boolean status;
	
	public CustDetails_Banker getDetails() {
		return details;
	}
	public void setDetails(CustDetails_Banker details) {
		this.details = details;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Details_status [details=" + details + ", status=" + status + "]";
	}
	public Details_status(CustDetails_Banker details, boolean status) {
		super();
		this.details = details;
		this.status = status;
	}
	public Details_status() {
		super();
	}
	
	
}
