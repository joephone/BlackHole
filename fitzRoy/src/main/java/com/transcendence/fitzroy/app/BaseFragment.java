package com.transcendence.fitzroy.app;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import com.transcendence.fitzroy.R;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Joephone on 2021/9/14 0014 下午 3:30
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class BaseFragment extends Fragment {

    View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayout(),container,false);
        return mRootView;
    }

    public abstract int getLayout();


    public final <V extends View> V findViewById(@IdRes int id) {
        if (mRootView == null) {
            return null;
        }
        return mRootView.findViewById(id);
    }



    public void setTitle(String title){
        ((TextView)mRootView.findViewById(R.id.tv_title)).setText(title);
    }

    public void setBackVisibily(View view){
        view.findViewById(R.id.rl_back).setVisibility(View.VISIBLE);
        view.findViewById(R.id.rl_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    /**
     * 单击回退
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitSystem();
        }
        return false;
    }
    /**
     * 双击退出
     */
    private static Boolean isExit = false;
    /**
     * 双击退出
     */
    private void exitSystem() {
        // 准备退出
        ScheduledExecutorService service = null;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(getActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            service = new ScheduledThreadPoolExecutor(2);
            service.schedule(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2, TimeUnit.SECONDS);
        }else {
            getActivity().finish();
            System.exit(0);
        }
    }
}
