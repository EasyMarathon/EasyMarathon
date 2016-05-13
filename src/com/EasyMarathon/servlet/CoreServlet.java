package com.EasyMarathon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EasyMarathon.service.CoreService;
import com.EasyMarathon.util.SignUtil;

/**
 * 请求处理的核心类
 */
@WebServlet(urlPatterns = "/wxcore")

public class CoreServlet extends HttpServlet
{
	private static final long serialVersionUID = 4440739483644821986L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce))
		{
			out.print(echostr);
		}
		out.print("false");
		out.close();
		out = null;
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String respXml = CoreService.processRequest(request);

		PrintWriter out = response.getWriter();
		out.print(respXml);
		out.close();
	}
}
