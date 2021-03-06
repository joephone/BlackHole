package com.transcendence.core.base.mvp;

import com.transcendence.core.utils.L;

import java.lang.ref.WeakReference;

/**
 * @author Joephone on 2019/6/25 11:57
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class LibBasePresenter<V> {

    /**
     * 弱引用
     */
    protected WeakReference<V> mViewWeakRef;

    /**
     * 进行绑定
     */
    public void onAttach(V view){
        L.d("onAttach");
        mViewWeakRef = new WeakReference<>(view);
    }

    /**
     * 进行解绑
     */
    public void onDetach(){
        L.d("onDetach");
        mViewWeakRef.clear();
    }
}
