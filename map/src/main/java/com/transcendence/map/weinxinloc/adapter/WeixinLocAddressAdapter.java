package com.transcendence.map.weinxinloc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    public AddressHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        AddressHolder myHolder = new AddressHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_weixin_loc_item, parent, false));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressHolder holder, int position) {
        holder.itemView.setTag(position);
        PoiItem poiItem = mList.get(position);
        if (position == selectPosition) {
            holder.mCheckBox.setChecked(true);
        } else {
            holder.mCheckBox.setChecked(false);
        }
        holder.mTvTitle.setText(poiItem.getTitle());
        holder.mTvAddressDetail.setText(poiItem.getProvinceName() + poiItem.getCityName() + poiItem.getAdName() + poiItem.getSnippet());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                setSelectPosition(position);
//                if (null != mOnItemClickLisenter) {
//                    mOnItemClickLisenter.onItemClick(position);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<PoiItem> list) {
        this.mList = list;
        selectPosition = -1;
        notifyDataSetChanged();
    }

    public void setSelectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }

    public class AddressHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        TextView mTvAddressDetail;
        CheckBox mCheckBox;


        public AddressHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvAddressDetail = (TextView) itemView.findViewById(R.id.tv_message);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
