package com.EasyMarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public class FreePicDao
{
	Connection conn = null;

	public FreePicDao(Connection c)
	{
		conn = c;
	}

	public HashMap<String, String> GetFreePicsByEventID(int eventID)
			throws SQLException
	{
		final String sql1 = "select PicID,WechatID from FreePics where EventID=? order by upTime DESC";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventID);
			ResultSet rs1 = ps1.executeQuery();
			HashMap<String, String> pics = new HashMap<>();

			while (rs1.next())
			{
				pics.put(rs1.getString(1), rs1.getString(2));
			}

			return pics;
		}
	}

	public HashMap<String, Integer> GetFreePicsByWechatID(String wechatID)
			throws SQLException
	{
		final String sql1 = "select PicID,EventID from FreePics where WechatID=? order by upTime DESC";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, wechatID);
			ResultSet rs1 = ps1.executeQuery();
			HashMap<String, Integer> pics = new HashMap<>();

			while (rs1.next())
			{
				pics.put(rs1.getString(1), rs1.getInt(2));
			}

			return pics;
		}
	}

	public boolean AddPic(int eventID, String wechatID, String picID)
			throws SQLException
	{
		final String sql1 = "insert into FreePics (EventID,WechatID,PicID,upTime) values(?,?,?,?)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventID);
			ps1.setString(2, wechatID);
			ps1.setString(3, picID);
			ps1.setLong(4, new Date().getTime());

			ps1.executeUpdate();
			return true;
		}
	}
}
