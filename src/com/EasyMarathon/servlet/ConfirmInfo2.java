package com.EasyMarathon.servlet;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.EasyMarathon.bean.Athlete;
import com.EasyMarathon.dao.AthleteDao;
import com.EasyMarathon.dao.DaoBase;

public class ConfirmInfo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfirmInfo2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String AthleteID = request.getParameter("AthleteID");
		String wechatID = request.getParameter("wechatID");
		int eventID = Integer.parseInt(request.getParameter("eventID"));
		Connection conn = DaoBase.getConnection(true);
		AthleteDao athleteDao = new AthleteDao(conn);
		Athlete athlete = new Athlete();
		athlete.setEventID(eventID); 
		athlete.setAthleteID(AthleteID);
		athlete.setWechatID(wechatID);
		if(athleteDao.updateAthlete(athlete)){
			response.sendRedirect("bg/lockInfosuccess.jsp");
		}
		else{
			response.sendRedirect("bg/lockInfounsuccess.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
