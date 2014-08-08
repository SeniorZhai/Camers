package com.bn.texiao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.bn.camera.Constant;
import com.bn.camera.MyCameraActivity;
import com.bn.camera.R;
import com.bn.util.LzpUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Laozhaopian extends Activity
{
	String path;
    ImageView im;
    Bitmap bitmap;
    Bitmap bm;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags
        (
        		WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
		setContentView(R.layout.laozhaopian);
		
		Intent it=getIntent();
		path=it.getStringExtra("path");
		if(path==null)
		{
			Toast.makeText(Laozhaopian.this,"路径不存在",Toast.LENGTH_SHORT);
		}
		BitmapFactory.Options opts = new BitmapFactory.Options();  
		opts.inSampleSize = 10;  
		bitmap = BitmapFactory.decodeFile(path, opts); 	
		im=(ImageView)findViewById(R.id.imageView7);
		 bm=LzpUtil.laozhaopian(bitmap);
		im.setImageBitmap(bm);
		
		Button b1=(Button)findViewById(R.id.laozhaoquxiao);
		Button b2=(Button)findViewById(R.id.laozhaobaocun);
		OnClickListener ll=new OnClickListener()
		{

			public void onClick(View v) {
				switch(v.getId())
				{
				case R.id.laozhaoquxiao:
					Laozhaopian.this.finish();
					break;
				case R.id.laozhaobaocun:
					try
					{
						 String path=Environment.getExternalStorageDirectory().getAbsolutePath();//获取存储路径
						 File fTest1=new File(path+"-ext/"+Constant.c+".jpg");
						 while(fTest1.exists())
			    		 {
			    			 Constant.c+=1;
			    			 fTest1=new File(path+"-ext/"+Constant.c+".jpg");	
			    		 }
						 Matrix mx=new Matrix();
						 mx.postScale(1,1);
						 Bitmap overlayb=Bitmap.createBitmap(bm,0,0,bm.getWidth(),bm.getHeight(),mx,true);
						 FileOutputStream fout=new FileOutputStream(fTest1);
						 BufferedOutputStream bos = new BufferedOutputStream(fout);  			  
						 overlayb.compress
						 (
								 Bitmap.CompressFormat.JPEG,   //图片格式
								 80, 						   //品质0-100
								 bos						   //使用的输出流
						  );
						 bos.flush();   
						 bos.close();				
						 Toast.makeText(Laozhaopian.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
					}
					catch(Exception e)
					{   		
						e.printStackTrace();
					}	
					try
					{
						 String path=Environment.getExternalStorageDirectory().getAbsolutePath();//获取存储路径
			
						 File fTest2=new File(path+"/"+Constant.c+".jpg");		
			   			 while(fTest2.exists())
			   			 {
			   				Constant.c+=1;	
			   				 fTest2=new File(path+"/"+Constant.c+".jpg");
			   			 }	    	
						 Matrix mx=new Matrix();
						 mx.postScale(1,1);
						 Bitmap overlayb=Bitmap.createBitmap(bm,0,0,bm.getWidth(),bm.getHeight(),mx,true);
						 FileOutputStream fout=new FileOutputStream(fTest2);
						 BufferedOutputStream bos = new BufferedOutputStream(fout);  			  
						 overlayb.compress
						 (
								 Bitmap.CompressFormat.JPEG,   //图片格式
								 80, 						   //品质0-100
								 bos						   //使用的输出流
						  );
						 bos.flush();   
						 bos.close();				
						 Toast.makeText(Laozhaopian.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
					}
					catch(Exception e)
					{   		
						e.printStackTrace();
					}	
					
					break;
				}
				
			}		
		};
		b1.setOnClickListener(ll);
		b2.setOnClickListener(ll);
	}

}
