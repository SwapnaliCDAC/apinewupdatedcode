package com.ladakh.dao;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.ladakh.pojos.PrefillForm;



import com.ladakh.pojos.Citizen;
import com.ladakh.pojos.DomicileApiErrorCodes;


@Repository
@Transactional
public class HomeDaoImpl implements HomeDao {

	Logger logger = LoggerFactory.getLogger(HomeDaoImpl.class);

	@PersistenceContext
	private EntityManager manager;


	
	@Override
	public String getCitizenId(String applicationId) {

		logger.info("Inside getCitizenId::");
		 String citizenId = null;
		try {
			
			Query query = manager
					.createNativeQuery("select czn_id from sap_requests WHERE application_id = ? ");
			query.setParameter(1, applicationId);
			
			logger.info("Returning from getApplicationIDss..");

			 citizenId = (String) query.getSingleResult();
             logger.info("Returning citizen ID: " + citizenId);
             return citizenId;

		} catch (NoResultException e) {
			logger.info("Exception Occurred...inside getApplicationID()..Couldn't retrieve application ID details!!");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception Occurred...inside getApplicationID()..Couldn't retrieve application ID details!!");
			return null;
		}

	}

	
	
	@Override
	public Citizen getContactNo(String citizenId1) {
		
		logger.info("Inside getCitizenId::");
		Citizen citizen = new Citizen();
		logger.info("citizenId1 "+citizenId1);
//		 String mobileNo = null;
		try {
			logger.info("Going to make a query....");
			
			Query query = manager
					.createQuery("from com.ladakh.pojos.Citizen a where a.cznId=:citizenId1");
			query.setParameter("citizenId1", citizenId1);
			citizen = (Citizen) query.getSingleResult();			
			logger.info("citizen printed value is " + citizen.toString());

			if(citizen == null) {
				logger.info("No such service request available.");
				return null;
			}else {

				logger.info("Returning from getCitizenId()....");
				return citizen;
			}
		} catch (NoResultException e) {
			logger.info("Exception Occurred...inside getContactNo()..Couldn't retrieve application ID details!!");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception Occurred...inside getContactNo()..Couldn't retrieve application ID details!!");
			return null;
		}
	}
	

	@Override
	public List<PrefillForm> prefillForm(String citizenId) {
		logger.info("inside prefillForm() of  UserDaoImpl1  ");

		Citizen czn = null;
		List<PrefillForm> prefill = new ArrayList<>();

		Query query = manager.createQuery("from com.ladakh.pojos.Citizen a where a.cznId=:citizenId");
		query.setParameter("citizenId", citizenId);
		czn = (Citizen) query.getSingleResult();

		PrefillForm prefillForm = new PrefillForm();

//		prefillForm.setCznId(czn.getCznId());
		prefillForm.setName(czn.getName());
		prefillForm.setParentagename(czn.getParentagename());
		prefillForm.setPhoneNumber(czn.getPhoneNumber());
		prefillForm.setContactNumber(czn.getCellNo());

//		prefillForm.setDOB(czn.getDob());
		
		Date dateofbirth = czn.getDob();
		logger.info("dateofbirth "+dateofbirth);
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDOB = outputFormat.format(dateofbirth);
        System.out.println("formattedDOBiii "+formattedDOB);
        
        prefillForm.setDOB(formattedDOB);
		
//		prefillForm.setGender(czn.getGender());
//		prefillForm.setEmail(czn.getEmail());
//		prefillForm.setUid(czn.getUid());

//		prefillForm.setCurAdddresLine1(czn.getCurAdddresLine1());
//		prefillForm.setCurAddressLine2(czn.getCurAddressLine2());
//		prefillForm.setCurDistrictFormId(czn.getCurDistrictFormId());
//		prefillForm.setCurPincode(czn.getCurPincode());
//		prefillForm.setCurTehsilFormId(czn.getCurTehsilFormId());

//		prefillForm.setPrmAdddresLine1(czn.getPrmAdddresLine1());
//		prefillForm.setPrmAddressLine2(czn.getPrmAddressLine2());
//		prefillForm.setPermanentDistrict(czn.getPrmDistrictFormId());	
		
		String districtCode = czn.getPrmDistrictFormId();
		logger.info("districtCode "+districtCode);
		String districtName = getDistrictNameForCode(districtCode);
		logger.info("districtName "+districtName);
        if (districtName != null) {
          
            prefillForm.setPermanentDistrict(districtName);
        } else {
            logger.info("District name not found for code: " + districtCode);
        }
        
		prefillForm.setPermanentPincode(czn.getPrmPincode());
		
//		prefillForm.setPermanentTehsil(czn.getPrmTehsilFormId());
		String tehsilCode = czn.getPrmTehsilFormId();
		logger.info("tehsilCode "+tehsilCode);
		String tehsilName = getTehsilNameForCode(tehsilCode);
		logger.info("tehsilName "+tehsilName);
		 if (tehsilName != null) {
	          
	            prefillForm.setPermanentTehsil(tehsilName);
	        } else {
	            logger.info("Tehsil name not found for code: " + tehsilName);
	        }		



		prefill.add(prefillForm);

		return prefill;
	}

