package com.transcendence.blackhole.ui.base.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.imageloader.GlideImageLoader;
import com.lzy.imagepicker.ui.ImagePreviewActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/5/27 14:57
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  生命周期
 * @Edition 1.0
 * @EditionHistory
 */

public class LifeCycleActivity extends TitleBarActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    private final int onCreate =1;
    private final int onStart =2;
    private final int onResume =3;
    private final int onRunning =4;
    private final int onPause =5;
    private final int onStop =6;
    private final int onDestroy =7;
    private final int onRestart =8;

    private ArrayList<ImageItem> imageList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    private ImageView ivImg;
    private List<String> items = new ArrayList<>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case onCreate:
                    items.add("1 onCreate  声明周期的第一个方法.做一些初始化的动作,例如setContentView");
                    adapter.notifyDataSetChanged();
                    break;
                case onStart:
                    items.add("2 onStart 表示Activity正在被启动.Activity为理论可见(取决上层界面是否透明),但不是前台无法操作");
                    adapter.notifyDataSetChanged();
                    break;
                case onResume:
                    items.add("3 onResume 表示Activity已经可见,并且为前台. 与onStart主要是前台后台,有无焦点的区别");
                    adapter.notifyDataSetChanged();
                    break;
                case onRunning:
                    items.add("4 Activity is running");
                    adapter.notifyDataSetChanged();
                    break;
                case onPause:
                    items.add("5 onPause 表示Activity正在停止. 做一些存储数据,停止动画操作.不能太耗时,这会影响新的Activity启动,onPause()必须先执行完,新Activity的onResume()才会执行.");
                    adapter.notifyDataSetChanged();
                    break;
                case onStop:
                    items.add("6 onStop 表示Activity即将停止,可以做稍微重量级的回收工作,同样不能太耗时.");
                    adapter.notifyDataSetChanged();
                    break;
                case onDestroy:
                    ToastUtils.show("7 onDestroy 表示Activity即将被销毁. 可以做一些最终的资源释放.");
                    break;
                case onRestart:
                    items.add("8 onRestart 表示Activity重新启动.当界面从不可见变为可见时调用,场景Home键切换,从任务栈返回");
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    private void sendMessage(int what) {
        Message msg = mHandler.obtainMessage();
        msg.what = what;
        mHandler.sendMessageDelayed(msg,2000);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("1 onCreate  声明周期的第一个方法.做一些初始化的动作,例如setContentView");
        sendMessage(onCreate);
    }




    @Override
    protected void onStart() {
        super.onStart();
        L.d("2 onStart 表示Activity正在被启动.Activity为理论可见(取决上层界面是否透明),但不是前台无法操作");
        sendMessage(onStart);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.d("onRestart 表示Activity重新启动.当界面从不可见变为可见时调用,场景Home键切换,从任务栈返回");
        sendMessage(onRestart);
    }


    @Override
    protected void onResume() {
        super.onResume();
        L.d("3 onResume 表示Activity已经可见,并且为前台. 与onStart主要是前台后台,有无焦点的区别");
//        ToastUtils.show("3 onResume 表示Activity已经可见,并且为前台. 与onStart主要是前台后台,有无焦点的区别");
        sendMessage(onResume);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    sendMessage(onRunning);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        L.d("5 onPause 表示Activity正在停止. 做一些存储数据,停止动画操作.不能太耗时,这会影响新的Activity启动,onPause()必须先执行完,新Activity的onResume()才会执行.");
        sendMessage(onPause);
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.d("6 onStop 表示Activity即将停止,可以做稍微重量级的回收工作,同样不能太耗时.");
        sendMessage(onStop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.d("7 onDestroy 表示Activity即将被销毁. 可以做一些最终的资源释放.");
        sendMessage(onDestroy);
    }


    @Override
    public void finish() {
        // TODO Auto-generated method stub
        L.d("finish()");
//        if (!PublicClass.Exception) {
//            ActivityStackManager.activityStackManager.pop();
//        }
        super.finish();
    }


    TextView mTvTab;

    @Override
    public void init() {
        setTitle("生命周期");
        ivImg = findViewById(R.id.ivImg);
        mTvTab = findViewById(R.id.tvTab);
        lvIndex = findViewById(R.id.lvIndex);

        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);

        lvIndex.setOnItemClickListener(this);
        ivImg.setOnClickListener(this);
        mTvTab.setOnClickListener(this);

        ImageItem imageItem = new ImageItem();
        imageItem.resourceId = R.mipmap.activity_basic_lifecycle;
        imageList.add(imageItem);

        initImagePicker();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_life_cycle;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    boolean isOrigin;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivImg:
                Intent intentPreview = new Intent(mActivity, ImagePreviewActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, imageList);
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivity(intentPreview);

//                Intent intent = new Intent(mActivity, ImagePreviewActivity.class);
//                intent.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
//                intent.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, imageList);
//                intent.putExtra(ImagePreviewActivity.ISORIGIN, isOrigin);
//                intent.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
//                startActivity(intent);
                break;
            case R.id.tvTab:
                startActivity(LifeCycleTwoActivity.class);
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
