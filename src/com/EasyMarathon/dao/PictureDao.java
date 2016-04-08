package com.EasyMarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PictureDao
{
	public static enum Status
	{
		onSale, hasBuy;
	}

	Connection conn = null;

	public PictureDao(Connection c)
	{
		conn = c;
	}

	public HashMap<String, Status> GetAllPics(int aID, int eventID)
			throws SQLException
	{
		final String sql1 = "select PicID,PicStatus from Pics where EventID=? AND AthleteID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventID);
			ps1.setInt(2, aID);
			ResultSet rs1 = ps1.executeQuery();
			HashMap<String, Status> pics = new HashMap<>();

			while (rs1.next())
			{
				pics.put(rs1.getString(1), Status.values()[rs1.getInt(2)]);
			}

			return pics;
		}
	}
	
	public Status GetPicByPicID(String picID)
			throws SQLException
	{
		final String sql1 = "select PicStatus from Pics where PicID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, picID);
			ResultSet rs1 = ps1.executeQuery();

			if (rs1.next())
			{
				return Status.values()[rs1.getInt(1)];
			}

			return null;
		}
	}

	public boolean AddPic(int eventID, int aID, String picID)
			throws SQLException
	{
		final String sql1 = "insert into Pics (EventID,AthleteID,PicID) values(?,?,?)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventID);
			ps1.setInt(2, aID);
			ps1.setString(3, picID);

			ps1.executeUpdate();
			return true;
		}
	}

	public Status ChgPicStatus(String picID, Status newStatus)
			throws SQLException
	{
		final String sql1 = "update Pics set PicStatus=? where PicID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, newStatus.ordinal());
			ps1.setString(2, picID);

			ps1.executeUpdate();
			return newStatus;
		}
	}
}
