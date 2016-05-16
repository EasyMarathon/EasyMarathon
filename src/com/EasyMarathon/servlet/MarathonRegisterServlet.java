package com.EasyMarathon.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.EasyMarathon.bean.GongzhonghaoInfo;
import com.EasyMarathon.bean.SNSUserInfo;
import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.bean.WeixinOauth2Token;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.UserDao;
import com.EasyMarathon.util.AdvancedUtil;

@WebServlet(urlPatterns = "/MarathonRegisterServlet", initParams =
{
	@WebInitParam(name="retrytime", value="60"),
	@WebInitParam(name="posibility", value="200"),
	@WebInitParam(name="total", value="2")
})
public class MarathonRegisterServlet extends HttpServlet
{
	private static final long serialVersionUID = -1847238807216447030L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String code = request.getParameter("code");

		if (!"authdeny".equals(code))
		{
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil
					.getOauth2AccessToken(GongzhonghaoInfo.appID,
							GongzhonghaoInfo.appsecret, code);
			String accessToken = ""; 
			String openId = ""; 
			if(weixinOauth2Token==null){
				accessToken = (String) session.getAttribute("accessToken");
				openId = (String) session.getAttribute("openId");
			}
			else{
				accessToken = weixinOauth2Token.getAccessToken();
				session.setAttribute("accessToken", accessToken);
				openId = weixinOauth2Token.getOpenId();
				session.setAttribute("openId", openId);
			}
			
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken,openId);
			session.setAttribute("snsUserInfo", snsUserInfo);
			System.out.println(snsUserInfo.getOpenId());
			Connection conn;
			String wechatID=snsUserInfo.getOpenId();
			conn = DaoBase.getConnection(true);
			UserDao userdao = new UserDao(conn);
			
			try
			{
				UserBean user=userdao.GetUser(wechatID);
				if(user==null)
				{
					System.out.println("这是新用户...");
					UserBean new_user=new UserBean();
					new_user.setWechatName(snsUserInfo.getNickname());
					new_user.setHeadImgUrl(snsUserInfo.getHeadImgUrl());
					new_user.setWechatID(wechatID);
					if(snsUserInfo.getSex()==2){
						new_user.setGender(1);
					}
					else{
						new_user.setGender(0);
					}
					System.out.println(new_user.getGender());
					userdao.AddBaseUser(new_user);
					DaoBase.close(conn, null, null);
					System.out.println("新用户写入数据库...");
					request.setAttribute("snsUserInfo", snsUserInfo);
					request.getRequestDispatcher("bg/marathonRegister.jsp").forward(request, response);
				}
				else if(checkIfPerfect(user))
				{
					DaoBase.close(conn, null, null);
					System.out.println("老用户，信息已完善，跳转赛事选择界面");
					request.setAttribute("snsUserInfo", snsUserInfo);
					request.getRequestDispatcher("/MarathonRegister2").forward(request, response);
				}
				else if(!checkIfPerfect(user)){
					DaoBase.close(conn, null, null);
					System.out.println("老用户，信息未完善");
					request.setAttribute("snsUserInfo", snsUserInfo);
					request.getRequestDispatcher("bg/marathonRegister.jsp").forward(request, response);;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("未知错误");
				response.sendRedirect("bg/error.jsp");
			}
		}
	}

	protected boolean checkIfPerfect(UserBean user){
		String IdentityCard = user.getIdentityCard();
		String IdentityPic = user.getIdentityPic();
		String BloodType = user.getBloodType();
		String Address = user.getAddress();
		float Height = user.getHeight();
		float Weight = user.getWeight();
		String UrgencyContact = user.getUrgencyContact();
		String UrgencyPhone = user.getUrgencyPhone();
		if(IdentityCard!=null && IdentityPic != null 
		   && BloodType!=null && Address!=null
		   && Height!=0 && Weight!=0
		   && UrgencyContact!=null && UrgencyPhone!=null){
			return true;
		}
		return false;
	}
}
