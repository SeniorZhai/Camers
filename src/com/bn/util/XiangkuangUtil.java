package com.bn.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class XiangkuangUtil
{
	
    public static Bitmap alphaLayer(Bitmap bm,Bitmap overlay)
    {
    	Bitmap result=null;
		int w=bm.getWidth();
		int h=bm.getHeight();
		int []  pixels=new int[w*h];
		//将图像的每个像素读到数组中
		bm.getPixels(pixels, 0, w, 0, 0, w, h);
		
		int width=overlay.getWidth();    //边框图片
		int height=overlay.getHeight();
		float Xscal=w*1f/width;
		float Yscal=h*1f/height;
		Matrix mx=new Matrix();
		mx.postScale(Xscal, Yscal);
		Bitmap overlaycopy=Bitmap.createBitmap(overlay,0,0,width,height,mx,true);
		int []opixels=new int[w*h];
		overlaycopy.getPixels(opixels,0,w,0,0,w,h);
		
		float nalpha=0;
		
		//定义RGB的矩阵
	       int alpha[]=new int[w * h];
	       int red[]=new int[w * h];
	       int green[]=new int[w * h];
	       int blue[]=new int[w * h];
	       
	       int oalpha[]=new int[w*h];
	       int ored[]=new int[w*h];
	       int ogreen[]=new int[w*h];
	       int oblue[]=new int[w*h];
	       
	       int newalpha[]=new int[w*h];
	       int newred[]=new int[w*h];
	       int newgreen[]=new int[w*h];
	       int newblue[]=new int[w*h];
	       
	     //初始化相应的矩阵
	       for (int j = 0; j < h; j++) 
	       {
	           for (int i = 0; i < w; i++) 
	           {
	               int tempPixel=pixels[j * w + i];   //将图像的像素的值里面的RGB  分离 出来
	               int otempPixel=opixels[j*w+i];
	               alpha[j * w + i]=(tempPixel >> 24) & 0xff;//向右移动24位，与0xff（11111111）进行与运算
	               red[j * w + i]=(tempPixel >>16) & 0xff;  //向右移动16位，与0xff（11111111）进行与运算
	               green[j * w + i]=(tempPixel >> 8) & 0xff;//向右移动8位，与0xff（11111111）进行与运算
	               blue[j * w + i]=(tempPixel)>>0 & 0xff;      //与0xff（11111111）进行与运算
	               
	               oalpha[j * w + i]=(otempPixel >> 24) & 0xff;//向右移动24位，与0xff（11111111）进行与运算
	               ored[j * w + i]=(otempPixel >>16) & 0xff;  //向右移动16位，与0xff（11111111）进行与运算
	               ogreen[j * w + i]=(otempPixel >> 8) & 0xff;//向右移动8位，与0xff（11111111）进行与运算
	               oblue[j * w + i]=(otempPixel)>>0 & 0xff;      //与0xff（11111111）进行与运算            
	          }
	       }
	     //恢复图
	       int pixelsTarget[]=new int[w * h];
	       for (int j = 0; j < h; j++) {
	            for (int i = 0; i < w; i++) {    
	            	
	            	
	            	
	            	if (ored[j*w+i]<50&&ogreen[j*w+i]<50&&oblue[j*w+i]<50)  
	                {  
	                    nalpha = 1F;  
	                }  
	                else  
	                {  
	                    nalpha = 0F;  
	                }  
	            	
	            	newred[j*w+i] = (int) (red[j*w+i] * nalpha + ored[j*w+i] * (1 - nalpha));  
	            	newgreen[j*w+i]= (int) (green[j*w+i] * nalpha + ogreen[j*w+i] * (1 - nalpha));  
	            	newblue[j*w+i] = (int) (blue[j*w+i] * nalpha + oblue[j*w+i] * (1 - nalpha));  
	            	newalpha[j * w + i] = (int) (alpha[j * w + i] * nalpha + oalpha[j * w + i] * (1 - nalpha));
	            	
	            	
	            	
	            	 newred[j*w+i]=Math.max(0, Math.min(newred[j*w+i],255));
		        	 newgreen[j*w+i]=Math.max(0,Math.min(255,newgreen[j*w+i]));
		        	 newblue[j*w+i]=Math.max(0,Math.min(255, newblue[j*w+i]));
	            	
	                pixelsTarget[j * w + i]+=newalpha[j * w + i]<<24;
	                pixelsTarget[j * w + i]+=newred[j * w + i]<<16;
	                pixelsTarget[j * w + i]+=newgreen[j * w + i]<<8;
	                pixelsTarget[j * w + i]+=newblue[j * w + i];
	            }
	       }   
	       result=Bitmap.createBitmap(pixelsTarget, w, h, Bitmap.Config.ARGB_8888); 
		return result;
    }
}
