package com.transcendence.core.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.transcendence.core.R;

/**
 * @author joephone
 * @date 2023/5/27
 * @desc
 */
public class TitleBarAc extends AbsBaseAc {

    protected boolean mIsBackVisible = true;
    protected boolean mHasTitle = true;
    protected LinearLayout mTopBar;
    protected ImageView mIvRight;


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    public void setContentView(int layoutResID, boolean hasTitle) {
        View content = getLayoutInflater().inflate(layoutResID, null);
        prepareContentView(content, hasTitle);
    }

    private void prepareContentView(View content, boolean hasTitle) {

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTopBar = findViewById(R.id.ll_top_bar);
        if(!mHasTitle){
            mTopBar.setVisibility(View.GONE);
        }else{
            if(mTopBar!=null){
                mTopBar.setVisibility(View.VISIBLE);
                setBackVisibility();
            }
        }
    }



    private void clickBack(View view) {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in_activity, R.anim.left_out_activity);
    }

    protected void setTitle(String title) {
        ((TextView) findViewById(R.id.tv_title)).setText(title);
    }

    protected void setRightImage(int res){
        if(mIvRight==null){
            mIvRight = findViewById(R.id.iv_right);
        }
        mIvRight.setImageResource(res);
    }

    protected void setBackVisibility() {
        ImageView iv_left = findViewById(R.id.iv_left);
        mIvRight = findViewById(R.id.iv_right);
//        ivRight2 = findViewById(R.id.iv_right2);
        if (mIsBackVisible) {
            iv_left.setVisibility(View.VISIBLE);
            iv_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickBack(view);
                }
            });
        } else {
            iv_left.setVisibility(View.INVISIBLE);
        }
    }
}
