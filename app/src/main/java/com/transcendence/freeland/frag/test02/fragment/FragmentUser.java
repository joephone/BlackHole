package com.transcendence.freeland.frag.test02.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.transcendence.freeland.R;

/**
 * @author joephone
 * @date 2025/3/14
 * @desc
 */
public class FragmentUser extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_test02_frag03,container,false);
        return view;
    }
}
