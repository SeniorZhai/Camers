package com.bn.util;

import java.util.Arrays;

import android.graphics.Bitmap;

public class PsUtil
{
    
	public static Bitmap lplsprocess(Bitmap bm) {
		Bitmap result=null;	
		int w=bm.getWidth();
		int h=bm.getHeight();
		int pixs[]=new int[w*h];
		bm.getPixels(pixs, 0, w, 0, 0, w, h);		
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
	               int tempPixel=pixs[j * w + i];            //将图像的像素的值里面的RGB  分离 出来
	               alpha[j * w + i]=(tempPixel >> 24) & 0xff;//向右移动24位，与0xff（11111111）进行与运算
	               red[j * w + i]=(tempPixel >>16) & 0xff;  //向右移动16位，与0xff（11111111）进行与运算
	               green[j * w + i]=(tempPixel >> 8) & 0xff;//向右移动8位，与0xff（11111111）进行与运算
	               blue[j * w + i]=(tempPixel)>>0 & 0xff;      //与0xff（11111111）进行与运算
	          }
	       } //处理RGB矩阵     根据不同的矩阵得到不同的RGB的数值
	       red=processMatrix(red,w,h);//单独色彩通道数据处理方法
	       green=processMatrix(green,w,h);  
	       blue=processMatrix(blue,w,h); 
	       
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

	private static int[] processMatrix(int[] rgb, int w, int h) {
		//滤镜处理RGB矩阵
		float[] temprgb=new float[w * h];
		int[] temprgbInt=new int[w * h];
		int span=9;   //3
		int array[]=new int[span];
		//对图片的每一个像素进行处理
		for(int i=1;i<w-2;i++)//列
		{
			for(int j=1;j<h-2;j++)//行
			{     //根据矩阵运算			
				 temprgb[j*w+i]=0;
				 array[0]=rgb[(j-1)*w+(i-1)];
				 array[1]=rgb[(j-1)*w+i];
				 array[2]=rgb[(j-1)*w+i+1];
				 array[3]=rgb[j*w+i-1];				 
				 array[4]=rgb[j*w+i];				 
				 array[5]=rgb[j*w+i+1];
				 array[6]=rgb[(j+1)*w+i-1];
				 array[7]=rgb[(j+1)*w+i];
				 array[8]=rgb[(j+1)*w+i+1];
				 Arrays.sort(array);
				 temprgb[j*w+i]=array[4];
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
}
