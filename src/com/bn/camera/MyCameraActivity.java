package com.bn.camera;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import com.bn.util.ScreenScaleResult;
import com.bn.util.ScreenScaleUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.OnZoomChangeListener;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class MyCameraActivity extends Activity implements
		SurfaceHolder.Callback {
	SurfaceView mySurfaceView; // SurfaceView的引用
	SurfaceHolder mySurfaceHolder; // SurfaceHolder的引用
	Camera myCamera; // Camera的引用
	static Bitmap bm; // 拍摄的照片
	MenuItem[] fenbian = new MenuItem[6]; // 分辨率 子菜单数组
	MenuItem[] shanguang = new MenuItem[2]; // 闪光子数组
	MenuItem[] goutu = new MenuItem[2]; // 构图子数组
	MenuItem[] chu = new MenuItem[2]; // 声音子数组
	MenuItem[] jiaoju = new MenuItem[5]; // 焦距数组
	Canvas canvas = new Canvas(); // 获取画布对象
	ImagePaint im; // 声明ImagePaint引用
	String path;
	File fTest;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置为横屏
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// 设置全屏显示
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		WindowManager wm = this.getWindowManager(); // 获得屏幕大小
		int width = wm.getDefaultDisplay().getWidth(); // 获得屏幕宽度
		int height = wm.getDefaultDisplay().getHeight(); // 获得屏幕高度

		// 设置显示主界面
		setContentView(R.layout.main);
		im = (ImagePaint) findViewById(R.id.imagepaint1);

		// 得到拍照预览SurfaceView的引用
		mySurfaceView = (SurfaceView) findViewById(R.id.mySurfaceView);
		// 获得SurfaceHolder
		mySurfaceHolder = mySurfaceView.getHolder();
		// 添加回调接口的实现
		mySurfaceHolder.addCallback(this);
		mySurfaceHolder.setFixedSize(0, 0);
		mySurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (myCamera != null) {// 若相机不为空则释放相机
			myCamera.stopPreview(); // 停止预览
			myCamera.release(); // 释放照相机对象
			myCamera = null; // 清空照相机引用
		}
	}

	// 重写SurfaceHolder.Callback接口中的方法
	@SuppressLint("NewApi")
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int width,
			int height) {
		if (myCamera == null) {// 若在非预览状态且未打开照相机则打开照相机
			myCamera = Camera.open();
		}
		if (myCamera != null) {// 若照相机成功打开且处在非预览状态则进入预览状态
			try {
				Camera.Parameters myParameters = myCamera.getParameters();
				// 设置照片格式
				myParameters.setPictureFormat(PixelFormat.JPEG);
				List<Camera.Size> sizes = myParameters
						.getSupportedPreviewSizes();
				int i = sizes.size();
				Camera.Size cs = sizes.get(i - 2);
				myParameters.setPreviewSize(cs.width, cs.height);
				myCamera.setParameters(myParameters);
				myCamera.setPreviewDisplay(mySurfaceHolder);
				myCamera.startPreview();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 重写SurfaceHolder.Callback接口中的方法
	public void surfaceCreated(SurfaceHolder holder) {

	}

	// 重写SurfaceHolder.Callback接口中的方法
	public void surfaceDestroyed(SurfaceHolder arg0) {

	}

	@SuppressLint("NewApi")
	public void paizhao() {
		if (myCamera != null) {
			Camera.Parameters myParameters = myCamera.getParameters(); // 获得参数
			if (Constant.flag4) // 判断标志位
			{

				myParameters.setFlashMode(myParameters.FLASH_MODE_TORCH);// 打开闪光灯
				myCamera.setParameters(myParameters);
				myCamera.takePicture(myShutterCallback, myRawCallback,
						myjpegCallback);// 拍照
				myParameters.setFlashMode(myParameters.FLASH_MODE_OFF);
				myCamera.setParameters(myParameters);
			} else {
				myCamera.takePicture(myShutterCallback, myRawCallback,
						myjpegCallback);// 拍照
			}
		}
	}

	ShutterCallback myShutterCallback = new ShutterCallback() {// 快门回调接口
		public void onShutter() {

		} // 空实现
	};
	PictureCallback myRawCallback = new PictureCallback() {// 拍照回调接口
		public void onPictureTaken(byte[] data, Camera camera) {
		} // 空实现
	};
	PictureCallback myjpegCallback = new PictureCallback() {// 拍照回调接口
		public void onPictureTaken(byte[] data, Camera camera) {
			bm = BitmapFactory.decodeByteArray(data, 0, data.length);

			if (!Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED)) { // 若没有插闪存卡则报错
				Toast.makeText(MyCameraActivity.this, "请检测内存卡",
						Toast.LENGTH_SHORT).show();
				myCamera.startPreview();
				return;
			}
			String path = Environment.getExternalStorageDirectory()
					.getAbsolutePath();// 获取存储路径
			File fTest1 = new File(path + "/" + Constant.c + ".jpg");
			while (fTest1.exists()) {
				Constant.c += 1;
				fTest1 = new File(path + "/" + Constant.c + ".jpg");
			}
			fTest = new File(path + "-ext/" + Constant.c + ".jpg");
			while (fTest.exists()) {
				Constant.c += 1;
				fTest = new File(path + "-ext/" + Constant.c + ".jpg");
			}
			try {
				FileOutputStream fout = new FileOutputStream(fTest);
				BufferedOutputStream bos = new BufferedOutputStream(fout);
				int w = Constant.x;
				int h = Constant.y;
				float Xscal = w * 1f / bm.getWidth();
				float Yscal = h * 1f / bm.getHeight();
				Matrix mx = new Matrix();
				mx.postScale(Xscal, Yscal);
				Bitmap overlayb = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
						bm.getHeight(), mx, true);
				overlayb.compress(Bitmap.CompressFormat.JPEG, // 图片格式
						80, // 品质0-100
						bos // 使用的输出流
				);
				bos.flush();
				bos.close();
				Toast.makeText(MyCameraActivity.this, "图片保存成功",
						Toast.LENGTH_SHORT).show();
				overlayb.recycle();
			} catch (Exception e) {
				e.printStackTrace();
			}
			bm.recycle();
			bm = BitmapFactory.decodeByteArray(data, 0, data.length);
			try {
				FileOutputStream fout = new FileOutputStream(fTest1);
				BufferedOutputStream bos = new BufferedOutputStream(fout);
				int w = Constant.x;
				int h = Constant.y;
				float Xscal = w * 1f / bm.getWidth();
				float Yscal = h * 1f / bm.getHeight();
				Matrix mx = new Matrix();
				mx.postScale(Xscal, Yscal);
				Bitmap overlayb = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
						bm.getHeight(), mx, true);
				overlayb.compress(Bitmap.CompressFormat.JPEG, // 图片格式
						80, // 品质0-100
						bos // 使用的输出流
				);
				bos.flush();
				bos.close();
				Toast.makeText(MyCameraActivity.this, "图片保存成功",
						Toast.LENGTH_SHORT).show();
				overlayb.recycle();
			} catch (IOException O) {
				O.printStackTrace();
			}
			bm.recycle(); // 释放图片资源
			Intent it = new Intent(MyCameraActivity.this, ImageActivity.class);
			String path1 = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			path1 = path1 + "-ext/";
			String path2 = path + "/";
			it.putExtra("str", path1);
			it.putExtra("str1", path2);
			MyCameraActivity.this.startActivity(it);
			myCamera.startPreview();
		}
	};

	@SuppressLint("NewApi")
	public void setzoom(float zoom) {
		int jiaoju = (int) zoom;
		if (myCamera != null) {
			Camera.Parameters myParameters = myCamera.getParameters();
			if (jiaoju == 0) {
				jiaoju = 1;
			} else {
				jiaoju = myParameters.getMaxZoom() * jiaoju / 100;
			}
			if (myParameters.isSmoothZoomSupported()
					|| myParameters.isZoomSupported()) {

				if (jiaoju <= 0) {
					jiaoju = 1;
				}
				jiaoju = -jiaoju;
				jiaoju = myParameters.getMaxZoom() + jiaoju;
				myParameters.setZoom(jiaoju);
			} else {
				Toast.makeText(MyCameraActivity.this, "该设备不支持变焦功能",
						Toast.LENGTH_SHORT).show();
			}

			myCamera.setParameters(myParameters);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu submenufen = menu.addSubMenu(Constant.MAIN_GROUP,
				Constant.MENU_FENBIAN, 0, R.string.fenbian);// 分辨率菜单
		submenufen.setIcon(R.drawable.fenbian);
		fenbian[0] = submenufen.add(Constant.FENBIAN_GROUP, Constant.MENU_FEN1,
				0, R.string.fen1);
		fenbian[1] = submenufen.add(Constant.FENBIAN_GROUP, Constant.MENU_FEN2,
				0, R.string.fen2);
		fenbian[2] = submenufen.add(Constant.FENBIAN_GROUP, Constant.MENU_FEN3,
				0, R.string.fen3);
		fenbian[3] = submenufen.add(Constant.FENBIAN_GROUP, Constant.MENU_FEN4,
				0, R.string.fen4);
		fenbian[4] = submenufen.add(Constant.FENBIAN_GROUP, Constant.MENU_FEN5,
				0, R.string.fen5);
		fenbian[5] = submenufen.add(Constant.FENBIAN_GROUP, Constant.MENU_FEN6,
				0, R.string.fen6);
		fenbian[0].setChecked(true);
		submenufen.setGroupCheckable(Constant.FENBIAN_GROUP, true, true);
		SubMenu shan = menu.addSubMenu(Constant.MAIN_GROUP, Constant.MENU_SHAN,
				0, R.string.shanguang); // 闪光子菜单
		shan.setIcon(R.drawable.shan);
		shanguang[0] = shan.add(Constant.SHAN_GROUP, Constant.MENU_SHAN1, 0,
				R.string.shan1);
		shanguang[1] = shan.add(Constant.SHAN_GROUP, Constant.MENU_SHAN2, 0,
				R.string.shan2);
		shan.setGroupCheckable(Constant.SHAN_GROUP, true, true);
		if (Constant.flag4) {
			shanguang[0].setChecked(true);
		} else {
			shanguang[1].setChecked(true);
		}
		SubMenu gou = menu.addSubMenu(Constant.MAIN_GROUP, Constant.MENU_GOUTU,
				0, R.string.goutu); // 构图子菜单
		gou.setIcon(R.drawable.goutu);
		goutu[0] = gou.add(Constant.MENU_GOUTU, Constant.MENU_GOU1, 0,
				R.string.gou1);
		goutu[1] = gou.add(Constant.MENU_GOUTU, Constant.MENU_GOU2, 0,
				R.string.gou2);
		gou.setGroupCheckable(Constant.MENU_GOUTU, true, true);
		if (Constant.flag3) {
			goutu[0].setChecked(true);
		} else {
			goutu[1].setChecked(true);
		}
		SubMenu chuping = menu.addSubMenu(Constant.MAIN_GROUP,
				Constant.MENU_SHENG, 0, R.string.chu); // 声音子菜单
		chuping.setIcon(R.drawable.chu);
		chu[0] = chuping.add(Constant.MENU_SHENG, Constant.MENU_SHENG1, 0,
				R.string.shan1);
		chu[1] = chuping.add(Constant.MENU_SHENG, Constant.MENU_SHENG2, 0,
				R.string.shan2);
		chuping.setGroupCheckable(Constant.MENU_SHENG, true, true);
		if (Constant.flag5) {
			chu[0].setChecked(true);
		} else {
			chu[1].setChecked(true);
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Constant.MENU_FEN1:
		case Constant.MENU_FEN2:
			item.setChecked(true);
			Constant.x = 800;
			Constant.y = 600;
			break;
		case Constant.MENU_FEN3:
			item.setChecked(true);
			Constant.x = 1024;
			Constant.y = 768;
			break;
		case Constant.MENU_FEN4:
			item.setChecked(true);
			Constant.x = 1280;
			Constant.y = 960;
			break;
		case Constant.MENU_FEN5:
			item.setChecked(true);
			Constant.x = 1600;
			Constant.y = 1200;
			break;
		case Constant.MENU_FEN6:
			item.setChecked(true);
			Constant.x = 3264;
			Constant.y = 2448;
			break;
		case Constant.MENU_SHAN1:
			item.setChecked(true);
			Constant.flag4 = true;
			break;
		case Constant.MENU_SHAN2:
			item.setChecked(true);
			Constant.flag4 = false;
			break;
		case Constant.MENU_GOU1:
			item.setChecked(true);
			Constant.flag3 = true;
			im.invalidate();
			break;
		case Constant.MENU_GOU2:
			item.setChecked(true);
			Constant.flag3 = false;
			im.invalidate();
			break;
		case Constant.MENU_SHENG1:
			item.setChecked(true);
			Constant.flag5 = true;
			break;
		case Constant.MENU_SHENG2:
			item.setChecked(true);
			Constant.flag5 = false;
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}