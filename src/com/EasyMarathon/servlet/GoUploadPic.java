package com.EasyMarathon.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.bean.SNSUserInfo;
import com.EasyMarathon.bean.WeixinOauth2Token;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.EventDao;
import com.EasyMarathon.dao.PictureDao;
import com.EasyMarathon.util.AdvancedUtil;

@WebServlet(urlPatterns = "/GoUploadPic", initParams =
{
	@WebInitParam(name="retrytime", value="60"),
	@WebInitParam(name="posibility", value="200"),
	@WebInitParam(name="total", value="2")
})
public class GoUploadPic  extends HttpServlet{
	Connection conn;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		conn = DaoBase.getConnection(true);
		EventDao eventdao=new EventDao(conn);
		Map<Integer, String> event=new HashMap<Integer, String>();
		try
		{
			
			ArrayList<EventBean> events = eventdao
					.GetEventByStatus(EventBean.Status.ongoing);
/*			ArrayList<EventBean> events = new ArrayList<EventBean>() ;
			EventBean eventbean=new EventBean();
			eventbean.setEventID(1);
			eventbean.setEventName("∫º÷›¬Ì¿≠À…");
			eventbean.setEventStatus(null);
			events.add(eventbean);*/
			for (EventBean eb : events)
			{
				
				event.put(eb.getEventID(), eb.getEventName());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("System Error:\n" + e.getMessage());
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
		request.setAttribute("event", event);
		request.getRequestDispatcher("bg/uploadPicforUser.jsp").forward(request,
				response);
	}

}
