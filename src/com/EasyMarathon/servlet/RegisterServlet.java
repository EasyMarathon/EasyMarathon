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
			session.setAttribute("snsUserInfo", snsUserInfo);
			System.out.println(snsUserInfo.getOpenId());
			// ����Ҫ���ݵĲ���
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
					System.out.println("�û������ڣ�����ע�����");
					response.sendRedirect("bg/register.jsp");
				}
				else
				{
					DaoBase.close(conn, null, null);
					System.out.println("�û����ڽ����̳�");
					response.sendRedirect("bg/mainPage.jsp");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("����");
				response.sendRedirect("bg/register.jsp");
			}
			
			

		}
		// ��ת��index.jsp
		
	}
}
