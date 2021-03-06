package com.transcendence.core.base.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transcendence.core.R;
import com.transcendence.core.utils.AppUtils;
import com.transcendence.core.utils.L;


/**
 * @author Joephone on 2019/5/16 18:12
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public abstract class TitleBarActivity extends BaseActivity {
    private View titleBar;
    private View statusView;
    private TextView tvTitle;
    private TextView tvRight;
    private ImageView ivBack;
    private ImageView ivRight;
    private FrameLayout flBack;

    protected boolean mHasBack = true;
    protected boolean mHasTitle = true;

    @Override
    public void setContentView(int layoutResID) {
//        L.d("title bar setContentView");
        prepareContentView(layoutResID, mHasBack);
    }

    public void setContentView(int layoutResID, boolean hasBack) {
        prepareContentView(layoutResID, hasBack);
    }

    public void setContentView(boolean hasBack) {
        prepareContentView(0, hasBack);
    }

    public void setContentView(boolean hasBack,boolean hasTitle) {
//        L.d("setContentView");
        mHasTitle = hasTitle;
        prepareContentView(0, hasBack);
    }

    private void prepareContentView(int layoutResID, boolean hasBack) {
//        L.d("title bar prepareContentView");
        LinearLayout parent = new LinearLayout(this);
        parent.setOrientation(LinearLayout.VERTICAL);
        titleBar = getLayoutInflater().inflate(R.layout.title, parent, false);
        if (mHasTitle) {
            parent.addView(titleBar);
            initTitle();
            // 添加原内容
            LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1);
            if (layoutResID > 0) {
                View mainView = getLayoutInflater().inflate(layoutResID, null);
                mainView.setLayoutParams(mainParams);
                parent.addView(mainView);
                mainView = parent;
                super.setContentView(mainView);
            } else {
                super.setContentView(parent);
            }
        }else {
            L.d("titleBar.setVisibility(View.GONE)");
            titleBar.setVisibility(View.GONE);
            super.setContentView(parent);
        }

    }

    private void initTitle() {
        if (titleBar != null) {
            flBack = (FrameLayout) titleBar.findViewById(R.id.fl_back);
            statusView = (View) titleBar.findViewById(R.id.title_status);
            tvRight = (TextView) titleBar.findViewById(R.id.tv_right);
            tvTitle = (TextView) titleBar.findViewById(R.id.tv_title);
            ivBack = (ImageView) titleBar.findViewById(R.id.iv_back);
            ivRight = (ImageView) titleBar.findViewById(R.id.iv_right);
            initStateHeigt();
//            ivBack.setVisibility(View.VISIBLE);
            flBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    protected void setTitle(String title) {
        setTitle(title, null);
    }

    protected void setTitle(String title, String rightText) {
        setTitle(true, title, rightText);
    }

    protected void setTitle(boolean showBackButton,String title) {
        setTitle(showBackButton, title, null);
    }


    protected void setTitle(boolean hasBack,boolean hasTitle) {
        mHasTitle = hasTitle;
        if(!mHasTitle && titleBar!=null){
            titleBar.setVisibility(View.GONE);
        }
        setTitle(hasBack, "", null);
    }

    protected void setTitle(boolean hasTitle) {
        mHasTitle = hasTitle;
        if(titleBar!=null){
            titleBar.setVisibility(View.GONE);
        }
    }

    protected void setTitle(boolean hasBack, String title, String rightText) {
        if (flBack != null) {
            flBack.setVisibility(hasBack ? View.VISIBLE : View.INVISIBLE);
        }

        if (tvTitle != null) {
            if(!TextUtils.isEmpty(title)){
                tvTitle.setText(title);
            }
        }

        if (tvRight != null) {
            tvRight.setVisibility(rightText == null ? View.INVISIBLE : View.VISIBLE);
            if(!TextUtils.isEmpty(rightText)){
                tvRight.setText(rightText);
            }
        }
    }


    private void initStateHeigt() {
        int statusHeight = AppUtils.getStatusHeight(this);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusHeight);
        statusView.setLayoutParams(l);
    }

}
