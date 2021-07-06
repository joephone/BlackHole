package com.transcendence.core.umeng;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.core.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class BaseUmengThirdLoginAndShareActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }

    protected void qq(View view) {
        ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title
                , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_app_ten, SHARE_MEDIA.QQ
        );
    }

    protected void weiXin(View view) {
        ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title
                , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_app_ten, SHARE_MEDIA.WEIXIN
        );
    }

    protected void weixinCircle(View view) {
        ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title
                , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_app_ten, SHARE_MEDIA.WEIXIN_CIRCLE
        );
    }

    protected void sina(View view) {
        ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title
                , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_app_ten, SHARE_MEDIA.SINA
        );
    }

    protected void Qzone(View view) {
        ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title
                , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_app_ten, SHARE_MEDIA.QZONE
        );
    }


    protected void qqLogin(View view) {
        authorization(SHARE_MEDIA.QQ);
    }

    protected void weiXinLogin(View view) {
        authorization(SHARE_MEDIA.WEIXIN);
    }

    protected void sinaLogin(View view) {
        authorization(SHARE_MEDIA.SINA);
    }

    //授权
    private void authorization(SHARE_MEDIA share_media) {
        UMShareAPI.get(this).getPlatformInfo(this, share_media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.d(TAG, "onStart " + "授权开始");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Log.d(TAG, "onComplete " + "授权完成");

                //sdk是6.4.4的,但是获取值的时候用的是6.2以前的(access_token)才能获取到值,未知原因
                String uid = map.get("uid");
                String openid = map.get("openid");//微博没有
                String unionid = map.get("unionid");//微博没有
                String access_token = map.get("access_token");
                String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
                String expires_in = map.get("expires_in");
                String name = map.get("name");
                String gender = map.get("gender");
                String iconurl = map.get("iconurl");

                Toast.makeText(getApplicationContext(), "name=" + name + ",gender=" + gender, Toast.LENGTH_SHORT).show();

                //拿到信息去请求登录接口。。。
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Log.d(TAG, "onError " + "授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Log.d(TAG, "onCancel " + "授权取消");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
