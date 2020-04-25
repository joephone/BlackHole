package com.transcendence.wan.core.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Joephone on 2020/4/24 15:37
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ParamUser {

    private ParamUser(){}

    private static ParamUser instance;

    public static ParamUser getInstance(){
        if(instance == null){
            instance = new ParamUser();
        }
        return instance;
    }

    public Map<String,Object> login(String username, String password){
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return map;
    }

    public Map<String,Object> logout() {
        Map<String,Object> map = new HashMap<>();
        return map;
    }
}
