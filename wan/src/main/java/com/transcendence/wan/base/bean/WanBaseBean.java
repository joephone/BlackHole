package com.transcendence.wan.base.bean;

import com.google.gson.Gson;
import com.transcendence.wan.utils.JsonFormatUtils;

import java.io.Serializable;

/**
 * @author Joephone on 2019/12/6 16:35
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WanBaseBean implements Serializable {

    private int errorCode;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public String toFormatJson() {
        return JsonFormatUtils.getInstance().format(toJson());
    }

}
