package com.transcendence.wan.album.act;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.base.app.LibApplication;
import com.transcendence.core.utils.L;
import com.transcendence.permissions.OnPermissionCallback;
import com.transcendence.permissions.PermissionPool;
import com.transcendence.permissions.Permissions;
import com.transcendence.wan.R;
import com.transcendence.wan.module.publicmod.model.ImageItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Joephone on 2021/4/1 0001 下午 1:47
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc image viewer
 * @Edition 1.0
 * @EditionHistory
 */
public class ImageViewerActivity extends TitleBarActivity implements ViewPager.OnPageChangeListener,View.OnClickListener {
    /**
     * 保存图片
     */
    private TextView tvSave;
    /**
     * 用于管理图片的滑动
     */
    private ViewPager mVp;
    /**
     * 显示当前图片的页数
     */
    private TextView tvCache;
    /**
     * 当前页数
     */
    private int currentIndex;
    /**
     * 接收穿过来当前选择的图片的数量
     */
    int code;
    private boolean isLocal;

    private ArrayList<String> imageList = new ArrayList<>();
    private ArrayList<String> imageTitles = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_viewer;
    }

    @Override
    protected void init() {
        mVp = findViewById(R.id.vp);
        tvSave = findViewById(R.id.tv_save);
        tvSave.setOnClickListener(this);
        tvCache = findViewById(R.id.tv_cache);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ImageItem itemsBean = (ImageItem) bundle.getSerializable("ImageItemsBean");
            imageList = itemsBean.getImageList();
            imageTitles = itemsBean.getImageTitles();
            currentIndex = itemsBean.getCurrentPosition();
        }

        ViewPagerAdapter adapter = new ViewPagerAdapter();
        mVp.setAdapter(adapter);
        mVp.setCurrentItem(currentIndex);
        mVp.addOnPageChangeListener(this);
        mVp.setEnabled(false);
        // 设定当前的页数和总页数
//        if (selet == 2) {
//            tvCache.setText((code + 1) + " / " + imageList.size());
//        }
        tvCache.setText((currentIndex + 1) + " / " + imageList.size());
    }


    public static void start(Context context, int position, ArrayList<String> imageList, ArrayList<String> imageTitles) {
        Bundle bundle = new Bundle();
        ImageItem itemsBean = new ImageItem();
        itemsBean.setImageList(imageList);
        itemsBean.setImageTitles(imageTitles);
        itemsBean.setCurrentPosition(position);
        bundle.putSerializable("ImageItemsBean",itemsBean);
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_save:
                Permissions.with(mActivity)
                        .permission(PermissionPool.MANAGE_EXTERNAL_STORAGE)
                        .request(new OnPermissionCallback() {
                            @Override
                            public void onGranted(List<String> permissions, boolean all) {  //imageList.get(currentIndex)
                                returnBitMap("http://8.136.115.97:8088/group1/M00/00/22/CIhzYWDCwIaAMnyOAACtXhZd35o157.jpg");
                            }

                            @Override
                            public void onDenied(List<String> permissions, boolean never) {
                                startAppSettings();
                                Toast.makeText(getApplicationContext(), "您拒绝了访问存储设备", Toast.LENGTH_SHORT).show();
                            }
                        });

                break;
        }
    }




//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if(requestCode == 0x10 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            downloadImage();
//        } else {
//            Toast.makeText(mActivity, "您拒绝了访问存储设备", Toast.LENGTH_SHORT).show();
//        }
//    }

//    final MyDownloadManager myDownloadManager = new MyDownloadManager();

//    private void downloadImage() {
//        L.d("imageList.get(currentIndex):"+imageList.get(currentIndex));
//        myDownloadManager.addDownloadTask(imageList.get(currentIndex));
//    }

    /**
     * ViewPager的适配器
     */
    private class ViewPagerAdapter extends PagerAdapter {

        private LayoutInflater inflater;

        ViewPagerAdapter() {
            inflater = getLayoutInflater();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            tvCache.setText((position + 1) + " / " + imageList.size());
            setTitle(imageTitles.get(position));
            View view = inflater.inflate(R.layout.activity_image_viewpager_very_image, container, false);
            final ImageView zoomImageView = view.findViewById(R.id.iv_img);
            final ProgressBar progressBar = view.findViewById(R.id.loading);
            // 保存网络图片的路径
            String adapterImageEntity = (String) getItem(position);
            L.d("adapterImageEntity:"+adapterImageEntity);
            String imageUrl;
            if (isLocal) {
                imageUrl = "file://" + adapterImageEntity;
                tvSave.setVisibility(View.GONE);
            } else {
                imageUrl = adapterImageEntity;
            }

            progressBar.setVisibility(View.VISIBLE);
            progressBar.setClickable(false);

            Glide.with(ImageViewerActivity.this).load(imageUrl)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            L.d("onException");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            /**这里应该是加载成功后图片的高,最大为屏幕的高*/
                            int height = resource.getIntrinsicHeight();
                            int wHeight = getWindowManager().getDefaultDisplay().getHeight();
                            if (height < wHeight) {
                                zoomImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            } else {
                                zoomImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            }
                            return false;
                        }
                    })
                    .error(R.drawable.pic_404)
                    .into(zoomImageView);

//            zoomImageView.setOnPhotoTapListener(ImageViewerActivity.this);
            container.addView(view, 0);
            return view;
        }

        @Override
        public int getCount() {
            if (imageList == null || imageList.size() == 0) {
                return 0;
            }
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        private Object getItem(int position) {
            return imageList.get(position);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int position) {
        currentIndex = position;
    }

    public static String MD5(String path)  {
        // URL url = new URL(path);
        //String url = path;

        //String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        //final long startTime = System.currentTimeMillis();

        //String filename=url.substring(url.lastIndexOf("/") + 1);
        String filename ="pp.jpg";
        return filename;
    }



//    public void onClick(View view) {
//        String s = setBitmap(this, bitmap);
//        Toast.makeText(this, "以保存到"+s, Toast.LENGTH_SHORT).show();
//    }

//    Bitmap bitmap;

    public void returnBitMap(final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    String path = setBitmap(mActivity, bitmap);
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(), "已保存到"+path, Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public String setBitmap(Context context, Bitmap bmp) {

        SimpleDateFormat formatter = new SimpleDateFormat("ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        // 首先保存图片    child 参数是保存图片的文件夹名字
//        File appDir = new File(Environment.getExternalStorageDirectory(), "最佳拍档");
        File appDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "sign_" + System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        String path = file.getAbsolutePath();
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), path, fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
//                             Toast.makeText(LibApplication.getAppContext(), "以保存到"+path, Toast.LENGTH_SHORT).show();
        return path;
    }


    /**
     * 启动当前应用设置页面
     */
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }



}
