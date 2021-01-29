package com.transcendence.wan.module.mine.model;

import com.transcendence.wan.core.bean.WanBaseBean;

/**
 * @Author Joephone on 2020/4/24 23:48
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RankListModel extends WanBaseBean {

    /**
     * data : {"curPage":1,"datas":[{"coinCount":12839,"level":129,"rank":1,"userId":20382,"username":"g**eii"},{"coinCount":11934,"level":120,"rank":2,"userId":3559,"username":"A**ilEyon"},{"coinCount":10409,"level":105,"rank":3,"userId":29303,"username":"深**士"},{"coinCount":9174,"level":92,"rank":4,"userId":2,"username":"x**oyang"},{"coinCount":8281,"level":83,"rank":5,"userId":27535,"username":"1**08491840"},{"coinCount":7387,"level":74,"rank":6,"userId":28694,"username":"c**ng0218"},{"coinCount":7012,"level":71,"rank":7,"userId":3753,"username":"S**phenCurry"},{"coinCount":6920,"level":70,"rank":8,"userId":29185,"username":"轻**宇"},{"coinCount":6853,"level":69,"rank":9,"userId":1534,"username":"j**gbin"},{"coinCount":6820,"level":69,"rank":10,"userId":28607,"username":"S**Brother"},{"coinCount":6817,"level":69,"rank":11,"userId":9621,"username":"S**24n"},{"coinCount":6780,"level":68,"rank":12,"userId":12467,"username":"c**yie"},{"coinCount":6777,"level":68,"rank":13,"userId":7891,"username":"h**zkp"},{"coinCount":6757,"level":68,"rank":14,"userId":27,"username":"y**ochoo"},{"coinCount":6741,"level":68,"rank":15,"userId":14829,"username":"l**changwen"},{"coinCount":6660,"level":67,"rank":16,"userId":7710,"username":"i**Cola7"},{"coinCount":6629,"level":67,"rank":17,"userId":12351,"username":"w**igeny"},{"coinCount":6618,"level":67,"rank":18,"userId":833,"username":"w**lwaywang6"},{"coinCount":6608,"level":67,"rank":19,"userId":7809,"username":"1**23822235"},{"coinCount":6585,"level":66,"rank":20,"userId":26707,"username":"p**xc.com"},{"coinCount":6530,"level":66,"rank":21,"userId":863,"username":"m**qitian"},{"coinCount":6500,"level":65,"rank":22,"userId":6095,"username":"W**derfulMtf"},{"coinCount":6459,"level":65,"rank":23,"userId":25793,"username":"F**_2014"},{"coinCount":6459,"level":65,"rank":24,"userId":12331,"username":"R**kieJay"},{"coinCount":6426,"level":65,"rank":25,"userId":2068,"username":"i**Cola"},{"coinCount":6419,"level":65,"rank":26,"userId":29076,"username":"f**ham"},{"coinCount":6305,"level":64,"rank":27,"userId":25419,"username":"蔡**打篮球"},{"coinCount":6279,"level":63,"rank":28,"userId":7590,"username":"陈**啦啦啦"},{"coinCount":6250,"level":63,"rank":29,"userId":1260,"username":"于**家的吴蜀黍"},{"coinCount":6234,"level":63,"rank":30,"userId":2160,"username":"R**iner"}],"offset":0,"over":false,"pageCount":1144,"size":30,"total":34320}
     */

    private RankListBean data;

    public RankListBean getData() {
        return data;
    }

    public void setData(RankListBean data) {
        this.data = data;
    }


}
