package com.transcendence.blackhole.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.core.R;

/**
 * @author Joephone on 2019/5/15 14:02
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class TabView extends FrameLayout {

    private ImageView mIv;
    private ImageView mIvSelect;
    private TextView mTv;
    private TextView mTvSelect;

    public TabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.tab_view,this);
        mIv = findViewById(R.id.ivTab);
        mIvSelect = findViewById(R.id.ivTabSelect);
        mTv = findViewById(R.id.tvTab);
        mTvSelect = findViewById(R.id.tvTabSelect);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.TabView);
        if(typedArray.hasValue(R.styleable.TabView_tabTextSize)){
            setTabTextSize(typedArray.getInt(R.styleable.TabView_tabTextSize,0));
        }


        typedArray.recycle();

        setProgress(0);
    }

    private void setTabTextSize(int size) {
        mTv.setTextSize(size);
    }


//    public TabView(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }


    public void setProgress(float progress) {
        mIv.setAlpha(1-progress);
        mIvSelect.setAlpha(progress);
        mTv.setAlpha(1-progress);
        mTvSelect.setAlpha(progress);
    }


    public void setIconAndText(int icon,int iconSelect,String text){
        mIv.setImageResource(icon);
        mIvSelect.setImageResource(iconSelect);
        mTv.setText(text);
        mTvSelect.setText(text);
    }
}
