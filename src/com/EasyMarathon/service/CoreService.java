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
					}else if (eventKey.equals("hot")) {		//热门话题
						Article article = new Article();
						String description = "中国六盘水与美国夏威夷“牵手”同跑马拉松跑步\n\n"
								+ "中新网  2016-05-17 09:25\n\n"
								+ "【摘要】\n“2016凉都·六盘水夏季国际马拉松赛”将于7月23日开跑，这项赛事与夏威夷举行的火奴鲁鲁马拉松赛结为“姊妹马拉松赛”。";
						String url = GongzhonghaoInfo.URL+"article_hot.jsp";
						String picUrl = GongzhonghaoInfo.URL+"articlePic/article_hot.jpg";
						
						article.setTitle("热门话题");
						article.setDescription(description);
						article.setPicUrl(picUrl);
						article.setUrl(url);
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						//创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						
						respXml = MessageUtil.messageToXml(newsMessage);
					}else if (eventKey.equals("view")) {	//装备指南
						Article article = new Article();
						
						String description = "马拉松的那些正经装备，你造吗？\n"
								+ "今天我们就来谈一谈马拉松装备的事，"
								+ "当然不是告诉你如何在赛道上穿的最fashion最潮流，"
								+ "而是实实在在地跟你说，这些装备都是做什么的。"
								+ "至于偶尔在赛道上看到的一些时尚运动奇葩们，"
								+ "其实只是在那边吓唬人的……所以，跑马拉松到底穿什么，"
								+ "以及为什么这些正经的装备对于跑者来说如此的重要？ "
								+ "我们找到了几位资深级别的跑友，"
								+ "让他们坐下来与我们认真地谈一谈关于马拉松装备的心得。";
						
						String url = GongzhonghaoInfo.URL+"article_view.jsp";
						String picUrl = GongzhonghaoInfo.URL+"articlePic/article_view.jpg";

						article.setTitle("装备指南");
						article.setDescription(description);
						article.setPicUrl(picUrl);
						article.setUrl(url);
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						//创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						
						respXml = MessageUtil.messageToXml(newsMessage);
					}else if (eventKey.equals("tip")) {	//跑城攻略
						Article article = new Article();
						
						String description = "马拉松新手必备攻略";
						
						String url = GongzhonghaoInfo.URL+"article_tip.jsp";
						String picUrl = GongzhonghaoInfo.URL+"articlePic/article_tip.jpg";

						article.setTitle("跑城攻略");
						article.setDescription(description);
						article.setPicUrl(picUrl);
						article.setUrl(url);
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						//创建图文消息
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
					}else if(ss.equals("#002admin")){
						res +="<a href=\""
								+ URL
								+ "ConfirmInfo"
								+ "\">点击进入运动员报名审核页面！</a>";
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
