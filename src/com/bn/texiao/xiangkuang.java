package com.bn.texiao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.bn.camera.Constant;
import com.bn.camera.R;
import com.bn.util.XiangkuangUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class xiangkuang extends Activity
{
	String path;
	Bitmap bitmap;
	ImageView im;
	TextView tv;
	Bitmap bm;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags
        (
        		WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
		setContentView(R.layout.xiangkuang);
		Intent it=getIntent();
		path=it.getStringExtra("path");
		if(path==null)
		{
			Toast.makeText(xiangkuang.this,"路径不存在",Toast.LENGTH_SHORT);
		}
		BitmapFactory.Options opts = new BitmapFactory.Options();  
		opts.inSampleSize = 4;  
		bitmap = BitmapFactory.decodeFile(path, opts); 
		im=(ImageView)findViewById(R.id.imageView6);
		im.setImageBitmap(bitmap);	
		tv=(TextView)findViewById(R.id.xiangkuangtx);
		
		final Button b1=(Button)findViewById(R.id.xiang1);
		final Button b2=(Button)findViewById(R.id.xiang2);
		final Button b3=(Button)findViewById(R.id.xiang3);
		final Button b4=(Button)findViewById(R.id.xiang4);
		final Button b5=(Button)findViewById(R.id.xiang5);
		final Button b6=(Button)findViewById(R.id.xiang6);
		final Button b7=(Button)findViewById(R.id.xiangquxiao);
		final Button b8=(Button)findViewById(R.id.xiangbaocun);
		
		OnClickListener ll =new OnClickListener()
		{
			public void onClick(View v)
			{
				switch(v.getId())
				{
				case R.id.xiang1:
					
					try
					{
						InputStream it=getAssets().open("xiang1.png");
						byte[]bos=new byte[it.available()];
						it.read(bos);
						it.close();
						Bitmap bm2=BitmapFactory.decodeByteArray(bos, 0,bos.length);
						BitmapFactory.Options opts5 = new BitmapFactory.Options();  
						opts5.inSampleSize = 10;  
						Bitmap bm1=BitmapFactory.decodeFile(path, opts5);
						bm=XiangkuangUtil.alphaLayer(bm1,bm2);
						im.setImageBitmap(bm);
						tv.setText(b1.getText().toString());
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					
					break;
				case R.id.xiang2:
					try
					{
						InputStream it=getAssets().open("xiang2.png");
						byte[]bos=new byte[it.available()];
						it.read(bos);
						it.close();
						Bitmap bm2=BitmapFactory.decodeByteArray(bos, 0,bos.length);
						BitmapFactory.Options opts5 = new BitmapFactory.Options();  
						opts5.inSampleSize = 10;  
						Bitmap bm1=BitmapFactory.decodeFile(path, opts5);
						bm=XiangkuangUtil.alphaLayer(bm1, bm2);
						im.setImageBitmap(bm);
						tv.setText(b2.getText().toString());
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					break;
				case R.id.xiang3:
					try
					{
						InputStream it=getAssets().open("xiang3.png");
						byte[]bos=new byte[it.available()];
						it.read(bos);
						it.close();
						Bitmap bm2=BitmapFactory.decodeByteArray(bos, 0,bos.length);
						BitmapFactory.Options opts5 = new BitmapFactory.Options();  
						opts5.inSampleSize = 10;  
						Bitmap bm1=BitmapFactory.decodeFile(path, opts5);
						bm=XiangkuangUtil.alphaLayer(bm1, bm2);
						im.setImageBitmap(bm);
						tv.setText(b3.getText().toString());
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					break;
				case R.id.xiang4:
					try
					{
						InputStream it=getAssets().open("xiang4.png");
						byte[]bos=new byte[it.available()];
						it.read(bos);
						it.close();
						Bitmap bm2=BitmapFactory.decodeByteArray(bos, 0,bos.length);
						BitmapFactory.Options opts5 = new BitmapFactory.Options();  
						opts5.inSampleSize = 10;  
						Bitmap bm1=BitmapFactory.decodeFile(path, opts5);
						bm=XiangkuangUtil.alphaLayer(bm1, bm2);
						im.setImageBitmap(bm);
						tv.setText(b4.getText().toString());
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					break;
				case R.id.xiang5:
					try
					{
						InputStream it=getAssets().open("xiang5.png");
						byte[]bos=new byte[it.available()];
						it.read(bos);
						it.close();
						Bitmap bm2=BitmapFactory.decodeByteArray(bos, 0,bos.length);
						BitmapFactory.Options opts5 = new BitmapFactory.Options();  
						opts5.inSampleSize = 10;  
						Bitmap bm1=BitmapFactory.decodeFile(path, opts5);
						bm=XiangkuangUtil.alphaLayer(bm1, bm2);
						im.setImageBitmap(bm);
						tv.setText(b5.getText().toString());
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					break;
				case R.id.xiang6:
					try
					{
						InputStream it=getAssets().open("xiang6.png");
						byte[]bos=new byte[it.available()];
						it.read(bos);
						it.close();
						Bitmap bm2=BitmapFactory.decodeByteArray(bos, 0,bos.length);
						BitmapFactory.Options opts5 = new BitmapFactory.Options();  
						opts5.inSampleSize = 10;  
						Bitmap bm1=BitmapFactory.decodeFile(path, opts5);
						bm=XiangkuangUtil.alphaLayer(bm1, bm2);
						im.setImageBitmap(bm);
						tv.setText(b6.getText().toString());
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					break;	
				case R.id.xiangquxiao:
					xiangkuang.this.finish();
					break;
				case R.id.xiangbaocun:
					try
					{
						 String path=Environment.getExternalStorageDirectory().getAbsolutePath();//获取存储路径
						 File fTest1=new File(path+"-ext/"+Constant.c+".jpg");
						 while(fTest1.exists())
			    		 {
			    			 Constant.c+=1;
			    			 fTest1=new File(path+"-ext/"+Constant.c+".jpg");	
			    		 }
						 File fTest2=new File(path+"/"+Constant.c+".jpg");		
			   			 while(fTest2.exists())
			   			 {
			   				Constant.c+=1;	
			   				 fTest2=new File(path+"/"+Constant.c+".jpg");
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
						 Toast.makeText(xiangkuang.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
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
						 Toast.makeText(xiangkuang.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
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
		b3.setOnClickListener(ll);
		b4.setOnClickListener(ll);
		b5.setOnClickListener(ll);
		b6.setOnClickListener(ll);
		b7.setOnClickListener(ll);
		b8.setOnClickListener(ll);
		
		
	}
	    
	    
}
