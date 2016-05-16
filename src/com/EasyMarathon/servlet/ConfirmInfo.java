package com.EasyMarathon.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EasyMarathon.bean.ConfirmData;
import com.EasyMarathon.dao.AthleteDao;
import com.EasyMarathon.dao.DaoBase;

public class ConfirmInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConfirmInfo() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DaoBase.getConnection(true);
		AthleteDao athleteDao = new AthleteDao(conn);
		ArrayList<ConfirmData> dataList = athleteDao.findAllNotChecked();
		if(dataList!=null){
			request.getSession().setAttribute("dataList", dataList);//未审核通过的运动员信息
		}
		request.getRequestDispatcher("bg/confirmInfo.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
