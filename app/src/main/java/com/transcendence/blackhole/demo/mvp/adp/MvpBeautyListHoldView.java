package com.transcendence.blackhole.demo.mvp.adp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.transcendence.blackhole.R;

/**
 * @author Joephone on 2019/7/9 16:29
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MvpBeautyListHoldView extends RecyclerView.ViewHolder {

    ImageView iv;

    public MvpBeautyListHoldView(@NonNull View itemView) {
        super(itemView);
        iv = itemView.findViewById(R.id.ivImg);
    }
}
