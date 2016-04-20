package com.EasyMarathon.action;

import java.applet.AppletContext;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.EasyMarathon.bean.PicBean;
import com.EasyMarathon.service.PicService;
import com.opensymphony.xwork2.ActionContext;

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
		System.out.println("�����û���Ƭ�ϴ�������");
		System.out.println("΢�źţ�"+wechatID);
		System.out.println("���ºţ�"+eventID);
		System.out.println(picture.getPicStatus());
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			ServletInputStream in = request.getInputStream();
			int index=-1;
			byte[] b=new byte[1024];
			System.out.println("��ӡrequest");
			while((index = in.read(b))!=-1){
				System.out.println(b.toString());
			}
			System.out.println("finish");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		
		if (picservice.uploadPicforUserService(picture.getFile(), eventID,wechatID))
			return "success";
		else
			return "fail";
	}

}
