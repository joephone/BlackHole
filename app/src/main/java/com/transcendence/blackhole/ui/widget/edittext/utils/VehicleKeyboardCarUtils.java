package com.transcendence.blackhole.ui.widget.edittext.utils;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.transcendence.blackhole.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VehicleKeyboardCarUtils {

    private Context mContext;
    private Activity mActivity;
    private KeyboardView mKeyboardView;
    private EditText mEdit;
    /**
     * 省份简称键盘
     */
    private Keyboard province_keyboard;
    /**
     * 数字与大写字母键盘
     */
    private Keyboard have_chinese_keyboar;
    private Keyboard english_keyboar;
    private Keyboard without_chinese_keyboar;

    /**
     * 软键盘切换判断
     */
    private boolean isChange = true;
    private boolean isChangeOne = true;
    private boolean isChangeTwo = true;
    /**
     * 判定是否是中文的正则表达式 [\\u4e00-\\u9fa5]判断一个中文 [\\u4e00-\\u9fa5]+多个中文
     */
    private String reg = "[\\u4e00-\\u9fa5]";

    public VehicleKeyboardCarUtils(Activity activity, EditText edit) {
        mActivity = activity;
        mContext = activity;
        mEdit = edit;
        init();

        province_keyboard = new Keyboard(mContext, R.xml.provice);//省份简称
        english_keyboar = new Keyboard(mContext, R.xml.english);//第二位  纯英文 允许o 不允许数字
        without_chinese_keyboar = new Keyboard(mContext, R.xml.qwerty_whitout_chinese);//第三位 数字+英文不允许o 不允许港 澳 学
        have_chinese_keyboar = new Keyboard(mContext, R.xml.qwerty_have_chinese);//最后一位 数字+英文+港澳学  不允许o

        mKeyboardView = activity
                .findViewById(R.id.keyboard_view);
        mKeyboardView.setKeyboard(province_keyboard);
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setOnKeyboardActionListener(listener);
    }

    private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
        @Override
        public void swipeUp() {}

        @Override
        public void swipeRight() {}

        @Override
        public void swipeLeft() {}

        @Override
        public void swipeDown() {}

        @Override
        public void onText(CharSequence text) {}

        @Override
        public void onRelease(int primaryCode) {}

        @Override
        public void onPress(int primaryCode) {}

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = mEdit.getText();
            int start = mEdit.getSelectionStart();
            if (primaryCode == -3) {// 回退
                if (editable != null && editable.length() > 0) {
                    //没有输入内容时软键盘重置为省份简称软键盘
                    if (editable.length() == 1) {
                        changeKeyboard(false);
                    }
                    //回退时软键盘重置为英文软键盘
                    if (editable.length() == 2) {
                        changeKeyboardOne(false);
                    }
                    if (editable.length() == 6) {
                        changeKeyboardTwo(false);
                    }
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            } else {
//                if (primaryCode == 27941) return;
                editable.insert(start, Character.toString((char) primaryCode)); //输入内容
                // 判断第一个字符是否是中文,是，则自动切换到数字软键盘
                if (mEdit.getText().toString().matches(reg)) {
                    changeKeyboard(true);
                }else if(start > 0 && start <= 4){
                    changeKeyboardOne(true);
                }else if(start > 4){
                    changeKeyboardTwo(true);
                }
            }
        }
    };

    /**
     * 指定切换软键盘 isnumber false表示要切换为省份简称软键盘 true表示要切换为数字软键盘
     */
    public void changeKeyboard(boolean isnumber) {
        if (isnumber) {
            mKeyboardView.setKeyboard(english_keyboar);
        } else {
            mKeyboardView.setKeyboard(province_keyboard);
        }
        isChange = !isChange;
    }

    /*
     * 指定切换软键盘 isEnglish false表示要切换为英文软键盘 true表示要切换为无中文字母软键盘
     */
    public void changeKeyboardOne(boolean isEnglish) {
        if (isEnglish) {
            mKeyboardView.setKeyboard(without_chinese_keyboar);
        } else {
            mKeyboardView.setKeyboard(english_keyboar);
        }
        isChangeOne = !isChangeOne;
    }

    /*
     * 指定切换软键盘 isEnglish false表示要切换为无中文字母软键盘 true表示要切换为包含港澳学中文字母软键盘
     */
    public void changeKeyboardTwo(boolean isEnglish) {
        if (isEnglish) {
            mKeyboardView.setKeyboard(have_chinese_keyboar);
        } else {
            mKeyboardView.setKeyboard(without_chinese_keyboar);
        }
        isChangeTwo = !isChangeTwo;
    }

    /**
     * 软键盘展示状态
     */
    public boolean isShow() {
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }

    /**
     * 软键盘展示
     */
    public void showKeyboard() {
        mKeyboardView.setVisibility(View.VISIBLE);
        startAnimation();
    }

    /**
     * 软键盘隐藏
     */
    public void hideKeyboard() {
        stopAnimation();
        mKeyboardView.setVisibility(View.GONE);
    }

    /**
     * 禁掉系统软键盘
     */
    public void hideSoftInputMethod() {
        mActivity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if (currentVersion >= 16) {
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        } else if (currentVersion >= 14) {
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }
        if (methodName == null) {
            mEdit.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName,
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(mEdit, false);
            } catch (NoSuchMethodException e) {
                mEdit.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 隐藏系统软键盘
     */
    public void hideSystemKeyBroad() {
        ((InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mActivity.getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    private Animation openAnimation, closeAnimation;

    private void init() {
        openAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.vehicle_keyboard_anim_entry_from_bottom);
        closeAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.vehicle_keyboard_anim_leave_from_bottom);
        closeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {}

            @Override
            public void onAnimationRepeat(Animation arg0) {}

            @Override
            public void onAnimationEnd(Animation arg0) {}
        });
    }

    private void startAnimation() {
        if (openAnimation != null) {
            mKeyboardView.startAnimation(openAnimation);
        }
    }

    public void stopAnimation() {
        if (closeAnimation != null) {
            mKeyboardView.startAnimation(closeAnimation);
        }
    }

}
