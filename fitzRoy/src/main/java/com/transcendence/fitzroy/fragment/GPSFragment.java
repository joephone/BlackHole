package com.transcendence.fitzroy.fragment;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.transcendence.core.base.app.LibApplication;
import com.transcendence.core.utils.L;
import com.transcendence.core.utils.gps.GPSUtils;
import com.transcendence.fitzroy.R;
import com.transcendence.fitzroy.app.BaseFragment;
import com.transcendence.network.cangahi.BaseObserver;
import com.transcendence.network.cangahi.BaseRequest;
import com.transcendence.network.cangahi.BaseResponse;
import com.transcendence.network.service.ParamAis;
import com.transcendence.permissions.OnPermissionCallback;
import com.transcendence.permissions.PermissionPool;
import com.transcendence.permissions.Permissions;
import com.transcendence.ui.scroll.HeaderZoomLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author Joephone on 2021/10/25 0014 下午 5:54
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class GPSFragment extends BaseFragment {

    private Handler mHandler = new Handler();
    private GPSUtils gpsUtils;
    private Location location;
    private TextView mTvContent;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static GPSFragment newInstance(String title) {
        GPSFragment fragment = new GPSFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_gps;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTvContent = findViewById(R.id.tv_content);
        gpsUtils = new GPSUtils(getActivity());
        requestLocPermission();
    }


    public void requestLocPermission(){
        PackageManager pm = getActivity().getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(PermissionPool.ACCESS_FINE_LOCATION, getActivity().getPackageName()));
        if (permission) {
            //("有这个权限");
            mHandler.postDelayed(runnable,0);
        }else {
            Permissions.with(getActivity())
                    // 申请多个权限
                    .permission(PermissionPool.GROUP.LOCATION)
                    .request(new OnPermissionCallback() {

                        @Override
                        public void onGranted(List<String> permissions, boolean all) {
                            if (all) {
//                            toast("获取录音和日历权限成功");
                                L.d("授权成功");
                                mHandler.postDelayed(runnable,0);
                            } else {
                                Toast.makeText(LibApplication.getAppContext(),"获取部分权限成功，但部分权限未正常授予",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onDenied(List<String> permissions, boolean never) {
                            if (never) {
                                Toast.makeText(getActivity(),"被永久拒绝授权，请手动授予权限",Toast.LENGTH_SHORT).show();
                                // 如果是被永久拒绝就跳转到应用权限系统设置页面
                                Permissions.startPermissionActivity(getActivity(), permissions);
                            } else {
//                            toast("获取录音和日历权限失败");
                                Toast.makeText(getActivity(),"获取权限失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            location = gpsUtils.getLocation();//获取位置信息

            if (location != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                            SPUtils.getInstance().save("lon",location.getLongitude());
//                            SPUtils.getInstance().save("lat",location.getLatitude());
//                            SPUtils.getInstance().save("addressStr",gpsUtils.getAddressStr());
                        L.d(""+location.getLongitude()+","+location.getLatitude()+","+gpsUtils.getAddressStr());
                        updateView(location);

                    }
                });
                mHandler.removeCallbacks(runnable);
            } else {
                mHandler.postDelayed(this, 1000);
            }
        }
    };


    /**
     * 实时更新文本内容
     *
     * @param location
     */
    private void updateView(Location location) {
        if (location != null) {
            mTvContent.setText("定位成功\n\n设备位置信息\n\n经度：");
            mTvContent.append(String.valueOf(location.getLongitude()));
            mTvContent.append("\n纬度：");
            mTvContent.append(String.valueOf(location.getLatitude()));
            mTvContent.append("\n所在地址：");
            mTvContent.append(String.valueOf(gpsUtils.getAddressStr()));
        } else {
            // 清空EditText对象
            mTvContent.getEditableText().clear();
        }
    }

}
