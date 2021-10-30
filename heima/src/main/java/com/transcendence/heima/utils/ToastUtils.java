package com.transcendence.heima.utils;

import android.widget.Toast;

import com.transcendence.heima.base.ProApplication;


/**
 * Toast
 *
 * @author gc
 * @since 1.1
 */
public class ToastUtils {

    public static Toast toast;

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showShort(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(ProApplication.getContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

}
