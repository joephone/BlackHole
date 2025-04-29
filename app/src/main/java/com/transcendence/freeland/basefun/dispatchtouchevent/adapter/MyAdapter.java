package com.transcendence.freeland.basefun.dispatchtouchevent.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String data;
    private int pageColor; // 页面的背景颜色
    public MyAdapter(String data, int pageColor) {
        this.data = data;
        this.pageColor = pageColor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(data + " - Item " + (position + 1));

        // 根据页面背景颜色调整Item的背景颜色
        int itemColor = adjustColor(pageColor, position);
        holder.itemView.setBackgroundColor(itemColor);
    }

    @Override
    public int getItemCount() {
        return 20; // 每个页面显示20个Item
    }

    // 调整颜色（示例：根据位置调整亮度）
    private int adjustColor(int baseColor, int position) {
        float[] hsv = new float[3];
        Color.colorToHSV(baseColor, hsv);
        hsv[2] *= (1 - position * 0.02f); // 逐渐变暗
        return Color.HSVToColor(hsv);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
