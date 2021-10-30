package com.transcendence.network.service;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Joephone on 2020/4/24 22:43
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ParamAis {


    private ParamAis(){}

    private static ParamAis instance;

    public static ParamAis getInstance(){
        if(instance == null){
            instance = new ParamAis();
        }
        return instance;
    }


    public Map<String,Object> aisFetch(List<String> list){
        String levelPatternGson = new Gson().toJson(list);
        Map<String,Object> map = new HashMap<>();
        Log.d("BlackHole",levelPatternGson);
        map.put("param",levelPatternGson);
        return map;
    }


    public Map<String,Object> aisFetchJson(String aisStr){
        List<String> list = new ArrayList<>();
        list.add(aisStr);
        String levelPatternGson = new Gson().toJson(list);
        Map<String,Object> map = new HashMap<>();
        map.put("param",levelPatternGson);
        return map;
    }



    public Map<String,Object> getShipInfo(String mmsi){
        Map<String,Object> map = new HashMap<>();
        map.put("mmsi",mmsi);
        return map;
    }

}
