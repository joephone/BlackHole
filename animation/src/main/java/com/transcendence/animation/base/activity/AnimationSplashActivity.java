package com.transcendence.animation.base.activity;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.transcendence.animation.R;
import com.transcendence.animation.index.ComplexIndexActivity;
import com.transcendence.animation.index.HighCopyIndexActivity;
import com.transcendence.animation.index.SimpleIndexActivity;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.utils.StringUtils;

import java.util.List;

/**
 * @author Joephone on 2019/5/15 18:07
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  动画起动页
 */
public class AnimationSplashActivity extends TitleBarActivity implements AdapterView.OnItemClickListener{
    private TextView tvEnter;
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;

    @Override
    public int getLayoutId() {
//        mHasTitle = false;
        return R.layout.activity_animation_splash;
    }


    public void initViews() {
        lvIndex = findViewById(R.id.lvIndex);
        tvEnter = findViewById(R.id.tvEnter);
    }

    @Override
    public void init() {
        initViews();
        initAdapter();

        // 由完全显示 --> 完全透明
        AlphaAnimation anim = new AlphaAnimation(0,1);
        anim.setDuration(1500);
        // REVERSE  反转
        anim.setRepeatMode(Animation.REVERSE);
        // INFINITE 无限的
        anim.setRepeatCount(Animation.INFINITE);
        tvEnter.startAnimation(anim);
    }

    private void initAdapter() {
        List<String> items = StringUtils.getStringList(mActivity,R.array.anim_main_index_item);
        adapter = new ArrayAdapter<>(mActivity,android.R.layout.simple_list_item_1,items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                //简单动画
                startActivity(SimpleIndexActivity.class);
                break;
            case 1:
                //复杂动画
                startActivity(ComplexIndexActivity.class);
                break;
            case 2:
                //Splash引导动画
                break;
            case 3:
                //Flip效果集合
                break;
            case 4:
                break;
            case 5:
                //Property效果
                break;
            case 6:
                //高仿动画效果
                startActivity(HighCopyIndexActivity.class);
                break;
        }
    }
}
