package com.ladakh.pojos;

import java.util.Arrays;
import java.util.Date;

public class PrefillForm {

	private String uid;
	private String cznId;
	private String Name;
	private String Parentagename;
	private String gender;
	private String DOB;
	
	private String phoneNumber;
	private String ContactNumber;
	private String email;
	
	private String curAdddresLine1;
	private String curAddressLine2;
	private String curDistrictFormId;
	private String curTehsilFormId;
	private String curPincode;
	
	private String prmAdddresLine1;
	private String prmAddressLine2;
	private String PermanentDistrict;
	private String PermanentTehsil;
	private String PermanentPincode;
	private String photoData;
    private String imageType;
    
    
	

	private String applicationId;
    
    public PrefillForm() {
		// TODO Auto-generated constructor stub
	}

    
    
    
    
    
	public PrefillForm(String uid, String cznId, String name, String parentagename, String gender, String dOB,
			String phoneNumber, String contactNumber, String email, String curAdddresLine1, String curAddressLine2,
			String curDistrictFormId, String curTehsilFormId, String curPincode, String prmAdddresLine1,
			String prmAddressLine2, String permanentDistrict, String permanentTehsil, String permanentPincode,
			String photoData, String imageType, String applicationId) {
		super();
		this.uid = uid;
		this.cznId = cznId;
		Name = name;
		Parentagename = parentagename;
		this.gender = gender;
		DOB = dOB;
		this.phoneNumber = phoneNumber;
		ContactNumber = contactNumber;
		this.email = email;
		this.curAdddresLine1 = curAdddresLine1;
		this.curAddressLine2 = curAddressLine2;
		this.curDistrictFormId = curDistrictFormId;
		this.curTehsilFormId = curTehsilFormId;
		this.curPincode = curPincode;
		this.prmAdddresLine1 = prmAdddresLine1;
		this.prmAddressLine2 = prmAddressLine2;
		PermanentDistrict = permanentDistrict;
		PermanentTehsil = permanentTehsil;
		PermanentPincode = permanentPincode;
		this.photoData = photoData;
		this.imageType = imageType;
		this.applicationId = applicationId;
	}






	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCznId() {
		return cznId;
	}

	public void setCznId(String cznId) {
		this.cznId = cznId;
	}	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurAdddresLine1() {
		return curAdddresLine1;
	}

	public void setCurAdddresLine1(String curAdddresLine1) {
		this.curAdddresLine1 = curAdddresLine1;
	}

	public String getCurAddressLine2() {
		return curAddressLine2;
	}

	public void setCurAddressLine2(String curAddressLine2) {
		this.curAddressLine2 = curAddressLine2;
	}

	public String getCurDistrictFormId() {
		return curDistrictFormId;
	}

	public void setCurDistrictFormId(String curDistrictFormId) {
		this.curDistrictFormId = curDistrictFormId;
	}

	public String getCurTehsilFormId() {
		return curTehsilFormId;
	}

	public void setCurTehsilFormId(String curTehsilFormId) {
		this.curTehsilFormId = curTehsilFormId;
	}

	public String getCurPincode() {
		return curPincode;
	}

	public void setCurPincode(String curPincode) {
		this.curPincode = curPincode;
	}

	public String getPrmAdddresLine1() {
		return prmAdddresLine1;
	}

	public void setPrmAdddresLine1(String prmAdddresLine1) {
		this.prmAdddresLine1 = prmAdddresLine1;
	}

	public String getPrmAddressLine2() {
		return prmAddressLine2;
	}

	public void setPrmAddressLine2(String prmAddressLine2) {
		this.prmAddressLine2 = prmAddressLine2;
	}	

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getPhotoData() {
		return photoData;
	}

	public void setPhotoData(String photoData) {
		this.photoData = photoData;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getParentagename() {
		return Parentagename;
	}

	public void setParentagename(String parentagename) {
		Parentagename = parentagename;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getContactNumber() {
		return ContactNumber;
	}

	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}

	public String getPermanentDistrict() {
		return PermanentDistrict;
	}

	public void setPermanentDistrict(String permanentDistrict) {
		PermanentDistrict = permanentDistrict;
	}

	public String getPermanentTehsil() {
		return PermanentTehsil;
	}

	public void setPermanentTehsil(String permanentTehsil) {
		PermanentTehsil = permanentTehsil;
	}

	public String getPermanentPincode() {
		return PermanentPincode;
	}

	public void setPermanentPincode(String permanentPincode) {
		PermanentPincode = permanentPincode;
	}

	
    
    
}