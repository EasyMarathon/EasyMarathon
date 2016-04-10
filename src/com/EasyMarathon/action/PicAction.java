package com.EasyMarathon.action;

import java.io.*;

import com.EasyMarathon.bean.MsgBean;
import com.EasyMarathon.service.PicService;
import com.opensymphony.xwork2.ActionSupport;


public class PicAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	File picture;
	int eventID;
	public File getPicture() {
		return picture;
	}
	public void setPicture(File picture) {
		this.picture = picture;
	}
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public String uploadPic() throws IOException{
		PicService picservice = new PicService();
		if(picservice.uploadPicService(picture,eventID))
		    return "success";
		else
			return "fail";
	}

	

	
}
