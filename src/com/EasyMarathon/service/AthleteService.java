package com.EasyMarathon.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.EasyMarathon.bean.Athlete;
import com.EasyMarathon.bean.ConfirmData;
import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.bean.PicBean;
import com.EasyMarathon.dao.AthleteDao;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.EventDao;
import com.EasyMarathon.dao.PictureDao;
import com.opensymphony.xwork2.ActionContext;

public class AthleteService
{
	public static int findAthlete(String wechatID, int eventID)
	{
		Connection conn;
		conn = DaoBase.getConnection(true);
		Integer athleteID = null;
		AthleteDao athletedao = new AthleteDao(conn);
		try
		{

			athleteID = athletedao.GetAthleteID(wechatID, eventID);
			if (athleteID == null)
			{
				return -1;
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

		return athleteID;
	}

	public static ArrayList<EventBean> GetEvents()
	{
		Connection conn;
		conn = DaoBase.getConnection(true);
		EventDao eventdao = new EventDao(conn);
		ArrayList<EventBean> events = null;
		try
		{

			events = eventdao.GetAllEvents();
			if (events == null)
			{
				return null;
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
		return events;
	}
	
	public static ArrayList<ConfirmData> GetAthlete()
	{
		Connection conn;
		conn = DaoBase.getConnection(true);
		AthleteDao athletedao = new AthleteDao(conn);
		ArrayList<ConfirmData> ConfirmDatas = null;
		try
		{

			ConfirmDatas = athletedao.GetsomeAthletes();
			if (ConfirmDatas == null)
			{
				return null;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
		return ConfirmDatas;
	}

	public static ArrayList<PicBean> returnPicture(HttpServletRequest request, int athleteID, int eventID)
	{
		
		HttpSession session = request.getSession();
		Connection conn;
		conn = DaoBase.getConnection(true);
		PictureDao picturedao = new PictureDao(conn);
		ArrayList<PicBean> pictures = new ArrayList<>();
		try
		{
			pictures = picturedao.GetAllPics(athleteID, eventID);
			System.out.println("进入AthleteService");
			session.setAttribute("pictures", pictures);
			session.setAttribute("eventID",eventID);
			System.out.println("赛事号为："+eventID);

		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return pictures;
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
		return pictures;
	}

	public static Boolean Bind(String wechatID, int eventID, int athleteID)
	{
		// 默认返回的文本消息内容
		System.out.println("进入绑定阶段！");
		Connection conn;
		conn = DaoBase.getConnection(true);
		AthleteDao athletedao = new AthleteDao(conn);
		try
		{
			if (!athletedao.AddAthlete(wechatID, eventID, athleteID))
			{
				System.out.println("绑定失败！");
				return false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
		System.out.println("绑定成功！");
		return true;

	}

}
