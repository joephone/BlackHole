package com.transcendence.blackhole.ui.rv.pullloadmorerecyclerviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.blackhole.R;

import java.util.List;
import java.util.Map;

/**
 * Created by WuXiaolong on 2015/9/14.
 */
public class StaggeredRecycleViewAdapter extends RecyclerView.Adapter<StaggeredRecycleViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Map<String, String>> dataList;

    public List<Map<String, String>> getDataList() {
        return dataList;
    }

    public StaggeredRecycleViewAdapter(Context context, List<Map<String, String>> dataList) {
        this.dataList = dataList;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pullloadmorerecyclerviewsample_staggered_recycler_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(dataList.get(position).get("text"));
        holder.title.setHeight(Integer.parseInt(dataList.get(position).get("height")));//高度随机，下拉刷新高度会变
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
