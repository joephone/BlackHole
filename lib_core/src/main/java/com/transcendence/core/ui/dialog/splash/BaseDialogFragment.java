package com.transcendence.core.ui.dialog.splash;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.transcendence.core.R;
import com.transcendence.core.base.app.CoreApp;

/**
 * @author joephone
 * @date 2023/6/13
 * @desc
 */
public abstract class BaseDialogFragment extends DialogFragment {

    protected static final String EXTRA_SHOW_TAG = "extra_show_tag";
    protected static final String EXTRA_PAGE_ID = "extra_page_id";

    protected Context mContext;

    protected LayoutInflater mInflater;

    /**
     * 标识是否显示过dialog
     */
    protected String pageId, showTag;

    /**
     * 是否可取消
     */
    protected boolean isCancelable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == mContext){
            mContext = CoreApp.getInstance().getApplicationContext();
        }
        mInflater = LayoutInflater.from(mContext);
        Bundle bundle = getArguments();
        if (bundle != null) {
            setCancelable(isCancelable);
            initArgument(bundle);
        }
    }

    @Override
    public void dismiss() {
        dismissAllowingStateLoss();
    }

    protected void initArgument(Bundle bundle) {
        showTag = bundle.getString(EXTRA_SHOW_TAG);
        pageId = bundle.getString(EXTRA_PAGE_ID);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDialog();
    }


    protected abstract View createContentView(LayoutInflater layoutInflater);


    /**
     * Dialog 关闭事件
     */
    protected void onDialogCloseEvent() {

    }


    /**
     * Dialog 显示事件
     */
    protected void onDialogShowEvent() {

    }

    protected Dialog createDialog() {
        final View contentView = createContentView(mInflater);
        final Dialog dialog = new Dialog(mContext, R.style.LCDialog_Alert);
        Window window = dialog.getWindow();
        //设置dialog全屏显示
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(lp);
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                onDialogShowEvent();
//                if (!TextUtils.isEmpty(showTag)) {
//                    MMkvHelper.Companion.getInstance().encode(Global.SP_KEY.HAS_AGREE, true);
//                }
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(contentView);
        return dialog;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (Exception e) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        }
    }
}
