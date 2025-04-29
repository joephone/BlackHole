package com.transcendence.gallery.viewpager.inter;

import com.transcendence.gallery.viewpager.MediaInfo;
import com.transcendence.gallery.viewpager.ScrollGalleryView;
import com.transcendence.gallery.viewpager.builder.GallerySettings;

import java.util.List;

public interface IGalleryBuilder {

    IGalleryBuilder settings(GallerySettings settings);

    IGalleryBuilder onImageClickListener(ScrollGalleryView.OnImageClickListener listener);

    IGalleryBuilder onImageLongClickListener(ScrollGalleryView.OnImageLongClickListener listener);

    IGalleryBuilder add(MediaInfo media);

    IGalleryBuilder add(List<MediaInfo> medias);

    ScrollGalleryView build();
}
