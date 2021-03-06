package com.transcendence.structure.activity.setting;

import android.view.View;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.utils.CacheDataManager;
import com.transcendence.core.widget.custom.StandardLayout;
import com.transcendence.structure.R;

/**
 * @author Joephone on 2019/5/10 15:13
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class SettingActivity extends TitleBarActivity implements View.OnClickListener {

    private StandardLayout slClearCache;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }


    @Override
    public void init() {
        setTitle("设置");
        slClearCache = findViewById(R.id.slClearCache);
        slClearCache.setOnClickListener(this);
        // 获取应用缓存大小
        slClearCache.setRightText(CacheDataManager.getTotalCacheSize(mActivity));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.slClearCache:
////                ImageLoader.clear(this);
//                CacheDataManager.clearAllCache(this);
//                // 重新获取应用缓存大小
//                slClearCache.setRightText(CacheDataManager.getTotalCacheSize(this));
//                break;
        }
    }
}
