package com.EasyMarathon.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.UserDao;

public class UserService {
	public boolean register(String userName,String celphone,String email,String wechatID)
	{
		Connection conn;
		conn = DaoBase.getConnection(true);
		UserBean userbean = null;
		UserDao userdao = new UserDao(conn);
		try
		{	
			userbean = userdao.GetUser(wechatID);
			userbean.setCelphone(celphone);
			userbean.setEmail(email);
			userbean.setUserName(userName);
			userdao.UpdUser(userbean);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			DaoBase.close(conn, null, null);
		}

		return true;
	}

}
