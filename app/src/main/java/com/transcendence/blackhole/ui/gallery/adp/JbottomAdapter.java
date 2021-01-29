package com.transcendence.blackhole.ui.gallery.adp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transcendence.blackhole.R;
import com.transcendence.core.utils.GlideUtils;

/**
 * @author Joephone on 2019/7/3 15:59
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class JbottomAdapter extends RecyclerView.Adapter{

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int [] mIvList;
    private ZbottomEvent listener;
    private int selectedItem = 0;

    public JbottomAdapter(Context context, int [] ivList) {
        mContext = context;
        this.mIvList = ivList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setListener(ZbottomEvent listener) {
        this.listener = listener;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JbottomHoldView(mLayoutInflater.inflate(R.layout.activity_gallary_z_main_bottom_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final JbottomHoldView holder = (JbottomHoldView) viewHolder;
        GlideUtils.getInstance().loadImageFromLocal(mIvList[position],holder.iv);
        if(selectedItem!=position){
            holder.iv.setAlpha(0.5f);
        }else {
            holder.iv.setAlpha(1f);
        }
        if(holder.iv!=null){
            if(listener!=null){
                listener.addRvView(holder.iv);
            }
        }
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onRvImageClick(v,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mIvList.length;
    }

    public interface ZbottomEvent{
        void onRvImageClick(View v,int position);
        void addRvView(View v);
    }
}
