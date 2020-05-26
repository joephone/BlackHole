package com.transcendence.blackhole.widget.rv.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @Author Joephone on 2020/4/18 0:15
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class OnMyScrollListener extends RecyclerView.OnScrollListener {

    // Used to mark whether scroll up.
    private boolean mIsScrollUp = false;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        // When not scroll
        if (newState == RecyclerView.SCROLL_STATE_IDLE && manager != null) {
            // Get the last fully displayed item position.
            int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
            int itemCount = manager.getItemCount();

            // Scroll to the last item, and it's scroll up.
            if (lastItemPosition == (itemCount - 1) && mIsScrollUp) {
                // Load More
                onLoadMore();
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        // > 0 indicates that it is scroll up, <= 0 means stop or scroll down.
        mIsScrollUp = dy > 0;
    }

    /**
     * Callback method for scroll to end.
     */
    public abstract void onLoadMore();
}
