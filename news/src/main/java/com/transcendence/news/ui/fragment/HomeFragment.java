package com.transcendence.news.ui.fragment;

import com.transcendence.news.base.act.NewsBaseFragment;
import com.transcendence.news.base.act.NewsBasePresenter;
import com.transcendence.news.listener.OnChannelListener;

/**
 * @Author Joephone on 2020/3/24 16:02
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class HomeFragment extends NewsBaseFragment implements OnChannelListener {
    @Override
    protected NewsBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return 0;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onItemMove(int starPos, int endPos) {

    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {

    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {

    }
}
