package com.EasyMarathon.action;

import com.EasyMarathon.bean.PicBean;
import com.EasyMarathon.service.PicService;

public class PicAction
{
	private PicBean picture;
	private String eventID;

	public PicBean getPicture()
	{
		return picture;
	}

	public void setPicture(PicBean picture)
	{
		this.picture = picture;
	}

	public String getEventID()
	{
		return eventID;
	}

	public void setEventID(String eventID)
	{
		this.eventID = eventID;
	}

	public String uploadPic()
	{
		// System.out.println(picture.getFile().getAbsolutePath());
		PicService picservice = new PicService();

		if (picservice.uploadPicService(picture.getFile(), eventID))
			return "success";
		else
			return "fail";
	}

	public String uploadPicforUser()
	{
		// System.out.println(picture.getFile().getAbsolutePath());
		PicService picservice = new PicService();

		if (picservice.uploadPicforUserService(picture.getFile(), eventID))
			return "success";
		else
			return "fail";
	}

}
