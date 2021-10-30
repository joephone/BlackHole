package com.transcendence.fitzroy.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.transcendence.core.utils.L;
import com.transcendence.fitzroy.R;
import com.transcendence.fitzroy.app.BaseFragment;
import com.transcendence.network.cangahi.BaseObserver;
import com.transcendence.network.cangahi.BaseRequest;
import com.transcendence.network.cangahi.BaseResponse;
import com.transcendence.network.service.ParamAis;
import com.transcendence.ui.scroll.HeaderZoomLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author Joephone on 2021/9/14 0014 下午 5:22
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class OtherFragment extends BaseFragment implements View.OnClickListener , HeaderZoomLayout.OnScrollListener{

    private LinearLayout ll_daynight_about;
    private HeaderZoomLayout mScroll;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static OtherFragment newInstance(String title) {
        OtherFragment fragment = new OtherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_other;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ll_daynight_about = findViewById(R.id.ll_daynight_about);
        ll_daynight_about.setOnClickListener(this);
        mScroll = findViewById(R.id.scrollView);
        mScroll.setOnScrollListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_daynight_about:
//                fetchAis();
                break;
        }
    }



    public void fetchAis(){
        List<String> list = new ArrayList<>();
        list.add("413764534");
        list.add("413764535");
        Map<String,Object> map = ParamAis.getInstance().aisFetch(list);

        BaseRequest.getInstance().getApiService().aisFetch(map).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<RequestBody>(getActivity()) {
            @Override
            public void onSuccess(BaseResponse<RequestBody> t) {
                //成功回调方法,能够直接在此更新ui,AndroidSchedulers.mainThread()表示切换到主线程
                L.d("success");
            }

            @Override
            public void onCodeError(BaseResponse baseReponse) {
                //失败回调方法,能够直接在此更新ui,AndroidSchedulers.mainThread()表示切换到主线程
                L.d("onCodeError");
            }

            @Override
            public void onFailure(Throwable e, boolean netWork) throws Exception {
                L.d("onFailure");
            }
        });

    }

    @Override
    public void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if(Math.abs(scrollY - oldScrollY)>50){
//            fetchAis();
            int i = 10/0;
        }
    }
}
