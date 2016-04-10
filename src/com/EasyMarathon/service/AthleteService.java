package com.EasyMarathon.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.bean.MsgBean;
import com.EasyMarathon.dao.AthleteDao;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.EventDao;
import com.EasyMarathon.dao.PictureDao;
import com.EasyMarathon.dao.PictureDao.Status;
import com.EasyMarathon.test.DoMsg;
import com.EasyMarathon.util.MessageUtil;
import com.EasyMarathon.util.SignUtil;

public class AthleteService {
	public static int findAthlete(String wechatID, int eventID)
	{
		Connection conn;
		conn = DaoBase.getConnection(true);
		Integer athleteID = null;
		AthleteDao athletedao = new AthleteDao(conn);
		try {

			athleteID = athletedao.GetAthleteID(wechatID, eventID);
			if (athleteID == null) {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoBase.close(conn, null, null);
		}
		
		return athleteID;		
	}
	
	public static ArrayList<EventBean> GetEvents()
	{
		Connection conn;
		conn = DaoBase.getConnection(true);
		EventDao eventdao = new EventDao(conn);
		ArrayList<EventBean> events = null;
		try {

			events = eventdao.GetAllEvents();
			if (events == null) {
				return null;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoBase.close(conn, null, null);
		}
			return events;		
	}
	public static HashMap<String, Status> returnPicture(int athleteID, int eventID) {
		Connection conn;
		conn = DaoBase.getConnection(true);
		PictureDao picturedao = new PictureDao(conn);
		HashMap<String, Status> pictures = new HashMap<String, Status>();
		try {
				pictures = picturedao.GetAllPics(athleteID, eventID);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return pictures;
		} finally {
			DaoBase.close(conn, null, null);
		}
		return pictures;
	}
	public  static Boolean Bind(String wechatID, int eventID, int athleteID) {
		// 默认返回的文本消息内容
		Connection conn;
		conn = DaoBase.getConnection(true);
		AthleteDao athletedao = new AthleteDao(conn);
		try {
			if(!athletedao.AddAthlete(wechatID, eventID, athleteID))
			{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
		return true;
		
	}
	
	
}
