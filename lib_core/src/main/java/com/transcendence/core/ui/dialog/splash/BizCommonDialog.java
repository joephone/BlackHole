package com.transcendence.core.ui.dialog.splash;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.transcendence.core.R;
import com.transcendence.core.global.Global;
import com.transcendence.core.ui.webview.MLWebviewActivity;
import com.transcendence.core.utils.mmkv.MMkvHelper;

/**
 * @author joephone
 * @date 2023/6/13
 * @desc 升级/开启消息通知/引导好评弹窗
 */
public class BizCommonDialog extends BaseDialogFragment {


    private static final String EXTRA_DIALOG_TYPE = "extra_dialog_type";

    //umeng 自定义事件统计id
    private static final String EVENT_ID_PRIVACY = "[show|click|close]_privacy";

    public static final String TAG_PRIVACYCLAUSE = "tag_privacyclause";
    public static final String TAG_UPDATE_POP = "tag_update_pop";
    public static final String TAG_TWICE_PRIVACYCLAUSE = "tag_twice_privacyclause";

    public static final int PRIVACY_CLAUSE = 4;
    public static final int TWICE_PRIVACY_CLAUSE = 3;
    public static final int NEWRNUPDATEPOP = 5;


    public static OnClickCustomButton onClickCustomButton;

    private int mDialogType;

    @Override
    protected void initArgument(Bundle bundle) {
        super.initArgument(bundle);
        mDialogType = bundle.getInt(EXTRA_DIALOG_TYPE);
    }

    public static BizCommonDialog newInstance(int dialogType, String pageId, OnClickCustomButton onClickCustomButton1) {
        onClickCustomButton = onClickCustomButton1;
        BizCommonDialog commonDialog = new BizCommonDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_DIALOG_TYPE, dialogType);
        bundle.putString(EXTRA_PAGE_ID, pageId);
        commonDialog.setArguments(bundle);
        return commonDialog;
    }


    /**
     * 初始化隐私条款
     */
    private View getPrivacyClauseContentView(LayoutInflater layoutInflater) {
        View contentView = layoutInflater.inflate(R.layout.dialog_privacy_clause, null);
        contentView.findViewById(R.id.privacyClauseDisagree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                onClickCustomButton.onClickCustomLeftButton();
            }
        });
        TextView privacyClauseContent = contentView.findViewById(R.id.privacyClauseContent);

        SpannableStringBuilder spannable = new SpannableStringBuilder(mContext.getResources().getString(R.string.privacy_policy_content));
        privacyClauseContent.setMovementMethod(LinkMovementMethod.getInstance());
        spannable.setSpan(new TextClick(), 62, 72
                , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new UserProtocolTextClick(), 73, 79
                , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        privacyClauseContent.setText(spannable);

        contentView.findViewById(R.id.privacyClauseAgree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                MMkvHelper.Companion.getInstance().encode(Global.SP_KEY.HAS_AGREE, true);
                onClickCustomButton.onClickCustomRightButton();
            }
        });
        return contentView;
    }

    private class TextClick extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            Intent intentArgu = new Intent(mContext, MLWebviewActivity.class);
            Bundle bundleArgu = new Bundle();
            bundleArgu.putBoolean("local", true);
            bundleArgu.putString("localPath", Global.BASE_URL_DF + Global.PRIVACY_CLAUSE);
            bundleArgu.putString("title", "隐私政策");
            bundleArgu.putBoolean("bGoneFlag", true);
            intentArgu.putExtra("localArgu", bundleArgu);
            startActivity(intentArgu);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ContextCompat.getColor(mContext, R.color.colorGitHubGreen));
        }
    }

    private class UserProtocolTextClick extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            Intent intentArgu = new Intent(mContext, MLWebviewActivity.class);
            Bundle bundleArgu = new Bundle();
            bundleArgu.putBoolean("local", true);
            bundleArgu.putString("localPath", Global.BASE_URL_DF + Global.USER_AGREEMENT);
            bundleArgu.putString("title", "东风风神用户协议");
            bundleArgu.putBoolean("bGoneFlag", true);
            intentArgu.putExtra("localArgu", bundleArgu);
            startActivity(intentArgu);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ContextCompat.getColor(mContext, R.color.colorGitHubGreen));
        }
    }

    /**
     * 二次确认隐私条款
     */
    private View getTwicePrivacyClauseContentView(LayoutInflater layoutInflater) {
        View contentView = layoutInflater.inflate(R.layout.dialog_privacy_clause_twice, null);
        contentView.findViewById(R.id.privacyClauseDisagree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                onClickCustomButton.onClickCustomLeftButton();
            }
        });

        contentView.findViewById(R.id.privacyClauseAgree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                onClickCustomButton.onClickCustomRightButton();
            }
        });
        return contentView;
    }

    @Override
    protected View createContentView(LayoutInflater layoutInflater) {
        switch (mDialogType) {
            //隐私条款
            case PRIVACY_CLAUSE:
                return getPrivacyClauseContentView(layoutInflater);
            //二次确认隐私条款
            case TWICE_PRIVACY_CLAUSE:
                return getTwicePrivacyClauseContentView(layoutInflater);
//            //更新提示
//            case NEWRNUPDATEPOP:
//                return getRnUpdatePop(layoutInflater);
        }
        return null;
    }

    public interface OnClickCustomButton {
        void onClickCustomLeftButton();

        void onClickCustomRightButton();
    }

}
