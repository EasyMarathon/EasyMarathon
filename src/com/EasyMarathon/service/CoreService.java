package com.EasyMarathon.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.EasyMarathon.message.resp.*;
import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.bean.GongzhonghaoInfo;
import com.EasyMarathon.message.resp.Article;
import com.EasyMarathon.message.resp.NewsMessage;
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
		String URL = GongzhonghaoInfo.URL;
		
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
						ArrayList<EventBean> events = AthleteService.GetEvents();
						res +="��Ҫ�鿴��ӰʦΪ�������������ɣ�"
							+ "��ظ�������ID�����磬�ظ���1\n\n�����б�����ID����������\n\n";
						for (EventBean s : events) {
							res += +s.getEventID() + ". " + s.getEventName() + "\n";
						}
						textMessage.setContent(res);
						respXml = MessageUtil.messageToXml(textMessage);
					}else if (eventKey.equals("hot")) {		//���Ż���
						Article article = new Article();
						String description = "�й�����ˮ�����������ġ�ǣ�֡�ͬ���������ܲ�\n\n"
								+ "������  2016-05-17 09:25\n\n"
								+ "��ժҪ��\n��2016����������ˮ�ļ�������������������7��23�տ��ܣ����������������ľ��еĻ�ū³³����������Ϊ�����������������";
						String url = GongzhonghaoInfo.URL+"article_hot.jsp";
						String picUrl = GongzhonghaoInfo.URL+"articlePic/article_hot.jpg";
						
						article.setTitle("���Ż���");
						article.setDescription(description);
						article.setPicUrl(picUrl);
						article.setUrl(url);
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						//����ͼ����Ϣ
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						
						respXml = MessageUtil.messageToXml(newsMessage);
					}else if (eventKey.equals("view")) {	//װ��ָ��
						Article article = new Article();
						
						String description = "�����ɵ���Щ����װ����������\n"
								+ "�������Ǿ���̸һ̸������װ�����£�"
								+ "��Ȼ���Ǹ���������������ϴ�����fashion�����"
								+ "����ʵʵ���ڵظ���˵����Щװ��������ʲô�ġ�"
								+ "����ż���������Ͽ�����һЩʱ���˶������ǣ�"
								+ "��ʵֻ�����Ǳ��Ż��˵ġ������ԣ��������ɵ��״�ʲô��"
								+ "�Լ�Ϊʲô��Щ������װ������������˵��˵���Ҫ�� "
								+ "�����ҵ��˼�λ���������ѣ�"
								+ "�����������������������̸һ̸����������װ�����ĵá�";
						
						String url = GongzhonghaoInfo.URL+"article_view.jsp";
						String picUrl = GongzhonghaoInfo.URL+"articlePic/article_view.jpg";

						article.setTitle("װ��ָ��");
						article.setDescription(description);
						article.setPicUrl(picUrl);
						article.setUrl(url);
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						//����ͼ����Ϣ
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						
						respXml = MessageUtil.messageToXml(newsMessage);
					}else if (eventKey.equals("tip")) {	//�ܳǹ���
						Article article = new Article();
						
						String description = "���������ֱر�����";
						
						String url = GongzhonghaoInfo.URL+"article_tip.jsp";
						String picUrl = GongzhonghaoInfo.URL+"articlePic/article_tip.jpg";

						article.setTitle("�ܳǹ���");
						article.setDescription(description);
						article.setPicUrl(picUrl);
						article.setUrl(url);
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						//����ͼ����Ϣ
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						
						respXml = MessageUtil.messageToXml(newsMessage);
					}
				}
			}
			// ���û�����Ϣʱ
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
							textMessage.setContent("<a href=\""
									+ URL
									+ "bg/FindPicServlet?eventID="
									+ eventID + "&aID=" + aID + "\">����鿴��Ƭ��</a>");
						} else {
							textMessage.setContent("�ף���ʱû��������Ƭ\n\n[��ʾ]\n����û�б������������~");
						}
					}
					else
					{
						textMessage.setContent("�Բ������²����ڣ�");
					}
				} catch (Exception e) {
					if(ss.equals("#001admin")){
						res +="<a href=\""
								+ URL
								+ "MasterUploadPre"
								+ "\">����ϴ���Ӱʦ��Ƭ��</a>";
					}else if(ss.equals("#002admin")){
						res +="<a href=\""
								+ URL
								+ "ConfirmInfo"
								+ "\">��������˶�Ա�������ҳ�棡</a>";
					}else{
						ArrayList<EventBean> events = AthleteService.GetEvents();
						res +="�Բ��������ڻ�������������˼��\n\n"
								+ "����Ҫ�鿴��ӰʦΪ�������������ɣ�"
								+ "��ظ�[����ID]����,�ظ�'1'\n\n�����б�����ID����������\n\n";
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
