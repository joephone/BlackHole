package com.mzelzoghbi.zgallery.adp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.transcendence.blackhole.library.R;

import java.util.ArrayList;

/**
 * @author Joephone on 2019/9/2 16:15
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ZgalleryGridImagesAdapter extends RecyclerView.Adapter<ZgalleryGridImagesAdapter.ZgalleryGridImageViewHolder> {

    Activity mActivity;
    ArrayList<String> images;
    private int imgPlaceHolderResId = -1;

    @NonNull
    @Override
    public ZgalleryGridImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ZgalleryGridImageViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_z_gallery_gv_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ZgalleryGridImageViewHolder zgalleryGridImageViewHolder, int i) {

    }


    @Override
    public int getItemCount() {
        return images.size();
    }


    class ZgalleryGridImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public ZgalleryGridImageViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

}
