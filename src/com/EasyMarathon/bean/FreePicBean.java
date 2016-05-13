package com.EasyMarathon.bean;

public class FreePicBean
{
	private int eventID = -1;
	private String wechatID = "";
	private String picID = "";
	private int downloadCnt = -1;
	private long upTime = -1;
	private String userName = "";
	private String eventName = "";
	private String headImgUrl =""; // 用户头像链接
	
	public int getEventID()
	{
		return eventID;
	}

	public void setEventID(int eventID)
	{
		this.eventID = eventID;
	}

	public String getWechatID()
	{
		return wechatID;
	}

	public void setWechatID(String wechatID)
	{
		this.wechatID = wechatID;
	}

	public String getPicID()
	{
		return picID;
	}

	public void setPicID(String picID)
	{
		this.picID = picID;
	}

	public int getDownloadCnt()
	{
		return downloadCnt;
	}

	public void setDownloadCnt(int downloadCnt)
	{
		this.downloadCnt = downloadCnt;
	}

	public long getUpTime()
	{
		return upTime;
	}

	public void setUpTime(long upTime)
	{
		this.upTime = upTime;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	
	
}
