package com.EasyMarathon.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.EasyMarathon.bean.MsgBean;
import com.EasyMarathon.util.SignUtil;

/**
 * Servlet implementation class Test01
 */
@WebServlet(urlPatterns = "/try")


public class BaseTest extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串
		if (echostr != null && echostr.length() > 1)
		{
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (SignUtil.checkSignature(signature, timestamp, nonce))
			{
				out.print(echostr);
			}
		}
		else
		{
			out.print("empty");
		}
		out.close();
		out = null;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		// 请求校验
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		if (SignUtil.checkSignature(signature, timestamp, nonce))
		{
			String odat = SimpleReply(request);
			out.print(odat);
		}

		out.close();
		out = null;
	}
	
	public String SimpleReply(HttpServletRequest request)
	{
		DoMsg gm = new DoMsg();
		MsgBean msg = null;
		MsgBean retmsg = new MsgBean();
		String ret = "";
		try
		{
			msg = gm.getMsg(request);

			System.out.println("GetAMsg:\n--From:"+msg.getFromUserName()+"\n--to:"+msg.getToUserName()+"\n--time:"+msg.getCreateTime()+"\n--content:"+msg.getContent());
			retmsg.setFromUserName(msg.getToUserName());
			retmsg.setToUserName(msg.getFromUserName());
			retmsg.setCreateTime(new Date().getTime()/1000);
			retmsg.setMsgType("text");
			
			if(msg.getContent().startsWith("sys-"))
			{
				String[] strs = msg.getContent().split(",");
				DaoTester dt = new DaoTester(msg.getFromUserName());
				switch(strs[0])
				{
				case "sys-reg":
					retmsg.setContent(dt.Register(strs));break;
				case "sys-list":
					retmsg.setContent(dt.ListEvents(strs));break;
				case "sys-my":
					retmsg.setContent(dt.ShowMe(strs));break;
				case "sys-event":
					retmsg.setContent(dt.ChooseEvents(strs));break;
				case "sys-+event":
					retmsg.setContent(dt.AddEvents(strs));break;
				case "sys-bind":
					retmsg.setContent(dt.BindEvents(strs));break;
				case "sys-pic":
					retmsg.setContent(dt.GetPics(strs));break;
				case "sys-freepic":
					retmsg.setContent(dt.GetFreePics(strs));break;
				case "sys-+pic":
					retmsg.setContent(dt.PutPic(strs));break;
				case "sys-buypic":
					retmsg.setContent(dt.BuyPic(strs));break;
				case "sys-+fp":
					retmsg.setContent(dt.PutFreePic(strs));break;
				case "sys-test":
					retmsg.setContent("get ans:"+NumTest.test());break;
				default:
					retmsg.setContent("我听不懂/:dig");break;
				}
			}
			else
				retmsg.setContent("你说的是："+msg.getContent());
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
			return "";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			retmsg.setContent("系统报错：\n"+e.getMessage());
		}
		
		ret = gm.makeMsg(retmsg);
		System.out.println("DeliverAMsg:\n"+ret);
		return ret;
	}
	
	
}
