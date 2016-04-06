package com.EasyMarathon.test;

public class EasyXml
{
	private StringBuffer data = null;
	
	public EasyXml(int type)
	{
		switch(type)
		{
		case 1:
			data = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		default:
			data = new StringBuffer("<xml>\n");
		}
		
	}
	
	public String getXml()
	{
		data.append("</xml>");
		return data.toString();
	}
	
	public void addData(String name,String value)
	{
		data.append("<").append(name).append(">").append("<![CDATA[");
		data.append(value).append("]]>");
		data.append("</").append(name).append(">\n");
	}
	
	public void addData(String name,Long value)
	{
		data.append("<").append(name).append(">");
		data.append(value.toString());
		data.append("</").append(name).append(">\n");
	}
}
