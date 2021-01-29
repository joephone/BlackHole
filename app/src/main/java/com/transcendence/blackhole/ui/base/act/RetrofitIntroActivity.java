package com.transcendence.blackhole.ui.base.act;

import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;

/**
 * @author Joephone on 2019/6/27 17:32
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RetrofitIntroActivity extends TitleBarActivity {

    TextView tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_intro;
    }

    @Override
    public void init() {
        setTitle("Retrofit介紹");
        tv = findViewById(R.id.tv);
        tv.setText("Retrofit介紹\n" +
                "Retrofit是Square(正方形)公司基于RESTful叉格推出的网絡\n" +
                "框架封装\n" +
                "々Retrofit与OKHttp的美系\n" +
                "Retrofit是基于OKHttp的网狢靖求框架的-ニ次封装,其本貭仍是OKHttp\n" +
                "\n" +
                "\n" +
                "Retrofit介绍\n" +
                "与其它网络库的对比\n" +
                "AndroidAsynHttp\n" +
                "基于HttpClient,作者已停止维护, Android5 0不再使用HttpClient,因此不推荐使用。Volley\n" +
                "基于HttpUrlConnection, Google官方推出,只适合轻量级网络交互如数据传输小,不适合大文件下传下载场景。\n" +
                "Retrofit优点\n" +
                "API设计简洁易用，注解化配置高度解耦、支持多种解析器、支持Rxjava\n" +
                " \n" +
                "\n" +
                " \n" +
                "Retrofit使用\n" +
                "Step1 Retrofit开源库、OkHttp网络库、 数据解析器集成、注册网络权限\n" +
                "依赖包导入\n" +
                "compile 'com.squareup.retrofit2:retrofit:2.2.0'\n" +
                "compile 'com.squareup.okhttp3:okhttp:3.4.1'\n" +
                "compile 'com.squareup.retrofit2:converter-gson:2.0.2\n" +
                "\n" +
                "网络权限\n" +
                "<uses-permission android:name= \" android.permission.INTERNET\"/>\n" +
                "\n" +
                "\n" +
                "下面列出一些简单的参数注解\n" +
                "\n" +
                "\n" +
                "@Url 替换url\n" +
                "\n" +
                "@QueryMap  替换url中查询参数\n" +
                "\n" +
                "@Header  替换header\n" +
                "\n" +
                "@FieldMap 替换post请求body中参数\n" +
                "\n" +
                "@FormUrlEncoded post请求需要加的方法注解\n" +
                "\n" +
                "@POST() 标示该方法为post请求\n" +
                "\n" +
                "@GET() 标示该方法为get请求");
    }
}
