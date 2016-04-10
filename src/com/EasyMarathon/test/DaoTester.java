package com.EasyMarathon.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.EasyMarathon.bean.EventBean;
import com.EasyMarathon.bean.FreePicBean;
import com.EasyMarathon.bean.PicBean;
import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.dao.AthleteDao;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.EventDao;
import com.EasyMarathon.dao.FreePicDao;
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
			UserBean user = new UserBean();
			user.setWechatID(wID);
			user.setUserName(cont[1]);
			user.setCelphone(cont[2]);
			user = userdao.AddUser(user);
			return "Success with openID:\n" + user.getWechatID();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
		finally
		{
			DaoBase.close(conn, null, null);
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
		finally
		{
			DaoBase.close(conn, null, null);
		}
	}

	String ListEvents(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		EventDao eventdao = new EventDao(conn);
		try
		{
			ArrayList<EventBean> events = eventdao
					.GetEventByStatus(EventBean.Status.ongoing);
			String ret = "get " + events.size() + " events\n";
			for (EventBean eb : events)
			{
				ret += "ID:" + eb.getEventID() + ",\tstatus:" + eb.getEventStatus().name()
						+ "\nName:" + eb.getEventName() + "\n";
			}
			return ret;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
		finally
		{
			DaoBase.close(conn, null, null);
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
				return "ºÅÂëÅÆ£º" + aid.toString();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
	}

	String AddEvents(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		EventDao eventdao = new EventDao(conn);
		try
		{
			Integer id = eventdao.AddEvent(cont[1]);
			if (id == null)
				return "Ê§°Ü";
			else
				return "ÐÂÔöeventID£º" + id;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
		finally
		{
			DaoBase.close(conn, null, null);
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
				return "ok";
			else
				return "ÒÑ´æÔÚ";
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
	}

	String GetPics(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		PictureDao picdao = new PictureDao(conn);
		try
		{
			ArrayList<PicBean> pics = picdao.GetAllPics(
					Integer.parseInt(cont[1]), Integer.parseInt(cont[2]));
			String ret = "get " + pics.size() + " pics\n";
			for (PicBean p : pics)
			{
				ret += "picID:" + p.getPicID() + "\nstatus:" + p.getPicStatus()
						+ "\n";
			}
			return ret;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
	}

	String PutPic(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		PictureDao picdao = new PictureDao(conn);
		try
		{
			PicBean pic = new PicBean();
			pic.setPicID(cont[3]);
			picdao.AddPic(Integer.parseInt(cont[1]), Integer.parseInt(cont[2]),pic);
			return "finish";
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
	}

	String BuyPic(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		PictureDao picdao = new PictureDao(conn);
		try
		{
			PicBean pic = picdao.GetPicByPicID(cont[1]);
			if (pic.getPicStatus() == PicBean.Status.onSale)
				pic.setPicStatus(PicBean.Status.hasBuy);
			else pic.setPicStatus(PicBean.Status.onSale);
			String ret = picdao.UpdPic(pic).getPicStatus().name();
			return "new Status:" + ret;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
	}

	String PutFreePic(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		FreePicDao fpdao = new FreePicDao(conn);
		try
		{
			fpdao.AddPic(Integer.parseInt(cont[1]), wID, cont[2]);
			return "finish";
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
	}

	String GetFreePics(String[] cont)
	{
		conn = DaoBase.getConnection(true);
		FreePicDao fpdao = new FreePicDao(conn);
		try
		{
			ArrayList<FreePicBean> pics = fpdao.GetFreePicsByWechatID(wID);
			String ret = "get " + pics.size() + " pics\n";
			for (FreePicBean fp : pics)
			{
				ret += "picID:" + fp.getPicID() + "\neventID:" + fp.getEventID()
						+ "\n";
			}
			return ret;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "System Error:\n" + e.getMessage();
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}
	}
}
