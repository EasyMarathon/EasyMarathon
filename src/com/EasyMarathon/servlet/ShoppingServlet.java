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
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");

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
					System.out.println("新用户，写入数据库");
					userdao.AddBaseUser(new_user);
					ArrayList<FreePicBean> freePicList = getFreePicture(conn);//默认排序
					ArrayList<FreePicBean> freePicListBySell = getFreePicBySellNum(conn);//销量（人气）优先
					ArrayList<FreePicBean> freePicListByTime = getFreePicByMoney(conn);//上传时间排序
					
					DaoBase.close(conn, null, null);
					
					session.setAttribute("freePicList", freePicList);
					session.setAttribute("freePicListBySell", freePicListBySell);
					session.setAttribute("freePicListByTime", freePicListByTime);
					response.sendRedirect("bg/mainPage.jsp");
				}
				else
				{
					ArrayList<FreePicBean> freePicList = getFreePicture(conn);//默认排序
					ArrayList<FreePicBean> freePicListBySell = getFreePicBySellNum(conn);//销量（人气）优先
					ArrayList<FreePicBean> freePicListByTime = getFreePicByMoney(conn);//上传时间排序
					DaoBase.close(conn, null, null);
					System.out.println("用户存在进入商城");
					session.setAttribute("freePicList", freePicList);
					session.setAttribute("freePicListBySell", freePicListBySell);
					session.setAttribute("freePicListByTime", freePicListByTime);
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
	}
	
	//辅助函数，获取本平台所有免费照片信息，默认排序
	private ArrayList<FreePicBean> getFreePicture(Connection conn){
		FreePicDao freePicDao = new FreePicDao(conn);
		return freePicDao.GetAllDefault();
	} 
	
	//销量（人气）排序
	private ArrayList<FreePicBean> getFreePicBySellNum(Connection conn){
		FreePicDao freePicDao = new FreePicDao(conn);
		return freePicDao.GetAllBySellNum();
	} 
	
	//上传时间排序
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
