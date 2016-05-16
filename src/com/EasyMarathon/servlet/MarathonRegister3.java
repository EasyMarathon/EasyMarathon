package com.EasyMarathon.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;

import com.EasyMarathon.bean.Athlete;
import com.EasyMarathon.bean.Athlete.State;
import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.dao.AthleteDao;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.EventDao;
import com.EasyMarathon.dao.UserDao;

public class MarathonRegister3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	
    public MarathonRegister3() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("eventID="+request.getParameter("eventID"));
		int eventID = Integer.parseInt(request.getParameter("eventID"));
		String wechatID = request.getParameter("user.wechatID");
		Athlete athlete = new Athlete();
		athlete.setEventID(eventID);
		athlete.setState(State.origin);
		athlete.setWechatID(wechatID);
		//request.setAttribute("eventID", eventID);
		
		Connection conn;
		conn = DaoBase.getConnection(true);
		AthleteDao athletedao = new AthleteDao(conn);
		try{
			System.out.println("----运动员信息录入数据库----");
			if (!athletedao.AddAthleteBase(athlete)){
				System.out.println("报名失败");
				UserBean user = new UserDao(conn).GetUser(wechatID);
				HttpSession session = request.getSession();
				session.setAttribute("user", user);//运动员注册时的信息
				EventBean event = new  EventDao(conn).GetEventByID(eventID);
				session.setAttribute("eventName", event.getEventName());//赛事名
				request.getRequestDispatcher("bg/athleteExistError.jsp").forward(request, response);
			}else{
				System.out.println("报名成功！");
				UserBean user = new UserDao(conn).GetUser(wechatID);
				request.setAttribute("user", user);//运动员注册时的信息
				EventBean event = new  EventDao(conn).GetEventByID(eventID);
				request.setAttribute("eventName", event.getEventName());//赛事名
				request.getRequestDispatcher("bg/registerSuccess.jsp").forward(request, response);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			DaoBase.close(conn, null, null);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
