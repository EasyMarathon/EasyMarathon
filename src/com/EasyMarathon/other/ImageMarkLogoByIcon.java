package com.EasyMarathon.other;   
  
import java.awt.AlphaComposite;   
import java.awt.Graphics2D;   
import java.awt.Image;   
import java.awt.RenderingHints;   
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;   
import java.awt.image.ColorConvertOp;
import java.io.File;   
import java.io.FileInputStream;   
import java.io.FileOutputStream;   
import java.io.InputStream;   
import java.io.OutputStream;   
  


import javax.imageio.ImageIO;   
import javax.swing.ImageIcon;   
  


import com.sun.image.codec.jpeg.JPEGCodec;   
import com.sun.image.codec.jpeg.JPEGImageDecoder;   
import com.sun.image.codec.jpeg.JPEGImageEncoder;   
  
/**  
 * 图片水印  
 */  
public class ImageMarkLogoByIcon {   
  
    /**  
     * @param args  
     */  
    public static void main(String[] args) {   
        String srcImgPath = "G:/电子商务/123.jpg";   
        String iconPath = "G:/电子商务/easyMarathon.png";   
        String targerPath = "G:/电子商务/1.jpg";    
        // 给图片添加水印   
        ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath);   

  
    }   
  
    /**  
     * 给图片添加水印  
     * @param iconPath 水印图片路径  
     * @param srcImgPath 源图片路径  
     * @param targerPath 目标图片路径  
     */  
    public static void markImageByIcon(String iconPath, String srcImgPath,   
            String targerPath) {   
        markImageByIcon(iconPath, srcImgPath, targerPath, null);   
    }   
  
    /**  
     * 给图片添加水印、可设置水印图片旋转角度  
     * @param iconPath 水印图片路径  
     * @param srcImgPath 源图片路径  
     * @param targerPath 目标图片路径  
     * @param degree 水印图片旋转角度  
     */  
    public static void markImageByIcon(String iconPath, String srcImgPath,   
            String targerPath, Integer degree) {   
        OutputStream os = null;   
        try {   
            Image srcImg = ImageIO.read(new File(srcImgPath));   
  
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),   
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB); 
            BufferedImage result=null;
         // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度   
            ImageIcon imgIcon = new ImageIcon(iconPath);
            double height=imgIcon.getIconHeight();
            double width=imgIcon.getIconWidth();
            double height1=buffImg.getHeight();
            double width1=buffImg.getWidth();
            if(height/width>height1/width1)
            {
            	height1=height1/width1*width;
            	width1=width;
            	
            }
           
            else
            {
            	width1=width1/height1*height;
            	height1=height;
            	
            }
            result = new BufferedImage((int)width1, (int)height1,  
                    BufferedImage.TYPE_INT_RGB); 
            
            /*result.getGraphics().drawImage(  
            		buffImg.getScaledInstance((int)width1, (int)height1,  
                            java.awt.Image.SCALE_SMOOTH), 0, 0, null); */
           // 得到画笔对象   
            // Graphics g= buffImg.getGraphics();   
            Graphics2D g = result.createGraphics();   
  
  
            g.drawImage(srcImg, 0, 0,(int)width1,(int)height1, null);  
            

            // 得到Image对象。   
            Image img = imgIcon.getImage();   
  
            /*float alpha = 0.5f; // 透明度   
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,   
                    alpha)); */  
  
            // 表示水印图片的位置   
            g.drawImage(img, 0, 0, null);   
  
            //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));   
  
            g.dispose();   
  
            os = new FileOutputStream(targerPath);   
            // 生成图片  
            srcImg.flush();
           
            ImageIO.write(result, "JPG", os);   
            os.flush();
            os.close();
            System.out.println("图片完成添加Icon印章。。。。。。");   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            try {   
                if (null != os)   
                    os.close();   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        }   
    }   
}  