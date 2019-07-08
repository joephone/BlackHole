package com.transcendence.structure.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transcendence.structure.R;


/**
 * Created by Joephone on 2019/5/13 17:13
 * E-Mail Address：joephonechen@gmail.com
 */

public class StructureMineFragment extends Fragment implements View.OnClickListener{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private String mContentText;

    TextView tvContent;


    public StructureMineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment .
     */
    public static StructureMineFragment newInstance(String param1) {
        StructureMineFragment fragment = new StructureMineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_structure_mine, container, false);
        tvContent = rootView.findViewById(R.id.tvContent);
        tvContent.setText(mContentText);
        tvContent.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.tvContent:
//                Intent intent = new Intent(getActivity(), SettingActivity.class);
//                startActivity(intent);
//                break;
        }
    }
}
