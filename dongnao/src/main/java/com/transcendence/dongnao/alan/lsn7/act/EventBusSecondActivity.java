package com.transcendence.dongnao.alan.lsn7.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.transcendence.dongnao.R;
import com.transcendence.dongnao.model.Student;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Joephone on 2020/3/12 20:31
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class EventBusSecondActivity extends AppCompatActivity {

    private Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsn7_second);


        bt = findViewById(R.id.bt);


    }


    public void send(View view){
        EventBus.getDefault().post(new Student("Joephone",28));
    }



}
