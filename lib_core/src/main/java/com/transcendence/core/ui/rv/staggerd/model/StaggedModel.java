package com.transcendence.core.ui.rv.staggerd.model;

public interface StaggedModel {

    //图片宽度,单位px
    int getWidth();
    //图片高度,单位px
    int getHeight();
    //标题,根据自身需要，可以不填
    String getTitle ();
    //网络图片
    String getThumb();
    //本地图片
    int localResorce();


}
