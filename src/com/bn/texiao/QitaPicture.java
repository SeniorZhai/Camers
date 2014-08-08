package com.bn.texiao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.bn.camera.Constant;
import com.bn.camera.R;
import com.bn.util.QitaUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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

public class QitaPicture extends Activity
{

	 String path;
	 Bitmap bitmap;
	 ImageView im;
	 TextView tv;
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
		setContentView(R.layout.qitatexiao);
		Intent it=getIntent();
		path=it.getStringExtra("path");
		if(path==null)
		{
			Toast.makeText(QitaPicture.this,"路径不存在",Toast.LENGTH_SHORT);
		}
		BitmapFactory.Options opts = new BitmapFactory.Options();  
		opts.inSampleSize = 4;  
		bitmap = BitmapFactory.decodeFile(path, opts); 
		im=(ImageView)findViewById(R.id.imageView5);
		im.setImageBitmap(bitmap);
		tv=(TextView)findViewById(R.id.qitatx);
		
		final Button b1=(Button)findViewById(R.id.fudiao);
		final Button b2=(Button)findViewById(R.id.dipian);
		final Button b3=(Button)findViewById(R.id.guangzhao);
		final Button b4=(Button)findViewById(R.id.ruihua);
		final Button b5=(Button)findViewById(R.id.mohu);
		final Button b6=(Button)findViewById(R.id.heibaix);
		final Button b7=(Button)findViewById(R.id.qitaquxiao);
		final Button b8=(Button)findViewById(R.id.qitabaocun);
		
		  OnClickListener ll=new OnClickListener()
		  {

			public void onClick(View v)
			{
			   switch(v.getId())
			   {
			   case R.id.fudiao:
				   BitmapFactory.Options opts5 = new BitmapFactory.Options();  
					opts5.inSampleSize = 10;  
					Bitmap bm5 = BitmapFactory.decodeFile(path, opts5);
					bm=QitaUtil.fudiaoprocess(bm5);
			        im.setImageBitmap(bm);			        
			        tv.setText(b1.getText().toString());
				   break;
			   case R.id.dipian:
				    BitmapFactory.Options opts = new BitmapFactory.Options();  
					opts.inSampleSize = 10;  
					Bitmap bm1 = BitmapFactory.decodeFile(path, opts);
					bm=QitaUtil.dipian(bm1);
			        im.setImageBitmap(bm);
			        
			        tv.setText(b2.getText().toString());
				   break;
			   case R.id.guangzhao:
				    BitmapFactory.Options opts4 = new BitmapFactory.Options();  
					opts4.inSampleSize = 10;  
					Bitmap bm4 = BitmapFactory.decodeFile(path, opts4);
					bm=QitaUtil.guangzhao(bm4);
			        im.setImageBitmap(bm);			      
			        tv.setText(b3.getText().toString());
				   break;
			   case R.id.ruihua:
				    BitmapFactory.Options opts1 = new BitmapFactory.Options();  
					opts1.inSampleSize = 10;  
					Bitmap bm6 = BitmapFactory.decodeFile(path, opts1);
					bm=QitaUtil.ruihuaprocess(bm6);
			        im.setImageBitmap(bm);
			        tv.setText(b4.getText().toString());
				   break;
			   case R.id.mohu:
				    BitmapFactory.Options opts2 = new BitmapFactory.Options();  
					opts2.inSampleSize = 10;  
					Bitmap bm2 = BitmapFactory.decodeFile(path, opts2);
					bm=QitaUtil.gaosiprocess(bm2);
			        im.setImageBitmap(bm);
			        tv.setText(b5.getText().toString());
				   break;
			   case R.id.heibaix:
				    BitmapFactory.Options opts3 = new BitmapFactory.Options();  
					opts3.inSampleSize = 10;  
					Bitmap bm3 = BitmapFactory.decodeFile(path, opts3);
					bm=QitaUtil.huidu(bm3);
			        im.setImageBitmap(bm);
			        tv.setText(b6.getText().toString());
				   break;
			   case R.id.qitaquxiao:
				   QitaPicture.this.finish();
				   break;
			   case R.id.qitabaocun:
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
						 Toast.makeText(QitaPicture.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
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
						 Toast.makeText(QitaPicture.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
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
