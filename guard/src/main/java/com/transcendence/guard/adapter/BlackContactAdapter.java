package com.transcendence.guard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.transcendence.guard.R;
import com.transcendence.guard.dao.BlackNumberDao;
import com.transcendence.guard.domian.BlackNumberInfo;

import java.util.List;

/**
 * @Author Joephone on 2021/2/18 0018 上午 11:05
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BlackContactAdapter extends BaseAdapter {

    private Context mContext;
    private List<BlackNumberInfo> list;
    private BlackNumberDao dao;
    private BlackContactCallback callback;

    public void setCallback(BlackContactCallback callback) {
        this.callback = callback;
    }

    public BlackContactAdapter(Context context, List<BlackNumberInfo> list) {
        this.mContext = context;
        this.list = list;
        dao = new BlackNumberDao(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.activity_black_number_item,null);
            holder = new ViewHolder();
            holder.mNameTv = convertView.findViewById(R.id.tv_name);
            holder.mModeTv = convertView.findViewById(R.id.tv_mode);
            holder.mContactImgv = convertView.findViewById(R.id.view_black_icon);
            holder.mDeleteView = convertView.findViewById(R.id.view_black_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mNameTv.setText(list.get(position).blackName+"("+list.get(position).blackNumber+")");
        holder.mModeTv.setText(list.get(position).getModeString(list.get(position).mode));
        holder.mNameTv.setTextColor(mContext.getResources().getColor(R.color.colorGitHubGreenPress));
        holder.mModeTv.setTextColor(mContext.getResources().getColor(R.color.colorGitHubGreenPress));
        holder.mDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean delete = dao.delete(list.get(position));
                if(delete){
                    list.remove(list.get(position));
                    notifyDataSetChanged();
                    //if DB have no one data, then callback
                    if(dao.getCount()==0){
                        callback.DataSizeChange();
                    }
                }else {
                    Toast.makeText(mContext,"删除失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

    public class ViewHolder{
        TextView mNameTv;
        TextView mModeTv;
        View mContactImgv;
        View mDeleteView;
    }

    public interface BlackContactCallback{
        void DataSizeChange();
    }
}
