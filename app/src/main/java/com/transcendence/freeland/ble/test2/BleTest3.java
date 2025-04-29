package com.transcendence.freeland.ble.test2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.freeland.R;
import com.transcendence.freeland.ble.test2.utils.BaiduDns;
import com.transcendence.freeland.ble.test2.utils.ForceMobileNetworkInterceptor;
import com.transcendence.freeland.ble.test2.utils.ForceUsingMobileDataSocketFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author joephone
 * @date 2023/6/4
 * @desc
 */
public class BleTest3 extends AppAc {

    TextView tv2;
    Context context;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_ble_firsttest;
    }

    @Override
    protected void initView() {
        context = BleTest3.this;
        TextView tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv_2);
        tv.setOnClickListener(v ->{
            change(BleTest3.this);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        isConnected(BleTest3.this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void isConnected(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // 获取当前活动的网络信息
        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        // 判断应用是否拥有WLAN权限
        boolean hasWifiPermission = checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED;   //isAppWiFiEnabled(context);//

        // 判断应用是否拥有移动数据权限
        boolean hasMobileDataPermission = isAppMobileDataEnabled(context);//checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED;

//        new TestNetWork().execute();
//        tv2.setText("\nisNetworkAvailable--"+isWifiEnabled(context));
        // 判断网络是否可用
//        boolean isNetworkAvailable = activeNetworkInfo != null && activeNetworkInfo.isConnected();
//"判断应用是否拥有WLAN权限"+hasWifiPermission+"\n判断应用是否拥有移动数据权限"+hasMobileDataPermission+
//        tv2.setText("\nisNetworkAvailable--"+isNetworkConnected());
        LogUtils.d("判断应用是否拥有WLAN权限"+hasWifiPermission);
////        LogUtils.d("判断应用是否拥有WIFI权限"+isWifiAvailable(context));
//        LogUtils.d("判断应用是否拥有移动数据权限"+hasMobileDataPermission);
//        LogUtils.d("isNetworkAvailable--"+isWifiEnabled(context));
//        LogUtils.d("检查应用的蓝牙权限是否已授予并且蓝牙已启用--"+ BlueToothCheck.isBluetoothPermissionGrantedAndEnabled(context));
        LogUtils.d("判断WiFi是否被禁用--"+isWifiDisabled(context));

        new TestNetWork().execute();
    }


    public void change(Context context){
//        LogUtils.d("LCBPermissionModule pushSystemPage");
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }


    // 检查应用内 WLAN 是否可用
    public boolean isAppWiFiEnabled(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkCapabilities nc = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
            }
            if (nc != null) {
                return nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
            }
        }
        return false;
    }

    // 检查应用内移动数据是否可用
    public static boolean isAppMobileDataEnabled(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkCapabilities nc = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
            }
            if (nc != null) {
                return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
            }
        }
        return false;
    }






    @SuppressLint("MissingPermission")
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    boolean flag = false;
    class TestNetWork extends AsyncTask<Void,Void,Boolean> {



        @Override
        protected Boolean doInBackground(Void... voids) {

            HttpURLConnection conn =null;

            try {
                conn = (HttpURLConnection) (new URL("http://www.baidu.com").openConnection());
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(30000);
                conn.setReadTimeout(30000);
                conn.connect();
                LogUtils.d("urlc.getResponseCode()---"+conn.getResponseCode());
                return (conn.getResponseCode() == 200);
            } catch (IOException e) {
                LogUtils.e( "Error checking internet connection" + e);
                return false;
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }

        }

        @Override
        protected void onPostExecute(Boolean result) {
            // 当操作完成后，在 UI 线程中执行一些操作（例如更新 UI）
            // result 参数即为 doInBackground() 方法的返回值
            if(result){
                flag = result;
                tv2.setText("\nisNetworkAvailable--"+flag);
                LogUtils.d("y");
            }else {
                WifiManager wifiManager = (WifiManager) BleTest3.this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                if(wifiManager.isWifiEnabled()){
                    if(result){
                        flag = true;
                    }else {
//                    flag = false;
//                    LogUtils.d("无法判断");
                        connOnMobile();
                    }
                }else {
                    LogUtils.d("Mobile ");
                    connOnMobile();
                }
                tv2.setText("\nisNetworkAvailable--"+flag);
            }

        }
    }

    OkHttpClient okHttpClient = null;
    private void connOnMobile(){
        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        toggleWiFi(false);
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            // 当前有可用的网络，可以使用网络进行网络请求
            LogUtils.d("当前有可用的网络，可以使用网络进行网络请求");
            LogUtils.d("当前wifi是否联接"+isWifiConnected(context));
        } else {
            LogUtils.d("当前无可用的网络，无法使用网络进行网络请求");
        }


        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);


        LogUtils.d("使用OKHttp");

        NetworkRequest.Builder builder =null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder =new NetworkRequest.Builder();
            builder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            builder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR);
            builder.addCapability(NetworkCapabilities.NET_CAPABILITY_MMS);
            builder.removeTransportType(NetworkCapabilities.TRANSPORT_WIFI);
            builder.removeCapability(NetworkCapabilities.NET_CAPABILITY_WIFI_P2P);
            builder.removeCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
            //强制使用蜂窝数据网络-移动数据
            builder.removeTransportType(NetworkCapabilities.TRANSPORT_WIFI);
