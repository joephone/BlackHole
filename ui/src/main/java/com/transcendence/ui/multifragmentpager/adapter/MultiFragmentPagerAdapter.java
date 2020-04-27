package com.transcendence.ui.multifragmentpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @Author Joephone on 2020/4/26 16:32
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MultiFragmentPagerAdapter<E,F extends Fragment> extends FragmentStatePagerAdapter {

    private final FragmentCreator<E, F> mCreator;
    private List<E> mDataList = null;

    public MultiFragmentPagerAdapter(FragmentManager fm, FragmentCreator<E, F> creator) {
        super(fm);
        mCreator = creator;
    }

    public void setDataList(List<E> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int i) {
        return mCreator.create(mDataList.get(i),i);
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public CharSequence getPageTitle(int position){
        return mCreator.getTitle(mDataList.get(position));
    }

    public interface FragmentCreator<E, F> {
        F create(E data, int pos);
        String getTitle(E data);
    }
}
