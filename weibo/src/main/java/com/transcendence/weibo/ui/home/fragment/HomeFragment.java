package com.transcendence.weibo.ui.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.transcendence.weibo.R;
import com.transcendence.weibo.base.common.entity.Status;
import com.transcendence.weibo.base.common.entity.User;
import com.transcendence.weibo.utils.DensityUtil;

import java.util.ArrayList;

/**
 * Created by wenmingvs on 16/4/27.
 */

public class HomeFragment extends Fragment {

    private ArrayList<Status> mDatas;
    public Context mContext;
    public Activity mActivity;
    public View mView;
    private LinearLayout mGroup;
    public RecyclerView mRecyclerView;
    public TextView mUserNameTextView;
    public TextView mErrorMessage;
    public SwipeRefreshLayout mSwipeRefreshLayout;
//    public WeiboAdapter mAdapter;
//    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
//    private HomeFragmentPresent mHomePresent;
//    private long mCurrentGroup = Constants.GROUP_TYPE_ALL;
    private LinearLayout mEmptyLayout;
//    private GroupPopWindow mPopWindow;
    private User mCurrentUser;
    private boolean mComeFromAccoutActivity;
    private String mUserName;

    /**
     * 顶部导航栏
     */
    private LinearLayout mTopBar;
    /**
     * 手指滑动距离多少个像素点的距离，才隐藏bar
     */
    private static int sHideThreshold;
    /**
     * 记录手指滑动的距离
     */
    private int mScrolledDistance = 0;
    /**
     * 记录bar是否显示或者隐藏
     */
    private boolean mControlsVisible = true;

    private TextView mToastTv;
    private RelativeLayout mToastBg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        mContext = getContext();
//        mHomePresent = new HomeFragmentPresentImp(this);
//        mComeFromAccoutActivity = getArguments().getBoolean("comeFromAccoutActivity");
        sHideThreshold = DensityUtil.dp2px(mContext, 20);
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.weiboRecyclerView);
        mTopBar = (LinearLayout) mView.findViewById(R.id.toolbar_home);
        mGroup = (LinearLayout) mView.findViewById(R.id.group);
        mUserNameTextView = (TextView) mView.findViewById(R.id.name);
        mEmptyLayout = (LinearLayout) mView.findViewById(R.id.emptydeault_layout);
        mErrorMessage = (TextView) mView.findViewById(R.id.errorMessage);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_refresh_widget);
        mToastTv = (TextView) mView.findViewById(R.id.toast_msg);
        mToastBg = (RelativeLayout) mView.findViewById(R.id.toast_bg);
        initRecyclerView();
//        initRefreshLayout();
//        initGroupWindows();
//        mSwipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mHomePresent.refreshUserName(mContext);
//                if (mComeFromAccoutActivity) {
//                    mHomePresent.firstLoadData(mContext, true);
//                } else {
//                    mHomePresent.firstLoadData(mContext, mActivity.getIntent().getBooleanExtra("fisrtstart", false));
//                }
//            }
//        });
        return mView;
    }


    public HomeFragment() {
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static HomeFragment newInstance(boolean comeFromAccoutActivity) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putBoolean("comeFromAccoutActivity", comeFromAccoutActivity);
        homeFragment.setArguments(args);
        return homeFragment;
    }


    public void initRecyclerView() {
        mDatas = new ArrayList<>();
//        mAdapter = new WeiboAdapter(mDatas, mContext) {
//            @Override
//            public void arrowClick(Status status, int position, Bitmap bitmap) {
//                TimelineArrowWindow arrowDialog = new TimelineArrowWindow(mContext, mDatas.get(position), mAdapter, position, mUserNameTextView.getText().toString(), bitmap);
//                arrowDialog.show();
//                int width = ScreenUtil.getScreenWidth(mContext) - DensityUtil.dp2px(mContext, 80);
//                arrowDialog.getWindow().setLayout(width, (ViewGroup.LayoutParams.WRAP_CONTENT));
//            }
//        };
//        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
//        RecyclerViewUtils.setHeaderView(mRecyclerView, new HomeHeadView(mContext));
    }
}