//            NetworkRequest build = builder.build();

//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

            connectivityManager.requestNetwork(builder.build(),new ConnectivityManager.NetworkCallback(){
                @Override
                public void onAvailable(@NonNull Network network) {
                    super.onAvailable(network);
                    LogUtils.d("onAvailable:"+network.toString());
                    try {
                        if (Build.VERSION.SDK_INT >= 23) {
                            ForceUsingMobileDataSocketFactory socketFactory = new ForceUsingMobileDataSocketFactory(BleTest3.this);
                            connectivityManager.bindProcessToNetwork(network); //这句话要加上哈，不然设置不生效
                            String url = "http://www.baidu.com";
                        //    okHttpClient = new OkHttpClient();   //new OkHttpClient.Builder().socketFactory(socketFactory).build();
//                            ForceUsingMobileDns forceUsingMobileDns = new ForceUsingMobileDns(BleTest3.this);
                            BaiduDns baiduDns = new BaiduDns();
                            okHttpClient = new OkHttpClient.Builder()
//                                    .dns(baiduDns)
                                    .addInterceptor(new ForceMobileNetworkInterceptor(BleTest3.this))
                                    .build();



                            final Request request = new Request.Builder()
                                    .url(url)
                                    .build();
                            Call call = okHttpClient.newCall(request);
                            call.enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    LogUtils.d("OkHttp Fail:"+e.getMessage());
//                                    if(isWifiConnected(BleTest3.this)){
//                                        flag = true;
//                                    } else {
                                        flag = false;
//                                    }
                                    tv2.setText("\nisNetworkAvailable--"+flag);
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    LogUtils.d("OkHttp Suc:" + response.code());
                                    flag = true;
                                    tv2.setText("\nisNetworkAvailable--"+flag);
                                    wifiManager.setWifiEnabled(true);
                                }
                            });
                        }
                    } catch (Exception e) {
                        LogUtils.d("okhttp exception"+e.getMessage());
                    }
                }
            });
        }
        toggleWiFi(true);
    }


    /**
     * 判断当前Wi-Fi是否可用
     * @param context 上下文
     * @return true：可用；false：不可用
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo != null && wifiInfo.isConnected();
    }


    private void connByMobile(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn =null;

                try {
                    // 设置移动数据网络
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    connectivityManager.setNetworkPreference(ConnectivityManager.TYPE_MOBILE);

                    conn = (HttpURLConnection) (new URL("http://www.baidu.com").openConnection());
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(30000);
                    conn.setReadTimeout(30000);
                    conn.setDoInput(true);
                    conn.connect();
                    LogUtils.d("urlc.getResponseCode()---"+conn.getResponseCode());
                    if(conn.getResponseCode() == 200){
                        flag = true;
                        tv2.setText("\nisNetworkAvailable--"+flag);
                    }
                } catch (IOException e) {
                    LogUtils.e( "Error checking internet connection" + e);
                    flag = false;
                } finally {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    connectivityManager.setNetworkPreference(ConnectivityManager.TYPE_WIFI);
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }

    /**
     * 判断当前应用所处的WiFi是否被禁用
     * @param context 上下文
     * @return true：WiFi被禁用；false：WiFi可用
     */
    public static boolean isWifiDisabled(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                return wifiManager == null || !wifiManager.isWifiEnabled();
            }
        }
        return true;
    }


    /**
     * WIFI网络开关
     *
     * @param enabled
     * @return 设置是否success
     */
    public  boolean  toggleWiFi( boolean  enabled) {
        WifiManager wm = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        return  wm.setWifiEnabled(enabled);

    }


//    private class ForceMobileNetworkInterceptor implements Interceptor {
//        @Override public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            if (shouldForceMobileNetwork(request.url().toString())) {
//                ConnectivityManager connectivityManager = (ConnectivityManager)
//                        context.getSystemService(Context.CONNECTIVITY_SERVICE);
//                connectivityManager.setNetworkPreference(ConnectivityManager.TYPE_MOBILE);
//            }
//
//            return chain.proceed(request);
//        }
//    }
//
//    private boolean shouldForceMobileNetwork(String url) {
//        // 判断条件，例如：url.contains("forceMobileNetwork")
//        // 如果符合条件，则返回 true；否则返回 false
//        return true;
//    }


}
