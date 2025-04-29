package com.transcendence.freeland.ui.rv.linkpage.act;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayoutMediator;
import com.transcendence.core.base.activity.AppAc;
import com.transcendence.freeland.R;
import com.transcendence.freeland.databinding.ActivityUiRvLinkpageMainBinding;

/**
 * 双表联动
 */
public class LinkPageMainActivity extends AppAc {

    private String[] mFragmentTitles;
    private String[] mFragmentPaths;
    private Fragment[] mFragments;

    private ActivityUiRvLinkpageMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUiRvLinkpageMainBinding.inflate(LayoutInflater.from(this));
        setContentView(mBinding.getRoot());
        setTitle("双表联动");
//        mBinding.toolbar.setTitle(R.string.app_name);
//        setSupportActionBar(mBinding.toolbar);

        mFragmentTitles = getResources().getStringArray(R.array.fragments);
        mFragmentPaths = getResources().getStringArray(R.array.fragments_full_path);
        mFragments = new Fragment[mFragmentTitles.length];

        mBinding.viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return LinkPageMainActivity.this.createFragment(position);
            }

            @Override
            public int getItemCount() {
                return mFragmentTitles.length;
            }
        });

        new TabLayoutMediator(mBinding.tabs, mBinding.viewPager, (tab, position) -> {
            tab.setText(mFragmentTitles[position]
                    .replace("SampleFragment", "")
                    .replaceAll("[A-Z]", " $0"));
        }).attach();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    private Fragment createFragment(Integer index) {
        if (mFragments[index] != null) {
            return mFragments[index];
        }
        String name = mFragmentPaths[index];
        Fragment fragment = null;
        try {
            fragment = (Fragment) Class.forName(name).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mFragments[index] = fragment;
        return mFragments[index];
    }
}
