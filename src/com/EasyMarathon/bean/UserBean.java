package com.EasyMarathon.bean;

import java.sql.Date;

public class UserBean
{
	public static enum Gender
	{
		male,female;
	}
	private String wechatID = "";
	private String userName = "";
	private String celphone = "";
	private String email = "";
	private Date birth;
	private Gender gender;
	
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public Date getBirth()
	{
		return birth;
	}
	public void setBirth(Date birth)
	{
		this.birth = birth;
	}
	public Gender getGender()
	{
		return gender;
	}
	public void setGender(int gender)
	{
		this.gender = Gender.values()[gender];
	}
	public String getWechatID()
	{
		return wechatID;
	}
	public void setWechatID(String wechatID)
	{
		this.wechatID = wechatID;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getCelphone()
	{
		return celphone;
	}
	public void setCelphone(String celphone)
	{
		this.celphone = celphone;
	}
	
}
