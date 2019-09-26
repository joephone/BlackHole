package com.transcendence.blackhole.ui.widget.scroll;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.BaseActivity;
import com.transcendence.blackhole.utils.L;
import com.transcendence.blackhole.widget.custom.scroll.ScrollRecyclerView;
import com.transcendence.blackhole.widget.custom.scroll.lfscroll.LfScrollLayout;

import java.util.ArrayList;

/**
 * @author Joephone on 2019/9/24 16:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class LfScrollLayoutActivity extends BaseActivity {

//    private ScrollLayout mScrollLayout;
    private ScrollRecyclerView mScrollRecyclerView;
//    private ScrollTextView mScrollTextView;
    public LfScrollLayout mScrollLayout;
    private ImageView mMap;
//    private Button mNews;
//    private Button mVideo;


    @Override
    public int getLayoutId() {
        return R.layout.activity_demo_scroll_map_scrolllayout;
    }

    @Override
    public void init() {
//        setTitle(false);
        mMap = findViewById(R.id.map);
        mScrollLayout = findViewById(R.id.scroll_layout);
//        mScrollLayout = findViewById(R.id.scroll_layout);
//        mScrollRecyclerView = findViewById(R.id.scroll_list);
//        mScrollTextView = findViewById(R.id.scroll_bottom);
//        mNews = findViewById(R.id.news);
//        mVideo = findViewById(R.id.video);
//        initRecyclerViewData();
//        initClick();
////        mTitle.setBackgroundColor(Color.argb(0, 63, 81, 181));
//        mScrollLayout.setBackgroundColor(Color.argb(0, 0, 0, 0));

        mScrollLayout.setOnScrollChangedListener(mOnScrollChangedListener);
    }

    private LfScrollLayout.OnScrollChangedListener mOnScrollChangedListener =
            new LfScrollLayout.OnScrollChangedListener() {
                @Override
                public void onScrollProgressChanged(float currentProgress) {
                    if (currentProgress >= 0) {
                        if (currentProgress < 0.5) {
//                            if (back.getVisibility() != View.GONE) {
//                                back.setVisibility(View.GONE);
//                            }
                        } else {
//                            if (back.getVisibility() != View.VISIBLE) {
//                                back.setVisibility(View.VISIBLE);
//                            }
                        }
                        float precent = 255 * currentProgress;
                        if (precent > 255) {
                            precent = 255;
                        } else if (precent < 0) {
                            precent = 0;
                        }
                        mScrollLayout.getBackground().setAlpha(255 - (int) precent);
                    } else {
//                        if (back.getVisibility() != View.VISIBLE) {
//                            back.setVisibility(View.VISIBLE);
//                        }
                    }
                }



                @Override
                public void onScrollFinished(LfScrollLayout.Status currentStatus) {
                    L.d("currentStatus:" + currentStatus.name());
//                    if (myHasFocus&&searchEt.isFocused()){
//                        if (flag>1){
//                            searchEt.setFocusable(true);
//                            searchEt.setFocusableInTouchMode(true);
//                            searchEt.clearFocus();
//                        }else {
//                            flag=flag+1;
//                        }
//                    }
                    switch ( currentStatus ) {
                        case CLOSED://地图全部被盖住
                            break;
                        case OPENED://地图被盖住一半
//                            switch (nowType){
//                                case history:
//                                case menu:
//                                    searchCancel.setVisibility(View.GONE);
//                                    mapMenuAdapter(AllAdapter.AllAdapterUIType.history);
//                                    break;
//                            }
                            break;
                        case EXIT://地图底部被盖住
//                            back.setVisibility(View.VISIBLE);
//                            laySearch.setVisibility(View.VISIBLE);
                            break;
                    }

                }

                @Override
                public void onChildScroll(int top) {
                }
            };



    private void initRecyclerViewData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("我是：" + i);
        }
        mScrollRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(this, list);
        mScrollRecyclerView.setAdapter(adapter);
    }

    private void initClick() {
//        mScrollLayout.setOnScrollChangedListener(new ScrollLayout.OnScrollChangedListener() {
//            @Override
//            public void onScrollChange(int status) {
//                scrollLayouChange(status);
//            }
//
//            @Override
//            public void onScrollProgress(int progress) {
//                if (progress > 0) {
////                    mTitle.setVisibility(View.VISIBLE);
//                } else {
////                    mTitle.setVisibility(View.INVISIBLE);
//                }
////                mTitle.setBackgroundColor(Color.argb(progress, 63, 81, 181));
//                mScrollLayout.setBackgroundColor(Color.argb(progress, 0, 0, 0));
//            }
//        });
//
//
//        mScrollTextView.setOnTextViewListener(new ScrollTextView.OnTextViewListener() {
//            @Override
//            public void onClick(View v) {
//                mScrollLayout.toggle(ScrollLayout.STATUS_DEFAULT);
//            }
//        });

        mMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("地图");
            }
        });

//        mNews.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.show("新闻");
//            }
//        });
//        mVideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.show("视频");
//            }
//        });

    }

//    private void scrollLayouChange(int status) {
//        mScrollTextView.setVisibility(status == ScrollLayout.STATUS_CLOSE
//                ? View.VISIBLE : View.GONE);
//    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private Context mContext;
        private ArrayList<String> mList;
        private LayoutInflater mInflater;

        public MyAdapter(Context context, ArrayList<String> list) {
            this.mContext = context;
            this.mList = list;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.activity_widget_custom_map_scroll_layout_two_item, parent, false);
            return new MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
            holder.mTv.setText(mList.get(position));
            holder.mTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "this is " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView mTv;

            public MyViewHolder(View itemView) {
                super(itemView);
                mTv = itemView.findViewById(R.id.tv);
            }
        }
    }
}
