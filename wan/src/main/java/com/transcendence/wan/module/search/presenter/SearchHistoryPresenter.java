package com.transcendence.wan.module.search.presenter;

import com.transcendence.wan.module.search.utils.SearchHistoryUtils;

import java.util.List;

/**
 * @author Joephone on 2019/9/10 15:57
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SearchHistoryPresenter {

    private final SearchHistoryUtils mSearchHistoryUtils = SearchHistoryUtils.newInstance();

    public void saveHistoryList(List<String>list){
        mSearchHistoryUtils.save(list);
    }

    public void getHistoryList(){
        mSearchHistoryUtils.get();
    }

}
