package com.transcendence.greenstar.demo.jscalljava;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.greenstar.R;

/**
 * @author joephone
 * @date 2025/4/25 19:40
 * @description
 * @edition 1.0
 */
public class JsCallJavaMainActivity extends AppAc implements View.OnClickListener {

    private Button btnJavaAndJs;
    private Button btnJsCallJava;
    private Button btnJsCallPhone;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_jscalljava_main;
    }

    @Override
    protected void initView() {
        btnJavaAndJs = (Button)findViewById( R.id.btn_java_and_js );
        btnJsCallJava = (Button)findViewById( R.id.btn_js_call_java );
        btnJsCallPhone = (Button)findViewById( R.id.btn_js_call_phone );

        btnJavaAndJs.setOnClickListener( this );
        btnJsCallJava.setOnClickListener( this );
        btnJsCallPhone.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == btnJavaAndJs ) {
            // Handle clicks for btnJavaAndJs
            Intent intent = new Intent(this,JavaAndJSActivity.class);
            startActivity(intent);
        } else if ( v == btnJsCallJava ) {
            // Handle clicks for btnJsCallJava
            Intent intent = new Intent(this,JsCallJavaVideoActivity.class);
            startActivity(intent);
        } else if ( v == btnJsCallPhone ) {
            // Handle clicks for btnJsCallPhone
            Intent intent = new Intent(this,JsCallJavaCallPhoneActivity.class);
            startActivity(intent);
        }
    }

}