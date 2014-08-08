package com.bn.texiao;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.bn.camera.Constant;
import com.bn.camera.R;
import com.bn.util.PsUtil;
import com.bn.util.QitaUtil;
import com.bn.util.ZengqiangUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Zqpicture extends Activity
{  
	Bitmap bitmap;
	ImageView im;
	String path;
	TextView tv;
	Bitmap bm;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{	
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags
        (
        		WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
		setContentView(R.layout.zengqiang);		
		Intent it=getIntent();
		path=it.getStringExtra("path");
		if(path==null)
		{
			Toast.makeText(Zqpicture.this,"路径不存在",Toast.LENGTH_SHORT);
		}
		BitmapFactory.Options opts = new BitmapFactory.Options();  
		opts.inSampleSize = 10;  
		bitmap = BitmapFactory.decodeFile(path, opts); 
		im=(ImageView)findViewById(R.id.imageView2);
		im.setImageBitmap(bitmap);
		tv=(TextView)findViewById(R.id.zengqiangtx);
		
		final Button b1=(Button)findViewById(R.id.zidong);
		final Button b2=(Button)findViewById(R.id.yejian);
		final Button b3=(Button)findViewById(R.id.shinei);
		final Button b4=(Button)findViewById(R.id.wennuan);
		final Button b5=(Button)findViewById(R.id.ku);
		final Button b6=(Button)findViewById(R.id.fanzhuan);		
		final Button b7=(Button)findViewById(R.id.zengquxiao);		
		final Button b8=(Button)findViewById(R.id.zengbaocun);		
		OnClickListener ll=new OnClickListener()
		{
			public void onClick(View v)
			{
				switch(v.getId())
				{
				case R.id.zidong:
				   BitmapFactory.Options opts5 = new BitmapFactory.Options();  
				   opts5.inSampleSize = 10;  
				   Bitmap bm5 = BitmapFactory.decodeFile(path, opts5);
				   bm=ZengqiangUtil.zidongPicture(bm5);
				   im.setImageBitmap(bm);
				   tv.setText(b1.getText().toString());
				   break;
				case R.id.yejian:
					BitmapFactory.Options opts = new BitmapFactory.Options();  
					opts.inSampleSize = 10;  
					Bitmap bm7 = BitmapFactory.decodeFile(path, opts);
					bm=ZengqiangUtil.yejianPicture(bm7);
			        im.setImageBitmap(bm);
			        tv.setText(b2.getText().toString());
					break;
				case R.id.shinei:	
					BitmapFactory.Options opts4 = new BitmapFactory.Options();  
					opts4.inSampleSize = 10;  
					Bitmap bm4 = BitmapFactory.decodeFile(path, opts4);
					bm=ZengqiangUtil.shineiPicture(bm4);
				    im.setImageBitmap(bm);
				    tv.setText(b3.getText().toString());
					break;
				case R.id.wennuan:
					BitmapFactory.Options opts3 = new BitmapFactory.Options();  
					opts3.inSampleSize = 10;  
					Bitmap bm6 = BitmapFactory.decodeFile(path, opts3);
					bm=ZengqiangUtil.huiduchuangkouBitmap(bm6);
					im.setImageBitmap(bm);
					tv.setText(b4.getText().toString());
					break;
				case R.id.ku:
					BitmapFactory.Options opts8 = new BitmapFactory.Options();  
					opts8.inSampleSize = 10;  
					Bitmap bm8 = BitmapFactory.decodeFile(path, opts8);
					bm=ZengqiangUtil.pinghengPicture(bm8);
				  //  Bitmap bitmap1=ZengqiangUtil.baohe(bm6);
					im.setImageBitmap(bm);
					tv.setText(b5.getText().toString());
					break;
				case R.id.fanzhuan:
					BitmapFactory.Options opts6= new BitmapFactory.Options();  
					opts6.inSampleSize = 10;  
					Bitmap bm3 = BitmapFactory.decodeFile(path, opts6);
					bm=ZengqiangUtil.fanzhuanPicture(bm3);
					im.setImageBitmap(bm);
					tv.setText(b6.getText().toString());
					break;
				case R.id.zengquxiao:
					Zqpicture.this.finish();
					break;
				case R.id.zengbaocun:
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
						 Toast.makeText(Zqpicture.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
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
						 Toast.makeText(Zqpicture.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
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
