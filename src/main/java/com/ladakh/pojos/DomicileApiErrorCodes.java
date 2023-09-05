package com.ladakh.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="dom_otp_data")
public class DomicileApiErrorCodes {
	
	String czn_id;
	String appplication_id;
	String mobileNo;
	DomMastErrorCode status_code;
	String response_msg;
	int otp;
	String otpexpiry_time;
	
	public DomicileApiErrorCodes() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	public DomicileApiErrorCodes(String czn_id, String appplication_id, String mobileNo, DomMastErrorCode status_code,
			String response_msg, int otp, String otpexpiry_time) {
		super();
		this.czn_id = czn_id;
		this.appplication_id = appplication_id;
		this.mobileNo = mobileNo;
		this.status_code = status_code;
		this.response_msg = response_msg;
		this.otp = otp;
		this.otpexpiry_time = otpexpiry_time;
	}




	@Override
	public String toString() {
		return "DomicileApiErrorCodes [czn_id=" + czn_id + ", appplication_id=" + appplication_id + ", mobileNo="
				+ mobileNo + ", status_code=" + status_code + ", response_msg=" + response_msg + ", otp=" + otp
				+ ", otpexpiry_time=" + otpexpiry_time + "]";
	}




	@Id	
	@Column(name="czn_id")
	public String getCzn_id() {
		return czn_id;
	}

	public void setCzn_id(String czn_id) {
		this.czn_id = czn_id;
	}
	@Column(name="app_id")
	public String getAppplication_id() {
		return appplication_id;
	}

	public void setAppplication_id(String appplication_id) {
		this.appplication_id = appplication_id;
	}

	@Column(name="mob_no")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	 
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "status_code", referencedColumnName = "status_code")
	public DomMastErrorCode getStatus_code() {
		return status_code;
	}

	public void setStatus_code(DomMastErrorCode status_code) {
		this.status_code = status_code;
	}
	
	@Column(name="response_msg")
	public String getResponse_msg() {
		return response_msg;
	}

	public void setResponse_msg(String response_msg) {
		this.response_msg = response_msg;
	}

	@Column(name="otp")
	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	@Column(name="otpexpiry_time")
	public String getOtpexpiry_time() {
		return otpexpiry_time;
	}

	public void setOtpexpiry_time(String otpexpiry_time) {
		this.otpexpiry_time = otpexpiry_time;
	}

	
	
	
	

}
