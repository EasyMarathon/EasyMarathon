package com.EasyMarathon.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.EasyMarathon.Button.*;
import com.EasyMarathon.token.Token;
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
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa6bb25947675b744&"
						+ "redirect_uri=http%3A%2F%2F120.27.106.188%2Feasyrun%2FoauthServlet&response_type=code&"
						+ "scope=snsapi_userinfo&state=STATE#wechat_redirect");

		ClickButton btn12 = new ClickButton();
		btn12.setName("��������");
		btn12.setType("click");
		btn12.setKey("personalInfo");

		ViewButton btn21 = new ViewButton();
		btn21.setName("����������");
		btn21.setType("view");
		btn21.setUrl("http://www.hzim.org/");

		ViewButton btn22 = new ViewButton();
		btn22.setName("�Ϻ����ʰ����������");
		btn22.setType("view");
		btn22.setUrl("http://www.shmarathon.com");

		ViewButton btn23 = new ViewButton();
		btn23.setName("��������");
		btn23.setType("view");
		btn23.setUrl("http://m.taobao.com");

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
		mainBtn1.setSub_button(new Button[] { btn11, btn12 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("��Ƭ��ѯ");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("���ܲ���");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args)
	{
		// �������û�Ψһƾ֤
		String appId = "wxa6bb25947675b744";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "c39ae4fc9da658a6642e2dd47626a45f";

		// ���ýӿڻ�ȡƾ֤
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token)
		{
			// �����˵�
			System.out.println(token);
			// boolean result = MenuUtil.deleteMenu(token.getAccessToken());
			boolean result = MenuUtil.createMenu(getMenu(),
					token.getAccessToken());
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
