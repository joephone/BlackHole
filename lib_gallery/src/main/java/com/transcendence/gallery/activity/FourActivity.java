package com.transcendence.gallery.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.gallery.R;
import com.transcendence.gallery.viewpager.MediaInfo;
import com.transcendence.gallery.viewpager.ScrollGalleryView;

import java.util.ArrayList;
import java.util.List;

public class FourActivity extends AppCompatActivity {

    private ScrollGalleryView scrollGalleryView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        scrollGalleryView = findViewById(R.id.scroll_gallery_view);

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.image1);
        images.add(R.drawable.beauty01);
        images.add(R.drawable.beauty02);
        images.add(R.drawable.beauty03);
        images.add(R.drawable.beauty04);
        images.add(R.drawable.beauty05);
        images.add(R.drawable.beauty06);
        images.add(R.drawable.beauty07);
        images.add(R.drawable.beauty08);
        images.add(R.drawable.beauty09);



        scrollGalleryView
                .setThumbnailSize(200)
                .setZoom(true)
                .withHiddenThumbnails(false)
                .hideThumbnailsOnClick(true)
                .hideThumbnailsAfter(5000)
                .addOnImageClickListener(new ScrollGalleryView.OnImageClickListener() {
                    @Override
                    public void onClick(int position) {

                    }
                })
                .setFragmentManager(getSupportFragmentManager());
        for (int i=0 ; i<images.size() ; i++) {
            scrollGalleryView.addMedia(
                    MediaInfo.mediaLoader(new PicassoImageLoader(images.get(i),PicassoImageLoader.LOCAL_IMAGE))
            );
        }

        //PicassoImageLoader(images.get(i),PicassoImageLoader.LOCAL_IMAGE)
    }

}
