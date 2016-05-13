package com.EasyMarathon.bean;

import java.io.File;

public class PicBean
{
	public static enum Status
	{
		onSale, hasBuy;
	}
	private File file;
	private String picID;
	private String author = "";
	private int Price = 0;
	private Status picStatus;

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public String getPicID()
	{
		return picID;
	}

	public void setPicID(String picID)
	{
		this.picID = picID;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public int getPrice()
	{
		return Price;
	}

	public void setPrice(int price)
	{
		Price = price;
	}

	public Status getPicStatus()
	{
		return picStatus;
	}

	public void setPicStatus(Status picStatus)
	{
		this.picStatus = picStatus;
	}

	public void setPicStatus(int picStatus)
	{
		this.picStatus = Status.values()[picStatus];
	}
}
