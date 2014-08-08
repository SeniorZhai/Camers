package com.bn.util;

import android.graphics.Bitmap;

public class ZengqiangUtil
{
	
	static int p1old;
	static int p2old;
	static float b;
	static int g1old=200;
	static int g2old=250;
	 
	public static Bitmap fanzhuanPicture(Bitmap bm)
	{
		 p1old=150;
		 p2old=200;
		 b=2.0f;
		 Bitmap bitmap=duibiduBitmap(bm);
		 return bitmap;
	}
	public static Bitmap yejianPicture(Bitmap bm)
	{
		 p1old=170;
		 p2old=200;
		 b=0.5f;
		 Bitmap bitmap=duibiduBitmap(bm);
		 return bitmap;
	}
	public static Bitmap zidongPicture(Bitmap bm)
	{
		p1old=150;
		p2old=200;
		b=3f;
		Bitmap bitmap=duibiduBitmap(bm);
		return bitmap;
	}
	public static Bitmap pinghengPicture(Bitmap bm)
	{
		p1old=100;
		p2old=200;
		b=1.5f;
		Bitmap bitmap=duibiduBitmap(bm);
		return bitmap;
	}
	public static Bitmap duibiduBitmap(Bitmap bm)
	{      		  
		   Bitmap result=null;
		   int w=bm.getWidth();
		   int h=bm.getHeight();
		   int[] pixels = new int[w * h];	 
		   bm.getPixels(pixels, 0, w, 0, 0, w, h);		   
		   //获取ARGB矩阵
	    int alpha[]=new int[w * h];
	    int red[]=new int[w * h];
	    int green[]=new int[w * h];
	    int blue[]=new int[w * h];
	    
	    for (int j = 0; j < h; j++) 
	    {
	        for (int i = 0; i < w; i++) 
	        {	        	
	            int tempPixel=pixels[j * w + i];
	            alpha[j * w + i]=(tempPixel >> 24) & 0xff;
	            red[j * w + i]=(tempPixel >> 16) & 0xff;
	            green[j * w + i]=(tempPixel >> 8) & 0xff;
	            blue[j * w + i]=(tempPixel) & 0xff;
	        }
	    }
	    //恢复图
	    float a=(255-b*(p2old-p1old))/(255-(p2old-p1old));
	    float p2new=(b*(p2old-p1old)+a*p1old);
	    int pixelsTarget[]=new int[w * h];
	    for (int j = 0; j < h; j++) {
	         for (int i = 0; i < w; i++) {	        	 
	        	 if(red[j*w+i]>=0&red[j*w+i]<=p1old)
	        	 {	        		 
	        		 red[j*w+i]= (int) (a*red[j*w+i]);	        	
	        	 }
	        	 if(green[j*w+i]>=0&green[j*w+i]<=p1old)
	        	 {	        		
	        		 green[j*w+i]= (int) (a*green[j*w+i]);	        	
	        	 }
	        	 if(blue[j*w+i]>=0&blue[j*w+i]<=p1old)
	        	 {	        		 
	        		 blue[j*w+i]=(int) (a*blue[j*w+i]);	        	
	        	 }
	        	 if(red[j*w+i]>p1old&red[j*w+i]<p2old)
	        	 {	        		 
	        		 red[j*w+i]=(int)(a*p1old+(red[j*w+i]-p1old)*b);
	        	 }
	        	 if(green[j*w+i]>p1old&green[j*w+i]<p2old)
	        	 {	        		 
	        		 green[j*w+i]=(int) (a*p1old+(green[j*w+i]-p1old)*b);
	        	 }
	        	 if(blue[j*w+i]>p1old&blue[j*w+i]<p2old)
	        	 {	        		 
	        		 blue[j*w+i]=(int)(a*p1old+(blue[j*w+i]-p1old)*b);
	        	 }
	        	 if(red[j*w+i]>=p2old&red[j*w+i]<255)
	        	 {	        		 	        		 
	        		 red[j*w+i]=(int)(p2new+(red[j*w+i]-p2old)*a);	        	
	        	 }
	        	 if(green[j*w+i]>=p2old&green[j*w+i]<255)
	        	 {      			        		 
	        		 green[j*w+i]=(int)(p2new+(green[j*w+i]-p2old)*a);
	        	 }
	        	 if(blue[j*w+i]>=p2old&blue[j*w+i]<255)
	        	 {	        		 	        		 
	        		 blue[j*w+i]=(int)(p2new+(blue[j*w+i]-p2old)*a);	        		
	        	 }
	        	 red[j*w+i]=Math.max(0, Math.min(red[j*w+i],255));
	        	 green[j*w+i]=Math.max(0,Math.min(255,green[j*w+i]));
	        	 blue[j*w+i]=Math.max(0,Math.min(255, blue[j*w+i]));
	        	 
	             pixelsTarget[j * w + i]+=alpha[j * w + i]<<24;
	             pixelsTarget[j * w + i]+=red[j * w + i]<<16;
	             pixelsTarget[j * w + i]+=green[j * w + i]<<8;
	             pixelsTarget[j * w + i]+=blue[j * w + i];
	         }
	    }
	    result=Bitmap.createBitmap(pixelsTarget, w, h, Bitmap.Config.ARGB_8888); 	    
		   return result;
	}
	public static Bitmap shineiPicture(Bitmap bm) {
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
	            	red[j * w + i]=(int)(red[j * w + i]*(1+0.2));
	            	green[j * w + i]=(int)(green[j * w + i]*(1+0.2));
	            	blue[j * w + i]=(int)(blue[j * w + i]*(1+0.2));
	            	 red[j*w+i]=Math.max(0, Math.min(red[j*w+i],255));
		        	 green[j*w+i]=Math.max(0,Math.min(255,green[j*w+i]));
		        	 blue[j*w+i]=Math.max(0,Math.min(255, blue[j*w+i]));
	                pixelsTarget[j * w + i]+=alpha[j * w + i]<<24;
	                pixelsTarget[j * w + i]+=red[j * w + i]<<16;
	                pixelsTarget[j * w + i]+=green[j * w + i]<<8;
	                pixelsTarget[j * w + i]+=blue[j * w + i];
	            }
	       }
	       result=Bitmap.createBitmap(pixelsTarget, w, h, Bitmap.Config.ARGB_8888); 
		return result;
	}
	
	public static Bitmap huiduchuangkouBitmap(Bitmap bm)
	{      		  
		   Bitmap result=null;
		   int w=bm.getWidth();
		   int h=bm.getHeight();
		   int[] pixels = new int[w * h];	 
		   bm.getPixels(pixels, 0, w, 0, 0, w, h);
		   
		   //获取ARGB矩阵
	    int alpha[]=new int[w * h];
	    int red[]=new int[w * h];
	    int green[]=new int[w * h];
	    int blue[]=new int[w * h];
	    
	    for (int j = 0; j < h; j++) 
	    {
	        for (int i = 0; i < w; i++) 
	        {	        	
	            int tempPixel=pixels[j * w + i];
	            alpha[j * w + i]=(tempPixel >> 24) & 0xff;
	            red[j * w + i]=(tempPixel >> 16) & 0xff;
	            green[j * w + i]=(tempPixel >> 8) & 0xff;
	            blue[j * w + i]=(tempPixel) & 0xff;
	        }
	    }
	    //恢复图
	  
	    int pixelsTarget[]=new int[w * h];
	    for (int j = 0; j < h; j++) {
	         for (int i = 0; i < w; i++) {	        	 
	        	
	        	 if(red[j*w+i]>=g1old&red[j*w+i]<=g2old)
	        	 {	        		 	        		 
	        		 red[j*w+i]=255;	        	
	        	 }
	        	 if(green[j*w+i]>=g1old&green[j*w+i]<=g2old)
	        	 {      			        		 
	        		 green[j*w+i]=255;
	        	 }
	        	 if(blue[j*w+i]>=g1old&blue[j*w+i]<=g2old)
	        	 {	        		 	        		 
	        		 blue[j*w+i]=255;	        		
	        	 }
	        	 red[j*w+i]=Math.max(0, Math.min(red[j*w+i],255));
	        	 green[j*w+i]=Math.max(0,Math.min(255,green[j*w+i]));
	        	 blue[j*w+i]=Math.max(0,Math.min(255, blue[j*w+i]));
	             pixelsTarget[j * w + i]+=alpha[j * w + i]<<24;
	             pixelsTarget[j * w + i]+=red[j * w + i]<<16;
	             pixelsTarget[j * w + i]+=green[j * w + i]<<8;
	             pixelsTarget[j * w + i]+=blue[j * w + i];
	         }
	    }	   
	    result=Bitmap.createBitmap(pixelsTarget, w, h, Bitmap.Config.ARGB_8888); 	    
		   return result;
	}
	
	
}
