package com.ladakh.dao;

import java.util.List;

import com.ladakh.pojos.PrefillForm;

import com.ladakh.pojos.Citizen;

import com.ladakh.pojos.DomicileApiErrorCodes;

public interface HomeDao {

		
	public List<PrefillForm> prefillForm(String citizenId) ;
	
	public String getCitizenId(String applicationId);

	public String checkStatus(String applicationId);

	public Citizen getContactNo(String citizenId);

	public boolean updateDomicileApiErrorCodes(DomicileApiErrorCodes domotpdata);
	
	public String otpvalidity(String citizenId);
}
