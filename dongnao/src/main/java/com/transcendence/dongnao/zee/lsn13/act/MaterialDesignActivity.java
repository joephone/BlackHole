package com.transcendence.dongnao.zee.lsn13.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.transcendence.core.utils.L;
import com.transcendence.dongnao.R;


/**
 * @author Joephone on 2019/11/20 16:27
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MaterialDesignActivity extends AppCompatActivity {

    private TextView tv;
    private Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsn13_md_test);

        tv = findViewById(R.id.tv);
        bt = findViewById(R.id.bt);


        L.d("tv--"+tv.getClass().getName());
        L.d("bt--"+bt.getClass().getName());
    }
}
