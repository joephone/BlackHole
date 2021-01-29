package com.transcendence.blackhole.index;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.hjq.toast.ToastUtils;
import com.transcendence.blackhole.R;
import com.transcendence.core.arouter.ARouterUtils;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.blackhole.core.AppConstantValue;
import com.transcendence.core.utils.L;
import com.transcendence.core.utils.StringUtils;
import com.transcendence.core.permission.PermissionPool;
import com.transcendence.core.permission.PermissionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/12/27 14:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class IndexActivity extends TitleBarActivity implements View.OnClickListener {
    private ImageView mIvAdd,mIvMap,mIvWan,mIvApp,mIvGreenStar,mIvMusic,mIvSwan,mIvDou,mIvChe;
    private List<ImageView> ivList = new ArrayList<>();


    private final int radius2 = 300;
    private boolean mUnLocFirstClick;

    @Override
    public void init() {
        setTitle(false,"总序列");
        mIvAdd = findViewById(R.id.ivAdd);
        mIvMap = findViewById(R.id.ivMap);
        mIvWan = findViewById(R.id.ivWan);
        mIvApp = findViewById(R.id.ivApp);
        mIvGreenStar = findViewById(R.id.ivGreenStar);
        mIvMusic = findViewById(R.id.ivMusic);
        mIvSwan = findViewById(R.id.ivSwan);
        mIvDou = findViewById(R.id.ivDou);
        mIvChe = findViewById(R.id.ivChe);

        ivList.add(mIvApp);
        ivList.add(mIvWan);
        ivList.add(mIvMap);
        ivList.add(mIvGreenStar);
        ivList.add(mIvMusic);
        ivList.add(mIvSwan);
        ivList.add(mIvDou);
        ivList.add(mIvChe);

        for (int i = 0; i < ivList.size(); i++) {
            ivList.get(i).setOnClickListener(this);
        }


        showCircleMenu();
    }

    private void showCircleMenu() {
        for (int i = 0; i < ivList.size(); i++) {
            final int ang = i;

            PointF point = new PointF();
            int avgAngle = (360 / (ivList.size() ));
            int angle = avgAngle * ang;

            point.x = (float) Math.cos(angle * (Math.PI / 180)) * radius2;
            point.y = (float) Math.sin(angle * (Math.PI / 180)) * radius2;


            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(ivList.get(ang), "translationX", 0, point.x);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(ivList.get(ang), "translationY", 0, point.y);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(1000);
            animatorSet.play(objectAnimatorX).with(objectAnimatorY);

            animatorSet.start();

            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    }

    private void closeCircleMenu() {
        for (int i = 0; i < ivList.size(); i++) {
            PointF point = new PointF();
            int avgAngle = (360 / (ivList.size() ));
            int angle = avgAngle * i;
//            L.d( "angle=" + angle);
            point.x = (float) Math.cos(angle * (Math.PI / 180)) * radius2;
            point.y = (float) Math.sin(angle * (Math.PI / 180)) * radius2;

            L.d( point.toString());
            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(ivList.get(i), "translationX", point.x, 0);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(ivList.get(i), "translationY", point.y, 0);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.play(objectAnimatorX).with(objectAnimatorY);
            animatorSet.start();
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_arouter;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mIvAdd.getId()) {
//            Boolean isShowing = (Boolean) iv11.getTag();
//            if (null == isShowing || isShowing == false) {
//                iv11.setTag(true);
//            } else {
//                iv11.setTag(false);
//                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv11, "rotation", 45, 0);
//                objectAnimator.setDuration(500);
//                objectAnimator.start();
//                closeCircleMenu();
//            }
        } else {
            L.d("position--"+ivList.indexOf(v));
            if(ivList.indexOf(v) >= AppConstantValue.mainIndex.length){
                ToastUtils.show("暂未开放");
                return;
            }

            if(ivList.indexOf(v)==2){
                if(mUnLocFirstClick){
                    showSystemPermissionsSettingDialog(mActivity);
                }else {
                    PermissionUtils.getInstance().checkPermissions(IndexActivity.this, PermissionPool.LOCATION,permissionResult);
                }
                return;
            }
            ARouterUtils.navigation(AppConstantValue.mainIndex[ivList.indexOf(v)]);
//            Toast.makeText(this, "点击了第" + (imageViews.indexOf(v) == -1 ? ivList.indexOf(v) : imageViews.indexOf(v)) + "个", Toast.LENGTH_SHORT).show();
        }
    }

    PermissionUtils.IPermissionsResult permissionResult = new PermissionUtils.IPermissionsResult() {
        @Override
        public void onGranted() {
            L.d("可以去");
            ARouterUtils.navigation(AppConstantValue.mainIndex[2]);
        }

        @Override
        public void onDenied() {
            L.d("不通过");
            mUnLocFirstClick = true;
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.getInstance().onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }


    /**
     * 不再提示权限时的展示对话框
     * String.format("%s需要获得%s权限才能正常使用此功能,请允许！", appLabel, permissionName);
     */
    AlertDialog mPermissionDialog;

    private void showSystemPermissionsSettingDialog(final Activity context) {
        final String mPackName = context.getPackageName();
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(context)
                    .setMessage(String.format("%s需要获得%s权限才能正常使用此功能,请允许！", StringUtils.getString(R.string.app_name), "定位"))
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();
                            mUnLocFirstClick = false;
                            PermissionUtils.getInstance().checkPermissions(IndexActivity.this, PermissionPool.LOCATION,permissionResult);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者做其他操作
                            cancelPermissionDialog();
                            //mContext.finish();
                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }

    //关闭对话框
    private void cancelPermissionDialog() {
        if (mPermissionDialog != null) {
            mPermissionDialog.cancel();
            mPermissionDialog = null;
        }

    }

}
