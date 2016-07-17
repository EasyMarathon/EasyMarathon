package com.EasyMarathon.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class IOTest 
{
public static void main(String[] args)
{
	try{
	FileWriter fileWritter = new FileWriter("G:\\face\\11.txt",true);
	
	  BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	  
	  for(int i=0;i<5;i++)
	  {
		  String data="hah "+" "+"1\n";
		 
           bufferWritter.write(data);
	  }
	  String data="temp.jpg"+" "+"2";
	  bufferWritter.write(data);
	  bufferWritter.close();
	  fileWritter.close();
	
	}
	
	catch(IOException e)
	{
		e.printStackTrace();
	}
}
}
