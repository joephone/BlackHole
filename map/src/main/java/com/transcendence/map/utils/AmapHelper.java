package com.transcendence.map.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.transcendence.blackhole.global.Global;
import com.transcendence.blackhole.utils.L;
import com.transcendence.blackhole.utils.SPUtils;
import com.transcendence.blackhole.utils.ScreenUtils;
import com.transcendence.blackhole.utils.StringUtils;
import com.transcendence.map.R;
import com.transcendence.map.listener.LocListener;
import com.transcendence.map.location.LocationTask;

/**
 * @author Joephone on 2019/11/8 15:09
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AmapHelper extends AppCompatActivity implements
        AMap.OnCameraChangeListener,
        AMap.OnMarkerClickListener,
        LocListener{
    /**
     *  高德地图异步定位
     */
    protected LocationTask mLocationTask;
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     *  高德地图对象
     */
    protected AMap aMap;
    /**
     *  绘制点标记 可移动、圆点、点击
     */
    protected Marker mMoveMark, mInitMark,tempMark;
    /**
     *  定位圆点、可移动、所有标识（车）
     */
    protected BitmapDescriptor initBitmap,moveBitmap,smallIdentificationBitmap,bigIdentificationBitmap;
    /**
     * 坐标动画
     */
    private ValueAnimator animator = null;
    /**
     *
     */
    private boolean mIsFirst = true;

    public AmapHelper(){
        L.d("父类构造");
    }

    protected void initHelper() {
        if (aMap != null) {
            moveToDefaultPosition();
            aMap.setOnCameraChangeListener(this);
        }

        initLocation();
        initBitmap();
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        L.d("onCameraChange");
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        L.d("onCameraChangeFinish");
        if(mIsFirst) {
            createMovingMarker();
//        startAnim(mMoveMark);
            mIsFirst = false;
        }
        animMarker();
    }


    private void initBitmap() {
        initBitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_marker_init);
        moveBitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_marker_move);
    }

    /**
     * 如果有默认坐标，则移动到默认位置，而不是北京
     */
    private void moveToDefaultPosition() {
        double defLat = StringUtils.string2Double(SPUtils.getInstance().getString(Global.MAP.DEFAULT_LAT,"0"));
        double defLon = StringUtils.string2Double(SPUtils.getInstance().getString(Global.MAP.DEFAULT_LON,"0"));
        if(defLat>0 && defLon >0){
            LatLng pos = new LatLng(defLat,defLon);
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,Global.standardZoom()));
        }
    }


    /**
     * 定位
     */
    private void initLocation() {
        mLocationTask= LocationTask.getInstance(mContext);
        mLocationTask.startLocation();
        mLocationTask.setListener(this);
    }



    @Override
    public void onLocSuc(AMapLocation target) {
        createLocMarker(target.getLatitude(),target.getLongitude());
        //存用户默认的经纬点
        SPUtils.getInstance().put(Global.MAP.DEFAULT_LAT,target.getLatitude()+"");
        SPUtils.getInstance().put(Global.MAP.DEFAULT_LON,target.getLongitude()+"");
        LatLng pos = new LatLng(target.getLatitude(),target.getLongitude());
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,Global.standardZoom()));
//        moveToPointCenter(aMap,target.getLatitude(),target.getLongitude(), Global.standardZoom());
    }

    /**
     * 创建初始位置图标
     */
    private void createLocMarker(double lat, double lng) {
        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.setFlat(true);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.position(new LatLng(lat, lng));
        markerOptions.icon(initBitmap);
        mInitMark = aMap.addMarker(markerOptions);
//        mInitialMark.setClickable(false);
    }

    /**
     * 创建移动位置图标
     */
    private void createMovingMarker() {
        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.setFlat(true);
//        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.position(new LatLng(0, 0));
        markerOptions.icon(moveBitmap);
        mMoveMark = aMap.addMarker(markerOptions);
        mMoveMark.setPositionByPixels(ScreenUtils.getScreenWidth(mContext) / 2,
                (ScreenUtils.getScreenHeight(mContext) / 2)-25);
//        mMoveMark.setClickable(false);
    }


//    private void startAnim(Marker marker1) {
//        ScaleAnimation anim = new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f);
//        anim.setDuration(300);
//        marker1.setAnimation(anim);
//        marker1.startAnimation(anim);
//    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        animMarker();
        return false;
    }



    private void animMarker() {
        if (animator != null) {
            animator.start();
            return;
        }
        animator = ValueAnimator.ofFloat(ScreenUtils.getScreenHeight(mContext)/2, ScreenUtils.getScreenHeight(mContext)/2 - 30);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(150);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                mMoveMark.setPositionByPixels(ScreenUtils.getScreenWidth(mContext) / 2, Math.round(value));
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mMoveMark.setIcon(moveBitmap);
            }
        });
        animator.start();
    }

    protected void endAnim() {
        if (animator != null && animator.isRunning()){
            animator.end();
        }
    }
}
