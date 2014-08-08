package com.bn.util;

import android.graphics.Bitmap;

public class LzpUtil {
	    
	public static Bitmap laozhaopian(Bitmap bm)               //黑白效果
	{
		Bitmap result=null;
		int w=bm.getWidth();
		int h=bm.getHeight();
		int []  pixels=new int[w*h];
		//将图像的每个像素读到数组中
		bm.getPixels(pixels, 0, w, 0, 0, w, h);
		
		//定义RGB的矩阵		
	       int alpha[]=new int[w * h];
	       int red[]=new int[w * h];
	       int green[]=new int[w * h];
	       int blue[]=new int[w * h];	   
	       
	     //初始化相应的矩阵
	       for (int j = 0; j < h; j++) 
	       {
	           for (int i = 0; i < w; i++) 
	           {
	               int tempPixel=pixels[j * w + i];   //将图像的像素的值里面的RGB  分离 出来
	               alpha[j * w + i]=(tempPixel >> 24) & 0xff;//向右移动24位，与0xff（11111111）进行与运算
	               red[j * w + i]=(tempPixel >>16) & 0xff;  //向右移动16位，与0xff（11111111）进行与运算
	               green[j * w + i]=(tempPixel >> 8) & 0xff;//向右移动8位，与0xff（11111111）进行与运算
	               blue[j * w + i]=(tempPixel)>>0 & 0xff;      //与0xff（11111111）进行与运算
	          }
	       }	       
	     //恢复图
	       int pixelsTarget[]=new int[w * h];
	       for (int j = 0; j < h; j++) 
	       {
	            for (int i = 0; i < w; i++) 
	           {           	
	             
	            	red[j*w+i]=(int) (0.393 * red[j*w+i]+0.769*green[j*w+i]+0.189*blue[j*w+i]);
	            	green[j*w+i]=(int) (0.349 * red[j*w+i]+0.686*green[j*w+i]+0.168*blue[j*w+i]);
	            	blue[j*w+i]=(int) (0.272 * red[j*w+i]+0.534*green[j*w+i]+0.131*blue[j*w+i]);
	            	 red[j*w+i]=Math.max(0, Math.min(red[j*w+i],255));
		        	 green[j*w+i]=Math.max(0,Math.min(255,green[j*w+i]));
		        	 blue[j*w+i]=Math.max(0,Math.min(255, blue[j*w+i]));
	                pixelsTarget[j * w + i]+=alpha[j*w+i]<<24;
	                pixelsTarget[j * w + i]+=red[j*w+i]<<16;
	                pixelsTarget[j * w + i]+=green[j*w+i]<<8;
	                pixelsTarget[j * w + i]+=blue[j*w+i];
	            }
	       }       
	       result=Bitmap.createBitmap(pixelsTarget, w, h, Bitmap.Config.ARGB_8888); 
		return result;
	}
}
