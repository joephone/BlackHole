package com.transcendence.wan.module.setting.act;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.core.utils.L;
import com.transcendence.ui.recyclerview.view.LoadMoreLayout;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanTitleBarActivity;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.module.setting.adapter.LanguageSetAdapter;
import com.transcendence.wan.module.setting.bean.LanguageBean;
import com.transcendence.wan.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageActivity extends WanTitleBarActivity {

    private RecyclerView mRv;
    private TextView tvLanguage;

    private LanguageSetAdapter mAdapter;
    private LoadMoreLayout mLoadMoreLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_setting_language;
    }

    @Nullable
    @Override
    protected WanBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        setTitle(StringUtils.getString(R.string.setting_language));
        mRv = findViewById(R.id.rv);
        tvLanguage = findViewById(R.id.tv_language);
    }

    @Override
    protected void loadData() {
        mLoadMoreLayout = findViewById(R.id.loadMoreLayout);
        mAdapter = new LanguageSetAdapter(getContext());
        mLoadMoreLayout.setAdapter(mAdapter,getContext());
        mLoadMoreLayout.onLoadMore(getData());
        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.d("position:"+position);
            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, LanguageActivity.class);
        context.startActivity(intent);
    }

    private List<LanguageBean> getData() {
        List<LanguageBean> list = new ArrayList<>();
        list.add(new LanguageBean(Locale.SIMPLIFIED_CHINESE, "简体中文"));
        list.add(new LanguageBean(Locale.US, "英文"));
//        Locale currentLanguage = LanguageUtil.getCurrentLanguage();
//        for (LanguageBean languageBean : list) {
//            if (currentLanguage.equals(languageBean.getLocale())) {
//                languageBean.setPress(true);
//                break;
//            }
//        }
        return list;
    }
}
