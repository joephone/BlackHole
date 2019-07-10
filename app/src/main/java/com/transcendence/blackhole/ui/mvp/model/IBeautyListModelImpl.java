package com.transcendence.blackhole.ui.mvp.model;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.ui.mvp.bean.Beauty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/7/9 16:53
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class IBeautyListModelImpl implements IBeautyListModel{

    @Override
    public void onLoadBeautyList(IBeautyListListener iBeautyListListener) {
        List<Beauty> beautyList = new ArrayList<>();
        beautyList.add(new Beauty(R.mipmap.beauty01));
        beautyList.add(new Beauty(R.mipmap.beauty02));
        beautyList.add(new Beauty(R.mipmap.beauty03));
        beautyList.add(new Beauty(R.mipmap.beauty04));
        beautyList.add(new Beauty(R.mipmap.beauty05));
        beautyList.add(new Beauty(R.mipmap.beauty06));
        beautyList.add(new Beauty(R.mipmap.beauty07));
        beautyList.add(new Beauty(R.mipmap.beauty08));
        beautyList.add(new Beauty(R.mipmap.beauty09));
        iBeautyListListener.onComplete(beautyList);
    }
}
