package com.ladakh.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {
	public String getEncryptedPwd(String pwd){		
		String encryptedPwd = "";		
		byte pwdBytes[] = pwd.getBytes();		
		try{
			
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(pwdBytes);
			byte messageDigest[] = algorithm.digest();
			StringBuffer hexString = new StringBuffer();
			
			for(int i = 0 ; i < messageDigest.length ; i++){
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if(hex.length() == 1){hexString.append(0);}
				hexString.append(hex);				
			}			
			encryptedPwd = hexString.toString();
		}
		catch(NoSuchAlgorithmException nse){System.out.println(nse);}
		return encryptedPwd;
	}
//	public static void main(String a[]){
//		PasswordEncryptor pe = new PasswordEncryptor();
//		System.out.println(pe.getEncryptedPwd("tr_csc123#"));
//	}

}
