package com.EasyMarathon.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.EasyMarathon.bean.MsgBean;


public class DoMsg
{
	
	public MsgBean parseMsg(Map<String, String> data)
	{
		MsgBean ret = new MsgBean();
		ret.setURL(data.get("URL"));
		ret.setFromUserName(data.get("FromUserName"));
		ret.setToUserName(data.get("ToUserName"));
		ret.setCreateTime(Long.parseLong(data.get("CreateTime")) );
		ret.setMsgType(data.get("MsgType"));
		ret.setContent(data.get("Content"));
		ret.setMsgId(data.get("MsgId"));
		return ret;
	}
	
	public MsgBean getMsg(HttpServletRequest request) throws IOException, DocumentException
	{
		InputStream ins = request.getInputStream();
		SAXReader saxReadr = new SAXReader();// 得到SAXReader对象
		Document document = saxReadr.read(ins);// 将输入流转成一个DOM4J文档类
		
		Map<String,String> map = new HashMap<String, String>();  
	    // 得到xml根元素  
	    Element root = document.getRootElement();
	    // 得到根元素的所有子节点  
	    List<Element> eList = root.elements();  
	   
	    // 遍历所有子节点  
	    for (Element e : eList)  
	        map.put(e.getName(), e.getText());  
	   
	    // 释放资源  
	    ins.close();  
	    ins = null;  
		
	    MsgBean ret = parseMsg(map);
	    
		return ret;
	}
	
	public String makeMsg(MsgBean inmsg)
	{		
		EasyXml xml = new EasyXml(0);
		xml.addData("ToUserName", inmsg.getToUserName());
		xml.addData("FromUserName", inmsg.getFromUserName());
		xml.addData("CreateTime", inmsg.getCreateTime());
		xml.addData("MsgType", inmsg.getMsgType());
		xml.addData("Content", inmsg.getContent());
		
		return xml.getXml();
	}
}
