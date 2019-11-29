package com.transcendence.map.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.transcendence.blackhole.global.Global;
import com.transcendence.blackhole.utils.L;
import com.transcendence.blackhole.utils.SPUtils;
import com.transcendence.blackhole.utils.StringUtils;
import com.transcendence.map.R;
import com.transcendence.map.listener.LocListener;
import com.transcendence.map.listener.PoiSearchListener;
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
        PoiSearch.OnPoiSearchListener,
        LocListener{

    private PoiSearch           mPoiSearch;
    private PoiSearch.Query     mQuery;
    private PoiSearchListener   mOnPoiSearchListener;
    /**
     *  高德地图异步定位
     */
    protected LocationTask mLocationTask;
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     *  高德地图View对象
     */
    protected MapView aMapView;

    /**
     *  高德地图对象
     */
    protected AMap aMap;
    /**
     * 定义一个UiSettings对象
     */
    private UiSettings mUiSettings;
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
    private LatLng mLatLng;
    /**
     * 是否第一次加载
     */
    private boolean mIsFirst = true;

    public AmapHelper(){
//        L.d("父类构造");
    }

    protected void initHelper() {
        if (aMap != null) {
            setAmap();
            moveToDefaultPosition();
            aMap.setOnCameraChangeListener(this);
        }

        initLocation();
        initBitmap();
    }

    /**
     * 设置高德地图
     */
    private void setAmap() {
        //实例化UiSettings类对象
        mUiSettings = aMap.getUiSettings();
        //是否允许显示缩放按钮
        mUiSettings.setZoomControlsEnabled(false);
        //控制比例尺控件是否显示
        mUiSettings.setScaleControlsEnabled(true);
        //高德地图的 logo 默认在左下角显示，不可以移除，但支持调整到固定位置。
        //mUiSettings.setLogoPosition(int position);//设置logo位置
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
//        L.d("onCameraChange");
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
//        L.d("onCameraChangeFinish");
        if(mIsFirst) {
            createLocMarker(cameraPosition.target.latitude,cameraPosition.target.longitude);
            createMovingMarker();
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
        //存用户默认的经纬点
        SPUtils.getInstance().put(Global.MAP.DEFAULT_LAT,target.getLatitude()+"");
        SPUtils.getInstance().put(Global.MAP.DEFAULT_LON,target.getLongitude()+"");
        mLatLng = new LatLng(target.getLatitude(),target.getLongitude());
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng,Global.standardZoom()));
        onStartPoiSearch(true, "", target.getCity(), new LatLonPoint(target.getLatitude(), target.getLongitude()));
    }


    /**
     * 定位成功后查询周围地位列表
     * @param isRefresh
     * @param keyWord
     * @param city
     * @param latLonPoint
     */
    private void onStartPoiSearch(boolean isRefresh, String keyWord, String city, LatLonPoint latLonPoint) {
        //第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        mQuery = new PoiSearch.Query(keyWord, "", city);
        // 设置每页最多返回多少条poiitem
        mQuery.setPageSize(30);
        // 设置查第一页
        mQuery.setPageNum(0);


        mPoiSearch = new PoiSearch(this, mQuery);

        mPoiSearch.setOnPoiSearchListener(this);
        if (latLonPoint != null) {
            //该范围的中心点-----半径，单位：米-----是否按照距离排序
            mPoiSearch.setBound(new PoiSearch.SearchBound(latLonPoint, 10000, true));
        }
        // 异步搜索
        mPoiSearch.searchPOIAsyn();
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
        startAnim(mInitMark);
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
        mMoveMark.setPositionByPixels(aMapView.getWidth() / 2,
                (aMapView.getHeight() / 2)-75);
//        mMoveMark.setClickable(false);
    }


    private void startAnim(Marker marker) {
        ScaleAnimation anim = new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f);
        anim.setDuration(1000);
        anim.setRepeatCount(5);
        marker.setAnimation(anim);
        marker.startAnimation();
    }



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
        animator = ValueAnimator.ofFloat(aMapView.getHeight()/2, aMapView.getHeight()/2 - 30);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(150);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                mMoveMark.setPositionByPixels(aMapView.getWidth() / 2, Math.round(value));
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

    @Override
    public void onPoiSearched(PoiResult result, int i) {
        L.d("onPoiSearched");
        if (i == 1000) {
            // 搜索poi的结果
            if (result != null && result.getQuery() != null) {
                if (mOnPoiSearchListener != null) {
                    L.d("mOnPoiSearchListener != null");
                    mOnPoiSearchListener.onPoiSearchList(result);
                }else {
                    L.d("mOnPoiSearchListener == null");
                }
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    public void setOnPoiSearchListener(PoiSearchListener onPoiSearchListener) {
        this.mOnPoiSearchListener = onPoiSearchListener;
    }


    /**
     *  回定位
     */
    protected void onMyLoc(){
        if (aMap != null && mLatLng != null) {
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng,Global.standardZoom()));
        }
    }
}
