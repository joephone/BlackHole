package com.transcendence.serialport;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.serialport.service.TimerService;


/**
 * @author hoshizora-rin
 */
public class MainActivity extends AppCompatActivity {
    private Serial1Util serial1Util;
    private Serial2Util serial2Util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSerialPort();

        TimerService.getConnet(this);
    }

    private void initSerialPort() {
        serial1Util = new Serial1Util();
        serial2Util = new Serial2Util();
        serial1Util.start();
        serial2Util.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        serial1Util.stop();
        serial2Util.stop();
    }
}
