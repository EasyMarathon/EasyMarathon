package com.EasyMarathon.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.liufeng.course.message.resp.TextMessage;

import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.bean.GongzhonghaoInfo;
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
		String res = new String();
		String URL = GongzhonghaoInfo.URL;
		
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
					System.out.println("key=" + eventKey);
					if (eventKey.equals("findPicture")) {
						ArrayList<EventBean> events = AthleteService.GetEvents();
						res +="若要查看摄影师为您拍摄的赛场风采，"
							+ "请回复【赛事ID】，如，回复：1\n\n赛事列表（赛事ID，赛事名）\n\n";
						for (EventBean s : events) {
							res += +s.getEventID() + ". " + s.getEventName() + "\n";
						}
						textMessage.setContent(res);
						respXml = MessageUtil.messageToXml(textMessage);
						// respXml = MessageUtil.messageToXml(textMessage);
					}
				}
			}
			// 当用户发消息时
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String ss = requestMap.get("Content");
				Integer eventID = null;
				try {
					eventID = Integer.parseInt(ss);
					int aID = AthleteService.findAthlete(fromUserName, eventID);
					ArrayList<EventBean> events = AthleteService.GetEvents();
					boolean flag = false;
					for (EventBean s : events) {
						if (s.getEventID() == eventID) {
							flag = true;
							break;
						}
					}
					if (flag) {
						if (aID >= 0) {
							System.out.println("AthleteID="+aID);
							//http://7891556.cn/easyrun/
							//http://15064r19x0.imwork.net/EasyMarathon/
							
							textMessage.setContent("<a href=\""
									+ URL
									+ "bg/FindPicServlet?eventID="
									+ eventID + "&aID=" + aID + "\">点击查看照片！</a>");
						} else {
							textMessage.setContent("亲，暂时没有您的照片\n\n[提示]\n您还没有报名该项赛事喔~");
						}
					}
					else
					{
						textMessage.setContent("对不起，赛事不存在！");
					}
				} catch (Exception e) {
					if(ss.equals("#001admin")){
						res +="<a href=\""
								+ URL
								+ "MasterUploadPre"
								+ "\">点击上传摄影师照片！</a>";
					}else{
						ArrayList<EventBean> events = AthleteService.GetEvents();
						res +="对不起，我现在还不能理解你的意思。\n\n"
								+ "若想要查看摄影师为您拍摄的赛场风采，"
								+ "请回复[赛事ID]，如,回复'1'\n\n赛事列表（赛事ID，赛事名）\n\n";
						for (EventBean s : events) {
							res += +s.getEventID() + ". " + s.getEventName() + "\n";
						}
					}
					textMessage.setContent(res);
				}
				respXml = MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
