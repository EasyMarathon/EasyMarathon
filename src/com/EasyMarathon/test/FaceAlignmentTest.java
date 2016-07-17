package com.EasyMarathon.test;

import com.EasyMarathon.util.FaceAlignment;
public class FaceAlignmentTest {

	public static void main(String[] args)
	{
		
		String fname = "G:\\face\\img_0006.JPG";
		String dname="G:\\face\\marathonIMG\\alignment\\2";
		FaceAlignment ni = new FaceAlignment();
		System.out.println("run dll");
		try{
			int ans = ni.cutface(fname, dname);
			System.out.println("ans:"+ans);
			System.getProperty("java.library.path");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	/*public static int test()
	{
		String fname = NumIdentify.curpath + "134.jpg";
		System.out.println("pic path:"+fname);
		try
		{
			NumIdentify ni = new NumIdentify();
			System.out.println("run dll");
			int ans = ni.GetID(fname);
			System.out.println("ans:"+ans);
			return ans;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -999;
		}
	}*/
}
