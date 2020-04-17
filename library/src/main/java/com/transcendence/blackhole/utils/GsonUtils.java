package com.transcendence.blackhole.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Joephone on 2020/4/17 0:09
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc Gson转换工具类
 * @Edition 1.0
 * @EditionHistory
 */

public class GsonUtils {

    private static GsonUtils instance;

    private GsonUtils(){}

    public static GsonUtils getInstance(){
        if(instance==null){
            instance = new GsonUtils();
        }
        return instance;
    }


    /**
     * @param jsonString
     *            json字符串
     * @param cls
     *            要转换的类
     * @param <T>
     *            返回要转换的类
     * @return
     */
    public <T> T json2Cls(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return t;
    }

    /**
     * @param jsonString
     *            json字符串
     * @param cls
     *            要转换的类
     * @param <T>
     *            返回List
     * @return
     */
    public <T> List<T> json2List(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString, new ListOfSomething<T>(cls));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param jsonString
     *            json字符串
     * @return 返回 List<Map<String, Object>>
     */
    public static List<Map<String, Object>> listKeyMaps(String jsonString) {
        List<Map<String, Object>> list = new ArrayList();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString, new TypeToken<List<Map<String, Object>>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public class ListOfSomething<T> implements ParameterizedType {

        private Class<?> wrapped;

        public ListOfSomething(Class<T> wrapped) {
            this.wrapped = wrapped;
        }

        public Type[] getActualTypeArguments() {
            return new Type[] { wrapped };
        }

        public Type getRawType() {
            return List.class;
        }

        public Type getOwnerType() {
            return null;
        }

    }
}
