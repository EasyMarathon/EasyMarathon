package com.EasyMarathon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			return user;
		}
	}

	public UserBean AddUser(String wechatID, String userName, String celphone) throws SQLException
	{
		final String sql1 = "insert into Users (WechatID,UserName,Celphone) values(?,?,?)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, wechatID);
			ps1.setString(2, userName);
			ps1.setString(3, celphone);
			
			ps1.executeUpdate();

			UserBean user = new UserBean();
			user.setWechatID(wechatID);
			user.setUserName(userName);
			user.setCelphone(celphone);
			return user;
		}
	}
}
