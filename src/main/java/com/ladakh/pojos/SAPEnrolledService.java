/**
 * eForms Portal and Back-Office for Jammu & Kashmir State
 * TestYetAnotherEJB.java created on Nov 19, 2010 at 10:36:32 AM by rksinha
 * Copyright (c) 2010-11, CDAC, Bangalore
 */
package com.ladakh.pojos;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * TODO : Write a short summary of this type
 *
 * @author jitendra
 *
 */
@Entity
@Table(name="sap_enrolled_services")
public class SAPEnrolledService implements Serializable{

	private String classId;
	private ServiceDeptCodeMap serviceDeptCodeMap;
	private String targetEndPoint;
	private String serviceAuthType;
	private String serviceType;
	private String umang_target_end_point;
	
	/*---------sap_enrolled_services:JOIN REMOVED EXERCISE(26-05-23)----------*/
//	private Collection<ServiceRequest> serviceRequests = new ArrayList<ServiceRequest>( );
	

	/**
	 * @return the serviceDeptCodeMap
	 */
	@OneToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="service_code", referencedColumnName="service_code")
	public ServiceDeptCodeMap getServiceDeptCodeMap() {
		return serviceDeptCodeMap;
	}
	/**
	 * @param serviceDeptCodeMap the serviceDeptCodeMap to set
	 */
	public void setServiceDeptCodeMap(ServiceDeptCodeMap serviceDeptCodeMap) {
		this.serviceDeptCodeMap = serviceDeptCodeMap;
	}
	/**
	 * @return the classId
	 */
	@Id
	@Column(name="class_id")
	public String getClassId() {
		return classId;
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(String classId) {
		this.classId = classId;
	}

	/**
	 * @return the targetEndPoint
	 */
	@Column(name="target_end_point")
	public String getTargetEndPoint() {
		return targetEndPoint;
	}
	/**
	 * @param targetEndPoint the targetEndPoint to set
	 */
	public void setTargetEndPoint(String targetEndPoint) {
		this.targetEndPoint = targetEndPoint;
	}
	/**
	 * @return the serviceAuthType
	 */
	@Column(name="service_auth_type")
	public String getServiceAuthType() {
		return serviceAuthType;
	}
	/**
	 * @param serviceAuthType the serviceAuthType to set
	 */
	public void setServiceAuthType(String serviceAuthType) {
		this.serviceAuthType = serviceAuthType;
	}
	
	/**
	 * @return the serviceRequests
	 */
	/*---------sap_enrolled_services:JOIN REMOVED EXERCISE(26-05-23)----------*/
	/*@OneToMany(cascade={CascadeType.PERSIST},mappedBy="sapEnrolledService")
	public Collection<ServiceRequest> getServiceRequests() {
		return serviceRequests;
	}
	*//**
	 * @param serviceRequests the serviceRequests to set
	 *//*
	public void setServiceRequests(Collection<ServiceRequest> serviceRequests) {
		this.serviceRequests = serviceRequests;
	}*/
	/**
	 * @return the serviceType
	 */
	@Column(name="response_mode")
	public String getServiceType() {
		return serviceType;
	}
	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	/**
	 * @return the umang_target_end_point
	 */
	@Column(name="umang_target_end_point")
	public String getUmang_target_end_point() {
		return umang_target_end_point;
	}
	/**
	 * @param umang_target_end_point the umang_target_end_point to set
	 */
	public void setUmang_target_end_point(String umang_target_end_point) {
		this.umang_target_end_point = umang_target_end_point;
	}
	/**
	 * @return the umang_classId
	 */
	
	
	
	
	
	
}
