package com.ladakh.controller;

/**
 * eForms Portal and Back-Office for Jammu & Kashmir State
 */


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletContext;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;




/**
 * TODO : Write a short summary of this type
 *
 * @author Aniket
 *
 */
public class SMSServices {
	
	static ExecutorService executor = Executors.newFixedThreadPool(5);
	String username;
	String password;
	String message;
	String senderId;
	String mobileNumber;
	String secureKey;
	String targetURL;
	String smsServiceType;
	String pushToggle;
	String templateid;
	
	
	

		
	public void sendSingleSMS(){
		
		System.out.println("Inside sendSingleSMS");
//		System.out.println(this);

		
		if(pushToggle.equalsIgnoreCase("FALSE")){
			System.out.println("SMS toggle is switched off");
		}else{
	
			String response = null;
			String encryptedPassword;
			String generatedhashKey = hashGenerator(username, senderId, message, secureKey);
			HttpClient client = new HttpClient();
			//PostMethod method = new PostMethod("http://msdgweb.mgov.gov.in/esms/sendsmsrequest");
			PostMethod method = new PostMethod(targetURL);

			try {
				
				
				
				System.out.println("templateid is"+templateid);
				encryptedPassword = MD5(password);
				method.addParameter(new NameValuePair("mobileno",mobileNumber));
				method.addParameter(new NameValuePair("senderid",senderId));
				method.addParameter(new NameValuePair("content",message));
				//method.addParameter(new NameValuePair("smsservicetype","singlemsg"));
				method.addParameter(new NameValuePair("smsservicetype",smsServiceType));
				method.addParameter(new NameValuePair("username",username));
				method.addParameter(new NameValuePair("password",encryptedPassword));
				method.addParameter(new NameValuePair("key",generatedhashKey));
//				method.addParameter(new NameValuePair("templateid","1007956210157621640"));
				method.addParameter(new NameValuePair("templateid",templateid));
				System.out.println("After Adding paramiters in PostMethod Method");
				client.executeMethod(method);
				System.out.println("After executeMethod");
				response = method.getResponseBodyAsString();
				System.out.println("SMS Sent Successfully!!");
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			System.out.println("Response received :"+response);
			System.out.println("End sendSingleSMS");
		}
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


	
	
	
	
}
