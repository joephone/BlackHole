package com.transcendence.wan.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.transcendence.wan.R;
import com.transcendence.wan.core.app.WanApp;
import com.transcendence.wan.utils.GuideSPUtils;

import per.goweii.anylayer.DialogLayer;
import per.goweii.anylayer.Layer;

/**
 * @author CuiZhen
 * @date 2019/8/31
 * GitHub: https://github.com/goweii
 */
public class PrivacyPolicyDialog extends DialogLayer {

    public static void showIfFirst(Context context, CompleteCallback callback) {
        if (GuideSPUtils.getInstance().isPrivacyPolicyShown()) {
            if (callback != null) {
                callback.onComplete();
            }
            return;
        }
        new PrivacyPolicyDialog(context, callback).show();
    }

    private PrivacyPolicyDialog(Context context, CompleteCallback callback) {
        super(context);
        onDismissListener(new Layer.OnDismissListener() {
            @Override
            public void onDismissing(Layer layer) {
            }

            @Override
            public void onDismissed(Layer layer) {
                if (callback != null) {
                    callback.onComplete();
                }
            }
        });
        contentView(R.layout.dialog_privacy_policy)
                .backgroundResource(R.color.transparent_black_65)
                .cancelableOnClickKeyBack(false)
                .cancelableOnTouchOutside(false)
                .onClickToDismiss(new Layer.OnClickListener() {
                    @Override
                    public void onClick(Layer layer, View v) {
                        GuideSPUtils.getInstance().setPrivacyPolicyShown();
                    }
                }, R.id.dialog_privacy_policy_tv_yes)
                .onClickToDismiss(new Layer.OnClickListener() {
                    @Override
                    public void onClick(Layer layer, View v) {
                        WanApp.exitApp();
                    }
                }, R.id.dialog_privacy_policy_tv_no);
    }

    @Override
    public void onAttach() {
        super.onAttach();
        TextView content = getView(R.id.dialog_privacy_policy_tv_content);
        String text = getActivity().getString(R.string.privacy_policy_content);
        String link = getActivity().getString(R.string.privacy_policy_content_link);
        int start = text.indexOf(link);
        int end = start + link.length();
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new Clickable(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UrlOpenUtils.Companion
//                        .with("file:///android_asset/privacy_policy.html")
//                        .open(getActivity());
            }
        }), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        content.setText(new SpannableStringBuilder().append(spannableString));
        content.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private class Clickable extends ClickableSpan implements View.OnClickListener {
        private final View.OnClickListener mListener;

        private Clickable(View.OnClickListener mListener) {
            this.mListener = mListener;
        }

        @Override
        public void onClick(@NonNull View v) {
            mListener.onClick(v);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getActivity().getResources().getColor(R.color.main));
            ds.setUnderlineText(false);
        }
    }

    public interface CompleteCallback {
        void onComplete();
    }
}
