package com.transcendence.wan.core.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Joephone on 2020/4/27 23:20
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ParamWx {

    private ParamWx(){}

    private static ParamWx instance;

    public static ParamWx getInstance(){
        if(instance == null){
            instance = new ParamWx();
        }
        return instance;
    }

    public Map<String,Object> wxChapterArticleList(int id, int page){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("page",page);
        return map;
    }

}
