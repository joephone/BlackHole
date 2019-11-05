package com.transcendence.map.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.transcendence.map.act.AmapLocationSourceActivity;


/**
 * @author Administrator
 */

public class MapMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        //BasicAmapFragmentActivity  BasicAmapActivity  AmapLocationActivity AmapLocationSourceActivity
        Intent intent = new Intent(this, AmapLocationSourceActivity.class);
        startActivity(intent);
        finish();
    }
}
