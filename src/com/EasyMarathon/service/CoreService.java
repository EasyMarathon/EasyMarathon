package com.EasyMarathon.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.liufeng.course.message.resp.TextMessage;

import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.util.MessageUtil;

/**
 * 核心服务类
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		String res=new String();
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					System.out.println("订阅");
					textMessage.setContent("您好，欢迎关注易跑平台");
					// 将消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 暂不做处理
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值判断用户点击的按钮
					System.out.println("key="+eventKey);
					if (eventKey.equals("findPicture")) {
						System.out.println("查找照片中...");
						//textMessage.setContent("查找照片中...");
						ArrayList<EventBean> events=AthleteService.GetEvents();
						for(EventBean s:events)
						{
							res+=+s.getEventID()+"："+s.getEventName()+"\n";
						}
						textMessage.setContent(res);
						respXml = MessageUtil.messageToXml(textMessage);
						//respXml = MessageUtil.messageToXml(textMessage);
					}
					/*else if (eventKey.equals("iteye")) {
						textMessage.setContent("ITeye即创办于2003年9月的JavaEye,从最初的以讨论Java技术为主的技术论坛，已经逐渐发展成为涵盖整个软件开发领域的综合性网站。\n\nhttp://www.iteye.com");
						respXml = MessageUtil.messageToXml(textMessage);
					}*/
				}
			}
			// 当用户发消息时
			else
				if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT))
			{
					String ss=requestMap.get("Content");
				    Integer eventID=null;
					try{
						eventID=Integer.parseInt(ss);
						int aID=AthleteService.findAthlete(fromUserName, eventID);
						if(aID>=0)
						{
							textMessage.setContent("<a href=\"FindPictureServlet?eventID="+eventID+"aID="+aID+"\">点击查看照片！</a>");
						}
						else
						{
							textMessage.setContent("<a href=\"LockInfoServlet?eventID="+eventID+"aID="+aID+"\">您的账号未绑定，点击这里绑定账号！</a>");
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}	
				    respXml = MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
