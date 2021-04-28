package com.transcendence.ui.dialog.hjq.layout;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.transcendence.ui.R;


/**
 * @Author Joephone on 2021/4/16 0016 下午 3:06
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public final class InputDialog {
    public static final class Builder
            extends CommonDialog.Builder<Builder>
            implements BaseDialog.OnShowListener,
            TextView.OnEditorActionListener {

        private OnListener mListener;
        private final EditText mInputView;

        public Builder(Context context) {
            super(context);
            setCustomView(R.layout.dialog_input);

            mInputView = findViewById(R.id.tv_input_message);
            mInputView.setOnEditorActionListener(this);

            addOnShowListener(this);
        }

        public Builder setHint(@StringRes int id) {
            return setHint(getString(id));
        }

        public Builder setHint(CharSequence text) {
            mInputView.setHint(text);
            return this;
        }

        public Builder setContent(@StringRes int id) {
            return setContent(getString(id));
        }

        public Builder setContent(CharSequence text) {
            mInputView.setText(text);
            int index = mInputView.getText().toString().length();
            if (index > 0) {
                mInputView.requestFocus();
                mInputView.setSelection(index);
            }
            return this;
        }

        public Builder setListener(OnListener listener) {
            mListener = listener;
            return this;
        }

        public Builder setNumOnly() {
            mInputView.setInputType(InputType.TYPE_CLASS_NUMBER);
            return this;
        }

        public Builder setMaxLength(int max) {
            setMaxLength(mInputView, max);
            return this;
        }

        /**
         * {@link BaseDialog.OnShowListener}
         */
        @Override
        public void onShow(BaseDialog dialog) {
            postDelayed(() -> showKeyboard(mInputView), 500);
        }

        //    @SingleClick
        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            if (viewId == R.id.tv_ui_confirm) {
                autoDismiss();
                if (mListener != null) {
                    mListener.onConfirm(getDialog(), mInputView.getText().toString());
                }
            } else if (viewId == R.id.tv_ui_cancel) {
                autoDismiss();
                if (mListener != null) {
                    mListener.onCancel(getDialog());
                }
            }
        }

        /**
         * {@link TextView.OnEditorActionListener}
         */
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // 模拟点击确认按钮
                onClick(findViewById(R.id.tv_ui_confirm));
                return true;
            }
            return false;
        }

        /**
         * 设置edittext最大的输入限制
         *
         * @param editText
         * @param length
         */
        private void setMaxLength(EditText editText, int length) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String str = s.toString();
                    if (str.length() > length) {
                        editText.setText(str.substring(0, length)); //截取前x位
                        editText.requestFocus();
                        editText.setSelection(editText.getText().length()); //光标移动到最后
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public interface OnListener {

        /**
         * 点击确定时回调
         */
        void onConfirm(BaseDialog dialog, String content);

        /**
         * 点击取消时回调
         */
        default void onCancel(BaseDialog dialog) {
        }
    }
}
