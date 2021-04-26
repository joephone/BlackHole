package com.transcendence.wan.album.act;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.utils.L;
import com.transcendence.wan.R;
import com.transcendence.wan.album.MyDownloadManager;
import com.transcendence.wan.module.publicmod.model.ImageItem;

import java.util.ArrayList;

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
                checkPermission();
                break;
        }
    }


    private void checkPermission() {
        int result = ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED) {
            downloadImage();
        } else {
//            dismiss();
//            saveButtonClicked = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x10);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 0x10 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            downloadImage();
        } else {
            Toast.makeText(mActivity, "您拒绝了访问存储设备", Toast.LENGTH_SHORT).show();
        }
    }

    final MyDownloadManager myDownloadManager = new MyDownloadManager();

    private void downloadImage() {
        L.d("imageList.get(currentIndex):"+imageList.get(currentIndex));
        myDownloadManager.addDownloadTask(imageList.get(currentIndex));
    }

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
}
