package com.bn.util;

enum ScreenOrien
{
	HP,  //表示横屏的枚举值
	SP   //表示竖屏的枚举值
}

//缩放计算的结果
public class ScreenScaleResult
{
	public int lucX;//x一侧留白宽度
	public  int lucY;//y一侧留白
	public  float ratio;//缩放比例
	public  ScreenOrien so;//横竖屏情况	
	
	public ScreenScaleResult(int lucX,int lucY,float ratio,ScreenOrien so)
	{
		this.lucX=lucX;
		this.lucY=lucY;
		
		this.ratio=ratio;
		this.so=so;
	}
	
	public String toString()
	{
		return "lucX="+lucX+", lucY="+lucY+", ratio="+ratio+", "+so;
	}
}