package com.transcendence.guard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.transcendence.guard.R;
import com.transcendence.guard.domian.ContactInfo;

import java.util.List;

/**
 * @Author Joephone on 2021/2/9 0009 下午 3:54
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class ContactAdapter extends BaseAdapter {

    private List<ContactInfo> contactInfos;
    private Context mContext;

    public ContactAdapter(List<ContactInfo> contactInfos, Context context) {
        this.contactInfos = contactInfos;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return contactInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactInfos.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.activity_contact_select_item,null);
            viewHolder = new ViewHolder();
            viewHolder.mNameTv = convertView.findViewById(R.id.tv_name);
            viewHolder.mPhoneTv = convertView.findViewById(R.id.tv_phone);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mNameTv.setText(contactInfos.get(position).name);
        viewHolder.mPhoneTv.setText(contactInfos.get(position).phone);
        return convertView;
    }

    public class ViewHolder{
        TextView mNameTv;
        TextView mPhoneTv;
    }
}
