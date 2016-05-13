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

/**
 * 授权后的回调请求处理
 */

@WebServlet(urlPatterns = "/oauthServlet", initParams =
{
	@WebInitParam(name="retrytime", value="60"),
	@WebInitParam(name="posibility", value="200"),
	@WebInitParam(name="total", value="2")
})
public class OAuthServlet extends HttpServlet
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
		System.out.println("CODE="+code);
		// 用户同意授权
		if (!"authdeny".equals(code))
		{
			// 获取网页授权access_token
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
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken,
					openId);
/*			System.out.println("SNS="+snsUserInfo);
			System.out.println("OpenID="+snsUserInfo.getOpenId());*/
			// 设置要传递的参数
			session.setAttribute("snsUserInfo", snsUserInfo);
			
			try
			{
				Connection conn;
				String wechatID=snsUserInfo.getOpenId();
				conn = DaoBase.getConnection(true);
				UserDao userdao = new UserDao(conn);
				UserBean user=userdao.GetUser(wechatID);
				if(user==null){
					System.out.println("用户不存在...");
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
					System.out.println("新用户添加成功...");
					DaoBase.close(conn, null, null);
				}
			}catch(Exception e){
				System.out.println("error message="+e.getMessage());
			}
			request.getRequestDispatcher("GoUploadPicServlet").forward(request, response);
		}
	}
}
