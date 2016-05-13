package com.EasyMarathon.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.EasyMarathon.bean.GongzhonghaoInfo;
import com.EasyMarathon.bean.SNSUserInfo;
import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.bean.WeixinOauth2Token;
import com.EasyMarathon.util.AdvancedUtil;

@WebServlet(urlPatterns = "/LockInfoOAuthServlet", initParams =
{
	@WebInitParam(name="retrytime", value="60"),
	@WebInitParam(name="posibility", value="200"),
	@WebInitParam(name="total", value="2")
})
public class LockInfoOAuthServlet {
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
					.getOauth2AccessToken(GongzhonghaoInfo.appID,
							GongzhonghaoInfo.appsecret, code);

			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			// 获取用户信息
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken,
					openId);

			// 设置要传递的参数

			//session.setAttribute("snsUserInfo", snsUserInfo);
			request.setAttribute("snsUserInfo", snsUserInfo);
		}
		// 跳转到index.jsp
		
		request.getRequestDispatcher("bg/lockInfo.jsp").forward(request,
				response);
	}
}
