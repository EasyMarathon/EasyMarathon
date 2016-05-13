package com.EasyMarathon.action;

import java.util.ArrayList;
import java.util.Map;

import com.EasyMarathon.bean.PicBean;
import com.EasyMarathon.bean.SNSUserInfo;
import com.EasyMarathon.service.AthleteService;
import com.opensymphony.xwork2.ActionContext;

public class AthleteAction
{
	SNSUserInfo snsUserInfo;
	int eventID;
	String aID;

	public SNSUserInfo getSnsUserInfo()
	{
		return snsUserInfo;
	}

	public void setSnsUserInfo(SNSUserInfo snsUserInfo)
	{
		this.snsUserInfo = snsUserInfo;
	}

	public String getAID()
	{
		return aID;
	}

	public void setAID(String aID)
	{
		this.aID = aID;
	}

	public int getEventID()
	{
		return eventID;
	}

	public void setEventID(int eventID)
	{
		this.eventID = eventID;
	}

	public String lockInfo()
	{

		// 首先判断运动员是否报名，没有报名提示
		// 报名的
		int athleteID=Integer.parseInt(aID);
		System.out.println(eventID);
		System.out.println("运动员编号："+aID);
		System.out.println(snsUserInfo.getOpenId());
		if (AthleteService.Bind(snsUserInfo.getOpenId(), eventID, athleteID))
		{
			return "success";
		}

		else
			return "fail";
	}
/*
	public String findPic()
	{
		int athleteID=Integer.parseInt(aID);
		ArrayList<PicBean> pictures = new ArrayList<>();
		pictures = AthleteService.returnPicture(athleteID, eventID);
		System.out.println(pictures.size());
		if (pictures.size()!=0)
		{
			
			return "success";
		}
		else
			return "fail";
	}
*/
}
