package com.EasyMarathon.test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
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
		SAXReader saxReadr = new SAXReader();// �õ�SAXReader����
		Document document = saxReadr.read(ins);// ��������ת��һ��DOM4J�ĵ���
		
		Map<String,String> map = new HashMap<String, String>();  
	    // �õ�xml��Ԫ��  
	    Element root = document.getRootElement();
	    // �õ���Ԫ�ص������ӽڵ�  
	    List<Element> eList = root.elements();  
	   
	    // ���������ӽڵ�  
	    for (Element e : eList)  
	        map.put(e.getName(), e.getText());  
	   
	    // �ͷ���Դ  
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
