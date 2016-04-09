package com.EasyMarathon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.bean.MsgBean;
import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.dao.AthleteDao;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.EventDao;
import com.EasyMarathon.dao.PictureDao;
import com.EasyMarathon.dao.PictureDao.Status;
import com.EasyMarathon.test.DoMsg;
import com.EasyMarathon.util.SignUtil;
@WebServlet(urlPatterns = "/ReturnPic", initParams =
{
	@WebInitParam(name="retrytime", value="60"),
	@WebInitParam(name="posibility", value="200"),
	@WebInitParam(name="total", value="2")
})
public class ReturnPic {
	private static final long serialVersionUID = 1L;

	Connection conn;
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
	
	public HashMap<String, Status> fun(String wechatID,int eventID)
	{
		conn = DaoBase.getConnection(true);
		AthleteDao athletedao=new AthleteDao(conn);
		HashMap<String, Status> pictures=new HashMap<String, Status>();
		try
		{
			
			Integer athleteID=athletedao.GetAthleteID(wechatID, eventID);
			if(athleteID==null)
			{
				System.out.println("请绑定账号");
				return null;
			}
			else
			{
				PictureDao picturedao=new PictureDao(conn);			
				pictures=picturedao.GetAllPics(athleteID, eventID);				
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DaoBase.close(conn, null, null);		
		}
		
		return pictures;
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
			
			String req=msg.getContent();
			String s1=req.substring(req.lastIndexOf(" ")+1);
			String s2=req.substring(0, req.lastIndexOf(" "));
			int eventID=Integer.parseInt(s1);
			int athleteID=Integer.parseInt(s2);
			if(EventExist(eventID))
			{
				/*if(AthleteExist(athleteID))
				{
					ret= "picture";
				}
				else
				{
					ret= "you need binding";
				}*/
			}
			else
			{
				ret="该赛事不存在 ";
			}
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
	
	public boolean EventExist(int eventID)
	{
		conn = DaoBase.getConnection(true);
		EventDao eventdao=new EventDao(conn);
		try
		{
			EventBean event = eventdao.GetEventByID(eventID);
			if(event==null)
			{
				DaoBase.close(conn, null, null);
				return false;
			}	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DaoBase.close(conn, null, null);		
		}
		return true;	
	}
	

	
	

}
