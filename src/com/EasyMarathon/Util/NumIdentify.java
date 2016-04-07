package com.EasyMarathon.Util;

import java.net.URI;
import java.net.URISyntaxException;

public class NumIdentify
{
	public native int Identify(String fname);

	static
	{
		String url = NumIdentify.class.getResource("").getPath();//.substring(1);
		String uri = "";
		try
		{
			uri = new URI(url).getPath();
		}
		catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.load(uri + System.mapLibraryName("NumIdentify"));
	}
}
