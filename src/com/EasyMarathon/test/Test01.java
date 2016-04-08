package com.EasyMarathon.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
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
@WebServlet(urlPatterns = "/simpletry", initParams =
{
	@WebInitParam(name="retrytime", value="60"),
	@WebInitParam(name="posibility", value="200"),
	@WebInitParam(name="total", value="2")
})


public class Test01 extends HttpServlet
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
			try
			{
				msg = gm.getMsg(request);
			}
			catch (DocumentException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("GetAMsg:\n--From:"+msg.getFromUserName()+"\n--to:"+msg.getToUserName()+"\n--time:"+msg.getCreateTime()+"\n--content:"+msg.getContent());
			retmsg.setFromUserName(msg.getToUserName());
			retmsg.setToUserName(msg.getFromUserName());
			retmsg.setCreateTime(new Date().getTime()/1000);
			retmsg.setMsgType("text");
			
			retmsg.setContent("你说的是："+msg.getContent());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ret = gm.makeMsg(retmsg);
		System.out.println("DeliverAMsg:\n"+ret);
		return ret;
	}
	
	
}
