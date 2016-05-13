package com.EasyMarathon.action;

import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.service.UserService;

public class UserAction {
	UserBean user;

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}
	public String register()
	{
		UserService userservice=new UserService();
		System.out.println("用户的ID为："+user.getWechatID());
		System.out.println("用户的手机为："+user.getCelphone());
		System.out.println("用户的邮箱为："+user.getEmail());
		if(userservice.register(user.getUserName(), user.getCelphone(), user.getEmail(), user.getWechatID()))
		{
			return "success";
		}
		else
		{
			return "fail";
		}
	}
	

}
