package com.transcendence.guard.domian;

/**
 * @Author Joephone on 2021/2/10 0010 下午 1:53
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BlackNumberInfo {

    public String blackNumber;
    public String blackName;

    public int mode;

    public String getModeString(int mode){
        switch (mode){
            case 1:
                return "电话拦截";
            case 2:
                return "短信拦截";
            case 3:
                return "电话，短信拦截";
        }
        return "";
    }

    public void setBlackNumber(String blackNumber) {
        this.blackNumber = blackNumber;
    }

    public void setBlackName(String blackName) {
        this.blackName = blackName;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getBlackNumber() {
        return blackNumber;
    }

    public String getBlackName() {
        return blackName;
    }
}
