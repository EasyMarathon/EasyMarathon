package com.EasyMarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.EasyMarathon.bean.UserBean;

public class UserDao
{
	Connection conn = null;

	public UserDao(Connection c)
	{
		conn = c;
	}

	public UserBean GetUser1(int eventID,int athleteID) throws SQLException
	{
		final String sql1 = "select * from Users ,Athletes where EventID=? and AthleteID=? and Users.WechatID=Athletes.WechatID";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setInt(1, eventID);
			ps1.setInt(2, athleteID);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next())// no match
				return null;

			// get user
			UserBean user = new UserBean();
			user.setWechatID(rs1.getString("WechatID"));
			user.setUserName(rs1.getString("UserName"));
			user.setCelphone(rs1.getString("Celphone"));
			user.setEmail(rs1.getString("Email"));
			user.setBirth(rs1.getDate("Birth"));
			user.setGender(rs1.getInt("Gender"));
			user.setIdentityCard(rs1.getString("IdentityCard"));
			user.setIdentityPic(rs1.getString("IdentityPic"));
			user.setBloodType(rs1.getString("BloodType"));
			user.setAddress(rs1.getString("Address"));
			user.setHeight(rs1.getFloat("Height"));
			user.setWeight(rs1.getFloat("Weight"));
			user.setUrgencyContact(rs1.getString("UrgencyContact"));
			user.setUrgencyPhone(rs1.getString("UrgencyPhone"));
			
			return user;
		}
	}
	public UserBean GetUser(String wechatID) throws SQLException
	{
		final String sql1 = "select * from Users where WechatID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, wechatID);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next())// no match
				return null;

			// get user
			UserBean user = new UserBean();
			user.setWechatID(wechatID);
			user.setUserName(rs1.getString("UserName"));
			user.setCelphone(rs1.getString("Celphone"));
			user.setEmail(rs1.getString("Email"));
			user.setBirth(rs1.getDate("Birth"));
			user.setGender(rs1.getInt("Gender"));
			user.setIdentityCard(rs1.getString("IdentityCard"));
			user.setIdentityPic(rs1.getString("IdentityPic"));
			user.setBloodType(rs1.getString("BloodType"));
			user.setAddress(rs1.getString("Address"));
			user.setHeight(rs1.getFloat("Height"));
			user.setWeight(rs1.getFloat("Weight"));
			user.setUrgencyContact(rs1.getString("UrgencyContact"));
			user.setUrgencyPhone(rs1.getString("UrgencyPhone"));
			
			return user;
		}
	}

	public UserBean AddBaseUser(UserBean user) throws SQLException
	{
		final String sql = "insert into Users (WechatID,Gender,WechatName,HeadImgUrl) values(?,?,?,?)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql))
		{
			ps1.setString(1, user.getWechatID());
			ps1.setInt(2, user.getGender().ordinal());
			ps1.setString(3, user.getWechatName());
			ps1.setString(4, user.getHeadImgUrl());
			ps1.executeUpdate();
			return user;
		}
	}
	
	public UserBean AddUser(UserBean user) throws SQLException
	{
		final String sql1 = "insert into Users (WechatID,UserName,Celphone,Email,Birth,Gender) values(?,?,?,?,?,?)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, user.getWechatID());
			ps1.setString(2, user.getUserName());
			ps1.setString(3, user.getCelphone());
			ps1.setString(4, user.getEmail());
			ps1.setDate(5, user.getBirth());
			ps1.setInt(6, user.getGender().ordinal());
			
			ps1.executeUpdate();

			return user;
		}
	}
	
	public UserBean UpdUser(UserBean user) throws SQLException
	{
		final String sql1 = "update Users set UserName=?,Celphone=?,Email=?,IdentityCard=?,"
				+ "IdentityPic=?,BloodType=?,Address=?,Height=?,Weight=?,UrgencyContact=?,UrgencyPhone=?"
				+ " where WechatID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
/*			System.out.println("--Dao.UpdUser--");
			System.out.println("getUserName="+user.getUserName());
			System.out.println("getCelphone="+user.getCelphone());
			System.out.println("getEmail="+user.getEmail());
			System.out.println("getIdentityCard="+user.getIdentityCard());
			System.out.println("getIdentityPic="+user.getIdentityPic());
			System.out.println("getBloodType="+user.getBloodType());
			System.out.println("getAddress="+user.getAddress());
			System.out.println("getHeight="+user.getHeight());
			System.out.println("getWeight="+user.getWeight());
			System.out.println("getUrgencyContact="+user.getUrgencyContact());
			System.out.println("getUrgencyPhone="+user.getUrgencyPhone());
			System.out.println("getWechatID="+user.getWechatID());*/
			
			ps1.setString(1, user.getUserName());
			ps1.setString(2, user.getCelphone());
			ps1.setString(3, user.getEmail());
			ps1.setString(4, user.getIdentityCard());
			ps1.setString(5, user.getIdentityPic());
			ps1.setString(6, user.getBloodType());
			ps1.setString(7, user.getAddress());
			ps1.setFloat(8, user.getHeight());
			ps1.setFloat(9, user.getWeight());
			ps1.setString(10, user.getUrgencyContact());
			ps1.setString(11, user.getUrgencyPhone());
			ps1.setString(12, user.getWechatID());
			
			ps1.executeUpdate();

			return user;
		}
	}
}
