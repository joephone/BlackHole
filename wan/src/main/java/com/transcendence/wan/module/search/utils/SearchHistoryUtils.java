package com.transcendence.wan.module.search.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.transcendence.core.utils.SPUtils;

import java.util.List;

/**
 * @author Joephone on 2019/9/10 15:58
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SearchHistoryUtils {

    private static final String SP_NAME = "search_history";
    private static final String KEY_WORD = "KEY_HISTORY";

    private final SPUtils mSPUtils = SPUtils.getInstance(SP_NAME);
    private final Gson mGson = new Gson();

    public static SearchHistoryUtils newInstance() {
        return new SearchHistoryUtils();
    }


    public void save(List<String> list){
        if (list == null || list.isEmpty()) {
            return;
        }

            String searchHistoryStr = mGson.toJson(list);
            mSPUtils.save(KEY_WORD,searchHistoryStr);
    }

    public List<String> get(){
        String searchHistoryStr = mSPUtils.get(KEY_WORD,"");
        if (TextUtils.isEmpty(searchHistoryStr)) {
            return null;
        }
        List<String> list = mGson.fromJson(searchHistoryStr,new TypeToken<List<String>>(){}.getType());
        return list;
    }

}
