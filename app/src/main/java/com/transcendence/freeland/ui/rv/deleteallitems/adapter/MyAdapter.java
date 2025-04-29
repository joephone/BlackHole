package com.transcendence.freeland.ui.rv.deleteallitems.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.freeland.R;

import java.util.List;

/**
 * @Author Joephone C
 * @CreateTime 2024年08月07日 18:16:59
 * @Desc TODO
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> mData;

    public MyAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ui_rv_delall_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.myTextView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tv_title);
        }
    }

    public void deleteItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void deleteAllItems() {
        int size = mData.size();
        mData.clear();
        notifyItemRangeRemoved(0, size);
    }
}
