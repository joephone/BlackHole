package com.transcendence.ui.recyclerview.wrapper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.transcendence.ui.R;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.ui.recyclerview.utils.LoadingState;

import java.util.List;

/**
 * @Author Joephone on 2020/4/25 0:08
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class LoadMoreWrapper<T> extends BaseAbsAdapter<T>{

    // Origin adapter
    private BaseAbsAdapter<T> mAdapter;
    // General view
    private final int TYPE_ITEM = 1;
    // Footer view
    private final int TYPE_FOOTER = 2;

    // The current loading state, the default is loading complete.
    private LoadingState mLoadingState = LoadingState.INIT;

    public LoadMoreWrapper(Context context, BaseAbsAdapter<T> adapter){
        super(context);
        this.mAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        // The last item is set to footer view.
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create view by the view type.
        if (viewType == TYPE_FOOTER) {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_rv_loading_footer_new, parent, false);
            return new FooterHolder(create(R.layout.layout_rv_loading_footer_new,parent));
        } else {
            return mAdapter.onCreateViewHolder(parent, viewType);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadMoreWrapper.FooterHolder) {
            FooterHolder footerHolder = (FooterHolder) holder;

            // Set loading view height.
//            loadMoreViewHolder.rlLoadingFooter.removeAllViews();
//            if (mLoadingViewHeight > 0) {
//                RelativeLayout.LayoutParams params =
//                        (RelativeLayout.LayoutParams) loadMoreViewHolder.rlLoadingFooter.getLayoutParams();
//                params.height = mLoadingViewHeight;
//                loadMoreViewHolder.rlLoadingFooter.setLayoutParams(params);
//            }

            // Display loading view.
            switch (mLoadingState) {
                case INIT:
                    footerHolder.mLayouts[0].setVisibility(View.GONE);
                    footerHolder.mLayouts[1].setVisibility(View.GONE);
                    break;
                case LOADING: // Loading
                    footerHolder.mLayouts[0].setVisibility(View.VISIBLE);
                    footerHolder.mLayouts[1].setVisibility(View.GONE);
                    break;
                case LOADING_COMPLETE: // Loading done
                    footerHolder.mLayouts[0].setVisibility(View.VISIBLE);
                    footerHolder.mLayouts[1].setVisibility(View.GONE);
                    break;
                case LOADING_END: // Loading end
                    footerHolder.mLayouts[0].setVisibility(View.GONE);
                    footerHolder.mLayouts[1].setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        } else {
            mAdapter.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount()+1;
    }


    @Override
    public void onRefresh(List<T> list) {
        mAdapter.onRefresh(list);
        notifyDataSetChanged();
    }

    @Override
    public void onLoadMore(List<T> list) {
        mAdapter.onLoadMore(list);
        notifyDataSetChanged();
    }


    /**
     * Footer的Holder
     */
    private class FooterHolder extends RecyclerView.ViewHolder {
        private LinearLayout[] mLayouts;

        private FooterHolder(View view) {
            super(view);
            mLayouts = new LinearLayout[]{
                    view.findViewById(R.id.ll_loading),
                    view.findViewById(R.id.ll_end)
            };
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        // GridLayoutManager
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // If the current position is footer view, then the item occupy two cells,
                    // Normal item occupy a cell.
                    return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * Set the pull-up state.
     *
     * @param loadingState 0.Loading 1.Loading done 2.Loading end
     */
    public void setLoadState(LoadingState loadingState) {
        this.mLoadingState = loadingState;
    }

    public LoadingState getLoadingState() {
        return mLoadingState;
    }
}
