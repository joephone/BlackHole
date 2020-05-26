package com.mzelzoghbi.zgallery;

import android.app.Activity;
import android.content.Intent;

import com.mzelzoghbi.zgallery.activities.ZGalleryActivity;

import java.util.ArrayList;

/**
 * @author Joephone on 2019/8/23 16:59
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class Zgallery {
    private Activity mActivity;
    private ArrayList<String> imagesURLs;
    private String title;
    private int selectedImgPosition;

    private Zgallery(Activity activity, ArrayList<String> imagesURLs) {
        this.imagesURLs = imagesURLs;
        this.mActivity = activity;
    }

    /**
     * Set z_toolbar title
     * @param title
     * @return
     */
    public Zgallery setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Setting starting position
     *
     * @param selectedImgPosition
     * @return
     */
    public Zgallery setSelectedImgPosition(int selectedImgPosition) {
        this.selectedImgPosition = selectedImgPosition;
        return this;
    }

    /**
     * Start the gallery activity with builder settings
     */
    public void show() {
        Intent gridActivity = new Intent(mActivity, ZGalleryActivity.class);
        gridActivity.putExtra(ZgalleryConstants.IntentPassingParams.IMAGES, imagesURLs);
        gridActivity.putExtra(ZgalleryConstants.IntentPassingParams.TITLE, title);
//        gridActivity.putExtra(ZgalleryConstants.IntentPassingParams.TOOLBAR_COLOR_ID, toolbarColor);
//        gridActivity.putExtra(ZgalleryConstants.IntentPassingParams.TOOLBAR_TITLE_COLOR, color);
        gridActivity.putExtra(ZgalleryConstants.IntentPassingParams.SELECTED_IMG_POS, selectedImgPosition);
//        gridActivity.putExtra(ZgalleryConstants.IntentPassingParams.BG_COLOR, backgroundColor);
        mActivity.startActivity(gridActivity);
    }

}
