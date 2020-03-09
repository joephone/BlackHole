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

    public String toJson() {
        return new Gson().toJson(this);
    }

    public String toFormatJson() {
        return JsonFormatUtils.format(toJson());
    }

}
