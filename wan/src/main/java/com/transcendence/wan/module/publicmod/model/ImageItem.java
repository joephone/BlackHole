package com.transcendence.wan.module.publicmod.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Author Joephone on 2021/4/23 0023 下午 2:03
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class ImageItem implements Serializable {

    private String desc;
    private int currentPosition;

    private ArrayList<String> imageList;
    private ArrayList<String> imageTitles;

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public ArrayList<String> getImageTitles() {
        return imageTitles;
    }

    public void setImageTitles(ArrayList<String> imageTitles) {
        this.imageTitles = imageTitles;
    }



}
