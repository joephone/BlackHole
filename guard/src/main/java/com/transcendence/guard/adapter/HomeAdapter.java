package com.transcendence.guard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.guard.R;


/**
 * @Author Joephone on 2021/2/8 0008 下午 2:07
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class HomeAdapter extends BaseAdapter {

    private Context mContext;

    public HomeAdapter(Context context) {
        this.mContext = context;
    }

    private int[] imageId = {
            R.drawable.ic_guard_phone_github_green_24dp,R.drawable.ic_guard_lock_github_green_24dp,R.drawable.ic_guard_android_githhub_green_24dp,
            R.drawable.ic_guard_lock_github_green_24dp,R.drawable.ic_guard_brush_github_green_24dp,R.drawable.ic_guard_autorenew_github_green_24dp,
            R.drawable.ic_guard_local_airport_github_green_24dp,R.drawable.ic_menu_github_green_24dp,R.drawable.ic_settings_github_green_24dp};

    private String[] names = {
            "手机防盗","通讯卫士","软件管家",
            "手机杀毒","缓存清理","进程管理",
            "流量统计","高级工具","设置中心"};

    @Override
    public int getCount() {
        return imageId.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext,R.layout.activity_home_guard_gv_item,null);
        ImageView iv = view.findViewById(R.id.iv_icon);
        TextView tv = view.findViewById(R.id.tv_name);
        iv.setImageResource(imageId[position]);
        tv.setText(names[position]);
        return view;
    }
}
