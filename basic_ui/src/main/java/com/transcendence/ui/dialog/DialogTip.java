package com.transcendence.ui.dialog;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


import com.transcendence.ui.R;
import com.transcendence.ui.dialog.listener.SimpleCallback;

import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.DialogLayer;
import per.goweii.anylayer.Layer;

/**
 * @Author Joephone on 2021/1/28 上午 10:20
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class DialogTip {

    private Context mContext;
    private CharSequence title;
    private CharSequence msg;
    private CharSequence yesText;
    private CharSequence noText;
    private boolean singleBtnYes = false;
    private boolean cancelable = true;
    private SimpleCallback<Void> callbackYes = null;
    private SimpleCallback<Void> callbackNo = null;
    private SimpleCallback<Void> onDismissListener = null;
    private DialogLayer mDialogLayer;


    public static DialogTip with(Context context) {
        return new DialogTip(context);
    }
    //
    private DialogTip(Context context) {
        this.mContext = context;
        mDialogLayer = AnyLayer.dialog(context);
        mDialogLayer.contentView(R.layout.basic_ui_dialog_tip)
                .gravity(Gravity.CENTER)
                .backgroundColorRes(R.color.transparent_black_55)
                .cancelableOnTouchOutside(cancelable)
                .cancelableOnClickKeyBack(cancelable)
                .onVisibleChangeListener(new Layer.OnVisibleChangeListener() {
                    @Override
                    public void onShow(Layer layer) {
                    }

                    @Override
                    public void onDismiss(Layer layer) {
                        if (onDismissListener != null) {
                            onDismissListener.onResult(null);
                        }
                    }
                })
                .bindData(new Layer.DataBinder() {
                    @Override
                    public void bindData(Layer layer) {
                        TextView tvYes = layer.getView(R.id.basic_ui_tv_dialog_tip_yes);
                        TextView tvNo = layer.getView(R.id.basic_ui_tv_dialog_tip_no);
                        View vLine = layer.getView(R.id.basic_ui_v_dialog_tip_line);

                        if (singleBtnYes) {
                            tvNo.setVisibility(View.GONE);
                            vLine.setVisibility(View.GONE);
                        } else {
                            tvNo.setVisibility(View.VISIBLE);
                            vLine.setVisibility(View.VISIBLE);
                            if (noText != null) {
                                tvNo.setText(noText);
                            } else {
                                tvNo.setText(R.string.basic_ui_dialog_btn_no);
                            }
                        }

                        if (yesText != null) {
                            tvYes.setText(yesText);
                        } else {
                            tvYes.setText(R.string.basic_ui_dialog_btn_yes);
                        }

                        TextView tvTitle = layer.getView(R.id.basic_ui_tv_dialog_tip_title);
                        if (title == null) {
                            tvTitle.setVisibility(View.GONE);
                        } else {
                            tvTitle.setVisibility(View.VISIBLE);
                            tvTitle.setText(title);
                        }

                        TextView tvContent = layer.getView(R.id.basic_ui_tv_dialog_tip_content);
                        tvContent.setText(msg);
                    }
                })
                .onClickToDismiss(new Layer.OnClickListener() {
                    @Override
                    public void onClick(Layer layer, View v) {
                        if (callbackYes != null) {
                            callbackYes.onResult(null);
                        }
                    }
                }, R.id.basic_ui_tv_dialog_tip_yes)
                .onClickToDismiss(new Layer.OnClickListener() {
                    @Override
                    public void onClick(Layer layer, View v) {
                        if (callbackNo != null) {
                            callbackNo.onResult(null);
                        }
                    }
                }, R.id.basic_ui_tv_dialog_tip_no);
    }

    public DialogTip yesText(CharSequence yesText) {
        this.yesText = yesText;
        return this;
    }

    public DialogTip yesText(int yesText) {
        this.yesText = mContext.getString(yesText);
        return this;
    }

    public DialogTip noText(CharSequence noText) {
        this.noText = noText;
        return this;
    }

    public DialogTip noText(int noText) {
        this.noText = mContext.getString(noText);
        return this;
    }

    public DialogTip title(CharSequence title) {
        this.title = title;
        return this;
    }

    public DialogTip title(int title) {
        this.title = mContext.getString(title);
        return this;
    }

    public DialogTip message(CharSequence msg) {
        this.msg = msg;
        return this;
    }

    public DialogTip message(int msg) {
        this.msg = mContext.getString(msg);
        return this;
    }

    public DialogTip singleYesBtn() {
        singleBtnYes = true;
        return this;
    }

    public DialogTip cancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public DialogTip onYes(SimpleCallback<Void> callback) {
        callbackYes = callback;
        return this;
    }

    public DialogTip onNo(SimpleCallback<Void> callback) {
        callbackNo = callback;
        return this;
    }

    public DialogTip onDismissListener(SimpleCallback<Void> onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public void dismiss() {
        if (mDialogLayer != null) {
            mDialogLayer.dismiss();
        }
    }

    public void show() {
        if (mDialogLayer != null) {
            mDialogLayer.show();
        }
    }
}
