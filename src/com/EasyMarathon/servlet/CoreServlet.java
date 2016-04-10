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
 * ������ĺ�����
 */
@WebServlet(urlPatterns = "/wxcore", initParams = 
{
	@WebInitParam(name = "appID", value = "wxa6bb25947675b744"),
	@WebInitParam(name = "appsecret", value = "c39ae4fc9da658a6642e2dd47626a45f") 
})

public class CoreServlet extends HttpServlet
{
	private static final long serialVersionUID = 4440739483644821986L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// ΢�ż���ǩ��
		String signature = request.getParameter("signature");
		// ʱ���
		String timestamp = request.getParameter("timestamp");
		// �����
		String nonce = request.getParameter("nonce");
		// ����ַ���
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// ����У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ��
		if (SignUtil.checkSignature(signature, timestamp, nonce))
		{
			out.print(echostr);
		}
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
