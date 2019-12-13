package com.transcendence.wan.ui.dialog;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.transcendence.wan.ui.anydialog.AnyDialog;
import com.transcendence.wan.ui.anydialog.OnDialogClickListener;

/**
 * @author CuiZhen
 * @date 2018/5/20
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class ViewHolder {

    private final AnyDialog mAnyDialog;

    private final FrameLayout mContainer;
    private final ImageView mBackground;
    private View mContent = null;

    private SparseArray<View> mViews = null;
    private SparseArray<OnDialogClickListener> mOnClickListeners = null;

    public ViewHolder(AnyDialog anyDialog) {
        this.mAnyDialog = anyDialog;
        mContainer = new FrameLayout(anyDialog.getContext());
        mContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mBackground = new ImageView(anyDialog.getContext());
        mBackground.setScaleType(ImageView.ScaleType.FIT_XY);
        mBackground.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mContainer.addView(mBackground);
    }

    public FrameLayout getContainer(){
        return mContainer;
    }

    public ImageView getBackground() {
        return mBackground;
    }

    public View getContent() {
        return mContent;
    }

    public void setContent(View content) {
        mContent = content;
    }

    public void bindListener() {
        if (mOnClickListeners == null) {
            return;
        }
        for (int i = 0; i < mOnClickListeners.size(); i++) {
            int viewId = mOnClickListeners.keyAt(i);
            final OnDialogClickListener listener = mOnClickListeners.valueAt(i);
            getView(viewId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(mAnyDialog, v);
                }
            });
        }
    }

    public <V extends View> V getView(@IdRes int viewId) {
        if (mViews == null) {
            mViews = new SparseArray<>();
        }
        if (mViews.indexOfKey(viewId) < 0) {
            V view = mContainer.findViewById(viewId);
            mViews.put(viewId, view);
            return view;
        }
        return (V) mViews.get(viewId);
    }

    public void addOnClickListener(@IdRes int viewId, OnDialogClickListener listener) {
        if (mOnClickListeners == null) {
            mOnClickListeners = new SparseArray<>();
        }
        if (mOnClickListeners.indexOfKey(viewId) < 0) {
            mOnClickListeners.put(viewId, listener);
        }
    }
}
