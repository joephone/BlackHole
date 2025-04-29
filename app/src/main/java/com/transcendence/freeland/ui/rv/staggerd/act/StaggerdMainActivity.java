package com.transcendence.freeland.ui.rv.staggerd.act;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.core.ui.rv.staggerd.GridItemDecoration;
import com.transcendence.core.ui.rv.staggerd.StaggedAdapter;
import com.transcendence.core.ui.rv.staggerd.StaggerdRecyclerView;
import com.transcendence.core.ui.rv.staggerd.callbacks.LoadMoreAndRefresh;
import com.transcendence.core.ui.rv.staggerd.model.StaggedModel;
import com.transcendence.freeland.R;
import com.transcendence.freeland.ui.rv.staggerd.model.FakeModel;

import java.util.ArrayList;
import java.util.List;


public class StaggerdMainActivity extends AppCompatActivity {

    MyAdapter<FakeModel> staggedAdapter;
    StaggerdRecyclerView str;

    private List<FakeModel> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_rv_staggerd_main);

        str = findViewById(R.id.str);
        staggedAdapter = new MyAdapter<>(this);
        str.link(staggedAdapter,2);

        //动画效果
        str.addAnimation(R.anim.right_to_left);
       //间距
        str.addDecoration(new GridItemDecoration(this,10));

        str.addCallbackListener(new LoadMoreAndRefresh() {
            @Override
            public void onLoadMore() {
                getData(false);
            }

            @Override
            public void onRefresh() {

                getData(true);
            }
        });

        getData(true);

    }


    /**
     * 自定义adapter继承staggedadapter
     */

    class MyAdapter<T extends StaggedModel> extends StaggedAdapter<T> {

        MyAdapter(Context c) {
            super(c);
        }


        @Override
        public RecyclerView.ViewHolder addViewHolder(ViewGroup viewGroup, int i) {
            //绑定自定义的viewholder
            View v = LayoutInflater.from(StaggerdMainActivity.this).inflate(R.layout.activity_ui_rv_staggerd_item,viewGroup,false);
            return new MyHolder(v);
        }

        @Override
        public void bindView(RecyclerView.ViewHolder viewHolder, int i) {

            MyHolder myHolder = (MyHolder)viewHolder;


            // 在加载图片之前设定好图片的宽高，防止出现item错乱及闪烁
            ViewGroup.LayoutParams layoutParams = myHolder.img.getLayoutParams();
            layoutParams.height = datas.get(i).getHeight();
            myHolder.img.setLayoutParams(layoutParams);

            myHolder.img.setImageResource(datas.get(i).localResorce());

        }


    }


    /**
     * 自定义viewholder
     */

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView img;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);

        }
    }


    /**
     * 模拟网络请求
     *
     * @param refresh
     */

    private void getData(final boolean refresh) {

         //模拟刷新，只插入一遍数据
        if (refresh){

            if (datas.size()==0){

                datas.add(new FakeModel(500,500,R.drawable.beauty01));
                datas.add(new FakeModel(500,1000,R.drawable.beauty02));
                datas.add(new FakeModel(500,750,R.drawable.beauty03));
                datas.add(new FakeModel(500,530,R.drawable.beauty04));
                datas.add(new FakeModel(500,400,R.drawable.beauty05));
                datas.add(new FakeModel(500,980,R.drawable.beauty06));
                datas.add(new FakeModel(500,600,R.drawable.beauty07));
                datas.add(new FakeModel(500,620,R.drawable.beauty08));
                datas.add(new FakeModel(500,680,R.drawable.beauty09));
                datas.add(new FakeModel(500,705,R.drawable.beauty10));
                datas.add(new FakeModel(500,885,R.drawable.beauty10));


            }

            staggedAdapter.refresh(datas);

        }else{

            //模拟加载更多

            datas.add(new FakeModel(500,840,R.drawable.beauty04));
            datas.add(new FakeModel(500,712,R.drawable.beauty05));
            datas.add(new FakeModel(500,624,R.drawable.beauty06));
            datas.add(new FakeModel(500,888,R.drawable.beauty07));


            staggedAdapter.loadMore(datas);
        }


    }

}
