package com.bn.util;

import android.graphics.Bitmap;

public class ShouhuiUtil
{  	
	  static float [][]Sobel=    //边缘检测
	  {
		   {0,1,0},
		   {1,-4,1},
		   {0,1,0}
	  };
	 public  static int p1old=70;
	   public static Bitmap shouhuiProcess(Bitmap bm)
	   {
		   Bitmap bitmap=processBitmap(bm,Sobel);
		   bitmap=grayColor(bitmap);
		   return bitmap;
	   }
	
		 private static Bitmap processBitmap(Bitmap bm, float[][] bOX_PH2) {  //边缘检测
				Bitmap result=null;
				int w=bm.getWidth();
				int h=bm.getHeight();
				int [] pixels=new int[w*h];
				bm.getPixels(pixels, 0, w, 0, 0, w, h);//将图像的每个像素读到数组中
				int alpha[]=new int[w * h];//定义RGB的矩阵
			    int red[]=new int[w * h];
			    int green[]=new int[w * h];
			    int blue[]=new int[w * h];
			    for (int j = 0; j < h; j++) {//初始化相应的矩阵
			           for (int i = 0; i < w; i++){
			               int tempPixel=pixels[j * w + i];   //将图像的像素的值里面的RGB  分离 出来
			               alpha[j * w + i]=(tempPixel >> 24) & 0xff;//向右移动24位，与0xff（11111111）进行与运算
			               red[j * w + i]=(tempPixel >>16) & 0xff;  //向右移动16位，与0xff（11111111）进行与运算
			               green[j * w + i]=(tempPixel >> 8) & 0xff;//向右移动8位，与0xff（11111111）进行与运算
			               blue[j * w + i]=(tempPixel)>>0 & 0xff;      //与0xff（11111111）进行与运算
			          }}
			     red=processMatrix(red,w,h,bOX_PH2);//处理RGB矩阵     根据不同的矩阵得到不同的RGB的数值
			     green=processMatrix(green,w,h,bOX_PH2);  
			     blue=processMatrix(blue,w,h,bOX_PH2); 
			     int pixelsTarget[]=new int[w * h]; //恢复图
			     for (int j = 0; j < h; j++) {
			          for (int i = 0; i < w; i++) {
			                pixelsTarget[j * w + i]+=alpha[j * w + i]<<24;
			                pixelsTarget[j * w + i]+=red[j * w + i]<<16;
			                pixelsTarget[j * w + i]+=green[j * w + i]<<8;
			                pixelsTarget[j * w + i]+=blue[j * w + i];
			           }}
			       
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
		 
		 public static Bitmap grayColor(Bitmap bm)
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
		            	int pk=(int)((0.11*red[j * w + i]+0.59*green[j * w + i]+0.3*blue[j * w + i])*0.3)+178; 		            	
		            	if(pk>255)
		            	{
		            		pk=255;
		            	}
		                pixelsTarget[j * w + i]+=alpha[j * w + i]<<24;
		                pixelsTarget[j * w + i]+=pk<<16;
		                pixelsTarget[j * w + i]+=pk<<8;
		                pixelsTarget[j * w + i]+=pk;
		            }
		       }
		       
		       result=Bitmap.createBitmap(pixelsTarget, w, h, Bitmap.Config.ARGB_8888); 
			   
			   return result;
		   }
		 		  
		public static Bitmap erzhihuaBitmap(Bitmap bm)
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
			        	 if(red[j*w+i]>=0&red[j*w+i]<=p1old)
			        	 {	        		 
			        		 red[j*w+i]= 0;	        	
			        	 }
			        	 if(green[j*w+i]>=0&green[j*w+i]<=p1old)
			        	 {	        		
			        		 green[j*w+i]=0;	        	
			        	 }
			        	 if(blue[j*w+i]>=0&blue[j*w+i]<=p1old)
			        	 {	        		 
			        		 blue[j*w+i]=0;	        	
			        	 }
			        	 if(red[j*w+i]>=p1old&red[j*w+i]<255)
			        	 {	        		 	        		 
			        		 red[j*w+i]=255;	        	
			        	 }
			        	 if(green[j*w+i]>=p1old&green[j*w+i]<255)
			        	 {      			        		 
			        		 green[j*w+i]=255;
			        	 }
			        	 if(blue[j*w+i]>=p1old&blue[j*w+i]<255)
			        	 {	        		 	        		 
			        		 blue[j*w+i]=255;	        		
			        	 }
			             pixelsTarget[j * w + i]+=alpha[j * w + i]<<24;
			             pixelsTarget[j * w + i]+=red[j * w + i]<<16;
			             pixelsTarget[j * w + i]+=green[j * w + i]<<8;
			             pixelsTarget[j * w + i]+=blue[j * w + i];
			         }
			    }
			    			   
			    result=Bitmap.createBitmap(pixelsTarget, w, h, Bitmap.Config.ARGB_8888); 	    
				return result;
			}
			
			public static Bitmap expandprocess(Bitmap bm)     //膨胀
			{           
				Bitmap result=null;
				int w=bm.getWidth();
				int h=bm.getHeight();
				int pixs[]=new int[w*h];//将图像的每个像素读到数组中
				bm.getPixels(pixs, 0, w, 0, 0, w, h);
				int alpha[]=new int[w * h];//定义RGB的矩阵
				int red[]=new int[w * h];
				int green[]=new int[w * h];
				int blue[]=new int[w * h];
			    for (int j = 0; j < h; j++){ //初始化相应的矩阵
			    	for (int i = 0; i < w; i++){
		               int tempPixel=pixs[j * w + i];   //将图像的像素的值里面的RGB  分离 出来
		               alpha[j * w + i]=(tempPixel >> 24) & 0xff;//向右移动24位，与0xff（11111111）进行与运算
		               red[j * w + i]=(tempPixel >>16) & 0xff;  //向右移动16位，与0xff（11111111）进行与运算
		               green[j * w + i]=(tempPixel >> 8) & 0xff;//向右移动8位，与0xff（11111111）进行与运算
		               blue[j * w + i]=(tempPixel)>>0 & 0xff;      //与0xff（11111111）进行与运算
		          }}
			     red=processMatrix(red,w,h);//单独色彩通道数据处理方法
			     green=processMatrix(green,w,h);  
			     blue=processMatrix(blue,w,h);
			     int pixelsTarget[]=new int[w * h];//恢复图
			     for (int j = 0; j < h; j++) {
		            for (int i = 0; i < w; i++) {
		                pixelsTarget[j * w + i]+=alpha[j * w + i]<<24;
		                pixelsTarget[j * w + i]+=red[j * w + i]<<16;
		                pixelsTarget[j * w + i]+=green[j * w + i]<<8;
		                pixelsTarget[j * w + i]+=blue[j * w + i];
		            }}
			     result=Bitmap.createBitmap(pixelsTarget, w, h, Bitmap.Config.ARGB_8888); 
			     return result;
			}
			private static int[] processMatrix(int[] rgb, int w, int h) 
			{
				float[] temprgb=new float[w * h];//滤镜处理RGB矩阵
				int[] temprgbInt=new int[w * h];
				for(int i=1;i<w-1;i++){ //腐蚀   中心点对比
					for(int j=1;j<h-1;j++){
						temprgbInt[j*w+i]=0;
						int pix=rgb[j*w+i];
						int pix1=rgb[(j-1)*w+i];
						int pix2=rgb[j*w+i-1];
						if(pix1>250||pix2>250){
							temprgbInt[j*w+i]=255;
							}
						if (pix1<10||pix2<10){
							temprgbInt[j*w+i]=0;
						}}}
				return temprgbInt;
			}
	
	 public static Bitmap caiseBitmap(Bitmap bm,Bitmap overlay)
	    {
	    	Bitmap result=null;
			int w=bm.getWidth();
			int h=bm.getHeight();
			int []  pixels=new int[w*h];
			//将图像的每个像素读到数组中
			bm.getPixels(pixels, 0, w, 0, 0, w, h);
			
		
			int []opixels=new int[w*h];	
			overlay.getPixels(opixels,0,w,0,0,w,h);
			
		
			
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
	
		            	newred[j*w+i] = (int) (red[j*w+i]  + ored[j*w+i]*0.5);  
		            	newgreen[j*w+i]= (int) (green[j*w+i]+ ogreen[j*w+i]*0.5);  
		            	newblue[j*w+i] = (int) (blue[j*w+i] + oblue[j*w+i]*0.5);  
		            	newalpha[j * w + i] = (int) (alpha[j * w + i]+ oalpha[j * w + i]);
         	
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
 
	 public static Bitmap youcaiBitmap(Bitmap bm,Bitmap overlay)
	    {
	    	Bitmap result=null;
			int w=bm.getWidth();
			int h=bm.getHeight();
			int []  pixels=new int[w*h];
			//将图像的每个像素读到数组中
			bm.getPixels(pixels, 0, w, 0, 0, w, h);
			
			
			int []opixels=new int[w*h];
			overlay.getPixels(opixels,0,w,0,0,w,h);
			
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
	
		            	newred[j*w+i] = (int) (red[j*w+i]  + ored[j*w+i]*0.2);  
		            	newgreen[j*w+i]= (int) (green[j*w+i]+ ogreen[j*w+i]*0.2);  
		            	newblue[j*w+i] = (int) (blue[j*w+i] + oblue[j*w+i]*0.2);  
		            	newalpha[j * w + i] = (int) (alpha[j * w + i]+ oalpha[j * w + i]);
         	
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
	
	  public static Bitmap xianshiBitmap(Bitmap bm,Bitmap overlay)
	    {
	    	Bitmap result=null;
			int w=bm.getWidth();
			int h=bm.getHeight();
			int []  pixels=new int[w*h];
			//将图像的每个像素读到数组中
			bm.getPixels(pixels, 0, w, 0, 0, w, h);
			
			
			int []opixels=new int[w*h];
			overlay.getPixels(opixels,0,w,0,0,w,h);
			
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
	
		            	newred[j*w+i] = (int) (red[j*w+i]  + ored[j*w+i]);  
		            	newgreen[j*w+i]= (int) (green[j*w+i]+ ogreen[j*w+i]);  
		            	newblue[j*w+i] = (int) (blue[j*w+i] + oblue[j*w+i] );  
		            	newalpha[j * w + i] = (int) (alpha[j * w + i]+ oalpha[j * w + i]);
            	
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
