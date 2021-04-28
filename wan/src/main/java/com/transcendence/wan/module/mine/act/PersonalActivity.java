package com.transcendence.wan.module.mine.act;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseActivity;
import com.transcendence.wan.core.mvp.WanTitleBarActivity;
import com.transcendence.wan.module.mine.presenter.MinePresenter;
import com.transcendence.wan.module.setting.presenter.SettingPresenter;
import com.transcendence.wan.utils.UserUtils;

/**
 * @Author Joephone on 2021/4/27 0027 下午 5:51
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class PersonalActivity extends WanTitleBarActivity<MinePresenter> implements View.OnClickListener{

    private TextView tvId,tvName,tvAddress;
    private LinearLayout llName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_personal;
    }

    @Nullable
    @Override
    protected MinePresenter initPresenter() {
        return new MinePresenter();
    }

    @Override
    protected void initView() {
        setTitle("个人中心");
        tvId = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_name);
        tvAddress = findViewById(R.id.tv_address);
        llName = findViewById(R.id.ll_name);
    }

    @Override
    protected void loadData() {
        if(UserUtils.getInstance().getLoginBean()!=null){
            tvId.setText(UserUtils.getInstance().getLoginBean().getId()+"");
            tvName.setText(UserUtils.getInstance().getLoginBean().getNickname());
        }
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, PersonalActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_name:

                break;
        }
    }
}
