package com.transcendence.blackhole.demo.allapp.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.blackhole.R;
import com.transcendence.core.utils.L;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Joephone on 2019/8/27 17:14
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AllAppFragment extends Fragment {

    private RecyclerView mRv;
    private List<ResolveInfo> activities;//每个应用启动Activity的信息

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_all_app, container,false);
        mRv  = view.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        setupAdapter();
        return view;
    }

    private void setupAdapter() {
        //根据启动条件获取Activity
        Intent startIntent =new Intent(Intent.ACTION_MAIN);
        startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final PackageManager pm=getActivity().getPackageManager();
        activities = pm.queryIntentActivities(startIntent, 0);
        //对activity标签排序
        Collections.sort(activities, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo resolveInfo, ResolveInfo t1) {
                return String.CASE_INSENSITIVE_ORDER.compare(
                        resolveInfo.loadLabel(pm).toString(),t1.loadLabel(pm).toString()
                );
            }
        });
        mRv.setAdapter(new ActivityAdapter(activities));
        L.d("Found " + activities.size() + " activities.");
    }


    public static AllAppFragment newInstance() {

        Bundle args = new Bundle();

        AllAppFragment fragment = new AllAppFragment();
        fragment.setArguments(args);
        return fragment;
    }


    class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ResolveInfo mResolveInfo;
        private TextView mNameTextView;

        public Viewholder(View itemView) {
            super(itemView);
            mNameTextView= (TextView) itemView;
            mNameTextView.setOnClickListener(this);
        }

        public void bindActivitys(ResolveInfo resolveInfo){
            this.mResolveInfo=resolveInfo;
            PackageManager pm = getActivity().getPackageManager();
            String appName = mResolveInfo.loadLabel(pm).toString();
            mNameTextView.setText(appName);
        }

        @Override
        public void onClick(View view) {
            //显示启动应用
            ActivityInfo activityInfo = mResolveInfo.activityInfo;
            Intent intent=new Intent(Intent.ACTION_MAIN)
                    .setClassName(activityInfo.packageName,activityInfo.name);
            startActivity(intent);
        }
    }

    class ActivityAdapter extends RecyclerView.Adapter<Viewholder>{

        private List<ResolveInfo> activitiesList;

        public ActivityAdapter (List<ResolveInfo> activitiesList){
            this.activitiesList=activitiesList;
        }

        @Override
        public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new Viewholder(view);
        }

        @Override
        public void onBindViewHolder(Viewholder holder, int position) {
            holder.bindActivitys(activitiesList.get(position));
        }

        @Override
        public int getItemCount() {
            return activitiesList.size();
        }
    }

}
