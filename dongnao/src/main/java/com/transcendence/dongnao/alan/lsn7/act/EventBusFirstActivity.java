package com.transcendence.dongnao.alan.lsn7.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.transcendence.dongnao.R;
import com.transcendence.dongnao.model.Student;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * @author Joephone on 2020/3/12 20:25
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class EventBusFirstActivity extends AppCompatActivity {

    TextView tv;
    Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsn7_first);

        EventBus.getDefault().register(this);

        tv = findViewById(R.id.tv);
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventBusFirstActivity.this,EventBusSecondActivity.class);
                startActivity(intent);
            }
        });

    }


    @Subscribe
    public void receiveMess(Student student){
        tv.setText(student.toString());
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
