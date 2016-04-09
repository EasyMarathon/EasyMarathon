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
		final String sql1 = "update Users set UserName=?,Celphone=?,Email=?,Birth=?,Gender=? where WechatID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, user.getUserName());
			ps1.setString(2, user.getCelphone());
			ps1.setString(3, user.getEmail());
			ps1.setDate(4, user.getBirth());
			ps1.setInt(5, user.getGender().ordinal());
			ps1.setString(6, user.getWechatID());
			
			ps1.executeUpdate();

			return user;
		}
	}
}
