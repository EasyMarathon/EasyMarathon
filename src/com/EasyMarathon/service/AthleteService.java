package com.EasyMarathon.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

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

	public static ArrayList<PicBean> returnPicture(int athleteID, int eventID)
	{
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		Connection conn;
		conn = DaoBase.getConnection(true);
		PictureDao picturedao = new PictureDao(conn);
		ArrayList<PicBean> pictures = new ArrayList<>();
		try
		{
			pictures = picturedao.GetAllPics(athleteID, eventID);
			System.out.println("����AthleteService");
			session.put("pictures", pictures);
			session.put("eventID",eventID);

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
		// Ĭ�Ϸ��ص��ı���Ϣ����
		System.out.println("����󶨽׶Σ�");
		Connection conn;
		conn = DaoBase.getConnection(true);
		AthleteDao athletedao = new AthleteDao(conn);
		try
		{
			if (!athletedao.AddAthlete(wechatID, eventID, athleteID))
			{
				System.out.println("��ʧ�ܣ�");
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
		System.out.println("�󶨳ɹ���");
		return true;

	}

}
