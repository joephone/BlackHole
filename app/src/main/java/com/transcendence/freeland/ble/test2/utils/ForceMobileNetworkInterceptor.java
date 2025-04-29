package com.transcendence.freeland.ble.test2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.transcendence.core.utils.log.LogUtils;

import java.io.IOException;
import java.lang.reflect.Method;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author joephone
 * @date 2023/6/8
 * @desc
 */
public class ForceMobileNetworkInterceptor implements Interceptor {
    private boolean isWifiEnabled=false;
//    private boolean useMobileData=false;
    private Context context;

    public ForceMobileNetworkInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        LogUtils.d("ForceMobileNetworkInterceptor intercept");
//        if (!isConnectedToWifi()) {
//            return chain.proceed(chain.request());
//        }
        Request request = chain.request();
//        if (!useMobileData) {
//            return chain.proceed(request);
//        }
        NetworkInfo netInfo = getNetworkInfo();
//        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            enableMobileData();
            try {
                Response response=chain.proceed(request);
                return response;
            } finally {
                disableMobileData();
            }
//        } else {
//            return chain.proceed(request);
//        }
    }

//    public boolean isConnectedToWifi() {
//        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//
//        if (mWifi.isConnected()) {
//            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//
//            if (wifiInfo != null) {
//                String ssid = wifiInfo.getSSID();
//                if (!TextUtils.isEmpty(ssid)) {
//                    if (USE_MOBILE_NETWORK) { // 将USE_MOBILE_NETWORK替换为你自己的判断条件
//                        useMobileData = true;
//                    }
//                }
//                return true;
//            }
//        }
//        return false;
//    }

    public void enableMobileData() {
        LogUtils.d("enableMobileData");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Method setMobileDataEnabledMethod = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            if (setMobileDataEnabledMethod != null) {
                setMobileDataEnabledMethod.invoke(connectivityManager, true);
            }
        } catch (Exception e) {
            LogUtils.d( "enable mobile data failed with exception: " + e.getMessage());

        }
    }

    public void disableMobileData() {
        LogUtils.d("disableMobileData");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Method setMobileDataEnabledMethod = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            if (setMobileDataEnabledMethod != null) {
                setMobileDataEnabledMethod.invoke(connectivityManager, false);
            }
        } catch (Exception e) {
            LogUtils.d( "disable mobile data failed with exception: " + e.getMessage());
        }
    }

    public NetworkInfo getNetworkInfo() {
        LogUtils.d("getNetworkInfo");
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = null;
        for (NetworkInfo networkInfo : connectionManager.getAllNetworkInfo()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                mobileNetworkInfo = networkInfo;
                break;
            }
        }
        return mobileNetworkInfo;
    }
}