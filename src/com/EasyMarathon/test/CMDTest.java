package com.EasyMarathon.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  
  
public class CMDTest {  

  

    	 public void runbat() {
    	        String cmd = "cmd /c start G:\\face\\facerecognize\\test.bat";// pass
    	        try {
    	            Process ps = Runtime.getRuntime().exec(cmd);
    	            ps.waitFor();
    	        } catch (IOException ioe) {
    	            ioe.printStackTrace();
    	        }
    	        catch (InterruptedException e) {
    	            // TODO Auto-generated catch block
    	            e.printStackTrace();
    	        }
    	        System.out.println("child thread donn");
    	    }

    	    public static void main(String[] args) {
    	    	CMDTest test1 = new CMDTest();
    	        test1.runbat();
    	        System.out.println("main thread");
    	    }
    
} 