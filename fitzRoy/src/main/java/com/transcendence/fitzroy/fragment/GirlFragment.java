package com.transcendence.fitzroy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.transcendence.core.utils.L;
import com.transcendence.core.widget.custom.TabView;
import com.transcendence.fitzroy.R;
import com.transcendence.fitzroy.app.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2021/9/14 0014 下午 3:38
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class GirlFragment extends BaseFragment {



    private List<TabView> mTabs = new ArrayList<>();

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static GirlFragment newInstance(String title) {
        GirlFragment fragment = new GirlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_rv;
    }

}
