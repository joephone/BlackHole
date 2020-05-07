package com.transcendence.wan.module.project.presenter;

import android.content.Context;

import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.module.mine.model.MineBean;
import com.transcendence.wan.module.project.view.ScrollFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/5/6 23:01
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ScrollFragmentPresenter extends WanBasePresenter<ScrollFragmentView> {

    public void inflateItem(Context context){

        int[] mineItemImgIds = context.getResources().getIntArray(R.array.mine_item_left_image);
        String [] mineItemTxtIds = context.getResources().getStringArray(R.array.mine_item_left_text);

        List<MineBean> list = new ArrayList<>();

        for (int i = 0; i < mineItemImgIds.length; i++) {
            MineBean bean = new MineBean();
            bean.setIvLeft(mineItemImgIds[i]);
            bean.setTvLeft(mineItemTxtIds[i]);
            list.add(bean);
        }

        if(isAttach()){
            getWanBaseView().inflateItemSuc(list);
        }
    }

}
