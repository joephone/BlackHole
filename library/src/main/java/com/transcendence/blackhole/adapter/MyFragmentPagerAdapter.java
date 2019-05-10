package com.transcendence.blackhole.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Joephone on 2019/2/27 16:29
 * E-Mail Address：joephonechen@gmail.com
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
//            switch (index){
//                case 0:
//                    return this.mList == null ? null : this.mList.get(position);
//                case 1:
//                    return this.mList == null ? null : this.mList.get(position);
////                case 2:
////                    break;
//                case 3:
//                    return this.mList == null ? null : this.mList.get(2);
//                case 4:
//                    return this.mList == null ? null : this.mList.get(3);
//            }
        return this.mList == null ? null : this.mList.get(position);
    }

    @Override
    public int getCount() {
        return this.mList == null ? 0 : this.mList.size();
    }
}