	private String getTehsilNameForCode(String tehsilCode) {
		
		logger.info("Inside getTehsilNameForCode");

		Connection con = null;
		Statement stmt = null;
		String tehsilName = null;
		
		try {
			logger.info("tehsilCode - "+tehsilCode);
						
			con=DBUtils.fetchDBConnection();
			
			stmt = con.createStatement();
			String query="select area_name from jksp_area where  code = '" + tehsilCode + "'";
			ResultSet rs = stmt.executeQuery(query.toString());
			
			while (rs.next()) {
				tehsilName = rs.getString("area_name");
	            
	        }			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
		return tehsilName;
	}

	private String getDistrictNameForCode(String districtCode) {
		logger.info("Inside getDistrictNameForCode");

		Connection con = null;
		Statement stmt = null;
		String districtName = null;
		
		try {
			logger.info("districtCode - "+districtCode);
						
			con=DBUtils.fetchDBConnection();
			
			stmt = con.createStatement();
			String query="select area_name from jksp_area where  code = '" + districtCode + "'";
			ResultSet rs = stmt.executeQuery(query.toString());
			
			while (rs.next()) {
				districtName = rs.getString("area_name");
	            
	        }			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
		return districtName;
	}
		

	
	public String getEncryptedPwd(String pwd) {
		String encryptedPwd = "";
		byte pwdBytes[] = pwd.getBytes();
		try {

			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(pwdBytes);
			byte messageDigest[] = algorithm.digest();
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1) {
					hexString.append(0);
				}
				hexString.append(hex);
			}
			encryptedPwd = hexString.toString();
		} catch (NoSuchAlgorithmException nse) {
			System.out.println(nse);
		}
		return encryptedPwd;
	}

	@Override
	public String checkStatus(String applicationId) {
		logger.info("inside checkStatus() of  UserDaoImpl1  ");
		Connection con = null;
		Statement stmt = null;
		String status = null;
		String appStatus = null;
		
		try {
			logger.info("applicationId - "+applicationId);
						
			con=DBUtils.fetchDBConnection();
			
			stmt = con.createStatement();
			String query="select status,esigned from  jksp_dom_appdata where  application_id = '" + applicationId + "'";
			ResultSet rs = stmt.executeQuery(query.toString());
			
			while (rs.next()) {
	            status = rs.getString("esigned");
	            appStatus = rs.getString("status");
	            status = "esigned: " + status + ", Appstatus: " + appStatus;
	            
	        }			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
		return status;
	}

	@Override
	public boolean updateDomicileApiErrorCodes(DomicileApiErrorCodes domotpdata) {
		// TODO Auto-generated method stub
		
			logger.info("Inside updateDomicileApiErrorCodes::");
			manager.merge(domotpdata);
			logger.info("Returning from updateDomicileApiErrorCodes::");
			return true;
		
	}

	@Override
	public String otpvalidity(String citizenId) {
		// TODO Auto-generated method stub
		logger.info("Inside otpvalidity::");
		 String otpExp = null;
		try {
			
			Query query = manager
					.createNativeQuery("select otpexpiry_time from dom_otp_data WHERE czn_id = ? ");
			query.setParameter(1, citizenId);
			
			logger.info("Returning from otpvalidity..");

			otpExp = (String) query.getSingleResult();
            logger.info("Returning citizen ID: " + citizenId);
            return otpExp;

		} catch (NoResultException e) {
			logger.info("Exception Occurred...inside otpvalidity()..Couldn't retrieve application ID details!!");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception Occurred...inside otpvalidity()..Couldn't retrieve application ID details!!");
			return null;
		}
	}
}
