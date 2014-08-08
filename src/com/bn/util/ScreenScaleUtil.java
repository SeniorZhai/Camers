package com.bn.util;

//计算缩放情况的工具类
public class ScreenScaleUtil
{
  public static final float sHpWidth=960;//原始横屏的宽度
  public static final float sHpHeight=540;//原始竖屏的高度
  public static final float whHpRatio=sHpWidth/sHpHeight;//原始横屏的宽高比
	
	
  public static final float sSpWidth=540;//原始横屏的宽度
  public static final float sSpHeight=960;//原始竖屏的高度
  public static final float whSpRatio=sSpWidth/sSpHeight;//原始竖屏的宽高比
	
	
	public static ScreenScaleResult calScale
	(
		float targetWidth,	//目标宽度
		float targetHeight	//目标高度
	)
	{
		ScreenScaleResult result=null;
		ScreenOrien so=null;
		
		//首先判断目标是横屏还是竖屏
		if(targetWidth>targetHeight)
		{
			so=ScreenOrien.HP;
		}
		else
		{
			so=ScreenOrien.SP;
		}
		
		System.out.println(so);
		
		//进行横屏结果的计算
		if(so==ScreenOrien.HP)
		{
			//计算目标的宽高比
			float targetRatio=targetWidth/targetHeight;
			
			if(targetRatio>whHpRatio)
			{
				//若目标宽高比大于原始宽高比则以目标的高度计算结果			    
			    float ratio=targetHeight/sHpHeight;
			    float realTargetWidth=sHpWidth*ratio;
			    float lcuX=(targetWidth-realTargetWidth)/2.0f;
			    float lcuY=0;
			    result=new ScreenScaleResult((int)lcuX,(int)lcuY,ratio,so);	
			}
			else
			{
				//若目标宽高比小于原始宽高比则以目标的宽度计算结果	
				
				
				float ratio=targetWidth/sHpWidth;
				float realTargetHeight=sHpHeight*ratio;
				float lcuX=0;
				float lcuY=(targetHeight-realTargetHeight)/2.0f;
				result=new ScreenScaleResult((int)lcuX,(int)lcuY,ratio,so);	
			}
		}
		//进行竖屏结果的计算
		if(so==ScreenOrien.SP)
		{
			//计算目标的宽高比
			float targetRatio=targetWidth/targetHeight;
			
			if(targetRatio>whSpRatio)
			{
				//若目标宽高比大于原始宽高比则以目标的高度计算结果			    
			    float ratio=targetHeight/sSpHeight;
			    float realTargetWidth=sSpWidth*ratio;
			    float lcuX=(targetWidth-realTargetWidth)/2.0f;
			    float lcuY=0;
			    result=new ScreenScaleResult((int)lcuX,(int)lcuY,ratio,so);	
			}
			else
			{
				//若目标宽高比小于原始宽高比则以目标的宽度计算结果	
				float ratio=targetWidth/sSpWidth;
				float realTargetHeight=sSpHeight*ratio;
				float lcuX=0;
				float lcuY=(targetHeight-realTargetHeight)/2.0f;
				result=new ScreenScaleResult((int)lcuX,(int)lcuY,ratio,so);	
			}
		}
		
		return result;
	}
}