package com.transcendence.wan.module.wxpublic.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.blackhole.utils.L;
import com.transcendence.global.API;
import com.transcendence.wan.R;
import com.transcendence.wan.module.wxpublic.model.WxChapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author Joephone on 2020/4/18 15:47
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WxPublicAccountHeadFragment extends Fragment {

    private ViewPager mVp;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static WxPublicAccountHeadFragment newInstance(String title) {
        WxPublicAccountHeadFragment fragment = new WxPublicAccountHeadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_navi_wx_public_account_head, container, false);
        L.d("WxPublicAccountListFragment onCreateView");
        initView(rootView);
        loadWxChapter();
        return rootView;
    }

    private void initView(View rootView) {
        mVp = rootView.findViewById(R.id.vp);
    }


    protected void loadWxChapter() {
        L.d("WxPublicAccountListFragment loadData");
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(API.WAN.WX_ARTICLE_CHAPTER).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.d("onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                L.d(" WxPublicAccountListFragment onResponse"+response.body().string());
                WxChapter chapter =  GsonUtils.getInstance().json2Cls(response.body().string(), WxChapter.class);
            }
        });
    }
}
