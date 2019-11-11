package com.transcendence.blackhole.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.blackhole.library.R;

/**
 * @author Joephone on 2019/5/9 17:30
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class StandardLayout extends FrameLayout {

    private TextView tvLeft;
    private TextView tvRight;
    private ImageView ivLeft;
    private ImageView ivRight;
    private View mLineView;

    public StandardLayout(Context context) {
        this(context,null);
    }

    public StandardLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StandardLayout(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.standard_layout,this);
        tvLeft = findViewById(R.id.tvLeft);
        tvRight = findViewById(R.id.tvRight);
        ivLeft = findViewById(R.id.ivLeft);
        ivRight = findViewById(R.id.ivRight);
        mLineView= findViewById(R.id.line);

        final TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.StandardLayout);

        // 左文本设置
        if (array.hasValue(R.styleable.StandardLayout_leftText)) {
            setLeftText(array.getString(R.styleable.StandardLayout_leftText));
        }

        // 左图标设置
        if (array.hasValue(R.styleable.StandardLayout_leftIcon)) {
            setLeftIcon(array.getResourceId(R.styleable.StandardLayout_leftIcon,0));
        }

        // 左提示设置
        if (array.hasValue(R.styleable.StandardLayout_leftHint)) {
            setLeftHint(array.getString(R.styleable.StandardLayout_leftHint));
        }



        // 右文本设置
        if (array.hasValue(R.styleable.StandardLayout_rightText)) {
            setRightText(array.getString(R.styleable.StandardLayout_rightText));
        }

        // 右图标设置
        if (array.hasValue(R.styleable.StandardLayout_rightIcon)) {
            setRightIcon(array.getResourceId(R.styleable.StandardLayout_rightIcon,0));
        }

        // 右提示设置
        if (array.hasValue(R.styleable.StandardLayout_leftHint)) {
            setLeftHint(array.getString(R.styleable.StandardLayout_leftHint));
        }

        if (array.hasValue(R.styleable.StandardLayout_rightHint)) {
            setRightHint(array.getString(R.styleable.StandardLayout_rightHint));
        }

        // 回收TypedArray
        array.recycle();

    }


    /**
     * 设置左边的标题
     */
    public void setLeftText(int stringId) {
        setLeftText(getResources().getString(stringId));
    }

    public void setLeftText(CharSequence text) {
        tvLeft.setText(text);
    }

    /**
     * 设置左边的提示
     */
    public void setLeftHint(int stringId) {
        setLeftHint(getResources().getString(stringId));
    }

    public void setLeftHint(CharSequence text) {
        tvLeft.setHint(text);
    }


    /**
     * 设置左边的图标
     */
    public void setLeftIcon(int iconId) {
//        L.logI("setLeftIcon---"+iconId);
        if (iconId > 0) {
            ivLeft.setVisibility(VISIBLE);
            ivLeft.setImageResource(iconId);
        }else if (iconId == 0){
            ivLeft.setVisibility(GONE);
        }
    }



    /**
     * 设置右边的标题
     */
    public void setRightText(int stringId) {
        setRightText(getResources().getString(stringId));
    }

    public void setRightText(CharSequence text) {
        tvRight.setText(text);
    }

    /**
     * 设置右边的提示
     */
    public void setRightHint(int stringId) {
        setLeftHint(getResources().getString(stringId));
    }

    public void setRightHint(CharSequence text) {
        tvRight.setHint(text);
    }


    /**
     * 设置右边的图标
     */
    public void setRightIcon(int iconId) {
        if (iconId > 0) {
            ivRight.setImageResource(iconId);
        }else {
            ivRight.setVisibility(INVISIBLE);
        }
    }



    /**
     * 设置左标题颜色
     */
    public void setLeftColor(int color) {
        tvLeft.setTextColor(color);
    }

    /**
     * 设置右标题颜色
     */
    public void setRightColor(int color) {
        tvRight.setTextColor(color);
    }

    /**
     * 设置左标题的文本大小
     */
    public void setLeftSize(int unit, float size) {
        tvLeft.setTextSize(unit, size);
    }

    /**
     * 设置右标题的文本大小
     */
    public void setRightSize(int unit, float size) {
        tvRight.setTextSize(unit, size);
    }

    /**
     * 设置分割线是否显示
     */
    public void setLineVisible(boolean visible) {
        mLineView.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置分割线的颜色
     */
    public void setLineColor(int color) {
        setLineDrawable(new ColorDrawable(color));
    }
    public void setLineDrawable(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mLineView.setBackground(drawable);
        }else {
            mLineView.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 设置分割线的大小
     */
    public void setLineSize(int size) {
        ViewGroup.LayoutParams layoutParams = mLineView.getLayoutParams();
        layoutParams.height = size;
        mLineView.setLayoutParams(layoutParams);
    }

    /**
     * 设置分割线边界
     */
    public void setLineMargin(int margin) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mLineView.getLayoutParams();
        params.leftMargin = margin;
        params.rightMargin = margin;
        mLineView.setLayoutParams(params);
    }

    /**
     * 获取左标题View对象
     */
    public TextView getLeftView() {
        return tvLeft;
    }

    /**
     * 获取右标题View对象
     */
    public TextView getRightView() {
        return tvRight;
    }

    /**
     * 获取分割线View对象
     */
    public View getLineView() {
        return mLineView;
    }
}
