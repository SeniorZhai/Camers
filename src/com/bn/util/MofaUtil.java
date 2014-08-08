package com.bn.util;

import android.graphics.Bitmap;

public class MofaUtil
{

	public static double[] target1={0,240,0};  //森之绿
	public static double[] target2={0,0,240};  //深海蓝
	public static double[] target3={250,0,0};  //桃花红
	
	public static Bitmap mofa(Bitmap bm,double[]target)//黑白效果
	{
		Bitmap result=null;
		int w=bm.getWidth();
		int h=bm.getHeight();
		int []  pixels=new int[w*h];
		//将图像的每个像素读到数组中
		bm.getPixels(pixels, 0, w, 0, 0, w, h);
		
		//定义RGB的数组		
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
	            	
	            	double y=0.299*red[j*w+i]+0.587*green[j * w + i]+0.114*blue[j * w + i];//计算灰度值
	            	 
	            	double dis=(target[0]-red[j*w+i])*(target[0]-red[j*w+i])+  //所给的像素与当前像素的距离
	            			(target[1]-green[j*w+i])*(target[1]-green[j*w+i])+
	            			(target[2]-blue[j*w+i])*(target[2]-blue[j*w+i]);
	            	dis=Math.sqrt(dis);//获取模
	            	
	            	double disFactor=dis/360.0;//设置比例
	            	
	            	double factor=0;//颜色饱和度因子
	            	if(disFactor<0.5) //如果比例小于0.5
	            	{
	            		factor=1-disFactor; //设置颜色饱和度因子
	            	}
	            	red[j*w+i]=(int)(y+factor*(red[j*w+i]-y));
	            	green[j * w + i]=(int)(y+factor*(green[j * w + i]-y));
	            	blue[j * w + i]=(int)(y+factor*(blue[j * w + i]-y));
	            	
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
