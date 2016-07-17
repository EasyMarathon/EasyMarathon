package com.EasyMarathon.bean;

public class ConfirmData {
	private String name = "";
	private int eventID;
	private String eventName = "";
	private String IDcard = "";
	private String phone = "";
	private String urgencyName = "";
	private String urgencyPhone = "";
	private String IdentityPic = "";
	private String wechatID ="";
	private Integer athleteID=null;
	
	public ConfirmData(String name,String eventName,String IDcard,
			String phone,String urgencyName,String urgencyPhone,
			String IdentityPic,String wechatID,int eventID,int athleteID){
		this.name=name;
		this.eventName=eventName;
		this.IDcard=IDcard;
		this.phone=phone;
		this.urgencyName=urgencyName;
		this.urgencyPhone=urgencyPhone;
		this.IdentityPic=IdentityPic;
		this.setWechatID(wechatID);
		this.eventID=eventID;
		this.athleteID=athleteID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getIDcard() {
		return IDcard;
	}
	public void setIDcard(String iDcard) {
		IDcard = iDcard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUrgencyName() {
		return urgencyName;
	}
	public void setUrgencyName(String urgencyName) {
		this.urgencyName = urgencyName;
	}
	public String getUrgencyPhone() {
		return urgencyPhone;
	}
	public void setUrgencyPhone(String urgencyPhone) {
		this.urgencyPhone = urgencyPhone;
	}

	public String getIdentityPic() {
		return IdentityPic;
	}

	public void setIdentityPic(String identityPic) {
		IdentityPic = identityPic;
	}

	public String getWechatID() {
		return wechatID;
	}

	public void setWechatID(String wechatID) {
		this.wechatID = wechatID;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public Integer getAthleteID() {
		return athleteID;
	}

	public void setAthleteID(Integer athleteID) {
		this.athleteID = athleteID;
	}
}
