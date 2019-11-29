package com.transcendence.map.weinxinloc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.transcendence.map.R;

import java.util.List;

/**
 * @author Joephone on 2019/11/22 13:44
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WeixinLocAddressAdapter extends RecyclerView.Adapter<WeixinLocAddressAdapter.AddressHolder>{

    private Context mContext;
    private List<PoiItem> mList;
    private int selectPosition = -1;
//    private OnItemClickLisenter mOnItemClickLisenter;

    public WeixinLocAddressAdapter(Context context, List<PoiItem> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public AddressHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressHolder addressHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AddressHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        TextView mTvAddressDetail;
        CheckBox mCheckBox;


        public AddressHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvAddress);
            mTvAddressDetail = (TextView) itemView.findViewById(R.id.tvAddressDetail);
//            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
