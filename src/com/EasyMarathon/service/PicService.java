package com.EasyMarathon.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import org.apache.struts2.ServletActionContext;

import com.EasyMarathon.bean.PicBean;
import com.EasyMarathon.bean.UserBean;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.FreePicDao;
import com.EasyMarathon.dao.PictureDao;
import com.EasyMarathon.other.MD5;
import com.EasyMarathon.util.FaceAlignment;
import com.EasyMarathon.util.Facerecognition;
import com.EasyMarathon.util.NumIdentify;
import com.EasyMarathon.bean.Athlete;
import com.EasyMarathon.bean.ConfirmData;

public class PicService {

	Connection conn;

	public static String bytes2Hex(byte[] src) {
		char[] res = new char[src.length * 2];
		final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		for (int i = 0, j = 0; i < src.length; i++) {
			res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
			res[j++] = hexDigits[src[i] & 0x0f];
		}

		return new String(res);
	}

	/* 给照片打水印 */
	public static void watermark(String iconPath, String srcImgPath, String targerPath) {
		OutputStream os = null;

		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			BufferedImage result = null;
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);
			double height = imgIcon.getIconHeight();
			double width = imgIcon.getIconWidth();
			double height1 = buffImg.getHeight();
			double width1 = buffImg.getWidth();
			if (height / width > height1 / width1) {
				height1 = height1 / width1 * width;
				width1 = width;
			} else {
				width1 = width1 / height1 * height;
				height1 = height;
			}
			result = new BufferedImage((int) width1, (int) height1, BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			Graphics2D g = result.createGraphics();
			g.drawImage(srcImg, 0, 0, (int) width1, (int) height1, null);
			// 得到Image对象。
			Image img = imgIcon.getImage();
			// 表示水印图片的位置
			g.drawImage(img, 0, 0, null);
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			srcImg.flush();
			ImageIO.write(result, "JPG", os);
			os.flush();
			System.out.println("图片完成添加Icon印章。。。。。。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/* 保存原图 */
	public static void Original(String srcImgPath, String targerPath) {
		OutputStream os = null;

		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = buffImg.createGraphics();

			g.drawImage(srcImg, 0, 0, srcImg.getWidth(null), srcImg.getHeight(null), null);

			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			srcImg.flush();

			ImageIO.write(buffImg, "JPG", os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean uploadPicService(File picture, String eventId, String authorName, int price, HttpServletRequest request)
	{
		System.out.println("摄影师上传照片中...");
		conn = DaoBase.getConnection(true);
		PictureDao picturedao = new PictureDao(conn);
		Integer eventID = null;
		try
		{
			eventID = Integer.parseInt(eventId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		String srcImgPath = picture.getAbsolutePath();// 已经包含照片名
		// System.out.println(srcImgPath);
		NumIdentify numidentify = new NumIdentify();
		int aID = numidentify.GetID(srcImgPath);
		System.out.println(aID);
		int ans=0;
		String name=null;
		String pictureURL=null;
		if (aID == -1)
		{
			ArrayList<ConfirmData> athlets=new ArrayList<ConfirmData>();
			System.out.println("无法进行号码牌识别，即将进行人脸识别！");
	       // String basePath = request.getSession().getServletContext().getRealPath("/");
	        //System.out.println("项目路径= "+basePath);
	        String path = "C:\\ProgramData\\face\\"+"tmp";
			FaceAlignment ni = new FaceAlignment();
    		System.out.println("run dll");
    		try{
    			System.out.println(1);
    			ans = ni.cutface(srcImgPath, path);
    			System.out.println("ans:"+ans);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
			System.out.println("图片对齐完毕");
			
			athlets=AthleteService.GetAthlete();
			if(athlets.size()==0)
			{
				System.out.println("找不到对应的人");
				return false;
			}
			System.out.println("运动员数为："+athlets.size());
			int i=0;
			File file =new File("C:\\ProgramData\\face\\libface_lfw.txt");
			try{
				
			
			
			  if(!file.exists()){
			       file.createNewFile();
			      }
			  FileWriter fileWritter = new FileWriter(file);

			  for(i=0;i<athlets.size();i++)
			  {
				  String pname = athlets.get(i).getIdentityPic();
				  int size=pname.length();
				  String data=pname.substring(0, size-4)+"-0.jpg "+i+"\r\n";
				  System.out.println("照片名："+athlets.get(i).getIdentityPic());
				  System.out.println("照片号："+String.valueOf(i));
				  System.out.println(data);
				  fileWritter.write(data);
				 
			  }
			  String data="tmp-0.jpg"+" "+i;
			  fileWritter.write(data);
			  fileWritter.flush();
			  fileWritter.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("成功写入数据！");
			Facerecognition face=new Facerecognition();
			ans=face.recFace(i, "test", "C:\\ProgramData\\face\\config\\netv8_2_deploy_lfw_0107.prototxt", "C:\\ProgramData\\face\\config\\recog0107_iter_2840000.caffemodel", i+1);
			//ans=0;
			name = athlets.get(ans).getName();//这里填识别出来的姓名
			pictureURL="bg\\UserPicture\\UserInfo\\"+athlets.get(ans).getIdentityPic();
			//pictureURL="C:\\Program Files\\Tomcat\\wcapps\\easyrun\\bg\\UserPicture\\UserInfo\\"+athlets.get(ans).getIdentityPic();
			aID=athlets.get(ans).getAthleteID();
			//file.delete();
			request.setAttribute("AthleteID", aID+"(人脸识别结果)");
		}
		else{
			System.out.println("号码牌识别成功！");
			UserBean user=UserService.findUser(eventID, aID);
			name=user.getUserName();
			pictureURL="bg\\UserPicture\\UserInfo\\"+user.getIdentityPic();
			request.setAttribute("AthleteID", aID+"(号码牌识别结果)");
			
		}
		//int aID=1206;
		//request.setAttribute("AthleteID", aID);
		
		//******此处添加人脸识别代码，返回参数：1.姓名  2.匹配出来的图片路径*******//
		
	
		request.setAttribute("Name", name);
		request.setAttribute("PictureURL", pictureURL);
		
		
		String iconPath = request.getSession().getServletContext()
				.getRealPath("/") + "bg/icon/EasyMarathon.png";
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "bg/imageCamera" + "/" + eventID + "/";
		System.out.println("摄影师目录：" + path);
		System.out.println("水印目录：" + iconPath);

		String picID = null;
		MD5 md5 = new MD5();
		String imgStr = null;
		try
		{
			FileInputStream fis = new FileInputStream(picture);
			System.out.println(1);
			byte[] bytes = new byte[fis.available()];
			System.out.println(2);
			imgStr = bytes2Hex(bytes);
			System.out.println(3);
			System.out.println("转换成功字符串成功！");
			fis.read(bytes);
			fis.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		// 对密码进行MD5加密
		try
		{
			picID = md5.md5Encode(imgStr);
			PicBean pic = new PicBean();
			pic.setPicID(picID);
			pic.setPrice(price);
			pic.setAuthor(authorName);
			picturedao.AddPic(eventID, aID, pic);
			System.out.println("生成照片名成功！");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally{
			DaoBase.close(conn, null, null);
		}

		String pathInitial = path + "initial" + "/";
		String pathwater = path + "watermark" + "/";
		try
		{
			File fileLocation = new File(pathInitial);
			if (!fileLocation.exists())
			{
				fileLocation.mkdirs();
				System.out.println("创建目录成功");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			File fileLocation = new File(pathwater);
			if (!fileLocation.exists())
				fileLocation.mkdirs();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		pathInitial = pathInitial + picID + ".jpg";
		pathwater = pathwater + picID + ".jpg";
		Original(srcImgPath, pathInitial);
		watermark(iconPath, srcImgPath, pathwater);
		
		return true;

	}

	public boolean uploadPicforUserService(File picture, String eventId, String wechatID, String path) {

		conn = DaoBase.getConnection(true);
		FreePicDao fpdao = new FreePicDao(conn);
		System.out.println("用户上传照片中。。。");

		int eventID = Integer.parseInt(eventId);
		String srcImgPath = picture.getAbsolutePath();// 已经包含照片名
		System.out.println(srcImgPath);
		System.out.println("用户目录：" + path);

		String picID = null;

		MD5 md5 = new MD5();

		String imgStr = null;
		System.out.println(0);
		try {
			FileInputStream fis = new FileInputStream(picture);
			System.out.println(1);
			byte[] bytes = new byte[fis.available()];
			System.out.println(2);
			imgStr = bytes2Hex(bytes);
			System.out.println(3);
			System.out.println("转换成功字符串成功！");
			fis.read(bytes);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			picID = md5.md5Encode(imgStr);
			fpdao.AddPic(eventID, wechatID, picID);
			System.out.println("数据库写入成功！");

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DaoBase.close(conn, null, null);
		}

		String pathInitial = path + "initial" + "/";
		try {
			File fileLocation = new File(pathInitial);
			if (!fileLocation.exists()) {
				fileLocation.mkdirs();
				System.out.println("创建目录成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pathInitial = pathInitial + picID + ".jpg";
		Original(srcImgPath, pathInitial);
		System.out.println("上传成功！");
		
		return true;

	}

}
