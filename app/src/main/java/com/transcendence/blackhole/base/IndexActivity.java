package com.transcendence.blackhole.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.ActionBarContextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.activity.paint.PaintActivity;
import com.transcendence.blackhole.activity.widget.button.JianbianButtonActivity;
import com.transcendence.blackhole.activity.widget.custom.StandardLayoutActivity;
import com.transcendence.blackhole.activity.widget.edittext.AutoClearEditActivity;
import com.transcendence.blackhole.base.mvp.BaseActivity;
import com.transcendence.blackhole.utils.StringUtils;
import com.transcendence.blackhole.widget.custom.StandardLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/5/15 18:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  app 索引页
 */
public class IndexActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ArrayAdapter<String> adapter;
    private StandardLayout edittext;
    private ListView lvIndex;
    private Context mContext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    public void initView() {
        lvIndex = findViewById(R.id.lvIndex);
    }

    @Override
    public void init() {
        this.mContext = mActivity;
        List<String> items = StringUtils.getStringList(mActivity,R.array.app_index_item);

        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);

        // 用于集成ActionBar 的ViewGroup ActionBar 将填充提供的ViewGroup(需要开发者自己在布局文件或代码中创建
        // ，建议使用RelativeLayout)
        RelativeLayout parent = (RelativeLayout) findViewById(R.id.rl);
        // 创建ActionBar des参数是ActionBar的唯一标识，请确保不为空
        @SuppressLint("RestrictedApi")
        ActionBarContextView socializeActionBar = new ActionBarContextView(mContext);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        socializeActionBar.setLayoutParams(layoutParams);
        // 添加ActionBar
        parent.addView(socializeActionBar);
    }

//    public static List<String> getStringList(Context context, int name) {
//        List<String> list = new ArrayList<>();
//        String[] array = context.getResources().getStringArray(name);
//        for (int i = 0; i < array.length; i++) {
//            list.add(array[i]);
//        }
//        return list;
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                //简单动画
                startActivity(MainActivity.class);
                break;
            case 1:
                //复杂动画
                break;
            case 2:
                //Splash引导动画
                break;
            case 3:
                //Flip效果集合
                break;
            case 4:
                break;
            case 5:
                //Property效果
                break;
        }
    }
}
