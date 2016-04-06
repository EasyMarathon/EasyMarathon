package com.EasyMarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.EasyMarathon.bean.UserBean;

public class AthleteDao
{
	Connection conn = null;

	public AthleteDao(Connection c)
	{
		conn = c;
	}

	public Integer GetAthleteID(String wechatID, int eventID)
			throws SQLException
	{
		final String sql1 = "select AthleteID from Athletes where WechatID=? AND EventID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, wechatID);
			ps1.setInt(2, eventID);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next())// no match
				return null;

			return rs1.getInt("AthleteID");
		}
	}

	public boolean AddAthlete(String wechatID, int eventID, int athleteID)
			throws SQLException
	{
		final String sql1 = "select WechatID from Athletes where AthleteID=? AND EventID=?";
		final String sql2 = "insert into Athletes (WechatID,EventID,AthleteID) values(?,?,?)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1);
				PreparedStatement ps2 = conn.prepareStatement(sql2))
		{
			ps1.setInt(1, athleteID);
			ps1.setInt(2, eventID);
			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next())// has record
			{
				if (!wechatID.equals(rs1.getString("WechatID")))// not match
					return false;
				else
					return true;
			}

			ps2.setString(1, wechatID);
			ps2.setInt(2, eventID);
			ps2.setInt(3, athleteID);

			ps2.executeUpdate();
			return true;
		}
	}
}
