package com.transcendence.map.weinxinloc.act;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.PoiItem;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.L;
import com.transcendence.core.mvp.act.BaseActivity;
import com.transcendence.map.R;
import com.transcendence.map.listener.LocListener;
import com.transcendence.map.location.LocationUtils;
import com.transcendence.map.weinxinloc.adapter.WeixinLocAddressAdapter;
import com.transcendence.map.weinxinloc.presenter.WeixinLocPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2020/5/27 00:02
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WeixinLocOriAct extends BaseActivity<WeixinLocPresenter> implements View.OnClickListener, LocListener {

    private ImageView back,ivSearch,ivMyLoc;
    private TextView tvSend;

    private RecyclerView mRv;
    private WeixinLocAddressAdapter mAdapter;
    private List<PoiItem> mList;

    private LocationUtils mLocationTask;


    private MapView mMapView;
    private AMap    mAMap;
    private UiSettings mUiSettings;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weixin_loc_main_ori;
    }

    @Nullable
    @Override
    protected WeixinLocPresenter initPresenter() {
        return new WeixinLocPresenter();
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back);
        ivSearch = findViewById(R.id.ivSearch);
        ivMyLoc = findViewById(R.id.ivMyLoc);
        tvSend = findViewById(R.id.tvSend);
        mRv = findViewById(R.id.rv);
        back.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        ivMyLoc.setOnClickListener(this);
        tvSend.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMap(savedInstanceState);
    }

    private void initMap(Bundle savedInstanceState) {
        mMapView = (MapView) findViewById(R.id.map);

        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        mAMap = mMapView.getMap();

        mUiSettings = mAMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(false);//是否显示地图中放大缩小按钮
        mUiSettings.setMyLocationButtonEnabled(false); // 是否显示默认的定位按钮
        mUiSettings.setScaleControlsEnabled(true);//是否显示缩放级别
        mAMap.setMyLocationEnabled(false);// 是否可触发定位并显示定位层

        mList = new ArrayList<>();
        mAdapter = new WeixinLocAddressAdapter(this, mList);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);

        initLoc();
    }

    private void initLoc() {
        mLocationTask = LocationUtils.getInstance();
        mLocationTask.startLocation();
        mLocationTask.setListener(this);
    }

    @Override
    protected void loadData() {

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, WeixinLocOriAct.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLocSuc(AMapLocation aMapLocation) {
        L.d("onLocSuc");
        mAMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()), Global.standardZoom()));
    }
}
