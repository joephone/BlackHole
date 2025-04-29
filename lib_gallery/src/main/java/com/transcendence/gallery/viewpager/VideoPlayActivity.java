package com.transcendence.gallery.viewpager;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.transcendence.core.base.route.RoutePath;
import com.transcendence.gallery.R;

public class VideoPlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getExtras().getString(ConstantsLibGallery.URL);
        setContentView(R.layout.video_fragment);
        final VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                View progress = findViewById(R.id.videoProgress);
                progress.setVisibility(View.GONE);

                videoView.requestFocus();
                MediaController vidControl = new MediaController(VideoPlayActivity.this);
                vidControl.setAnchorView(videoView);
                videoView.setMediaController(vidControl);
                videoView.start();
            }
        });
        videoView.setVideoURI(Uri.parse(url));
    }
}
