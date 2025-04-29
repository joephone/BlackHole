package com.transcendence.freeland.frag.test02.act;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.freeland.R;
import com.transcendence.freeland.frag.test02.fragment.FragmentHome;
import com.transcendence.freeland.frag.test02.fragment.FragmentSort;
import com.transcendence.freeland.frag.test02.fragment.FragmentUser;

public class FragmentTest02Act extends AppAc implements View.OnClickListener {

    private TextView home,sort,user;
    private ImageView homeImg,sortImg,userImg;
    private LinearLayout homeMenu,sortMenu,userMenu;
    private Fragment homeFragment,sortFragment,userFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_test02;
    }

    @Override
    protected void initView() {
        setTitle("Fragment底部导航栏");
        home = findViewById(R.id.tv_home);
        sort = findViewById(R.id.tv_sort);
        user = findViewById(R.id.tv_user);
        homeImg = findViewById(R.id.iv_home);
        sortImg = findViewById(R.id.iv_sort);
        userImg = findViewById(R.id.iv_user);
        homeMenu = findViewById(R.id.ll_home);
        sortMenu = findViewById(R.id.ll_sort);
        userMenu = findViewById(R.id.ll_user);
        homeMenu.setOnClickListener(this);
        sortMenu.setOnClickListener(this);
        userMenu.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if(homeFragment == null){
            homeFragment = new FragmentHome();
            transaction.add(R.id.fl_container,homeFragment);
        } else {
            transaction.show(homeFragment);
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragments(transaction);
        menuInit();
        switch (v.getId()) {
            case R.id.ll_home:
                home.setTextColor(Color.BLUE);
                if(homeFragment == null){
                    homeFragment = new FragmentHome();
                    transaction.add(R.id.fl_container,homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case R.id.ll_sort:
                sort.setTextColor(Color.BLUE);
                if(sortFragment == null){
                    sortFragment = new FragmentSort();
                    transaction.add(R.id.fl_container,sortFragment);
                } else {
                    transaction.show(sortFragment);
                }
                break;
            case R.id.ll_user:
                user.setTextColor(Color.BLUE);
                if(userFragment == null){
                    userFragment = new FragmentUser();
                    transaction.add(R.id.fl_container,userFragment);
                } else {
                    transaction.show(userFragment);
                }
                break;
        }
        transaction.commit();  //提交处理 重要
    }



    private void hideFragments(FragmentTransaction transaction) {
        if(homeFragment !=null) {
            transaction.hide(homeFragment);
        }
        if(sortFragment !=null) {
            transaction.hide(sortFragment);
        }
        if(userFragment !=null) {
            transaction.hide(userFragment);
        }
    }

    private void menuInit() {
        home.setTextColor(Color.BLACK);
        sort.setTextColor(Color.BLACK);
        user.setTextColor(Color.BLACK);
    }
}
