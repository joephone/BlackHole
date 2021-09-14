package com.trancesdence.fitzroy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FitzRoyMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main_fitz_roy;
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, FitzRoyMainActivity.class);
        context.startActivity(intent);
    }
}
