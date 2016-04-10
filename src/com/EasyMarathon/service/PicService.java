package com.EasyMarathon.service;

import java.io.*;

import org.apache.struts2.ServletActionContext;

import com.EasyMarathon.dao.PictureDao;
import com.EasyMarathon.other.MD5;

public class PicService {
	private PictureDao 		picturedao;

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

	public boolean uploadPicService(File picture, int eventID) throws IOException {
		picturedao = null;
		int aID = 0;
		String path = ServletActionContext.getServletContext().getRealPath("/");
		FileInputStream fis = new FileInputStream(picture);
		byte[] bytes = new byte[fis.available()];
		fis.read(bytes);
		fis.close();
		String imgStr = byte2hex(bytes);
		MD5 md5 = new MD5();
		String picID = null;
		try {
			// 对密码进行MD5加密
			picID = md5.md5Encode(imgStr);
					picturedao.AddPic(eventID, aID, picID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println(picture);
			InputStream in = new FileInputStream(picture);
			String dir = path + picID + "/";
			try {
				File fileLocation = new File(dir);
				if (!fileLocation.exists())
					fileLocation.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String fileName1 = "initial.jpg";
			String fileName2 = "watermark.jpg";
			File uploadFile1 = new File(dir, fileName1);
			File uploadFile2 = new File(dir, fileName2);
			OutputStream out1 = new FileOutputStream(uploadFile1);
			OutputStream out2 = new FileOutputStream(uploadFile2);
			byte[] buffer = new byte[1024 * 1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out1.write(buffer, 0, length);
				out2.write(buffer, 0, length);
			}
			in.close();
			out1.close();
			out2.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("upload picture failed");
			return false;
		}
	}

}
