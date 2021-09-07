package com.transcendence.map.weinxinloc.act;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.map.R;

/**
 * @author Joephone on 2019/11/22 16:32
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WeixinLocSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back,ivSearch,ivMore;
    private RecyclerView mRv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_loc_search);

        initView();
    }


    private void initView() {
        back = findViewById(R.id.back);
        ivMore = findViewById(R.id.iv_right);
        mRv = findViewById(R.id.rv);
        back.setOnClickListener(this);
        ivMore.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.iv_right:
                WeixinLocOriAct.start(this);
                break;
        }

    }
}
