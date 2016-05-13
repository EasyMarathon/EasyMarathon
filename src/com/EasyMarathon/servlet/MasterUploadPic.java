package com.EasyMarathon.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.EasyMarathon.other.MD5;
import com.EasyMarathon.service.PicService;


public class MasterUploadPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MasterUploadPic() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void getPictureUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String eventID = null;
		String authorName = "";
		String filename = null;
		int price = 0;
		
		//��ô����ļ���Ŀ����  
        DiskFileItemFactory factory = new DiskFileItemFactory(); 
        
        String basePath = request.getSession().getServletContext().getRealPath("/");
        System.out.println("��Ŀ·��= "+basePath);
        String path = basePath+"bg\\UserPicture\\PayedPicture\\";
        factory.setRepository(new File(path));  
        factory.setSizeThreshold(1024*1024); 
        //��ˮƽ��API�ļ��ϴ�����  
        ServletFileUpload upload = new ServletFileUpload(factory); 
        upload.setHeaderEncoding("UTF-8");
        try {  
            //�����ϴ�����ļ�  
        	List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
        	
        	for(FileItem item : list){  
            	//��ȡ������������  
            	String name = item.getFieldName();  
            	//�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ  
                if(item.isFormField()){                     
                    //��ȡ�û�����������ַ��� ����Ϊ���ύ�������� �ַ������͵�  
                    String value = item.getString("UTF-8");
                    if(name.equals("authorName")){
                    	System.out.println("item.name:"+name);
                        System.out.println("item.value:"+value);
                        authorName = value;
                    }
                    else if(name.equals("eventID")){
                    	System.out.println("item.name:"+name);
                        System.out.println("item.value:"+value);
                        eventID = value;
                    	path += value+"\\";
                    }else if(name.equals("price")){
                    	System.out.println("item.name:"+name);
                        System.out.println("item.value:"+value);
                        price = Integer.parseInt(value);
                    }
                }  
            }
        	
            for(FileItem item : list){  
            	//��ȡ������������  
            	String name = item.getFieldName(); 
                if(!item.isFormField()){ //��ͼƬ          
                	//��ȡ·����  
                    String value = item.getName();
                    System.out.println("item.name:"+name);
                    System.out.println("item.value:"+value);
                    int start = value.lastIndexOf("\\");
                    //��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б��
                    filename = value.substring(start+1);
                    System.out.println("filename:"+filename);//filename������׺
                    System.out.println("path: "+path);
                    File fileParent = new File(path);
                    if  (!fileParent .exists()  && !fileParent.isDirectory())      
                    {       
                        fileParent.mkdirs();
                    }
                    //���ļ�������MD5
                    MD5 md5 = new MD5();
                    String time = Long.toString(System.currentTimeMillis());
                    filename=md5.md5Encode(filename+time)+".jpg";
                    System.out.println("New filename:"+filename);//filename������׺
                    File fileChild = new File(path,filename);
                    OutputStream out = new FileOutputStream(fileChild);  
                    InputStream in = item.getInputStream();  
                    int length = 0 ;  
                    byte [] buf = new byte[1024] ;  
                    System.out.println("��ȡ�ϴ��ļ����ܹ���������"+item.getSize());  
  
                    while( (length = in.read(buf) ) != -1){  
                        out.write(buf, 0, length);  
                    }
                    in.close();  
                    out.close();
                    
                    //д�����ݿ�
                    PicService picservice = new PicService();
                    System.out.println("fileChildl ·��="+fileChild.getAbsolutePath());
            		if (picservice.uploadPicService(fileChild, eventID,authorName,price))
            			request.getRequestDispatcher("bg/uploadsuccess.jsp").forward(request, response);
            		else
            			response.sendRedirect("bg/upLoadunsuccess.jsp");
                }  
            }
        }catch (FileUploadException e) {  
        	e.printStackTrace();  
        	response.sendRedirect("bg/upLoadunsuccess.jsp");
//        	request.getRequestDispatcher("bg/upLoadunsuccess.jsp").forward(request, response);
	    }  
	    catch (Exception e) {           
	        e.printStackTrace();  
	        response.sendRedirect("bg/upLoadunsuccess.jsp");
	    }
	}
}
