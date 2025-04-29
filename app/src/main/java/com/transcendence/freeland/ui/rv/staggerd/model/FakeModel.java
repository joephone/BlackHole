package com.transcendence.freeland.ui.rv.staggerd.model;


import com.transcendence.core.ui.rv.staggerd.model.StaggedModel;

public class FakeModel implements StaggedModel {


    private int width;
    private int height;
    private int resourceId;

    public FakeModel(int width, int height, int resourceId){
        this.width = width;
        this.height = height;
        this.resourceId = resourceId;
    }



    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getThumb() {
        return null;
    }

    @Override
    public int localResorce() {
        return resourceId;
    }
}
