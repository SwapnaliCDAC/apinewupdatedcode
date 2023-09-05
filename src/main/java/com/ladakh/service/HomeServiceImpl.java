package com.ladakh.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ladakh.pojos.PrefillForm;

import com.ladakh.dao.HomeDao;

@Service
@Transactional
public class HomeServiceImpl implements HomeService {

	Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);

	@Autowired
	private HomeDao homeDao;

	@PersistenceContext
	private EntityManager manager;

		@Override
	public String getCitizenId(String applicationId) {

		logger.info("Inside getCitizenId::");

		return homeDao.getCitizenId(applicationId);
	}
	
	
	
	@Override
	public String checkStatus(String applicationId) {

		logger.info("Inside getCitizenId::");

		return homeDao.checkStatus(applicationId);
	}
	

	
	@Override
	public List<PrefillForm> prefillForm(String citizenId) {
		logger.info(" inside service of checkStatus() ");
		return homeDao.prefillForm(citizenId);
	}


	
}
