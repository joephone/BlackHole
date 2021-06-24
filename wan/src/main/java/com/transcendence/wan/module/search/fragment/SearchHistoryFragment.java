package com.transcendence.wan.module.search.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.core.base.activity.BaseFragment;
import com.transcendence.wan.R;

/**
 * @author Joephone on 2019/9/9 10:46
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SearchHistoryFragment extends BaseFragment {

    RecyclerView mRvHot;
    RecyclerView mRvHistory;

    //    private BaseQuickAdapter<String, BaseViewHolder> mHotAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView==null){
            mRootView = inflater.inflate(R.layout.fragment_search_history, container, false);
            init(mRootView);
        }else {  //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if(parent!=null){
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Logs.logE("LabelSearchFragment onViewCreated");
    }


    private void init(View rootView) {
        mRvHot = rootView.findViewById(R.id.rvHot);
        mRvHistory = rootView.findViewById(R.id.rvHistory);
    }


    public static SearchHistoryFragment getInstance() {
        return new SearchHistoryFragment();
    }
}
