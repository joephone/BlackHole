package com.mzelzoghbi.zgallery.adp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.transcendence.blackhole.library.R;
import com.transcendence.blackhole.utils.GlideUtils;

import java.util.ArrayList;

/**
 * @author Joephone on 2019/8/23 16:57
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ZgalleryPagerAdapter extends PagerAdapter {

    Activity mActivity;
    LayoutInflater mLayoutInflater;
    ArrayList<String> images;

    public ZgalleryPagerAdapter(Activity activity, ArrayList<String> images) {
        this.mActivity = activity;
        mLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.activity_z_gallery_vp_item, container, false);
        ImageView iv = itemView.findViewById(R.id.iv);

        GlideUtils.getInstance().loadImageFromNew(mActivity,images.get(position),iv);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
