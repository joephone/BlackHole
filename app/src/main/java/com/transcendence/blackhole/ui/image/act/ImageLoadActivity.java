package com.transcendence.blackhole.ui.image.act;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjq.image.ImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.imageloader.GlideImageLoader;
import com.lzy.imagepicker.view.CropImageView;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;

import java.util.ArrayList;

/**
 * @author Joephone on 2019/6/19 11:17
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ImageLoadActivity extends TitleBarActivity implements View.OnClickListener{

    ImageView mIvImg;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    /**
     * 当前选择的所有图片
     */
    private ArrayList<ImageItem> imageList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_imageload;
    }

    @Override
    public void init() {
        setTitle("图片加载");
        initImagePicker();
        mIvImg = findViewById(R.id.ivImg);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        mIvImg.setOnClickListener(this);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ImageItem imageItem = new ImageItem();
        switch (v.getId()){
            case R.id.textView1:
                imageList.clear();
                imageItem.path = "https://www.baidu.com/img/bd_logo.png";
                imageList.add(imageItem);
                ImageLoader.loadImage(mIvImg,imageItem.path);
                break;
            case R.id.textView2:
                imageList.clear();
                imageItem.resourceId = R.mipmap.beauty02;
                imageList.add(imageItem);
                ImageLoader.loadImage(mIvImg,imageItem.resourceId);
                break;
            case R.id.textView3:
                imageList.clear();
                imageItem.path = "/storage/emulated/0/WildmaIDCardCamera/WildmaIDCardCamera.idCardFrontCrop.jpg";
                imageList.add(imageItem);
                ImageLoader.loadImage(mIvImg,imageItem.path);
                break;
            case R.id.ivImg:
                if(imageList.size()>0){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ImageItem",imageList.get(0));
                    startActivity( PictruePreviewActivity.class,bundle);
                }
                break;
        }
    }


    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }
}
