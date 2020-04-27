package com.transcendence.wan.module.mine.model;

/**
 * @Author Joephone on 2020/4/27 10:03
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MyCoinListBean {

    /**
     * coinCount : 13
     * date : 1587922278000
     * desc : 2020-04-27 01:31:18 签到 , 积分：10 + 3
     * id : 198298
     * reason : 签到
     * type : 1
     * userId : 60527
     * userName : 15171484007
     */

    private int coinCount;
    private long date;
    private String desc;
    private int id;
    private String reason;
    private int type;
    private int userId;
    private String userName;

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
