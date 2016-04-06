package com.EasyMarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.EasyMarathon.bean.UserBean;

public class PictureDao
{
	Connection conn = null;

	public PictureDao(Connection c)
	{
		conn = c;
	}

	public HashMap<String,Integer> GetPics(int aID, int eventID)
			throws SQLException
	{
		final String sql1 = "select PicID,PicStatus from Pics where EventID=? AND AthleteID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventID);
			ps1.setInt(2, aID);
			ResultSet rs1 = ps1.executeQuery();
			HashMap<String,Integer> pics = new HashMap<>();
			
			while (rs1.next())
			{
				pics.put(rs1.getString(1), rs1.getInt(2));
			}

			return pics;
		}
	}

	public boolean AddPic(String wechatID, int eventID, String picID)
			throws SQLException
	{
		final String sql1 = "insert into Pics (WechatID,EventID,PicID) values(?,?,?)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, wechatID);
			ps1.setInt(2, eventID);
			ps1.setString(3, picID);

			ps1.executeUpdate();
			return true;
		}
	}
}
