package com.EasyMarathon.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.EasyMarathon.bean.PicBean;
import com.EasyMarathon.service.AthleteService;


public class FindPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aID = request.getParameter("aID");
		int eventID = Integer.parseInt(request.getParameter("eventID"));
		int athleteID=Integer.parseInt(aID);
		ArrayList<PicBean> pictures = new ArrayList<>();
		pictures = AthleteService.returnPicture(request,athleteID, eventID);
		System.out.println(pictures.size());
		if (pictures.size()!=0){
			response.sendRedirect("findPicture.jsp");
		}
		else{
			response.sendRedirect("findunsuccess.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
