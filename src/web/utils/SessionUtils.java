package web.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class SessionUtils
{
	    //生成图片的宽度 
		private static final int IMAGE_WIDTH = 70;
		
		//生成图片的高度
		private static final int IMAGE_HEIGHT = 30;
		
		//生成字符的个数
		private static final int CHAR_NUM = 4;
		
		//生成随机线条的个数
		private static final int LINE_NUM = 8;
		
		//字体的大小
		private static final int FONT_SIZE = 24;
		
		//所有可能的随机字符
		private static final String CHAR_DATA_BASE = "1234567890abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
		
		//字体库
		private static final String[] FONT_DATA ={"Courier New","楷体","Hobo Std","SketchFlow Print","Tekton Pro","方正舒体","宋体"};
		
		public static void getVerifyCodeImage()
		{
	    	//在内存中创建一个画布
	    	BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
	    	
	    	//获得一个画笔
	    	Graphics pen = image.getGraphics();
	    	
	    	//设置画笔的颜色
	    	pen.setColor(new Color(randomNum(200, 230),randomNum(200, 230),randomNum(200, 230)));
	    	
	    	//画一个矩形作为背景框
	    	pen.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
	    	
	    	//在画布上画上随机字符
	    	StringBuffer buffer = new StringBuffer();
	    	for(int i = 0 ; i < CHAR_NUM ; i++)
	    	{
	    		pen.setColor(new Color(randomNum(50, 180),randomNum(50, 180),randomNum(50, 180)));
	    		pen.setFont(new Font(FONT_DATA[0], Font.BOLD, FONT_SIZE));
	    		char a = CHAR_DATA_BASE.charAt(randomNum(0, CHAR_DATA_BASE.length()));
	    		buffer.append(a);
	    		pen.drawString(String.valueOf(a), (IMAGE_WIDTH/CHAR_NUM)*i+2, randomNum(IMAGE_HEIGHT-FONT_SIZE+8,FONT_SIZE));
	    	}
	    	
	    	//画上随机的线条
	    	for (int i = 0; i < LINE_NUM; i++)
			{
	    		pen.setColor(new Color(randomNum(50, 180),randomNum(50, 180),randomNum(50, 180)));
	    		pen.drawLine(randomNum(0,IMAGE_WIDTH), randomNum(0, IMAGE_HEIGHT), randomNum(0,IMAGE_WIDTH), randomNum(0, IMAGE_HEIGHT));
			}
	    	
//	    	request.getSession().setAttribute("verifyCode", buffer.toString());
//	    	
//	    	//将图片发送到客户端
//	    	OutputStream out = response.getOutputStream(); 
//	    	
//	    	//压缩图片格式
//	    	//将图片压缩到哪里
//	    	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//	    	//压缩那张图片
//	    	encoder.encode(image);
	    	
	    	File file = new File("verifyCode.png");
	    	try
			{
				ImageIO.write(image, "png", file);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		private static int randomNum(int minValue,int maxValue)
	    {
	    	//获取随机值
	    	Random random = new Random();
	    	int randomValue = 0;
	    	randomValue = random.nextInt(maxValue-minValue)+minValue;
	    	return randomValue;
	    }
}
