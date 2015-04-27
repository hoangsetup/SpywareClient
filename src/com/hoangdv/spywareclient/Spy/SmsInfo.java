package com.hoangdv.spywareclient.Spy;

public class SmsInfo {
	private String phoneNumber;
	private String timeStamp;
	private String bodySms;
	private String bType;

	public SmsInfo(String phoneNumber, String timeStamp, String bodySms) {
		this.phoneNumber = phoneNumber;
		this.timeStamp = timeStamp;
		this.bodySms = bodySms;
		setbType("1");
	}

	public SmsInfo() {
		setbType("1");
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

	public String getbType() {
		return bType;
	}

	public void setbType(String bType) {
		this.bType = bType;
	}

}
