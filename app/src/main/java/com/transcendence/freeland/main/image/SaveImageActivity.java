package com.transcendence.freeland.main.image;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.freeland.R;

import java.io.OutputStream;

/**
 * @author joephone
 * @date 2023/1/21
 * @desc Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
 * Uri uri = Uri.fromFile(file);
 * intent.setData(uri);
 * sendBroadcast(intent);
 */
public class SaveImageActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView btn_saveImage;
    private ImageView iv;

    public static void start(Context context){
        Intent intent = new Intent(context,SaveImageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_image);
        btn_saveImage = findViewById(R.id.tv_save_image);
        btn_saveImage.setOnClickListener(this);
        iv = findViewById(R.id.iv);
        //"https://t7.baidu.com/it/u=2621658848,3952322712&fm=193&f=GIF"
        Glide.with(this).load(R.drawable.ic_qr_zfb).into(iv);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_save_image) {//对于共享区间写入的权限，在API29 Android系统10之前是需要申请的
            //在API29及之后是不需要申请的，默认是允许的
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            } else {
                //保存图片到相册
                SaveImage();
            }
        }
    }

    //请求权限后的结果回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //保存图片到相册
                SaveImage();
            } else {
                Toast.makeText(this, "你拒绝了该权限，无法保存图片！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveImage() {
        //获取要保存的图片的位图
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        //API 29之前可用. API29之后该方法已经被弃用了
        //MediaStore 相当于管理媒体资源的一个管理器，类似于一个数据库，对媒体资源的一个索引(包括图片 音频 视频)，在里面都有索引
//        if (MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "", "") == null) {
//            Toast.makeText(this, "保存失败！", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
//        }
        //创建一个子线程，将耗时任务在子线程中完成，防止主线程被阻塞
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //获取要保存的图片的位图
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_qr_zfb);
                    //创建一个保存的Uri
                    Uri saveUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
                    if (TextUtils.isEmpty(saveUri.toString())) {
                        Looper.prepare();
                        Toast.makeText(SaveImageActivity.this, "保存失败！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                        return;
                    }
                    LogUtils.d("saveUri---"+saveUri.toString());
                    OutputStream outputStream = getContentResolver().openOutputStream(saveUri);
                    //将位图写出到指定的位置
                    //第一个参数：格式JPEG 是可以压缩的一个格式 PNG 是一个无损的格式
                    //第二个参数：保留原图像90%的品质，压缩10% 这里压缩的是存储大小
                    //第三个参数：具体的输出流
                    if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)) {
                        Looper.prepare();
                        Toast.makeText(SaveImageActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    } else {
                        Looper.prepare();
                        Toast.makeText(SaveImageActivity.this, "保存失败！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
