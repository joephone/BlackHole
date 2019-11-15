/**
 * Copyright 2014-present Amberfog
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.transcendence.map.mobike.main.act;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.amap.api.maps.CameraUpdateFactory;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.transcendence.blackhole.utils.L;
import com.transcendence.map.R;


public class SlidingUpPanelActivity extends AmapFragmentActivity implements SlidingUpPanelLayout.PanelSlideListener {

    RelativeLayout rlOrnek;

    //private ListView mListView;
    private SlidingUpPanelLayout mSlidingUpPanelLayout;

    private View mTransparentHeaderView;
    private View mTransparentView;
    private View mSpaceView;
    private RelativeLayout mSlidingContainer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidinguppanel_main);

        //mListView = (ListView) findViewById(R.id.list);
        //mListView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

        mSlidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.slidingLayout);
        mSlidingUpPanelLayout.setEnableDragViewTouchEvents(true);

//        rlOrnek = (RelativeLayout)findViewById(R.id.rlOrnek);
        mSlidingContainer= findViewById(R.id.slidingContainer);

        int mapHeight = getResources().getDimensionPixelSize(R.dimen.map_height);
        mSlidingUpPanelLayout.setPanelHeight(mapHeight); // you can use different height here
        mSlidingUpPanelLayout.setScrollableView(mSlidingContainer, mapHeight);

        mSlidingUpPanelLayout.setPanelSlideListener(this);

        // transparent view at the top of ListView
//        mTransparentView = findViewById(R.id.transparentView);


        // init header view for ListView
        mTransparentHeaderView = LayoutInflater.from(this).inflate(R.layout.activity_slidinguppanel_transparent_header_view, null, false);
        mSpaceView = mTransparentHeaderView.findViewById(R.id.space);

  /*      ArrayList<String> testData = new ArrayList<String>(100);
        for (int i = 0; i < 100; i++) {
            testData.add("Item " + i);
        }*/
        //mListView.addHeaderView(mTransparentHeaderView);
        /*mListView.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_slidinguppanel_simple_list_item, testData));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSlidingUpPanelLayout.collapsePane();
            }
        });
        collapseMap();*/

//        mMapFragment = MapFragment.newInstance();
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.mapContainer, mMapFragment, "map");
//        fragmentTransaction.commit();

//        setUpMapIfNeeded();
    }







    private void collapseMap() {
        Log.e("EVENT", "collapseMap");
        mSpaceView.setVisibility(View.VISIBLE);
        //mTransparentView.setVisibility(View.GONE);
    }

    private void expandMap() {
        Log.e("EVENT", "expandMap");
        mSpaceView.setVisibility(View.GONE);
        //mTransparentView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPanelSlide(View view, float v) {
    }

    @Override
    public void onPanelCollapsed(View view) {
        expandMap();
        if (mMap != null) {
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14f), 1000, null);
        }else {
            L.d("mMap == null");
        }

    }

    @Override
    public void onPanelExpanded(View view) {
        collapseMap();
        if (mMap != null) {
            mMap.animateCamera(CameraUpdateFactory.zoomTo(11f), 1000, null);
        }else {
            L.d("mMap == null");
        }
    }

    @Override
    public void onPanelAnchored(View view) {

    }
}
