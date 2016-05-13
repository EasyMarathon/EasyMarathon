package com.EasyMarathon.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.EasyMarathon.bean.FreePicBean;
import com.EasyMarathon.bean.GongzhonghaoInfo;
import com.EasyMarathon.bean.SNSUserInfo;
import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.bean.WeixinOauth2Token;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.FreePicDao;
import com.EasyMarathon.dao.UserDao;
import com.EasyMarathon.util.AdvancedUtil;

@WebServlet(urlPatterns = "/ShoppingServlet", initParams =
{
	@WebInitParam(name="retrytime", value="60"),
	@WebInitParam(name="posibility", value="200"),
	@WebInitParam(name="total", value="2")
})
public class ShoppingServlet extends HttpServlet
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
					System.out.println("���û���д�����ݿ�");
					userdao.AddBaseUser(new_user);
					ArrayList<FreePicBean> freePicList = getFreePicture(conn);//Ĭ������
					ArrayList<FreePicBean> freePicListBySell = getFreePicBySellNum(conn);//����������������
					ArrayList<FreePicBean> freePicListByTime = getFreePicByMoney(conn);//�ϴ�ʱ������
					
					DaoBase.close(conn, null, null);
					
					session.setAttribute("freePicList", freePicList);
					session.setAttribute("freePicListBySell", freePicListBySell);
					session.setAttribute("freePicListByTime", freePicListByTime);
					response.sendRedirect("bg/mainPage.jsp");
				}
				else
				{
					ArrayList<FreePicBean> freePicList = getFreePicture(conn);//Ĭ������
					ArrayList<FreePicBean> freePicListBySell = getFreePicBySellNum(conn);//����������������
					ArrayList<FreePicBean> freePicListByTime = getFreePicByMoney(conn);//�ϴ�ʱ������
					DaoBase.close(conn, null, null);
					System.out.println("�û����ڽ����̳�");
					session.setAttribute("freePicList", freePicList);
					session.setAttribute("freePicListBySell", freePicListBySell);
					session.setAttribute("freePicListByTime", freePicListByTime);
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
	}
	
	//������������ȡ��ƽ̨���������Ƭ��Ϣ��Ĭ������
	private ArrayList<FreePicBean> getFreePicture(Connection conn){
		FreePicDao freePicDao = new FreePicDao(conn);
		return freePicDao.GetAllDefault();
	} 
	
	//����������������
	private ArrayList<FreePicBean> getFreePicBySellNum(Connection conn){
		FreePicDao freePicDao = new FreePicDao(conn);
		return freePicDao.GetAllBySellNum();
	} 
	
	//�ϴ�ʱ������
		private ArrayList<FreePicBean> getFreePicByMoney(Connection conn){
			FreePicDao freePicDao = new FreePicDao(conn);
			return freePicDao.GetAllByTime();
		} 
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request,response);
	}
}
