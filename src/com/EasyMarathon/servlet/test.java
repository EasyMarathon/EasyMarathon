package com.EasyMarathon.servlet;

public class test {
	static String ss="3123    asdasd";
	public static void main(String args[])
	{
		String s1=ss.substring(ss.lastIndexOf(" ")+1);
		String s2=ss.substring(0, ss.lastIndexOf(" "));
		System.out.println(s1);
		System.out.println(s2);
	}

}
