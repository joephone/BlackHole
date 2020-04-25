package com.transcendence.wan.core.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Joephone on 2020/4/24 22:43
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ParamMap {


    private ParamMap(){}

    private static ParamMap instance;

    public static ParamMap getInstance(){
        if(instance == null){
            instance = new ParamMap();
        }
        return instance;
    }


    public Map<String,Object> page(int page){
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        return map;
    }

}
