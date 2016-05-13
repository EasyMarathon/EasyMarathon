package com.EasyMarathon.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.UserDao;

public class UserService {
	

	public boolean register(String userName,String celphone,String email,String wechatID)
	{
		System.out.println("进入service");
		Connection conn;
		conn = DaoBase.getConnection(true);
		System.out.println(1);
		UserBean userbean=new UserBean();
		System.out.println(2);
		System.out.println(3);
		UserDao userdao = new UserDao(conn);
		System.out.println(4);
		boolean flag;
		try
		{	
			userbean.setWechatID(wechatID);
			System.out.println(5);
			userbean.setCelphone(celphone);
			System.out.println(6);
			userbean.setEmail(email);
			System.out.println(7);
			userbean.setUserName(userName);
			userbean.setBirth(null);
			userbean.setGender(1);			
			System.out.println(8);
			userdao.AddUser(userbean);
			System.out.println("添加用户成功。。。");
			flag=true;
		}
		catch (SQLException e)
		{
			System.out.println(1);
			e.printStackTrace();
			flag=false;
		}
		finally
		{
			
			DaoBase.close(conn, null, null);
			
		}

		return flag;
	}

}
