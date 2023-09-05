package com.ladakh.controller;

public class SMSWorkerThread implements Runnable {

	
private SMSServices smsObj;
    
    public SMSWorkerThread(SMSServices s){
        this.smsObj=s;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		smsObj.sendSingleSMS();

	}

}



