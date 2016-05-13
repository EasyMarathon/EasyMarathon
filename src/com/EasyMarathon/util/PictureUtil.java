package com.EasyMarathon.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PictureUtil {
	private static String path = "C:\\easyrun\\UserPicture\\";
	
	public static void show(String fileName,HttpServletRequest request,HttpServletResponse response){  
        File file=new File(path+fileName);  
        if(file.exists()&&!"".equals(fileName)){  
            try {  
                DataOutputStream dos=new DataOutputStream(response.getOutputStream());  
                DataInputStream dis=new DataInputStream(new FileInputStream(file.getAbsolutePath()));  
                byte[] data=new byte[2048];  
                while((dis.read(data))!=-1){  
                    dos.write(data);  
                    dos.flush();  
                }  
                dis.close();  
                dos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }
}
