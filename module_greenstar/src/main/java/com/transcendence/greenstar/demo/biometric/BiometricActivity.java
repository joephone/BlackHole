package com.transcendence.greenstar.demo.biometric;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.greenstar.R;

import java.util.concurrent.Executor;

/**
 * @author joephone
 * @date 2023/5/15
 * @desc
 */
public class BiometricActivity extends AppAc {

    TextView mTvBiometric;
    
    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_biometric;
    }

    @Override
    protected void initView() {
        mTvBiometric = findViewById(R.id.tv_biometric);
        mTvBiometric.setOnClickListener(v -> {
            onBiometric();
        });
    }

    private void onBiometric() {
        BiometricPrompt.PromptInfo promptInfo=new BiometricPrompt.PromptInfo.Builder()
                .setTitle("指纹登录")
                .setDescription("用户指纹验证")
                .setNegativeButtonText("取消")
                .build();
        getPrompt().authenticate(promptInfo);
    }

    //我这里写了一个方法，也可以不写，直接把这个里面的代码放在上面的点击事件里也是可以的
    private BiometricPrompt getPrompt(){
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback=new BiometricPrompt.AuthenticationCallback() {
            //指纹验证错误
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                showMsg(errString.toString());
            }
            //指纹验证成功
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                showMsg("指纹验证成功");
//                Intent intent = new Intent(MainActivity.this, MainActivity2.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
            }
            //指纹验证失败
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                showMsg("指纹验证失败");
            }
        };
        BiometricPrompt biometricPrompt=new BiometricPrompt(this,executor,callback);
        return  biometricPrompt;
    }

}
