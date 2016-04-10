package com.EasyMarathon.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.struts2.ServletActionContext;

import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.PictureDao;
import com.EasyMarathon.other.ImageMarkLogoByIcon;
import com.EasyMarathon.other.MD5;
import com.EasyMarathon.util.NumIdentify;

public class PicService {
	
	Connection conn;

	public String byte2hex(byte[] b) // 二行制转字符串
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + "%";
		}
		return hs.toUpperCase();
	}
	
	/*给照片打水印*/
	public static void watermark(String iconPath, String srcImgPath,String targerPath) {   
        OutputStream os = null;   
        
        try {   
            Image srcImg = ImageIO.read(new File(srcImgPath));   
  
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),   
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB); 
            BufferedImage result=null;
         // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度   
            ImageIcon imgIcon = new ImageIcon(iconPath);
            double height=imgIcon.getIconHeight();
            double width=imgIcon.getIconWidth();
            double height1=buffImg.getHeight();
            double width1=buffImg.getWidth();
            if(height/width>height1/width1)
            {
            	height1=height1/width1*width;
            	width1=width;
            }
            else
            {
            	width1=width1/height1*height;
            	height1=height;       	
            }
            result = new BufferedImage((int)width1, (int)height1,  
                    BufferedImage.TYPE_INT_RGB); 
           // 得到画笔对象    
            Graphics2D g = result.createGraphics();    
            g.drawImage(srcImg, 0, 0,(int)width1,(int)height1, null);             
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
	/*保存原图*/
	public static void Original(String srcImgPath,String targerPath )
	{
        OutputStream os = null;   
        
        try {   
            Image srcImg = ImageIO.read(new File(srcImgPath));   
  
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),   
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB); 
            Graphics2D g = buffImg.createGraphics();   
            
            g.drawImage(srcImg, 0, 0,srcImg.getWidth(null),srcImg.getHeight(null), null);   
  
            g.dispose();   
            os = new FileOutputStream(targerPath);   
            // 生成图片  
            srcImg.flush();
           
            ImageIO.write(buffImg, "JPG", os);   
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

	public boolean uploadPicService(File picture, String eventId)  {
		conn = DaoBase.getConnection(true);
		PictureDao picturedao=new PictureDao(conn);
		Integer eventID = null;
		try
		{
			eventID=Integer.parseInt(eventId);		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String srcImgPath=picture.getAbsolutePath();//已经包含照片名
		//System.out.println(srcImgPath);
		NumIdentify numidentify=new NumIdentify();
		int aID = numidentify.GetID(srcImgPath);
		System.out.println(aID);
		if(aID==-1)
		{
			System.out.println("图片未识别");
			return false;
		}
		//int aID=120;
		String iconPath=ServletActionContext.getServletContext().getRealPath("/")+"icon/EasyMarathon.png";
		String path=ServletActionContext.getServletContext().getRealPath("/")+"imageCamera"+"/"+eventID+"/";
		System.out.println("摄影师目录："+path);
		System.out.println("水印目录："+iconPath);

		String picID = null;
		MD5 md5 = new MD5();
		String imgStr=null;
		try{
		FileInputStream fis = new FileInputStream(picture);
		byte[] bytes = new byte[fis.available()];
		imgStr = byte2hex(bytes);
		fis.read(bytes);
		fis.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
			// 对密码进行MD5加密
		try{
			picID = md5.md5Encode(imgStr);
			picturedao.AddPic(eventID, aID, picID);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		String pathInitial = path + "initial"+"/";
		String pathwater=path+"watermark"+"/";
		try {
			File fileLocation = new File(pathInitial);
			if (!fileLocation.exists())
				fileLocation.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			File fileLocation = new File(pathwater);
			if (!fileLocation.exists())
				fileLocation.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pathInitial=pathInitial+picID+".jpg";
		pathwater=pathwater+picID+".jpg";
		Original(srcImgPath,pathInitial);
        watermark(iconPath, srcImgPath, pathwater);
        DaoBase.close(conn, null, null);
			return true;
	
	}
	
	public boolean uploadPicforUserService(File picture, String eventId)  {
		conn = DaoBase.getConnection(true);
		PictureDao picturedao=new PictureDao(conn);
		
		int eventID=Integer.parseInt(eventId);		
		String srcImgPath=picture.getAbsolutePath();//已经包含照片名
		//System.out.println(srcImgPath);
		NumIdentify numidentify=new NumIdentify();
		int aID = numidentify.GetID(srcImgPath);
		System.out.println(aID);
		if(aID==-1)
		{
			System.out.println("图片未识别");
			return false;
		}
		//int aID=120;
		String path=ServletActionContext.getServletContext().getRealPath("/")+"imageCamera"+"/"+eventID+"/";
		System.out.println("摄影师目录："+path);
	
		String picID = null;
		MD5 md5 = new MD5();
		String imgStr=null;
		try{
		FileInputStream fis = new FileInputStream(picture);
		byte[] bytes = new byte[fis.available()];
		imgStr = byte2hex(bytes);
		fis.read(bytes);
		fis.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
			// 对密码进行MD5加密
		try{
			picID = md5.md5Encode(imgStr);
			picturedao.AddPic(eventID, aID, picID);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		String pathInitial = path + "initial"+"/";
		try {
			File fileLocation = new File(pathInitial);
			if (!fileLocation.exists())
				fileLocation.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pathInitial=pathInitial+picID+".jpg";		
		Original(srcImgPath,pathInitial);
        DaoBase.close(conn, null, null);
			return true;
	
	}

}
