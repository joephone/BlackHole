package com.transcendence.blackhole.demo.mvp.presenter;

import com.transcendence.core.base.mvp.LibBasePresenter;
import com.transcendence.blackhole.demo.mvp.bean.Beauty;
import com.transcendence.blackhole.demo.mvp.model.IBeautyListModel;
import com.transcendence.blackhole.demo.mvp.model.IBeautyListModelImpl;
import com.transcendence.blackhole.demo.mvp.view.IBeautyListView;
import com.transcendence.core.utils.L;

import java.util.List;

/**
 * @author Joephone on 2019/7/9 16:28
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class BeautyListPresenter <V extends IBeautyListView> extends LibBasePresenter<V> {
    //1 左手  view 层引用
    IBeautyListView iBeautyView;    //强引用
    //2 右手   model层引用
    IBeautyListModel iBeautyModel = new IBeautyListModelImpl();

    //3 构造方法
    public BeautyListPresenter() {  //T view 撤出参数
        //强引用
//        this.iBeautyView = (IBeautyListView) view;
//        //弱引用
//        mViewWeakRef = new WeakReference<>((IBeautyListView)view);
    }



    /**
     *  4 执行
     */
    public void presenter(){
        if (mViewWeakRef.get() != null) {
            mViewWeakRef.get().onShowLoading();
            if (iBeautyModel != null) {
                iBeautyModel.onLoadBeautyList(new IBeautyListModel.IBeautyListListener() {

                    @Override
                    public void onComplete(List<Beauty> beautyList) {
                        L.d("onComplete");
                        if (mViewWeakRef.get() != null) {
                            mViewWeakRef.get().onShowBeautyList(beautyList);
                            mViewWeakRef.get().onDismissLoading();
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
////                                        Thread.sleep(2000);
//                                        L.d("onShowBeautyList"+beautyList.size());
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//
//                                }
//                            }).start();

                        }
                    }

                    @Override
                    public void onFail() {

                    }
                });
            }
        }
    }

}
