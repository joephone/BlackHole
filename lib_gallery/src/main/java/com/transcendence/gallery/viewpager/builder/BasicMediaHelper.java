package com.transcendence.gallery.viewpager.builder;


import com.transcendence.gallery.viewpager.MediaInfo;
import com.transcendence.gallery.viewpager.inter.IMediaHelper;
import com.transcendence.gallery.viewpager.loader.DefaultVideoLoader;

public abstract class BasicMediaHelper implements IMediaHelper {

    @Override
    public MediaInfo video(String url, int placeholderViewId) {
        return MediaInfo.mediaLoader(new DefaultVideoLoader(url, placeholderViewId));
    }
}
