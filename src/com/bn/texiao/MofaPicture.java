package com.bn.texiao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.bn.camera.Constant;
import com.bn.camera.R;
import com.bn.util.MofaUtil;
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

public class MofaPicture extends Activity
{
     String path;
     Bitmap bitmap;
     ImageView im;
     MofaUtil mc;
     TextView tv;
     Bitmap bm;
	@Override
	public  void onCreate(Bundle savedInstanceState)
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
		setContentView(R.layout.mofa);
		Intent it=getIntent();
		path=it.getStringExtra("path");
		if(path==null)
		{
			Toast.makeText(MofaPicture.this,"路径不存在",Toast.LENGTH_SHORT);
		}
		BitmapFactory.Options opts = new BitmapFactory.Options();  
		opts.inSampleSize = 4;  
		bitmap = BitmapFactory.decodeFile(path, opts); 
		im=(ImageView)findViewById(R.id.imageView4);
		im.setImageBitmap(bitmap);	
		tv=(TextView)findViewById(R.id.mofatx);
		final Button b1=(Button)findViewById(R.id.taohua);
		
		final Button b4=(Button)findViewById(R.id.senzi);
		final Button b5=(Button)findViewById(R.id.shenhai);
		
		final Button b7=(Button)findViewById(R.id.mofaquxiao);
		final Button b8=(Button)findViewById(R.id.mofabaocun);
		
		 OnClickListener ll=new OnClickListener()
		  {

			public void onClick(View v)
			{
			   switch(v.getId())
			   {
			   case R.id.taohua:				    
				    BitmapFactory.Options opts5 = new BitmapFactory.Options();  
					opts5.inSampleSize = 10;  
					Bitmap bm5 = BitmapFactory.decodeFile(path, opts5);
					bm=MofaUtil.mofa(bm5,mc.target3);
			        im.setImageBitmap(bm);
			        tv.setText(b1.getText().toString());
				   break;
			 
			   case R.id.senzi:
				    BitmapFactory.Options opts1 = new BitmapFactory.Options();  
					opts1.inSampleSize = 10;  
					Bitmap bm3 = BitmapFactory.decodeFile(path, opts1);
					bm=MofaUtil.mofa(bm3,mc.target1);
			        im.setImageBitmap(bm);
			        tv.setText(b4.getText().toString());
				   break;
			   case R.id.shenhai:
				    BitmapFactory.Options opts2 = new BitmapFactory.Options();  
					opts2.inSampleSize = 10;  
					Bitmap bm2 = BitmapFactory.decodeFile(path, opts2);
					bm=MofaUtil.mofa(bm2,mc.target2);
			        im.setImageBitmap(bm);
			        tv.setText(b5.getText().toString());
				   break;
			
			   case R.id.mofaquxiao:
				   MofaPicture.this.finish();
				   break;
			   case R.id.mofabaocun:
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
						 Toast.makeText(MofaPicture.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
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
						 Toast.makeText(MofaPicture.this,"图片保存成功",Toast.LENGTH_SHORT).show();			
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
	
		b4.setOnClickListener(ll);
		b5.setOnClickListener(ll);
	
		b7.setOnClickListener(ll);
		b8.setOnClickListener(ll);
	}

}
