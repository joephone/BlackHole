package com.transcendence.wan.module.mine.act;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.imageloader.GlideImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.transcendence.core.aop.PermissionAop;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.L;
import com.transcendence.permissions.OnPermissionCallback;
import com.transcendence.permissions.PermissionPool;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseActivity;
import com.transcendence.wan.module.mine.presenter.MinePresenter;
import com.transcendence.wan.utils.UserUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * @Author Joephone on 2021/4/27 0027 下午 5:51
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class PersonalActivity extends WanBaseActivity<MinePresenter> implements View.OnClickListener, PromptButtonListener{

    private TextView tvId,tvName,tvAddress;
    private ImageView ivAvatar;
    private LinearLayout llName;
    private PromptDialog promptDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_personal;
    }

    @Nullable
    @Override
    protected MinePresenter initPresenter() {
        return new MinePresenter();
    }

    @Override
    protected void initView() {
        setTitle("个人中心");
        tvId = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_name);
        tvAddress = findViewById(R.id.tv_address);
        llName = findViewById(R.id.ll_name);
        ivAvatar = findViewById(R.id.iv_avatar);
        ivAvatar.setOnClickListener(this);

        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        initImagePicker();
    }

    @Override
    protected void loadData() {
        if(UserUtils.getInstance().getLoginBean()!=null){
            tvId.setText(UserUtils.getInstance().getLoginBean().getId()+"");
            tvName.setText(UserUtils.getInstance().getLoginBean().getNickname());
        }
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, PersonalActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_name:
                break;
            case R.id.iv_avatar:
                showPromDialog();
//                Permissions.with(mActivity)
//                        .permission(new String[]{PermissionPool.MANAGE_EXTERNAL_STORAGE,PermissionPool.CAMERA})
//                        .request(new OnPermissionCallback() {
//                            @Override
//                            public void onGranted(List<String> permissions, boolean all) {
//
//                            }
//
//                            @Override
//                            public void onDenied(List<String> permissions, boolean never) {
//
//                            }
//                        });

                break;
        }
    }

    @PermissionAop({PermissionPool.MANAGE_EXTERNAL_STORAGE,PermissionPool.CAMERA})
    private void showPromDialog() {
        PromptButton cancle = new PromptButton("取消", null);
        cancle.setTextColor(Color.parseColor("#0076ff"));
        //设置显示的文字大小及颜色
//                promptDialog.getAlertDefaultBuilder().textSize(12).textColor(Color.GRAY);
        //默认两个按钮为Alert对话框，大于三个按钮的为底部SHeet形式展现
        promptDialog.showAlertSheet("", true, cancle,
                new PromptButton("拍照", PersonalActivity.this),
                new PromptButton("相册", PersonalActivity.this));
    }


    /**
     * 根据需要处理返回键，这里主要针对Alert和Sheet的对话框的返回处理
     */
    @Override
    public void onBackPressed() {
        if (promptDialog.onBackPressed())
            super.onBackPressed();
    }

    @Override
    public void onClick(PromptButton button) {
        switch (button.getText()){
            case "相册":
                Intent intent = new Intent(mActivity, ImageGridActivity.class);
                /* 如果需要进入选择的时候显示已经选中的图片，
                 * 详情请查看ImagePickerActivity
                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                startActivityForResult(intent, Global.REQUEST_CODE.REQUEST_CODE_SELECT);
                break;
            case "拍照":
                intent = new Intent(mActivity, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, Global.REQUEST_CODE.REQUEST_CODE_SELECT);
                break;
        }
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(false);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(1000);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(1000);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }


    ArrayList<ImageItem> images = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == Global.REQUEST_CODE.REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.get(0) != null && !TextUtils.isEmpty(images.get(0).path)) {
                    File outFile = new File(images.get(0).path);
                    if (outFile.exists()) {
                        L.d("outFile 存在" + images.get(0).path);
                    } else {
                        L.d("outFile 不存在");
                    }
                    L.d("images.get(0).path--------" + images.get(0).path);
                    L.d("outFile.getPath()--------" + outFile.getPath());
                    ImagePicker.getInstance().getImageLoader().displayImage(mActivity, images.get(0).path, ivAvatar, 110, 110);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == Global.REQUEST_CODE.REQUEST_CODE_PREVIEW) {
//                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
//                if (images != null) {
//                    selImageList.clear();
//                    selImageList.addAll(images);
//                    adapter.setImages(selImageList);
//                }
            }
        }
    }
}
