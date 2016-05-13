package com.EasyMarathon.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.struts2.ServletActionContext;

import com.EasyMarathon.bean.PicBean;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.FreePicDao;
import com.EasyMarathon.dao.PictureDao;
import com.EasyMarathon.other.MD5;
import com.EasyMarathon.util.NumIdentify;

public class PicService
{

	Connection conn;

	public static String bytes2Hex(byte[] src){         
        char[] res = new char[src.length*2];    
        final char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};    
        for(int i=0,j=0; i<src.length; i++){    
            res[j++] = hexDigits[src[i] >>>4 & 0x0f];    
            res[j++] = hexDigits[src[i] & 0x0f];    
        }    
            
        return new String(res);    
    }   

	/* 给照片打水印 */
	public static void watermark(String iconPath, String srcImgPath,
			String targerPath)
	{
		OutputStream os = null;

		try
		{
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			BufferedImage result = null;
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);
			double height = imgIcon.getIconHeight();
			double width = imgIcon.getIconWidth();
			double height1 = buffImg.getHeight();
			double width1 = buffImg.getWidth();
			if (height / width > height1 / width1)
			{
				height1 = height1 / width1 * width;
				width1 = width;
			}
			else
			{
				width1 = width1 / height1 * height;
				height1 = height;
			}
			result = new BufferedImage((int) width1, (int) height1,
					BufferedImage.TYPE_INT_RGB);
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
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (null != os)
					os.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/* 保存原图 */
	public static void Original(String srcImgPath, String targerPath)
	{
		OutputStream os = null;

		try
		{
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = buffImg.createGraphics();

			g.drawImage(srcImg, 0, 0, srcImg.getWidth(null),
					srcImg.getHeight(null), null);

			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			srcImg.flush();

			ImageIO.write(buffImg, "JPG", os);
			os.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (null != os)
					os.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public boolean uploadPicService(File picture, String eventId, String authorName, int price)
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
		if (aID == -1)
		{
			System.out.println("图片未识别");
			return false;
		}
		// int aID=120;
		String iconPath = ServletActionContext.getServletContext()
				.getRealPath("/") + "bg/icon/EasyMarathon.png";
		String path = ServletActionContext.getServletContext().getRealPath("/")
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
		DaoBase.close(conn, null, null);
		return true;

	}

	public boolean uploadPicforUserService(File picture, String eventId,String wechatID,String path)
	{
		
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

		try
		{
			picID = md5.md5Encode(imgStr);
			fpdao.AddPic(eventID, wechatID,picID);
			System.out.println("数据库写入成功！");
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		String pathInitial = path + "initial" + "/";
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
		pathInitial = pathInitial + picID + ".jpg";
		Original(srcImgPath, pathInitial);
		System.out.println("上传成功！");
		DaoBase.close(conn, null, null);
		return true;

	}
	
	/*
	 * public boolean uploadPicforUserService(File picture, String eventId,String wechatID)
	{
		//TODO:wrong implement
		System.out.println(1);
		conn = DaoBase.getConnection(true);
		FreePicDao fpdao = new FreePicDao(conn);
		System.out.println("用户上传照片中。。。");

		int eventID = Integer.parseInt(eventId);
		String srcImgPath = picture.getAbsolutePath();// 已经包含照片名
		System.out.println(srcImgPath);
		String path = ServletActionContext.getServletContext().getRealPath("/")
				+ "bg/imageCamera" + "\\" + eventID+"\\";
		System.out.println("用户目录：" + path);

		String picID = null;
		
		MD5 md5 = new MD5();
		
		String imgStr = null;
		System.out.println(0);
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
			fpdao.AddPic(eventID, wechatID,picID);
			System.out.println("生成照片名成功！");
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		String pathInitial = path + "initial" + "/";
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
		pathInitial = pathInitial + picID + ".jpg";
		Original(srcImgPath, pathInitial);
		System.out.println("上传成功！");
		DaoBase.close(conn, null, null);
		return true;

	}
	 * */

}
