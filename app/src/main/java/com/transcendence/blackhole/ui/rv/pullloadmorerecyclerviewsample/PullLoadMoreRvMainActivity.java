package com.transcendence.blackhole.ui.rv.pullloadmorerecyclerviewsample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.transcendence.blackhole.R;

import java.util.ArrayList;
import java.util.List;


public class PullLoadMoreRvMainActivity extends AppCompatActivity {

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pullloadmorerecyclerviewsample_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(true);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        // 设置ViewPager的数据等
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适合很多tab
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//tab均分,适合少的tab
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        firstFragment = new FirstFragment();
        Bundle data = new Bundle();
        data.putInt("id", 0);
        firstFragment.setArguments(data);
        adapter.addFrag(firstFragment, "Frist");

        secondFragment = new SecondFragment();
        data = new Bundle();
        data.putInt("id", 1);
        secondFragment.setArguments(data);
        adapter.addFrag(secondFragment, "Second");


        thirdFragment = new ThirdFragment();
        data = new Bundle();
        data.putInt("id", 2);
        thirdFragment.setArguments(data);
        adapter.addFrag(thirdFragment, "Third");

        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_clear:
                Toast.makeText(PullLoadMoreRvMainActivity.this, "First清除成功", Toast.LENGTH_LONG).show();
                firstFragment.clearData();
                break;
            case R.id.menu_about:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/WuXiaolong/PullLoadMoreRecyclerView"));
                startActivity(intent);
                break;
        }

        return true;
    }
}
