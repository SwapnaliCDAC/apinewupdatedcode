package com.ladakh.service;


import java.util.List;
import com.ladakh.pojos.PrefillForm;

public interface HomeService {


	
	public List<PrefillForm> prefillForm(String citizenId) ;	
	
	public String checkStatus(String applicationId);

	public String getCitizenId(String applicationId);	

	
}
