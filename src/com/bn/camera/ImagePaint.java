package com.bn.camera;

import com.bn.util.ScreenScaleResult;
import com.bn.util.ScreenScaleUtil;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera.AutoFocusCallback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

public class ImagePaint extends View
{	
	private final  Paint paint;//画笔
	private final  Paint pw=new Paint();
	private Bitmap resultBitmap;//结果图
	MyCameraActivity myactivity;
	Bitmap bm1;
	Bitmap bm2;
	Bitmap overlaybm5;
	Bitmap overlaybm;
	Bitmap overlaybm1;
	Bitmap bm5;
	static final int MOVE_JULI=10;
	static boolean flagmove=false;
	 float x1=0;                        //点击当前x的位置
	 float y1=0;                        //点击当前y的位置
     static int Rball=20;               //白色圆的半径
     int w1=0;
     public final int xiangce=0;        //调相册系数
     int y=0;                           //屏幕自适应 高度值
     static float radio=1;              //屏幕自适应比例 
     ImageActivity iv;
     ScreenScaleResult SSRE;
	public ImagePaint(Context context,AttributeSet attri) {
		super(context,attri);
		myactivity=(MyCameraActivity) context;
		
		paint = new Paint();//新建画笔			
	}    
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		
		int width=canvas.getWidth();
		int height=canvas.getHeight();
		ScreenScaleUtil SSUL=new ScreenScaleUtil();
		SSRE=SSUL.calScale(width, height);
		if(SSRE==null)
		{
			return ;
		}
		else
		{
			int y=SSRE.lucY;
			radio=SSRE.ratio;			
		}
		if(Constant.flag3)
		{
		pw.setColor(Color.WHITE);
		pw.setAlpha(200);
    	canvas.drawLine(250*radio,0,250*radio,height, pw);
		canvas.drawLine(680*radio,0,680*radio,height, pw);
		canvas.drawLine(460*radio,0,460*radio,height,pw);
		canvas.drawLine(0,120*radio,860*radio,120*radio, pw);
		canvas.drawLine(0,420*radio,860*radio,420*radio,pw);
		canvas.drawLine(0,270*radio,860*radio,270*radio,pw);
		}
		Paint pt=new Paint();
		bm1=BitmapFactory.decodeResource(getResources(), R.drawable.anniu1);
		int w=bm1.getWidth();
		int h=bm1.getHeight();
		 w1=width/7;
		float scalX=w1*1f/w;
		float scalY=height/2*1f/h;
		Matrix mx=new Matrix();
		mx.postScale(scalX, scalY);
		overlaybm=Bitmap.createBitmap(bm1,0,0,w,h,mx,true);	
		canvas.drawBitmap(overlaybm,width-w1,0, pt);
		bm2=BitmapFactory.decodeResource(getResources(), R.drawable.anniu2);
		overlaybm1=Bitmap.createBitmap(bm2,0,0,w,h,mx,true);		
		canvas.drawBitmap(overlaybm1,width-w1,overlaybm.getHeight(),pt);
		Paint pi=new Paint();
		pi.setAlpha(200);
		bm5=BitmapFactory.decodeResource(getResources(), R.drawable.tuola);
		int w2=bm5.getWidth();
		int h2=bm5.getHeight();
		int h3=height*(3/5);
	//	float scalX1=w2*1f/w2;
		float scalY1=(float) ((height*0.6)*1f/h2);
		Matrix mx1=new Matrix();
		mx1.postScale(1, scalY1);
		overlaybm5=Bitmap.createBitmap(bm5,0,0,w2,h2,mx1,true);
		canvas.drawBitmap(overlaybm5, 60*radio,135*radio+y,pi);
		Paint pa=new Paint();
		pa.setColor(Color.WHITE);
		pa.setAntiAlias(true);
		if(Constant.flag1)
		{
			canvas.drawCircle(70*radio,overlaybm5.getHeight()+135*radio+y,Rball, pa);
			Constant.dx=60*radio;
			Constant.dy=430*radio+y;
		}
		else
		{	
			canvas.drawCircle(Constant.dx,Constant.dy,Rball, pa);
			
		}    
	} 
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		WindowManager window=myactivity.getWindowManager();
		int width=window.getDefaultDisplay().getWidth();
		int height=window.getDefaultDisplay().getHeight();	
		int action=event.getAction();
	     switch(action)
	     {
	     case MotionEvent.ACTION_DOWN:
	    	    flagmove=false;
	    	    x1=event.getX();
	    	    y1=event.getY();    	  
	    	    break;
	     case MotionEvent.ACTION_UP:
	    	
	    	 if(x1>0&&x1<width-bm1.getWidth())
	    	 {      	   
	    		 
	    		 if(flagmove==false) 
		    	 {		 
						//自动变焦
						 myactivity.myCamera.autoFocus(new AutoFocusCallback()  
				         {  
							public void onAutoFocus(boolean success,
									android.hardware.Camera camera)
							{								
								
							}	
				         });
						 if(Constant.flag5)
						 {
							 myactivity.paizhao();
						 }
						 return false;
		    	 }
	    		 else
	    		 {
	    			 return false;
	    		 }
	    	 }
	    	 if(x1>=width-w1&x1<width&y1>0&y1<=overlaybm.getHeight())
			 {
	    		 myactivity.openOptionsMenu();
			 }
	    	 if(x1>=width-w1&x1<width&y1>overlaybm.getHeight()&y1<=overlaybm.getHeight()+overlaybm1.getHeight())
			 {
				 myactivity.paizhao();				
			 }
	    	 break;
	     case MotionEvent.ACTION_MOVE:
	    	if(flagmove!=true)
	    	{
	    		 float x2=Math.abs(event.getX()-x1);
	    		 float y2=Math.abs(event.getY()-y1);
	    		 float y3=event.getY()-y1;
	    		 if(x2>MOVE_JULI||y2>MOVE_JULI)
	    		 {
	    			     flagmove=true;
	    			 
		         }    	    
	        }
	    	if(Math.abs(event.getX()-Constant.dx)<Rball)
	    	{
	    		 Constant.flag1=false;
	    		 Constant.dx=70*radio;
	    		 Constant.dy=event.getY();
			     if(Constant.dy>overlaybm5.getHeight()+135*radio+y)
			     {
			    	 Constant.dy=overlaybm5.getHeight()+135*radio+y;
			     }
			     if(Constant.dy<135*radio+y)
			     {
			    	 Constant.dy=135*radio+y;
			     }
			     ImagePaint.this.invalidate();
	    		 float juli=Constant.dy-135*radio+y;
	    		 float jiaoju=juli/3; 
	    		 if(jiaoju>=100)
	    		 {
	    			 jiaoju=100;
	    		 }
	    		 myactivity.setzoom(jiaoju);
	    		 return true;		 
	    	}
	    	break;
	     } 
		return true;
	}
}
