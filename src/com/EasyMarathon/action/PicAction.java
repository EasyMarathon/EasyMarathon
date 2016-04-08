package com.EasyMarathon.action;

import java.io.*;

import com.EasyMarathon.bean.MsgBean;
import com.EasyMarathon.bean.PicBean;
import com.EasyMarathon.service.PicService;
import com.opensymphony.xwork2.ActionSupport;


public class PicAction 
{
	private static final long serialVersionUID = 1L;
	private PicBean picture;
	private int eventID;

	public PicBean getPicture() {
		return picture;
	}
	public void setPicture(PicBean picture) {
		this.picture = picture;
	}
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public String uploadPic(){
		//System.out.println(picture.getFile().getAbsolutePath());
		PicService picservice = new PicService();
		
		if(picservice.uploadPicService(picture.getFile(),eventID))
		    return "success";
		else
			return "fail";
	}

	

	
}
