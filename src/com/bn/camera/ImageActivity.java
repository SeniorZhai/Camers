package com.bn.camera;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bn.texiao.Laozhaopian;
import com.bn.texiao.MofaPicture;
import com.bn.texiao.QitaPicture;
import com.bn.texiao.ShouhuiPicture;
import com.bn.texiao.Zqpicture;
import com.bn.texiao.xiangkuang;

public class ImageActivity extends Activity {

	String ft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 设置全屏显示
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.huoqu);

		// LayoutInflater la=LayoutInflater.from(ImageActivity.this);

		MyCameraActivity mca = new MyCameraActivity();
		Intent it = this.getIntent();
		ft = it.getStringExtra("str");
		if (ft == null) {
			Toast.makeText(ImageActivity.this, "没有找到文件", Toast.LENGTH_SHORT)
					.show();
		}
		ft = ft + Constant.c + ".jpg";
		// ft1=ft1+MyCameraActivity.c+".jpg";

		// ft=ft+"/"+MyCameraActivity.c+".jpg";
		Bitmap bm = BitmapFactory.decodeFile(ft);
		// Bitmap bm1=BitmapFactory.decodeFile(ft1);
		ImageView iv = (ImageView) findViewById(R.id.imageView1);
		if (bm == null) {
			ft = it.getStringExtra("str1");
			ft = ft + Constant.c + ".jpg";
			bm = BitmapFactory.decodeFile(ft);
			iv.setImageBitmap(bm);
		} else {
			iv.setImageBitmap(bm);
		}
		Button b1 = (Button) findViewById(R.id.button1);
		Button b2 = (Button) findViewById(R.id.button2);
		Button b3 = (Button) findViewById(R.id.button3);
		Button b4 = (Button) findViewById(R.id.button4);
		Button b5 = (Button) findViewById(R.id.button5);
		Button b6 = (Button) findViewById(R.id.button6);

		OnClickListener ll = new OnClickListener() {

			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.button1:
					Intent it = new Intent(ImageActivity.this, Zqpicture.class);
					it.putExtra("path", ft);
					ImageActivity.this.startActivity(it);
					break;
				case R.id.button2:
					Intent intent = new Intent(ImageActivity.this,
							ShouhuiPicture.class);
					intent.putExtra("path", ft);
					ImageActivity.this.startActivity(intent);
					break;
				case R.id.button3:
					Intent intent5 = new Intent(ImageActivity.this,
							Laozhaopian.class);
					intent5.putExtra("path", ft);
					ImageActivity.this.startActivity(intent5);
					break;
				case R.id.button4:
					Intent it1 = new Intent(ImageActivity.this,
							MofaPicture.class);
					it1.putExtra("path", ft);
					ImageActivity.this.startActivity(it1);
					break;
				case R.id.button5:
					Intent it4 = new Intent(ImageActivity.this,
							xiangkuang.class);
					it4.putExtra("path", ft);
					ImageActivity.this.startActivity(it4);
					break;
				case R.id.button6:
					Intent it2 = new Intent(ImageActivity.this,
							QitaPicture.class);
					it2.putExtra("path", ft);
					ImageActivity.this.startActivity(it2);
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
	}

}
