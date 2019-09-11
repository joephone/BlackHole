package com.transcendence.blackhole.demo.rvmonitor.adp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.rvmonitor.act.RvMonitorBean;

import java.util.List;

/**
 * @author Joephone on 2019/8/20 15:36
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RvMonitorAdapter extends RecyclerView.Adapter<RvMonitorAdapter.RvMonitorHolderView>{
    private List<RvMonitorBean> mList;

    public RvMonitorAdapter(List<RvMonitorBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RvMonitorHolderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View viewHolder = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recyclerview_monitor_item,viewGroup,false);
        return new RvMonitorHolderView(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull RvMonitorHolderView holder, int position) {
        int status = mList.get(position).getStatus();
        switch (status){
            case 0:
                holder.tv.setText("播放");
//                holder.tv.setTextColor(R.id.tv, 0xffff0000);
                break;
            case 1:
                holder.tv.setText("停止");
//                holder.tv.setTextColor(R.id.tv, 0xff454545);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class RvMonitorHolderView extends RecyclerView.ViewHolder {
        TextView tv;

        public RvMonitorHolderView(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
