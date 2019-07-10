package com.transcendence.blackhole.ui.mvp.adp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.ui.mvp.bean.Beauty;
import com.transcendence.blackhole.utils.GlideUtils;

import java.util.List;

/**
 * @author Joephone on 2019/7/9 16:17
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MvpBeautyListAdapter extends RecyclerView.Adapter{

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Beauty> beautyList;
    private int selectedItem = 0;

    public MvpBeautyListAdapter(Context context, List<Beauty> beautyList) {
        mContext = context;
        this.beautyList = beautyList;
        mLayoutInflater = LayoutInflater.from(context);
    }


    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MvpBeautyListHoldView(mLayoutInflater.inflate(R.layout.activity_mvp_beauty_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final MvpBeautyListHoldView holder = (MvpBeautyListHoldView) viewHolder;
        GlideUtils.getInstance().loadMipmap(mContext,beautyList.get(position).getImageId(),holder.iv);



    }

    @Override
    public int getItemCount() {
        return beautyList.size();
    }


}
