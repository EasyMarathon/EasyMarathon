package com.EasyMarathon.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.dao.AthleteDao;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.EventDao;
import com.EasyMarathon.dao.PictureDao;
import com.EasyMarathon.dao.UserDao;

public class DaoTester
{
	Connection conn;
	String wID = "";

	public DaoTester(String wID)
	{
		this.wID = wID;
	}

	String Register(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		UserDao userdao = new UserDao(conn);
		try
		{
			UserBean user = userdao.AddUser(wID, cont[1], cont[2]);
			return "Success with openID:\n" + user.getWechatID();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
	}

	String ShowMe(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		UserDao userdao = new UserDao(conn);
		try
		{
			UserBean user = userdao.GetUser(wID);
			if (user == null)
				return "ÉÐÎ´×¢²á";
			else
				return "Name:" + user.getUserName() + "\nCelPhone:"
						+ user.getCelphone();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
	}

	String ListEvents(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		EventDao eventdao = new EventDao(conn);
		try
		{
			ArrayList<EventBean> events = eventdao.GetEventByStatus(0);
			String ret = "get " + events.size() + " events\n";
			for (EventBean eb : events)
			{
				ret += "ID:" + eb.getEventID() + "\nName:" + eb.getEventName()
						+ "\n";
			}
			return ret;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
	}

	String ChooseEvents(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		AthleteDao athdao = new AthleteDao(conn);
		try
		{
			Integer aid = athdao.GetAthleteID(wID, Integer.parseInt(cont[1]));
			if (aid == null)
				return "Î´°ó¶¨";
			else
				return aid.toString();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
	}

	String BindEvents(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		AthleteDao athdao = new AthleteDao(conn);
		try
		{
			boolean res = athdao.AddAthlete(wID, Integer.parseInt(cont[1]),
					Integer.parseInt(cont[2]));
			if (res)
				return "ÒÑ´æÔÚ";
			else
				return "ok";
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
	}

	String GetPics(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		PictureDao picdao = new PictureDao(conn);
		try
		{
			HashMap<String, Integer> pics = picdao.GetPics(
					Integer.parseInt(cont[1]), Integer.parseInt(cont[2]));
			String ret = "get " + pics.size() + " pics\n";
			for (Map.Entry<String, Integer> e : pics.entrySet())
			{
				ret += "picID:" + e.getKey() + "\nstatus:" + e.getValue()
						+ "\n";
			}
			return ret;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
	}
}
