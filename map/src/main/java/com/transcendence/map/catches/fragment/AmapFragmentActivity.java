package com.transcendence.map.catches.fragment;

import android.os.Bundle;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.SupportMapFragment;
import com.transcendence.map.R;
import com.transcendence.map.catches.act.FatherActivity;

/**
 * @author Joephone on 2019/10/15 16:38
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class AmapFragmentActivity extends FatherActivity {

    private AMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpMapIfNeeded();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
        }
    }
}
