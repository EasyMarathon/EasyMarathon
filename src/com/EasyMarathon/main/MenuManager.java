package com.EasyMarathon.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.EasyMarathon.Button.*;
import com.EasyMarathon.token.Token;
import com.EasyMarathon.util.CommonUtil;
import com.EasyMarathon.util.MenuUtil;

/**
 * 菜单管理器类
 */
public class MenuManager
{
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	private static Menu getMenu()
	{
		ViewButton btn11 = new ViewButton();
		btn11.setName("我要上传");
		btn11.setType("view");
		btn11.setUrl(
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa6bb25947675b744&"
						+ "redirect_uri=http%3A%2F%2F120.27.106.188%2Feasyrun%2FoauthServlet&response_type=code&"
						+ "scope=snsapi_userinfo&state=STATE#wechat_redirect");

		ClickButton btn12 = new ClickButton();
		btn12.setName("个人中心");
		btn12.setType("click");
		btn12.setKey("personalInfo");

		ViewButton btn21 = new ViewButton();
		btn21.setName("杭州马拉松");
		btn21.setType("view");
		btn21.setUrl("http://www.hzim.org/");

		ViewButton btn22 = new ViewButton();
		btn22.setName("上海国际半程马拉松赛");
		btn22.setType("view");
		btn22.setUrl("http://www.shmarathon.com");

		ViewButton btn23 = new ViewButton();
		btn23.setName("其他赛事");
		btn23.setType("view");
		btn23.setUrl("http://m.taobao.com");

		ClickButton btn31 = new ClickButton();
		btn31.setName("热门话题");
		btn31.setType("click");
		btn31.setKey("hot");

		ViewButton btn32 = new ViewButton();
		btn32.setName("装备指南");
		btn32.setType("view");
		btn32.setUrl("http://m.taobao.com");

		ClickButton btn33 = new ClickButton();
		btn33.setName("跑城攻略");
		btn33.setType("click");
		btn33.setKey("tip");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("族友专区");
		mainBtn1.setSub_button(new Button[] { btn11, btn12 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("照片查询");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("易跑部落");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args)
	{
		// 第三方用户唯一凭证
		String appId = "wxa6bb25947675b744";
		// 第三方用户唯一凭证密钥
		String appSecret = "c39ae4fc9da658a6642e2dd47626a45f";

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token)
		{
			// 创建菜单
			System.out.println(token);
			// boolean result = MenuUtil.deleteMenu(token.getAccessToken());
			boolean result = MenuUtil.createMenu(getMenu(),
					token.getAccessToken());
			String ans = MenuUtil.getMenu(token.getAccessToken());
			System.out.println(ans);

			// 判断菜单创建结果
			if (result)
			{
				System.out.println("菜单创建成功！");
				log.info("菜单创建成功！");
			}
			else
			{
				log.info("菜单创建失败！");
				System.out.println("菜单创建失败！");
			}
		}
	}
}
