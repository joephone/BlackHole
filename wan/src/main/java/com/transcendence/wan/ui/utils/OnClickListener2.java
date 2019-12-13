package com.transcendence.wan.ui.utils;

import android.view.View;

import com.transcendence.wan.utils.ClickHelper;

/**
 * @author Cuizhen
 * @date 2018/5/7-下午4:40
 */
public abstract class OnClickListener2 implements View.OnClickListener {

    @Override
    public final void onClick(final View v) {
        ClickHelper.onlyFirstSameView(v, new ClickHelper.Callback() {
            @Override
            public void onClick(View view) {
                onClick2(view);
            }
        });
    }

    public abstract void onClick2(View v);
}