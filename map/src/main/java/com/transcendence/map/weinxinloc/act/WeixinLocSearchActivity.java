package com.transcendence.map.weinxinloc.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.transcendence.map.R;

/**
 * @author Joephone on 2019/11/22 16:32
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WeixinLocSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back,ivSearch;
    private RecyclerView mRv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_loc_search);

        initView();
    }


    private void initView() {
        back = findViewById(R.id.back);
        mRv = findViewById(R.id.rv);
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.back){
            finish();
        }
    }
}
