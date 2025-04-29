package com.transcendence.freeland.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.base.route.RoutePath;
import com.transcendence.core.base.route.RouteUtils;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.core.widget.menugroup.CircleMenuAdapter;
import com.transcendence.core.widget.menugroup.CircleMenuItem;
import com.transcendence.freeland.R;
import com.transcendence.freeland.databinding.ActivityArouterBinding;


import java.util.ArrayList;
import java.util.List;

/**
 * @author joephone
 * @date 2023/1/19
 * @desc
 */
public class ArouterAc extends AppAc {

    ActivityArouterBinding activityBinding;
    private List<CircleMenuItem> mMenuItems = new ArrayList<>();
    private String[] mItemTexts = new String[] { "MainApp ", "GreenStar", "Music" };
    private int[] mItemImgs = new int[] { R.mipmap.ic_app_ten,
            R.mipmap.ic_app_green_star, R.mipmap.ic_app_music };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_arouter;
    }

    protected void initView() {
        mIsBackVisible = false;
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_arouter);

        initData(mItemTexts, mItemImgs);
        //中心视图
        View centerView = LayoutInflater.from(this).inflate(R.layout.circle_menu_item_center,null,false);

        centerView.setOnClickListener(v ->  {
////            SaveImageActivity.launch(this);
////            startAc(SaveZfPicAc.class);
//            new PopupDrugDetail(ArouterAc.this).showPopup();

//            Logcat.enableLogcat(this);  jump.app/open?param=Tom&param2=Cat
            PackageManager packageManager = getPackageManager();
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("dffs_app://"));
            List<ResolveInfo> activities =packageManager.queryIntentActivities(intent, 0);
            boolean isValid = !activities.isEmpty();
            if (isValid) {
                startActivity(intent);
            } else {
                LogUtils.d("打开第三方APP失败");
            }
        });

        activityBinding.circleMenuGroup.setAdapter(new CircleMenuAdapter(mMenuItems));
        activityBinding.circleMenuGroup.setCenterView(centerView);
        activityBinding.circleMenuGroup.setOnMenuItemClickListener((view, pos) ->  {
//            showToast(mItemTexts[pos]);
            switch (pos){
                case 0:
                    RouteUtils.navigationActivity(RoutePath.App.PAGER_MAIN);
                    break;
                case 1:
                    RouteUtils.navigationActivity(RoutePath.GreenStar.PAGER_MAIN);
                    break;
                case 2:
                    RouteUtils.navigationActivity(RoutePath.Gallery.PAGER_MAIN);
//                    RouteUtils.navigationActivity(RoutePath.Music.PAGER_MAIN);
                    break;
            }
        });
        activityBinding.circleMenuGroup.startAutoCycle(45f);//autoCycle();
    }



    private void initData(String[] mItemTexts, int[] mItemImgs) {
        if (mItemImgs==null && mItemTexts==null){
            throw new IllegalArgumentException("Text or Image not allow be null");
        }
        int count = mItemImgs==null ? mItemTexts.length: mItemImgs.length;
        if (mItemImgs!=null && mItemTexts!=null){
            count = Math.min(mItemImgs.length,mItemTexts.length);
        }

        for (int i=0;i<count;i++){
            mMenuItems.add(new CircleMenuItem(mItemImgs[i],mItemTexts[i]));
        }
    }
}
