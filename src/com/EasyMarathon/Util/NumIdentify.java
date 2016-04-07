package com.EasyMarathon.Util;

public class NumIdentify
{
	public native int Identify(String fname);

	static
	{
		System.loadLibrary("NumIdentify");
	}
}
