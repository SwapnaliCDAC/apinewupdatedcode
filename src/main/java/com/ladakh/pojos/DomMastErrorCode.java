package com.ladakh.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dom_mast_status_code")
public class DomMastErrorCode {
	
	String status_code;
	String status_details;
	
		
	public DomMastErrorCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DomMastErrorCode(String status_code, String status_details) {
		super();
		this.status_code = status_code;
		this.status_details = status_details;
	}

	@Override
	public String toString() {
		return "DomMastErrorCode [status_code=" + status_code + ", status_details=" + status_details + "]";
	}

	@Id 
	@Column(name="status_code")
	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	@Column(name="status_details")
	public String getStatus_details() {
		return status_details;
	}

	public void setStatus_details(String status_details) {
		this.status_details = status_details;
	}
	
	
	

}
