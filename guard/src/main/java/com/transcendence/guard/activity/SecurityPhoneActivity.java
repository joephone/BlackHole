package com.transcendence.guard.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.transcendence.guard.R;
import com.transcendence.guard.adapter.BlackContactAdapter;
import com.transcendence.guard.dao.BlackNumberDao;
import com.transcendence.guard.domian.BlackNumberInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2021/2/18 0018 上午 11:01
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class SecurityPhoneActivity extends GuardBaseActivity implements View.OnClickListener{

    private FrameLayout mHaveBlackNumber;
    private FrameLayout mNoBlackNumber;
    private BlackNumberDao dao;
    private ListView mLv;
    private int pagenumber = 0;
    private int pagesize = 15;
    private int totalNumber;
    private List<BlackNumberInfo> pageBlackNumber = new ArrayList<>();
    private BlackContactAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_phone);
        initView();
        fillData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(totalNumber!=dao.getCount()){
            //数据发生变化
            if(dao.getCount()>0){
                mHaveBlackNumber.setVisibility(View.VISIBLE);
                mNoBlackNumber.setVisibility(View.GONE);
            } else {
                mHaveBlackNumber.setVisibility(View.GONE);
                mNoBlackNumber.setVisibility(View.VISIBLE);
            }
            pagenumber = 0;
            pageBlackNumber.clear();
            pageBlackNumber.addAll(dao.getPageAll(pagenumber,pagesize));
            if(adapter!=null){
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void initView() {
        mHaveBlackNumber = findViewById(R.id.fl_haveblacknumber);
        mNoBlackNumber = findViewById(R.id.fl_noblacknumber);
        mLv = findViewById(R.id.lv_blacknumbers);
        mLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case AbsListView.OnScrollListener
                            .SCROLL_STATE_IDLE:
                        int lastVisiblePosition = mLv.getLastVisiblePosition();
                        if(lastVisiblePosition == pageBlackNumber.size()-1){
                            pagenumber++;
                            if(pagenumber*pagesize>=totalNumber){
                                Toast.makeText(SecurityPhoneActivity.this,"没有更多的数据了",Toast.LENGTH_SHORT).show();
                            } else {
                              pageBlackNumber.addAll(dao.getPageAll(pagenumber,pagesize));
                              adapter.notifyDataSetChanged();
                            }
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void fillData() {
        dao = new BlackNumberDao(this);
        totalNumber = dao.getCount();
        if(totalNumber == 0){
            mHaveBlackNumber.setVisibility(View.GONE);
            mNoBlackNumber.setVisibility(View.VISIBLE);
        }else if(totalNumber >0){
            mHaveBlackNumber.setVisibility(View.VISIBLE);
            mNoBlackNumber.setVisibility(View.GONE);
            pagenumber = 0;
            if(pageBlackNumber.size()>0){
                pageBlackNumber.clear();
            }
            pageBlackNumber.addAll(dao.getPageAll(pagenumber,pagesize));
            if(adapter == null){
                adapter = new BlackContactAdapter(SecurityPhoneActivity.this,pageBlackNumber);
                adapter.setCallback(new BlackContactAdapter.BlackContactCallback() {
                    @Override
                    public void DataSizeChange() {
                        fillData();
                    }
                });
                mLv.setAdapter(adapter);
            }
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
