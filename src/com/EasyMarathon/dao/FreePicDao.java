package com.EasyMarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.EasyMarathon.bean.FreePicBean;

public class FreePicDao
{
	Connection conn = null;

	public FreePicDao(Connection c)
	{
		conn = c;
	}

	public ArrayList<FreePicBean> GetAllDefault(){//获取所有照片，默认排序
		final String sql1 = "select Users.WechatName, Events.EventName, "
				+ "FreePics.PicID, FreePics.DownloadCnt, Events.EventID, Users.HeadImgUrl "
				+ "from FreePics, Users, Events "
				+ "where Events.EventID=FreePics.EventID "
				+ "and FreePics.WechatID=Users.WechatID";
		System.out.println(sql1);
		ArrayList<FreePicBean> pics = new ArrayList<>();
		try (PreparedStatement ps1 = conn.prepareStatement(sql1)){
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next())
			{
				FreePicBean fp = new FreePicBean();
				fp.setUserName(rs1.getString(1));
				fp.setEventName(rs1.getString(2));
				fp.setPicID(rs1.getString(3));
				fp.setDownloadCnt(rs1.getInt(4));
				fp.setEventID(rs1.getInt(5));
				fp.setHeadImgUrl(rs1.getString(6));
				pics.add(fp);
			}
		} catch (SQLException e) {
			System.out.println("error code="+e.getErrorCode());
			System.out.println("error message"+e.getMessage());
		}
		return pics;
	}
	
	public ArrayList<FreePicBean> GetAllBySellNum(){//获取所有照片，销量优先
		final String sql1 = "select Users.WechatName, Events.EventName, "
				+ "FreePics.PicID, FreePics.DownloadCnt, Events.EventID, Users.HeadImgUrl "
				+ "from FreePics, Users, Events "
				+ "where Events.EventID=FreePics.EventID "
				+ "and FreePics.WechatID=Users.WechatID "
				+ "order by FreePics.DownloadCnt DESC";
		System.out.println(sql1);
		ArrayList<FreePicBean> pics = new ArrayList<>();
		try (PreparedStatement ps1 = conn.prepareStatement(sql1)){
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next())
			{
				FreePicBean fp = new FreePicBean();
				fp.setUserName(rs1.getString(1));
				fp.setEventName(rs1.getString(2));
				fp.setPicID(rs1.getString(3));
				fp.setDownloadCnt(rs1.getInt(4));
				fp.setEventID(rs1.getInt(5));
				fp.setHeadImgUrl(rs1.getString(6));
				pics.add(fp);
			}
		} catch (SQLException e) {
			System.out.println("error code="+e.getErrorCode());
			System.out.println("error message"+e.getMessage());
		}
		return pics;
	}
	
	public ArrayList<FreePicBean> GetAllByTime(){//获取所有照片，上传时间排序
		final String sql1 = "select Users.WechatName, Events.EventName, "
				+ "FreePics.PicID, FreePics.DownloadCnt, Events.EventID, Users.HeadImgUrl "
				+ "from FreePics, Users, Events "
				+ "where Events.EventID=FreePics.EventID "
				+ "and FreePics.WechatID=Users.WechatID "
				+ "order by FreePics.upTime DESC";
		System.out.println(sql1);
		ArrayList<FreePicBean> pics = new ArrayList<>();
		try (PreparedStatement ps1 = conn.prepareStatement(sql1)){
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next())
			{
				FreePicBean fp = new FreePicBean();
				fp.setUserName(rs1.getString(1));
				fp.setEventName(rs1.getString(2));
				fp.setPicID(rs1.getString(3));
				fp.setDownloadCnt(rs1.getInt(4));
				fp.setEventID(rs1.getInt(5));
				fp.setHeadImgUrl(rs1.getString(6));
				pics.add(fp);
			}
		} catch (SQLException e) {
			System.out.println("error code="+e.getErrorCode());
			System.out.println("error message"+e.getMessage());
		}
		return pics;
	}
	
	
	public ArrayList<FreePicBean> GetFreePicsByEventID(int eventID)
			throws SQLException
	{
		final String sql1 = "select PicID,WechatID,DownloadCnt,upTime from FreePics where EventID=? order by upTime DESC";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventID);
			ResultSet rs1 = ps1.executeQuery();
			ArrayList<FreePicBean> pics = new ArrayList<>();

			while (rs1.next())
			{
				FreePicBean fp = new FreePicBean();
				fp.setEventID(eventID);
				fp.setPicID(rs1.getString(1));
				fp.setWechatID(rs1.getString(2));
				fp.setDownloadCnt(rs1.getInt(3));
				fp.setUpTime(rs1.getLong(4));
				
				pics.add(fp);
			}

			return pics;
		}
	}

	public ArrayList<FreePicBean> GetFreePicsByWechatID(String wechatID)
			throws SQLException
	{
		final String sql1 = "select PicID,EventID,DownloadCnt,upTime from FreePics where WechatID=? order by upTime DESC";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, wechatID);
			ResultSet rs1 = ps1.executeQuery();

			ArrayList<FreePicBean> pics = new ArrayList<>();

			while (rs1.next())
			{
				FreePicBean fp = new FreePicBean();
				fp.setWechatID(wechatID);
				fp.setPicID(rs1.getString(1));
				fp.setEventID(rs1.getInt(2));
				fp.setDownloadCnt(rs1.getInt(3));
				fp.setUpTime(rs1.getLong(4));
				
				pics.add(fp);
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
