package com.EasyMarathon.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.liufeng.course.message.resp.TextMessage;

import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.util.MessageUtil;

/**
 * ���ķ�����
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml��ʽ����Ϣ����
		String respXml = null;
		String res = new String();
		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// �¼�����
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					System.out.println("����");
					textMessage.setContent("���ã���ӭ��ע����ƽ̨");
					// ����Ϣ����ת����xml
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO �ݲ�������
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// �¼�KEYֵ���봴���˵�ʱ��keyֵ��Ӧ
					String eventKey = requestMap.get("EventKey");
					// ����keyֵ�ж��û�����İ�ť
					System.out.println("key=" + eventKey);
					if (eventKey.equals("findPicture")) {
						System.out.println("������Ƭ��...");
						// textMessage.setContent("������Ƭ��...");
						ArrayList<EventBean> events = AthleteService.GetEvents();
						for (EventBean s : events) {
							res += +s.getEventID() + "��" + s.getEventName() + "\n";
						}
						textMessage.setContent(res);
						respXml = MessageUtil.messageToXml(textMessage);
						// respXml = MessageUtil.messageToXml(textMessage);
					}
					/*
					 * else if (eventKey.equals("iteye")) {
					 * textMessage.setContent(
					 * "ITeye��������2003��9�µ�JavaEye,�������������Java����Ϊ���ļ�����̳���Ѿ��𽥷�չ��Ϊ���������������������ۺ�����վ��\n\nhttp://www.iteye.com"
					 * ); respXml = MessageUtil.messageToXml(textMessage); }
					 */
				}
			}
			// ���û�����Ϣʱ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String ss = requestMap.get("Content");
				Integer eventID = null;
				try {
					eventID = Integer.parseInt(ss);
					System.out.println("΢�źţ�" + fromUserName);
					int aID = AthleteService.findAthlete(fromUserName, eventID);
					System.out.println(aID);
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
							textMessage.setContent("<a href=\"http://120.27.106.188/easyrun/bg/findPic?eventID="
									+ eventID + "&aID=" + aID + "&snsUserInfo.openId=" + fromUserName + "\">����鿴��Ƭ��</a>");
						} else {
							textMessage.setContent("<a href=\"http://120.27.106.188/easyrun/bg/lockInfo.jsp?eventID="
									+ eventID + "&wechatID=" + fromUserName + "\">������������������Ϣ</a>");
						}
					}
					else
					{
						textMessage.setContent("�Բ������²����ڣ�");
					}
				} catch (Exception e) {
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
