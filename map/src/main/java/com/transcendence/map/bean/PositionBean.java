package com.transcendence.map.bean;

/**
 * @author Joephone on 2019/11/11 14:25
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class PositionBean {

    public double latitue;

    public double longitude;

    public String address;

    public String city;

    public PositionBean() {
    }

    public PositionBean(double latitude, double longtitude, String address, String city) {
        this.latitue = latitude;
        this.longitude = longtitude;
        this.address = address;
        this.city = city;
    }

}
