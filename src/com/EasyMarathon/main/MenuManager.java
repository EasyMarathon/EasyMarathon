package com.EasyMarathon.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.EasyMarathon.Button.*;
import com.EasyMarathon.bean.GongzhonghaoInfo;
import com.EasyMarathon.bean.Token;
import com.EasyMarathon.util.CommonUtil;
import com.EasyMarathon.util.MenuUtil;

/**
 * �˵���������
 */
public class MenuManager
{
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * ����˵��ṹ
	 * 
	 * @return
	 */
	private static Menu getMenu()
	{
		ViewButton btn11 = new ViewButton();
		btn11.setName("��Ҫ�ϴ�");
		btn11.setType("view");
		btn11.setUrl(
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ GongzhonghaoInfo.appID+"&"
						+ "redirect_uri="
						+ GongzhonghaoInfo.URL
						+ "oauthServlet&response_type=code&"
						+ "scope=snsapi_userinfo&state=STATE#wechat_redirect");
		//http://7891556.cn/easyrun/
		ViewButton btn12 = new ViewButton();
		btn12.setName("�����̳�");
		btn12.setType("view");
		btn12.setUrl(
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ GongzhonghaoInfo.appID+"&"
						+ "redirect_uri="
						+ GongzhonghaoInfo.URL
						+ "ShoppingServlet&response_type=code&"
						+ "scope=snsapi_userinfo&state=STATE#wechat_redirect");

		ViewButton btn13 = new ViewButton();
		btn13.setName("���±���");
		btn13.setType("view");
		btn13.setUrl(
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ GongzhonghaoInfo.appID+"&"
						+ "redirect_uri="
						+ GongzhonghaoInfo.URL
						+ "MarathonRegisterServlet&response_type=code&"
						+ "scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		ClickButton btn21 = new ClickButton();
		btn21.setName("��Ƭ��ѯ");
		btn21.setType("click");
		btn21.setKey("findPicture");

		ClickButton btn31 = new ClickButton();
		btn31.setName("���Ż���");
		btn31.setType("click");
		btn31.setKey("hot");

		ViewButton btn32 = new ViewButton();
		btn32.setName("װ��ָ��");
		btn32.setType("view");
		btn32.setUrl("http://m.taobao.com");

		ClickButton btn33 = new ClickButton();
		btn33.setName("�ܳǹ���");
		btn33.setType("click");
		btn33.setKey("tip");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("����ר��");
		mainBtn1.setSub_button(new Button[] { btn11, btn12,btn13 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("���ܲ���");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, btn21, mainBtn3 });

		return menu;
	}

	public static void main(String[] args)
	{
		// �������û�Ψһƾ֤
		String appId = GongzhonghaoInfo.appID;
		// �������û�Ψһƾ֤��Կ
		String appSecret = GongzhonghaoInfo.appsecret;

		// ���ýӿڻ�ȡƾ֤
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token)
		{
			// �����˵�
			System.out.println(token);
			//boolean result = MenuUtil.deleteMenu(token.getAccessToken());
			boolean result = MenuUtil.createMenu(getMenu(),token.getAccessToken());
			String ans = MenuUtil.getMenu(token.getAccessToken());
			System.out.println(ans);

			// �жϲ˵��������
			if (result)
			{
				System.out.println("�˵������ɹ���");
				log.info("�˵������ɹ���");
			}
			else
			{
				log.info("�˵�����ʧ�ܣ�");
				System.out.println("�˵�����ʧ�ܣ�");
			}
		}
	}
}
