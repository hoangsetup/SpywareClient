package com.hoangdv.spywareclient.Spy;

public class SmsInfo {
	private String phoneNumber;
	private String timeStamp;
	private String bodySms;

	public SmsInfo(String phoneNumber, String timeStamp, String bodySms) {
		this.phoneNumber = phoneNumber;
		this.timeStamp = timeStamp;
		this.bodySms = bodySms;
	}

	public SmsInfo() {

	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getBodySms() {
		return bodySms;
	}

	public void setBodySms(String bodySms) {
		this.bodySms = bodySms;
	}

}
