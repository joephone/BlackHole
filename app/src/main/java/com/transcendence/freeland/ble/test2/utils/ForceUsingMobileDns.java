package com.transcendence.freeland.ble.test2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Dns;

/**
 * @author joephone
 * @date 2023/6/8
 * @desc
 */
public class ForceUsingMobileDns implements Dns {

    private Context context;

    public ForceUsingMobileDns(Context context) {
        this.context = context;
    }

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network mobileNetwork = null;
                Network[] networks = connectivityManager.getAllNetworks();
                for (Network network : networks) {
                    if (connectivityManager.getNetworkInfo(network).getType() == ConnectivityManager.TYPE_MOBILE) {
                        mobileNetwork = network;
                        break;
                    }
                }
                if (mobileNetwork != null) {
                    return (List<InetAddress>) mobileNetwork.getByName(hostname);
                }
            } else {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (networkInfo != null && networkInfo.isConnected()) {
                    return Arrays.asList(InetAddress.getAllByName(networkInfo.getExtraInfo()));
                }
            }

        return Dns.SYSTEM.lookup(hostname);
    }

//    /**
//     * 判断当前Wi-Fi是否可用
//     * @return true：可用；false：不可用
//     */
//    private static boolean isWifiConnected() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) MyApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        return wifiInfo != null && wifiInfo.isConnected();
//    }
}
