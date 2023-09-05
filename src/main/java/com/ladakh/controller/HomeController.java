package com.ladakh.controller;

import java.io.UnsupportedEncodingException;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ladakh.service.HomeService;



import com.ladakh.dao.HomeDao;
import com.ladakh.pojos.PrefillForm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.app.pojos.UmangRequests;
import com.google.gson.Gson;
import com.ladakh.pojos.Citizen;
import com.ladakh.pojos.DomMastErrorCode;
import com.ladakh.pojos.DomicileApiErrorCodes;

@Controller
@RequestMapping("/service")
public class HomeController {

	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private HomeService homeService;

	@Autowired
	private HomeDao homeDao;

	@PostConstruct
	public void init() {
		logger.info("in init of " + getClass().getName() + " " + homeService);
	}

	@PersistenceContext
	private EntityManager manager;

	@RequestMapping(value = "/home")
	@ResponseBody
	public String homePage() {
		logger.info("inside home api");
		return "hello, project started successfully!!!";
	}

	/* ===================== prefillForm API START===================== */

	@ResponseBody
	@RequestMapping(value = "/prefillForm", method = RequestMethod.POST)
//	public String prefillForm(@RequestBody Map<String, Object> details) throws JSONException {
	public String prefillForm(@RequestBody Map<String, Object> details) throws JSONException {

		logger.info("inside controller of prefillForm ");
		Map<String, Object> jsonReqData = new HashMap<String, Object>(details);

		String applicationId = (String) jsonReqData.get("applicationId");
		logger.info("applicationId id is " + applicationId);

		// To check the domicile status of the applicationid
		String status = homeService.checkStatus(applicationId);
		logger.info("status of applicationId from Backoffice  is - " + status);

		String response = null;
		if (!(status == null)) {
			if (status.equalsIgnoreCase("esigned: f, Appstatus: RECEIVED")) {
				logger.info("Domicile not Issued Case");
				String responseMessage = "Application has been received";

				// Create the desired output JSON structure
				ObjectMapper objectMapper = new ObjectMapper();
				ObjectNode outputNode = objectMapper.createObjectNode();
				outputNode.put("Status", "Failed");
				outputNode.put("Errorcode", "ER001");
				outputNode.put("Remarks", responseMessage);

				// Convert the output to JSON string
				String outputJson = null;
				try {
//					outputJson = objectMapper.writeValueAsString(outputNode);
					outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(outputJson);
				response = outputJson.toString();
				return response;

			} else if (status.equalsIgnoreCase("esigned: f, Appstatus: RESUBMIT")) {
				logger.info("Domicile not Issued Case");
				String responseMessage = "Application has been resubmitted.";

				// Create the desired output JSON structure
				ObjectMapper objectMapper = new ObjectMapper();
				ObjectNode outputNode = objectMapper.createObjectNode();
				outputNode.put("Status", "Failed");
				outputNode.put("Errorcode", "ER002");
				outputNode.put("Remarks", responseMessage);

				// Convert the output to JSON string
				String outputJson = null;
				try {
//					outputJson = objectMapper.writeValueAsString(outputNode);
					outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(outputJson);
				response = outputJson.toString();
				return response;

			} else if (status.equalsIgnoreCase("esigned: f, Appstatus: REJECTED")) {
				logger.info("Domicile not Issued Case");
				String responseMessage = "Application has been Rejected.";

				// Create the desired output JSON structure
				ObjectMapper objectMapper = new ObjectMapper();
				ObjectNode outputNode = objectMapper.createObjectNode();
				outputNode.put("Status", "Failed");
				outputNode.put("Errorcode", "ER003");
				outputNode.put("Remarks", responseMessage);

				// Convert the output to JSON string
				String outputJson = null;
				try {
//					outputJson = objectMapper.writeValueAsString(outputNode);
					outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(outputJson);
				response = outputJson.toString();
				return response;

			}else if (status.equalsIgnoreCase("esigned: f, Appstatus: COMPLETED")) {
				logger.info("Domicile not Issued Case");
				String responseMessage = "Application is in process.";

				// Create the desired output JSON structure
				ObjectMapper objectMapper = new ObjectMapper();
				ObjectNode outputNode = objectMapper.createObjectNode();
				outputNode.put("Status", "Failed");
				outputNode.put("Errorcode", "ER004");
				outputNode.put("Remarks", responseMessage);

				String outputJson = null;
				try {
//					outputJson = objectMapper.writeValueAsString(outputNode);
					outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(outputJson);
				response = outputJson.toString();
				return response;

			}
						
		} else {
			System.out.println("inside else");
			String responseMessage = "Internal system error.";

			// Create the desired output JSON structure
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode outputNode = objectMapper.createObjectNode();
			outputNode.put("Status", "Failed");
			outputNode.put("Errorcode", "ER005");
			outputNode.put("Remarks", responseMessage);

			String outputJson = null;
			try {
//				outputJson = objectMapper.writeValueAsString(outputNode);
				outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(outputJson);
			response = outputJson.toString();
			return response;

		}

		String citizenId = homeService.getCitizenId(applicationId);
		logger.info("citizen id is " + citizenId);

		Citizen citizen = new Citizen();

		logger.info("Going to make call to getContactNo()...");
		citizen = homeDao.getContactNo(citizenId);

		String mobileNo = citizen.getCellNo();
		logger.info("mobileNo::::" + mobileNo);
	
		
		if (mobileNo == null ) {
			logger.info("Mobile no is NA");
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode outputNode = objectMapper.createObjectNode();
			outputNode.put("Remarks", "Mobile no is NA");
			
			try {
//				String outputJson = objectMapper.writeValueAsString(outputNode);
				String outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
				 return outputJson;
			} catch (JsonProcessingException e) {
				logger.info("Inside JsonProcessingException");
				e.printStackTrace();
			}
				
				 
		}
		
		String name = citizen.getName();
		logger.info("Name ::::" + name);

		int randomPin = (int) (Math.random() * 9000) + 1000;
		String otp = String.valueOf(randomPin);
		logger.info("OTP is here ::" + otp);
		
		int intotp = Integer.parseInt(otp);
		logger.info("intotp -"+intotp);
		
		Date d = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(d);
		logger.info("today is " + d.toString());
		 	
		try {
			logger.info("----------Going to send OTP SMS -------");
			DomicileApiErrorCodes  domotpdata = new DomicileApiErrorCodes();
			DomMastErrorCode dommaserrcode = new DomMastErrorCode();
			
			 String newTimestamp = null;	     
			 String currTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
  		     System.out.println("Current Timestamp is " + currTimestamp);

  		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  		        Date currentDate = null;
  		        Date tokenExpiry = null;
  		        try {
  		            currentDate = dateFormat.parse(currTimestamp);
  		            System.out.println("currentDate "+currentDate);
  		        } catch (Exception e) {
  		            e.printStackTrace();
  		        }

  		        if (currentDate != null) {
  		            Calendar calendar = Calendar.getInstance();
  		            calendar.setTime(currentDate);
  		            calendar.add(Calendar.MINUTE, 2);
  		            tokenExpiry = calendar.getTime();
  		            System.out.println("tokenExpiry "+tokenExpiry);
  		             newTimestamp = dateFormat.format(tokenExpiry);
  		            System.out.println("Timestamp after adding 2 minutes: " + newTimestamp);
  		        }
			
			String smsBody = "Dear " + name + ", OTP to login to e-Service Portal is " + otp
					+ ". Do not share with anyone-JKGovt.";
			System.out.println("smsBody --" + smsBody);

			String templateid = "1007481338174428853";			

			// SMS Gateway URL
			String smsUrl = "https://msdgweb.mgov.gov.in/esms/sendsmsrequestDLT";

			URL url = new URL(smsUrl);
			String username = "jkitd-stateportal";
			String password = "Stateportal@123";
			String senderId = "JKGOVT";
			String secureKey = "a91a7295-3488-4364-9d86-9af8c7df66fc";
			String encryptedPassword;
			String smsServiceType="singlemsg";
			
			String generatedhashKey = hashGenerator(username, senderId, smsBody, secureKey);
			System.out.println("Generated Hash Key : "+generatedhashKey);
			HttpClient client = new HttpClient();
			System.out.println("Scheme Register");
			PostMethod method = new PostMethod(smsUrl);
			
//			String expiryDate = domotpdata.getOtpexpiry_time();
			 String otpvalidity = homeDao.otpvalidity(citizenId);
			System.out.println("expiryDate "+otpvalidity);
			Date date = new Date();
			try {
	             date = dateFormat.parse(otpvalidity);
	            System.out.println(date);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			
			if (currentDate.compareTo(date) <= 0) {
				System.out.println("Your OTP is still valid");
			    // Create the desired output JSON structure
			    ObjectMapper objectMapper = new ObjectMapper();
			    ObjectNode outputNode = objectMapper.createObjectNode();
			    outputNode.put("Status", "Success");
			    outputNode.put("Remarks", "Your OTP is still valid.");
			    try {
			        String outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
			        return outputJson;
			    } catch (JsonProcessingException e) {
			    	System.out.println("Inside JsonProcessingException");
			        e.printStackTrace();
			    }
			}

		
			encryptedPassword = MD5(password);
			logger.info("encrypted pass :  "+encryptedPassword);
			method.addParameter(new NameValuePair("mobileno",mobileNo));
			method.addParameter(new NameValuePair("senderid",senderId));
			method.addParameter(new NameValuePair("content",smsBody));
			method.addParameter(new NameValuePair("smsservicetype",smsServiceType));
			method.addParameter(new NameValuePair("username",username));
			method.addParameter(new NameValuePair("password",encryptedPassword));
			method.addParameter(new NameValuePair("key",generatedhashKey));
			method.addParameter(new NameValuePair("templateid",templateid));
			System.out.println("After Adding paramiters in PostMethod Method");
			client.executeMethod(method);
			System.out.println("After execute Method");
			response = method.getResponseBodyAsString();
			logger.info("SMS Sent Successfully!! : ");
			logger.info("Response received :"+response);
			logger.info("End sendSingleSMS");
			
			String statusCode = null;
	        Pattern pattern = Pattern.compile("\\d+");
	        Matcher matcher = pattern.matcher(response);
	        if (matcher.find()) {
	             statusCode = matcher.group();
	             logger.info("statusCode: " + statusCode);
	        } else {
	        	logger.info("No number found in the input string.");
	        }
	        
			logger.info("response "+response); 
			

			
			//Going to persist data in table
			
			domotpdata.setCzn_id(citizenId);
			domotpdata.setAppplication_id(applicationId);
			domotpdata.setMobileNo(mobileNo);
			domotpdata.setOtp(intotp);
			domotpdata.setResponse_msg(response);
			domotpdata.setOtpexpiry_time(newTimestamp);
			
			logger.info("test 1");
			dommaserrcode.setStatus_code(statusCode);
			dommaserrcode.setStatus_details(response);
			logger.info("test 1");
			domotpdata.setStatus_code(dommaserrcode);
			
			
			boolean updatedata = homeDao.updateDomicileApiErrorCodes(domotpdata);
			if (updatedata == true){
				logger.info("Updated details Successfully in DomicileApiErrorCodes");
			}else{
				logger.info("Not updated details in DomicileApiErrorCodes ");
				
			}
			
			if (statusCode.contains("402")) {
				ObjectMapper objectMapper = new ObjectMapper();
				ObjectNode outputNode = objectMapper.createObjectNode();
				outputNode.put("Status", "Success");
				outputNode.put("Remarks", "OTP send successfully to registered mobile no."
						+ "Note:Otp valid for 2 min only.");
				try {
//					String outputJson = objectMapper.writeValueAsString(outputNode);
					String outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
					 return outputJson;
				} catch (JsonProcessingException e) {
					logger.info("Inside JsonProcessingException");
					e.printStackTrace();
				}
	        	
	        }
			
//				else if (statusCode.contains("406")) {
//				ObjectMapper objectMapper = new ObjectMapper();
//				ObjectNode outputNode = objectMapper.createObjectNode();
//				outputNode.put("Remarks", "Invalid or Duplicate numbers");
//				try {
////					String outputJson = objectMapper.writeValueAsString(outputNode);
//					String outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
//					 return outputJson;
//				} catch (JsonProcessingException e) {
//					logger.info("Inside JsonProcessingException");
//					e.printStackTrace();
//				}
//	        	
//	        }
				
			JSONObject jsonResponse = new JSONObject();
					
			jsonResponse.put("Remark", response.trim());
		
			return jsonResponse.toString();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;


	}

	/* ===================== prefillForm API END===================== */

	

	
		@ResponseBody
		@RequestMapping(value = "/validateOtp", method = RequestMethod.POST)
		public String validateOtp(@RequestBody Map<String, Object> details) throws JSONException {
		
		
		logger.info("inside controller of prefillForm ");
		Map<String, Object> jsonReqData = new HashMap<String, Object>(details);

		String applicationId = (String) jsonReqData.get("applicationId");
		logger.info("applicationId id is " + applicationId);

		String citizenId = homeService.getCitizenId(applicationId);
		logger.info("citizen id is " + citizenId);
		 if(citizenId == null) {
				logger.info("citizenId is null");
				ObjectMapper objectMapper = new ObjectMapper();
				ObjectNode outputNode = objectMapper.createObjectNode();
				outputNode.put("Remarks", "Invalid Application Id");
				try {
//					String outputJson = objectMapper.writeValueAsString(outputNode);
					String outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
					 return outputJson;
				} catch (JsonProcessingException e) {
					logger.info("Inside JsonProcessingException");
					e.printStackTrace();
				}
			}
		
		 String currTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
//		 System.out.println("Current Timestamp is " + currTimestamp);

		 // Parse the current timestamp string to a Date object
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date currentDate = null;
//		 Date tokenExpiry = null;
		    try {
		            currentDate = dateFormat.parse(currTimestamp);
		            System.out.println("currentDate timestamp "+currentDate);
		        } catch (Exception e) {
  		            e.printStackTrace();
  		        }
		        	
		 String otpvalidity = homeDao.otpvalidity(citizenId);
		 if(otpvalidity == null) {
			logger.info("Please Enter proper Application Id");
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode outputNode = objectMapper.createObjectNode();
			outputNode.put("Remarks", "Invalid Application Id");
			try {
//				String outputJson = objectMapper.writeValueAsString(outputNode);
				String outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
				 return outputJson;
			} catch (JsonProcessingException e) {
				logger.info("Inside JsonProcessingException");
				e.printStackTrace();
			}
		}
		
		Date date = new Date();
        try {
            date = dateFormat.parse(otpvalidity);
            System.out.println("Otp expired timestamp " + date);
        } catch (ParseException ep) {
            System.err.println("Error parsing date: " + ep.getMessage());
        }
		if ( currentDate.compareTo(date) > 0 ){
				logger.info(" OTP  has expired");
				// Create the desired output JSON structure
				ObjectMapper objectMapper = new ObjectMapper();
				ObjectNode outputNode = objectMapper.createObjectNode();
//				outputNode.put("Status", "Failed");
				outputNode.put("Remarks", "OTP  has expired");
				try {
//					String outputJson = objectMapper.writeValueAsString(outputNode);
					String outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
					 return outputJson;
				} catch (JsonProcessingException e) {
					logger.info("Inside JsonProcessingException");
					e.printStackTrace();
				}
		}		
		String jsonString = "";
		String finals = null;
		try {
			List<PrefillForm> prefill = new ArrayList<>();
			prefill = homeService.prefillForm(citizenId);
			logger.info("prefill " + prefill);
			if (!prefill.isEmpty()) {
				logger.info("json = " + new Gson().toJson(prefill));
				jsonString = new Gson().toJson(prefill);
				String newString = jsonString.substring(1, jsonString.length() - 1);
				logger.info("newString is " + newString);
	
	
				// Parse the input JSON
				ObjectMapper objectMapper = new ObjectMapper();
				ObjectNode inputNode = objectMapper.readValue(newString, ObjectNode.class);
	
				// Create the desired output JSON structure
				ObjectNode outputNode = objectMapper.createObjectNode();
				outputNode.put("Status", "Success");
				outputNode.put("Remarks", "Domicile Issued Case.");
				outputNode.set("Result", inputNode);
	
				// Convert the output to JSON string
//				String outputJson = objectMapper.writeValueAsString(outputNode);
				String outputJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
				System.out.println(outputJson);
	
				finals = outputJson.toString();
				return finals;
			} else {
				JSONObject obj2 = new JSONObject();
				obj2.put("Status", "Failed");
				obj2.put("Description", "Citizen-id not found. Please register yourself");
				return obj2.toString();
	
			}
	
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		JSONObject obj3 = new JSONObject();
		obj3.put("Status", "Failed");
		obj3.put("Description", "Some error occured.Please try again ");
		return obj3.toString();
	
	
		}
		
		private static String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException{
				
				MessageDigest md;
				md = MessageDigest.getInstance("SHA-1");
				byte[] md5 = new byte[64];
				md.update(text.getBytes("iso-8859-1"), 0, text.length());
				md5 = md.digest();
			 
				return convertedToHex(md5);
			 }
			
			private static String convertedToHex(byte[] data){
				
				StringBuffer buf = new StringBuffer();
				for (int i = 0; i < data.length; i++)
				{
					int halfOfByte = (data[i] >>> 4) & 0x0F;
					int twoHalfBytes = 0;
					do
					{
						if ((0 <= halfOfByte) && (halfOfByte <= 9))
						{
							buf.append( (char) ('0' + halfOfByte) );
						}
						else
						{
							buf.append( (char) ('a' + (halfOfByte - 10)) );
						}
						halfOfByte = data[i] & 0x0F;
					}
					while(twoHalfBytes++ < 1);
				}
				return buf.toString();
			 }
			
			
			protected String hashGenerator(String userName, String senderId,String content, String secureKey) {

				StringBuffer finalString=new StringBuffer();

				finalString.append(userName.trim()).append(senderId.trim()).append(content.trim()).append(secureKey.trim());

				String hashGen=finalString.toString();
				StringBuffer sb = null;
				MessageDigest md;
				try {
					md = MessageDigest.getInstance("SHA-512");
					md.update(hashGen.getBytes());
					byte byteData[] = md.digest();
					//convert the byte to hex format method 1
					sb = new StringBuffer();
					for (int i = 0; i < byteData.length; i++) {
						sb.append(Integer.toString((byteData[i] & 0xff) + 0x100,16).substring(1));
					}
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				return sb.toString();
			}
	
}
