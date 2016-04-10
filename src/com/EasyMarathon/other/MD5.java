package com.EasyMarathon.other;

import java.security.MessageDigest;
public class MD5 {
	//MD5µ•œÚº”√‹
	public String md5Encode(String inStr)throws Exception{
		MessageDigest md5=null;
		try{
			md5=MessageDigest.getInstance("MD5");
		}catch(Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		byte[] byteArray=inStr.getBytes("UTF-8");
		byte[] md5Byte=md5.digest(byteArray);
		StringBuffer hexValue =new StringBuffer();
		for(int i=0;i<md5Byte.length;i++){
			int val=((int)md5Byte[i])&0xff;
			if(val<16){
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
	/*
	 * test main
	 * 
	public static void main(String args[]) throws Exception{
		String str=new String("myNameIsGCC");
		System.out.println("origin:"+str);
		System.out.println("MD5:"+md5Encode(str));
	}*/
}