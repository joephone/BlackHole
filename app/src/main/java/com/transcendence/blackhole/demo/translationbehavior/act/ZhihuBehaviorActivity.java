package com.transcendence.blackhole.demo.translationbehavior.act;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.blackhole.R;
import com.transcendence.core.widget.custom.TabView;

/**
 * @author JackChen 2018/3/3 09:09
 *  自定义Behavior，并且使用MD实现仿知乎中向上滑菜单底部FloatingActionBar消失，向下滑动菜单底部FloatingActionBar显示上来
 */

public class ZhihuBehaviorActivity extends AppCompatActivity {

    TabView mTabOne;
    TabView mTabTwo;
    TabView mTabThree;
    TabView mTabFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_behavior);

        initViews();

        //这两句代码必须要有，并且需要把 values -> style 中的theme主题修改为NoActionBar ,
        Toolbar tool_bar = findViewById(R.id.tool_bar);
        setSupportActionBar(tool_bar);

        RecyclerView recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // 下边这两种写法都是可以的
                View itemView = LayoutInflater.from(ZhihuBehaviorActivity.this).inflate(R.layout.activity_demo_behavior_item, parent, false) ;
//                View itemView = View.inflate(ZhihuBehaviorActivity.this , R.layout.item_behavior , null) ;
                ViewHolder holder = new ViewHolder(itemView) ;
                return holder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });

    }

    private void initViews() {
        mTabOne = findViewById(R.id.tabOne);
        mTabTwo = findViewById(R.id.tabTwo);
        mTabThree = findViewById(R.id.tabThree);
        mTabFour = findViewById(R.id.tabFour);
//        mTabOne.setOnClickListener(this);
//        mTabTwo.setOnClickListener(this);
//        mTabThree.setOnClickListener(this);
//        mTabFour.setOnClickListener(this);
        initTabs();
    }

    private void initTabs() {
        mTabOne.setIconAndText(R.mipmap.ic_navi_message_normal,R.mipmap.ic_navi_message_press,"消息");
        mTabTwo.setIconAndText(R.mipmap.ic_navi_contacts_normal,R.mipmap.ic_navi_contacts_press,"联系人");
        mTabThree.setIconAndText(R.mipmap.ic_navi_discovery_normal,R.mipmap.ic_navi_discovery_press,"发现");
        mTabFour.setIconAndText(R.mipmap.ic_navi_me_normal,R.mipmap.ic_navi_me_press,"我的");
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
