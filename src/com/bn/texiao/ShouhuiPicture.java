package com.bn.texiao;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.bn.camera.Constant;
import com.bn.camera.R;
import com.bn.util.LzpUtil;
import com.bn.util.QitaUtil;
import com.bn.util.ShouhuiUtil;
import com.bn.util.ZengqiangUtil;

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

public class ShouhuiPicture extends Activity
{

	 String path;
	 Bitmap bitmap;
	 ImageView im;
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
		setContentView(R.layout.shouhui);
		Intent it=getIntent();
		path=it.getStringExtra("path");
		if(path==null)
		{
			Toast.makeText(ShouhuiPicture.this,"路径不存在",Toast.LENGTH_SHORT);
		}
		BitmapFactory.Options opts = new BitmapFactory.Options();  
		opts.inSampleSize = 4;  
		bitmap = BitmapFactory.decodeFile(path, opts); 
		im=(ImageView)findViewById(R.id.imageView3);
		im.setImageBitmap(bitmap);
		tv=(TextView)findViewById(R.id.shouhuitx);
		
		final Button b1=(Button)findViewById(R.id.heibai);
		final Button b2=(Button)findViewById(R.id.xianshi);
		final Button b3=(Button)findViewById(R.id.shumiao);
		final Button b4=(Button)findViewById(R.id.caise);
		final Button b5=(Button)findViewById(R.id.youcai);
		final Button b6=(Button)findViewById(R.id.tanbi);
		final Button b7=(Button)findViewById(R.id.shouhuiquxiao);
		final Button b8=(Button)findViewById(R.id.shouhuibaocun);
		
		OnClickListener ll=new OnClickListener()
		{

			public void onClick(View v)
			{
				switch(v.getId())
				{
				case R.id.heibai:
					BitmapFactory.Options opts = new BitmapFactory.Options();  
					opts.inSampleSize = 10;  
					Bitmap bm5 = BitmapFactory.decodeFile(path, opts);
					bm=ShouhuiUtil.shouhuiProcess(bm5);
                    bm=QitaUtil.dipian(bm);
                    ShouhuiUtil.p1old=72;
                    bm=ShouhuiUtil.erzhihuaBitmap(bm);
			        im.setImageBitmap(bm);
			        tv.setText(b1.getText().toString());
					break;
				case R.id.xianshi:
					BitmapFactory.Options opts1 = new BitmapFactory.Options();  
					opts1.inSampleSize = 10;  
					Bitmap bm1 = BitmapFactory.decodeFile(path, opts1);
					bm=ShouhuiUtil.shouhuiProcess(bm1);
                    bm=QitaUtil.dipian(bm);
                    ShouhuiUtil.p1old=72;
                    bm=ShouhuiUtil.erzhihuaBitmap(bm);
                    Bitmap bitmap9=QitaUtil.huidu(bm1);
                    bm=ShouhuiUtil.caiseBitmap(bitmap9, bm);
			        im.setImageBitmap(bm);
			        tv.setText(b2.getText().toString());
					break;
				case R.id.shumiao:
					BitmapFactory.Options opts4 = new BitmapFactory.Options();  
					opts4.inSampleSize = 10;  
					Bitmap bm3=BitmapFactory.decodeFile(path, opts4);
					bm=ShouhuiUtil.shouhuiProcess(bm3);
					bm=QitaUtil.dipian(bm);
	                ShouhuiUtil.p1old=72;
	                bm=ShouhuiUtil.erzhihuaBitmap(bm);
				    bm=ShouhuiUtil.caiseBitmap(bm3, bm);
				    bm=ZengqiangUtil.shineiPicture(bm);
			        im.setImageBitmap(bm);
			        tv.setText(b3.getText().toString());
					break;
				case R.id.caise:
					BitmapFactory.Options opts2 = new BitmapFactory.Options();  
					opts2.inSampleSize = 10;  
					Bitmap bm2 = BitmapFactory.decodeFile(path, opts2);
					bm=ShouhuiUtil.shouhuiProcess(bm2);
					bm=QitaUtil.dipian(bm);
	                ShouhuiUtil.p1old=72;
	                bm=ShouhuiUtil.erzhihuaBitmap(bm);
				    bm=ShouhuiUtil.caiseBitmap(bm2, bm);				    
			        im.setImageBitmap(bm);
			        tv.setText(b4.getText().toString());
					break;
				case R.id.youcai:
					BitmapFactory.Options opts7 = new BitmapFactory.Options();  
					opts7.inSampleSize = 10;  
					Bitmap bm7 = BitmapFactory.decodeFile(path, opts7);
					bm=ShouhuiUtil.shouhuiProcess(bm7);
					bm=QitaUtil.dipian(bm);
	                ShouhuiUtil.p1old=72;
	                bm=ShouhuiUtil.erzhihuaBitmap(bm);
				    bm=ShouhuiUtil.youcaiBitmap(bm7,bm);
			        im.setImageBitmap(bm);
			        tv.setText(b5.getText().toString());
					break;
				case R.id.tanbi:
					BitmapFactory.Options opts3 = new BitmapFactory.Options();  
					opts3.inSampleSize = 10;  
					Bitmap bm6 = BitmapFactory.decodeFile(path, opts3);
					bm=ShouhuiUtil.shouhuiProcess(bm6);
                    bm=QitaUtil.dipian(bm);
                    ShouhuiUtil.p1old=72;
                    bm=ShouhuiUtil.erzhihuaBitmap(bm);
                    for(int i=0;i<2;i++)
                    {
                       bm=ShouhuiUtil.expandprocess(bm);	
                    }					
			        im.setImageBitmap(bm);
			        tv.setText(b6.getText().toString());
					break;
				case R.id.shouhuiquxiao:
					ShouhuiPicture.this.finish();
					break;
				case R.id.shouhuibaocun:
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
						 Toast.makeText(ShouhuiPicture.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
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
						 Toast.makeText(ShouhuiPicture.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
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
