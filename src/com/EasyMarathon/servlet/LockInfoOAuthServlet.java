package com.EasyMarathon.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		// �û�ͬ����Ȩ���ܻ�ȡ��code
		String code = request.getParameter("code");

		// �û�ͬ����Ȩ
		if (!"authdeny".equals(code))
		{
			// ��ȡ��ҳ��Ȩaccess_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil
					.getOauth2AccessToken("wxa6bb25947675b744",
							"c39ae4fc9da658a6642e2dd47626a45f", code);

			// ��ҳ��Ȩ�ӿڷ���ƾ֤
			String accessToken = weixinOauth2Token.getAccessToken();
			// �û���ʶ
			String openId = weixinOauth2Token.getOpenId();
			// ��ȡ�û���Ϣ
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken,
					openId);

			// ����Ҫ���ݵĲ���

			//session.setAttribute("snsUserInfo", snsUserInfo);
			request.setAttribute("snsUserInfo", snsUserInfo);
		}
		// ��ת��index.jsp
		
		request.getRequestDispatcher("lockInfo.jsp").forward(request,
				response);
	}
}
