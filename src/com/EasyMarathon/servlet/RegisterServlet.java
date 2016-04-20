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

import com.EasyMarathon.bean.SNSUserInfo;
import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.bean.WeixinOauth2Token;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.UserDao;
import com.EasyMarathon.util.AdvancedUtil;

@WebServlet(urlPatterns = "/RegisterServlet", initParams =
{
	@WebInitParam(name="retrytime", value="60"),
	@WebInitParam(name="posibility", value="200"),
	@WebInitParam(name="total", value="2")
})
public class RegisterServlet extends HttpServlet
{
	private static final long serialVersionUID = -1847238807216447030L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");

		// 用户同意授权
		if (!"authdeny".equals(code))
		{
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil
					.getOauth2AccessToken("wxa6bb25947675b744",
							"c39ae4fc9da658a6642e2dd47626a45f", code);

			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			// 获取用户信息
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken,
					openId);
			session.setAttribute("snsUserInfo", snsUserInfo);
			System.out.println(snsUserInfo.getOpenId());
			// 设置要传递的参数
			Connection conn;
			String wechatID=snsUserInfo.getOpenId();
			conn = DaoBase.getConnection(true);
			UserDao userdao = new UserDao(conn);
			UserBean user=new UserBean();
			try
			{
				user=userdao.GetUser(wechatID);
				if(user==null)
				{
					DaoBase.close(conn, null, null);
					System.out.println("用户不存在，进入注册界面");
					response.sendRedirect("bg/register.jsp");
				}
				else
				{
					DaoBase.close(conn, null, null);
					System.out.println("用户存在进入商城");
					response.sendRedirect("bg/mainPage.jsp");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("报错");
				response.sendRedirect("bg/register.jsp");
			}
			
			

		}
		// 跳转到index.jsp
		
	}
}
