package com.EasyMarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.EasyMarathon.bean.PicBean;

public class PictureDao
{
	Connection conn = null;

	public PictureDao(Connection c)
	{
		conn = c;
	}

	public ArrayList<PicBean> GetAllPics(int aID, int eventID)
			throws SQLException
	{
		final String sql1 = "select PicID,PicStatus,Author,Price from Pics where EventID=? AND AthleteID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventID);
			ps1.setInt(2, aID);
			ResultSet rs1 = ps1.executeQuery();
			ArrayList<PicBean> pics = new ArrayList<>();

			while (rs1.next())
			{
				PicBean pic = new PicBean();
				pic.setPicID(rs1.getString(1));
				pic.setPicStatus(rs1.getInt(2));
				pic.setAuthor(rs1.getString(3));
				pic.setPrice(rs1.getInt(4));
				pics.add(pic);
			}

			return pics;
		}
	}

	public PicBean GetPicByPicID(String picID) throws SQLException
	{
		final String sql1 = "select PicStatus,Author,Price from Pics where PicID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, picID);
			ResultSet rs1 = ps1.executeQuery();

			if (rs1.next())
			{
				PicBean pic = new PicBean();
				pic.setPicID(picID);
				pic.setPicStatus(rs1.getInt(1));
				pic.setAuthor(rs1.getString(2));
				pic.setPrice(rs1.getInt(3));
				return pic;
			}

			return null;
		}
	}

	public boolean AddPic(int eventID, int aID, PicBean pic) throws SQLException
	{
		final String sql1 = "insert into Pics (EventID,AthleteID,PicID,Price,Author) values(?,?,?,?,?)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventID);
			ps1.setInt(2, aID);
			ps1.setString(3, pic.getPicID());
			ps1.setInt(4, pic.getPrice());
			ps1.setString(5, pic.getAuthor());

			ps1.executeUpdate();
			return true;
		}
	}

	public PicBean UpdPic(PicBean pic) throws SQLException
	{
		final String sql1 = "update Pics set PicStatus=?,Price=?,Author=? where PicID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, pic.getPicStatus().ordinal());
			ps1.setInt(2, pic.getPrice());
			ps1.setString(3, pic.getAuthor());
			ps1.setString(4, pic.getPicID());

			ps1.executeUpdate();
			return pic;
		}
	}
}
