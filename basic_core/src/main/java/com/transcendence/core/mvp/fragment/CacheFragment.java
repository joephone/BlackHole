package com.transcendence.core.mvp.fragment;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author Cuizhen
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

abstract class CacheFragment extends Fragment {

    protected View mRootView = null;
    protected boolean mViewCreated = false;

    private SparseArray<View> mCacheViews = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            final int layoutId = getLayoutRes();
            if (layoutId > 0) {
                mRootView = inflater.inflate(getLayoutRes(), container, false);
            }
        }
        mViewCreated = true;
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRootView = null;
        if (mCacheViews != null) {
            mCacheViews.clear();
            mCacheViews = null;
        }
        mViewCreated = false;
    }

    @Nullable
    public View getRootView() {
        return mRootView;
    }

    public final <V extends View> V getView(@IdRes int id) {
        if (mCacheViews == null) {
            mCacheViews = new SparseArray<>();
        }
        View view = mCacheViews.get(id);
        if (view == null) {
            view = findViewById(id);
            if (view != null) {
                mCacheViews.put(id, view);
            }
        }
        return (V) view;
    }

    public final <V extends View> V findViewById(@IdRes int id) {
        if (mRootView == null) {
            return null;
        }
        return mRootView.findViewById(id);
    }

    protected abstract int getLayoutRes();


}
