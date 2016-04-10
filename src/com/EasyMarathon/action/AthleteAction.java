package com.EasyMarathon.action;

import java.util.HashMap;
import java.util.Map;

import com.EasyMarathon.bean.SNSUserInfo;
import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.dao.PictureDao.Status;
import com.EasyMarathon.service.AthleteService;
import com.opensymphony.xwork2.ActionContext;

public class AthleteAction {
	SNSUserInfo snsUserInfo;
	int eventID;
	int aID;
	public SNSUserInfo getSnsUserInfo() {
		return snsUserInfo;
	}
	public void setSnsUserInfo(SNSUserInfo snsUserInfo) {
		this.snsUserInfo = snsUserInfo;
	}
	public int getaID() {
		return aID;
	}
	public void setaID(int aID) {
		this.aID = aID;
	}
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String lockInfo()
	{
	
		//首先判断运动员是否报名，没有报名提示
		//报名的
		if(AthleteService.Bind(snsUserInfo.getOpenId(), eventID, aID))
		{
			return "success";
		}
		
		else
			return "false";
	}
	public String findPic()
	{
		ActionContext actionContext = ActionContext.getContext();
		Map<String,Object> session = actionContext.getSession();
		HashMap<String, Status> picutres=new HashMap<String, Status>();
		picutres=AthleteService.returnPicture(aID, eventID);
		if(picutres!=null)
		{
			session.put("pictures", picutres);
			return "success";
		}
		else
			return "false";
	}

}
