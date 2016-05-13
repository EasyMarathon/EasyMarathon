package com.EasyMarathon.action;

import java.applet.AppletContext;
import java.io.IOException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.EasyMarathon.bean.PicBean;
import com.EasyMarathon.bean.Token;
import com.EasyMarathon.bean.WeixinOauth2Token;
import com.EasyMarathon.service.PicService;
import com.opensymphony.xwork2.ActionContext;
import com.EasyMarathon.util.AdvancedUtil;
import com.EasyMarathon.util.CommonUtil;

public class PicAction
{
	private PicBean picture;
	private String eventID;
	private String wechatID;

	public String getWechatID() {
		return wechatID;
	}

	public void setWechatID(String wechatID) {
		this.wechatID = wechatID;
	}

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

/*	public String uploadPic()
	{
		// System.out.println(picture.getFile().getAbsolutePath());
		PicService picservice = new PicService();

		if (picservice.uploadPicService(picture.getFile(), eventID))
			return "success";
		else
			return "fail";
	}*/

}
