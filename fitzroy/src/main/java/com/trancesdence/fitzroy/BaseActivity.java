package com.trancesdence.fitzroy;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

/**
 * @Author Joephone on 2021/9/14 0014 上午 11:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.theme_tudor_white));

        }
        setContentView(getLayout());
        setTitle(getResources().getString(R.string.app_name));

    }
    public abstract int getLayout();
    public void setTitle(String title){
        ((TextView)findViewById(R.id.tv_title)).setText(title);
    }
    public void setBackVisibily(){
        findViewById(R.id.rl_back).setVisibility(View.VISIBLE);
        findViewById(R.id.rl_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBack(view);
            }
        });
    }
    public void clickBack(View view){
        finish();
    }

}
