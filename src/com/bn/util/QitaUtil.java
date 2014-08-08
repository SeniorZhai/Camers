package com.bn.util;

import android.graphics.Bitmap;

public class QitaUtil
{
	static float[][] JQBY_LJ= //浮雕效果
		   {
		     {0f,-4f,0f},
		     {-4f,17f,-4f},
		     {0f,-4f,0f}
		   }; 
	
	static float [][] BOX_RH={//锐化效果
		{0,-1f,0},
	     {-1f,5f,-1f},
	     {0,-1f,0}
     };	
	static float [][] BOX_PH={//普通的box的矩阵
		{1/9f,1/9f,1/9f},
		{1/9f,1/9f,1/9f},
		{1/9f,1/9f,1/9f}
     }; 
	
	public static Bitmap ruihuaprocess(Bitmap bm) {
		Bitmap bmTemp=ruihuaprocess(bm,BOX_RH);
		return bmTemp;
	}
	public static Bitmap fudiaoprocess(Bitmap bm)
	{
		Bitmap bitmap=ruihuaprocess(bm,JQBY_LJ);
		return bitmap;		
	}
	 public static Bitmap gaosiprocess(Bitmap bm) {     //模糊效果
			Bitmap bmTemp=ruihuaprocess(bm,BOX_PH);
			return bmTemp;
		}
	public static Bitmap ruihuaprocess(Bitmap bm, float[][] sobel) {
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
	      //处理RGB矩阵     根据不同的矩阵得到不同的RGB的数值
	       red=processMatrix(red,w,h,sobel);//单独色彩通道数据处理方法
	       green=processMatrix(green,w,h,sobel);  
	       blue=processMatrix(blue,w,h,sobel);        
	     //恢复图
	       int pixelsTarget[]=new int[w * h];
	       for (int j = 0; j < h; j++) {
	            for (int i = 0; i < w; i++) {           	
	                pixelsTarget[j * w + i]+=alpha[j * w + i]<<24;
	                pixelsTarget[j * w + i]+=red[j * w + i]<<16;
	                pixelsTarget[j * w + i]+=green[j * w + i]<<8;
	                pixelsTarget[j * w + i]+=blue[j * w + i];
	            }
	       }
	       result=Bitmap.createBitmap(pixelsTarget, w, h, Bitmap.Config.ARGB_8888); 
		return result;
	}
	private static int[] processMatrix(int[] rgb, int w, int h, float[][] sobel) {
		//滤镜处理RGB矩阵
		float[] temprgb=new float[w * h];
		int[] temprgbInt=new int[w * h];
		int span=sobel.length;   //3
		
		//对图片的每一个像素进行处理
		for(int i=1;i<=w-2;i++)
		{
			for(int j=1;j<=h-2;j++)
			{     //根据矩阵运算
				
				temprgb[j*w+i]=0;
				for(int k=0;k<span;k++)
				{
					for(int l=0;l<span;l++)
					{
						temprgb[j*w+i]+=sobel[k][l]*rgb[(j-1+k)*w+(i-1+l)];
					}					
				}
				if(temprgb[j*w+i]>=255)
				{
					temprgb[j*w+i]=255;
				}
				else if(temprgb[j*w+i]<=0)
				{
					temprgb[j*w+i]=0;
				}
                
               temprgbInt[j*w+i]=(int)temprgb[j*w+i];
				
			}
		}	
		return temprgbInt;
	}
	
	public static Bitmap dipian(Bitmap bm)               //底片效果
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
	       for (int j = 0; j < h; j++) {
	            for (int i = 0; i < w; i++) {           	
	            	red[j*w+i]=255-red[j*w+i];
	            	green[j*w+i]=255-green[j*w+i];
	            	blue[j*w+i]=255-blue[j*w+i];
	                pixelsTarget[j * w + i]+=alpha[j * w + i]<<24;
	                pixelsTarget[j * w + i]+=red[j * w + i]<<16;
	                pixelsTarget[j * w + i]+=green[j * w + i]<<8;
	                pixelsTarget[j * w + i]+=blue[j * w + i];
	            }
	       }   
	       result=Bitmap.createBitmap(pixelsTarget, w, h, Bitmap.Config.ARGB_8888); 
		return result;
	}
	
	
		public static Bitmap huidu(Bitmap bm)               //黑白效果
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
		            	double sCol=0;
		            	double y=0.299*red[j*w+i]+0.587*green[j*w+i]+0.114*blue[j*w+i];		            	
		            	red[j*w+i]=(int) (y+sCol*(red[j*w+i]-y));
		            	green[j*w+i]=(int) (y+sCol*(green[j*w+i]-y));
		            	blue[j*w+i]=(int) (y+sCol*(blue[j*w+i]-y));
		                pixelsTarget[j * w + i]+=alpha[j*w+i]<<24;
		                pixelsTarget[j * w + i]+=red[j*w+i]<<16;
		                pixelsTarget[j * w + i]+=green[j*w+i]<<8;
		                pixelsTarget[j * w + i]+=blue[j*w+i];
		            }
		       }       
		       result=Bitmap.createBitmap(pixelsTarget, w, h, Bitmap.Config.ARGB_8888); 
			return result;
		}
		
		public static Bitmap guangzhao(Bitmap bm)               //光照效果
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
		       for (int j = 1, length = h - 1; j < length; j++)
				{
					for (int i = 1, len = w - 1; i < len; i++)
					{
		               int tempPixel=pixels[j * w + i];   //将图像的像素的值里面的RGB  分离 出来
		               alpha[j * w + i]=(tempPixel >> 24) & 0xff;//向右移动24位，与0xff（11111111）进行与运算
		               red[j * w + i]=(tempPixel >>16) & 0xff;  //向右移动16位，与0xff（11111111）进行与运算
		               green[j * w + i]=(tempPixel >> 8) & 0xff;//向右移动8位，与0xff（11111111）进行与运算
		               blue[j * w + i]=(tempPixel)>>0 & 0xff;      //与0xff（11111111）进行与运算
		          }
		       }
		       
		       int centerX = w/2;
				int centerY = h/ 2;
				int radius = Math.min(centerX, centerY);
				final float strength = 150F; // 光照强度 100~150
		     //恢复图
		       int pixelsTarget[]=new int[w * h];
		       for (int j = 0; j < h; j++) 
		       {
		            for (int i = 0; i < w; i++) 
		           {          
		            	
		            
		            	     
		            int distance = (int) (Math.pow((centerY - j), 2) + Math.pow(centerX - i, 2));
						if (distance < radius * radius)
						{
							// 按照距离大小计算增加的光照值
							int resut = (int) (strength * (1.0 - Math.sqrt(distance) / radius));
							red[j*w+i] = red[j*w+i] + resut;
							green[j*w+i] = green[j*w+i] + resut;
							blue[j*w+i] = blue[j*w+i] + resut;
							
							red[j*w+i]=Math.max(0, Math.min(red[j*w+i],255));
				        	green[j*w+i]=Math.max(0,Math.min(255,green[j*w+i]));
				        	blue[j*w+i]=Math.max(0,Math.min(255,blue[j*w+i]));
							
						}						
		            			
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
