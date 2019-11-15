package com.transcendence.map.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.SupportMapFragment;
import com.transcendence.map.R;

/**
 * @author Joephone on 2019/10/15 13:57
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class BasicAmapFragmentActivity extends FragmentActivity {

    private AMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map_basic_amap);
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
