package com.transcendence.map.weinxinloc.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.transcendence.blackhole.utils.L;
import com.transcendence.map.R;
import com.transcendence.map.mobike.main.act.AmapFragmentActivity;
import com.transcendence.map.weinxinloc.adapter.WeixinLocAddressAdapter;
import com.transcendence.map.weinxinloc.utils.DataConversionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/10/18 15:47
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WeixinLocActivity extends AmapFragmentActivity implements View.OnClickListener {

    private ImageView back,ivSearch,ivMyLoc;
    private TextView tvSend;
    private RecyclerView mRv;
    private WeixinLocAddressAdapter mAdapter;

    /**
     * 地址列表数据源
     */
    private List<PoiItem> mList;
    private PoiItem mSelectPoiItem;
    private GeocodeSearch.OnGeocodeSearchListener mOnGeocodeSearchListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_loc_main);
        initMapViewFragment();
        initView();
    }


    private void initView() {
        back = findViewById(R.id.back);
        ivSearch = findViewById(R.id.ivSearch);
        ivMyLoc = findViewById(R.id.ivMyLoc);
        tvSend = findViewById(R.id.tvSend);
        mRv = findViewById(R.id.rv);
        back.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        ivMyLoc.setOnClickListener(this);
        tvSend.setOnClickListener(this);

        initRv();
    }

    private void initRv() {
        LinearLayoutManager manager = new LinearLayoutManager(WeixinLocActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);


        GeocodeSearch geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(mOnGeocodeSearchListener);

        mList = new ArrayList<>();
        mAdapter = new WeixinLocAddressAdapter(this, mList);
        mRv.setAdapter(mAdapter);

        //逆地址搜索监听器
        mOnGeocodeSearchListener = new GeocodeSearch.OnGeocodeSearchListener() {

            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int resultcode) {
                L.d("onRegeocodeSearched");
                if(resultcode == 1000){
                    if (regeocodeResult != null) {
                        PoiItem userSelectPoiItem = DataConversionUtils.changeToPoiItem(regeocodeResult);
                        if (null != mList) {
                            mList.clear();
                        }
                        mList.addAll(regeocodeResult.getRegeocodeAddress().getPois());
                        if (null != userSelectPoiItem) {
                            mList.add(0, userSelectPoiItem);
                        }
                        mAdapter.setList(mList);
                        mRv.smoothScrollToPosition(0);
                    }else {
                        L.d("regeocodeResult == null");
                    }
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        };



    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.back){
            finish();
        }
        if(v.getId() == R.id.ivSearch){
            Intent intent = new Intent(WeixinLocActivity.this,WeixinLocSearchActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.ivMyLoc){
            if (mapUtil != null) {
                L.d("mapUtil != null");
                mapUtil.onMyLoc();
            }
        }
        if(v.getId() == R.id.tvSend){

        }
    }




}
