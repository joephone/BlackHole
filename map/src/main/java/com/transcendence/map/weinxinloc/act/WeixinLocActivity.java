package com.transcendence.map.weinxinloc.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.transcendence.blackhole.utils.L;
import com.transcendence.map.R;
import com.transcendence.map.listener.PoiSearchListener;
import com.transcendence.map.mobike.main.act.AmapFragmentActivity;

import java.util.List;

/**
 * @author Joephone on 2019/10/18 15:47
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WeixinLocActivity extends AmapFragmentActivity implements View.OnClickListener,PoiSearchListener {

    private ImageView back,ivSearch,ivMyLoc;
    private TextView tvSend;
    private RecyclerView mRv;

    /**
     * 地址列表数据源
     */
    private List<PoiItem> mList;



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
            }else {
                L.d("mapUtil == null");
            }
        }
        if(v.getId() == R.id.tvSend){

        }
    }

    @Override
    public void onPoiSearchList(PoiResult result) {

    }
}
