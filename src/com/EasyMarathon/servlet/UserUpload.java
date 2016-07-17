package com.EasyMarathon.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.struts2.ServletActionContext;

import com.EasyMarathon.bean.WeixinMedia;
import com.EasyMarathon.bean.WeixinOauth2Token;
import com.EasyMarathon.dao.DaoBase;
import com.EasyMarathon.dao.FreePicDao;
import com.EasyMarathon.other.MD5;
import com.EasyMarathon.service.PicService;
import com.EasyMarathon.util.AdvancedUtil;
import com.EasyMarathon.util.CommonUtil;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileUploadException;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload; 

public class UserUpload extends HttpServlet{

	private List<String> filenames=new ArrayList<String>();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("--------< uploadforUser >--------");
		System.out.println();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			getPictureUrl(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void getPictureUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String eventID = null;
		String wechatID = null;
		String filename = null;
		//获得磁盘文件条目工厂  
        DiskFileItemFactory factory = new DiskFileItemFactory(); 
        
        String basePath = request.getSession().getServletContext().getRealPath("/");
        System.out.println("项目路径= "+basePath);
        String path = basePath+"bg\\UserPicture\\freePicture\\";
        //String path = "C:\\easyrun\\UserPicture\\";
        factory.setRepository(new File(path));  
        factory.setSizeThreshold(1024*1024); 
        //高水平的API文件上传处理  
        ServletFileUpload upload = new ServletFileUpload(factory); 
        upload.setHeaderEncoding("UTF-8");
        try {  
            //可以上传多个文件  
        	List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
        	
        	for(FileItem item : list){  
            	//获取表单的属性名字  
            	String name = item.getFieldName();  
            	//如果获取的 表单信息是普通的 文本 信息  
                if(item.isFormField()){                     
                    //获取用户具体输入的字符串 ，因为表单提交过来的是 字符串类型的  
                    String value = item.getString("UTF-8");
                    if(name.equals("wechatID")){
                    	System.out.println("item.name:"+name);
                        System.out.println("item.value:"+value);
                        wechatID = value;
                    	//request.setAttribute("wechatID", value);
                    }
                    else if(name.equals("eventID")){
                    	System.out.println("item.name:"+name);
                        System.out.println("item.value:"+value);
                        eventID = value;
                    	//request.setAttribute("eventID", value);
                    	path += value+"\\";
                    }
                }  
            }
        	
            for(FileItem item : list){  
            	//获取表单的属性名字  
            	String name = item.getFieldName(); 
                if(!item.isFormField()){ //是图片          
                	//获取路径名  
                    String value = item.getName();
                    System.out.println("item.name:"+name);
                    System.out.println("item.value:"+value);
                    int start = value.lastIndexOf("\\");
                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠
                    filename = value.substring(start+1);
                    System.out.println("filename:"+filename);//filename包含后缀
                    System.out.println("path: "+path);
                    File fileParent = new File(path);
                    if  (!fileParent .exists()  && !fileParent.isDirectory())      
                    {       
                        fileParent.mkdirs();
                    }
                    //给文件命名加MD5
                    MD5 md5 = new MD5();
                    String time = Long.toString(System.currentTimeMillis());
                    filename=md5.md5Encode(filename+time)+".jpg";
                    System.out.println("New filename:"+filename);//filename包含后缀
                    File fileChild = new File(path,filename);
                    OutputStream out = new FileOutputStream(fileChild);  
                    InputStream in = item.getInputStream();  
                    int length = 0 ;  
                    byte [] buf = new byte[1024] ;  
                    System.out.println("获取上传文件的总共的容量："+item.getSize());  
  
                    while( (length = in.read(buf) ) != -1){  
                        out.write(buf, 0, length);  
                    }
                    in.close();  
                    out.close();
                }  
            }
        }catch (FileUploadException e) {  
        	e.printStackTrace();  
        	request.getRequestDispatcher("bg/upLoadunsuccess.jsp").forward(request, response);
	    }  
	    catch (Exception e) {           
	        e.printStackTrace();  
	        request.getRequestDispatcher("bg/upLoadunsuccess.jsp").forward(request, response);
	    }
        //写入数据库
        Connection conn = DaoBase.getConnection(true);
        FreePicDao fpdao = new FreePicDao(conn);
        try {
			fpdao.AddPic(Integer.parseInt(eventID), wechatID,filename);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("bg/upLoadunsuccess.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.getRequestDispatcher("bg/upLoadunsuccess.jsp").forward(request, response);
			e.printStackTrace();
		}
        finally{
        	System.out.println("上传成功！");
    		DaoBase.close(conn, null, null);
        }
        request.getRequestDispatcher("bg/uploadsuccess.jsp").forward(request, response);
	}
}
