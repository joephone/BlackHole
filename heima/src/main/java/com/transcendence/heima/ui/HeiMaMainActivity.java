package com.transcendence.heima.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.transcendence.heima.R;

public class HeiMaMainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_heima);
        initView();
    }
    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("首页");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.
                rdTextColorPress));
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //RadioGroup选中状态改变监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        //setCurrentItem()方法中第二个参数控制页面切换动画，true:打开，false:关闭
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_count:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_video:
                        viewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_me:
                        viewPager.setCurrentItem(3, false);
                        break;
                }
            }
        });
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        /**此处后续添加**/
//        HomeFragment homeFragment = new HomeFragment();
//        CountFragment countFragment = new CountFragment();
//        VideoFragment videoFragment = new VideoFragment();
//        MeFragment meFragment = new MeFragment();
//        List<Fragment> alFragment = new ArrayList<Fragment>();
//        alFragment.add(homeFragment);
//        alFragment.add(countFragment);
//        alFragment.add(videoFragment);
//        alFragment.add(meFragment);
//        viewPager.setOffscreenPageLimit(3); //三个界面之间来回切换都不会重新加载数据。
//        //ViewPager设置适配器
//        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),
//                alFragment));
//        viewPager.setCurrentItem(0); //ViewPager显示第一个Fragment

        //ViewPager页面切换监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.rb_home);
                        tv_main_title.setText("首页");
                        rl_title_bar.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_count);
                        tv_main_title.setText("统计");
                        rl_title_bar.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_video);
                        tv_main_title.setText("视频");
                        rl_title_bar.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        radioGroup.check(R.id.rb_me);
                        rl_title_bar.setVisibility(View.GONE);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    protected long exitTime; //记录第一次点击时的时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(HeiMaMainActivity.this, "再按一次退出黑马头条",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                HeiMaMainActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
