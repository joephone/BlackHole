package com.transcendence.gallery.viewpager.inter;

import androidx.fragment.app.FragmentManager;

import com.transcendence.gallery.viewpager.builder.GallerySettings;

public interface ISettingsBuilder {
    ISettingsBuilder thumbnailSize(int thumbnailSize );
    ISettingsBuilder enableZoom(boolean isZoomEnabled);
    ISettingsBuilder withFragmentManager(FragmentManager fragmentManager);
    GallerySettings build();
}
