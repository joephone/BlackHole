package com.transcendence.wan.core.fragment;


import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.core.mvp.presenter.WanTitlebarPresenter;

/**
 * @Author Joephone on 2021/1/26 下午 12:29
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class WanTitleBarFragment<P extends WanTitlebarPresenter> extends WanBaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.title;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

//    @Override
//    protected void initWidget(View root) {
//        super.initWidget(root);
//
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//    }
//
//    @Override
//    protected void onBindViewBefore(View root) {
//        super.onBindViewBefore(root);
//        // on before onBindViewBefore call
//        ViewStub stub = (ViewStub) root.findViewById(R.id.view_stub);
//        stub.setLayoutResource(getContentLayoutId());
//        stub.inflate();
//    }


//    /**
//     *  标题栏view 包含标题栏
//     */
//    private View titleBar;
//    /**
//     *  状态栏view
//     */
//    private View statusView;
//    private TextView tvTitle;
//    private TextView tvRight;
//    private ImageView ivBack;
//    private ImageView ivRight;
//    private FrameLayout flBack;
//    private RelativeLayout rlTitle;
//    /**
//     *  是否显示返回键
//     */
//    protected boolean mHasBack = true;
//    /**
//     *  是否含有标题栏
//     */
//    protected boolean mHasTitle = true;
//
//
//    public void setContentView(int layoutResID) {
//        prepareContentView(layoutResID, mHasBack);
//    }
//
//    public void setContentView(int layoutResID, boolean hasBack) {
//        prepareContentView(layoutResID, hasBack);
//    }
//
//    public void setContentView(boolean hasBack) {
//        prepareContentView(0, hasBack);
//    }
//
//    public void setContentView(boolean hasBack,boolean hasTitle) {
//        mHasTitle = hasTitle;
//        prepareContentView(0, hasBack);
//    }
//
//    private void prepareContentView(int layoutResID, boolean hasBack) {
//        LinearLayout parent = new LinearLayout(getContext());
//        parent.setOrientation(LinearLayout.VERTICAL);
//        titleBar = getLayoutInflater().inflate(R.layout.title, parent, false);
//        if (mHasTitle) {
//            parent.addView(titleBar);
//            initTitle();
//            // 添加原内容
//            LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    1);
//            if (layoutResID > 0) {
//                View mainView = getLayoutInflater().inflate(layoutResID, null);
//                mainView.setLayoutParams(mainParams);
//                parent.addView(mainView);
//                mainView = parent;
//                super.onCreateView(mainView);
//
//            } else {
//                super.setContentView(parent);
//            }
//        }else {
//            titleBar.setVisibility(View.GONE);
//            super.setContentView(parent);
//        }
//
//    }
//
//    private void initTitle() {
//        if (titleBar != null) {
//            flBack = (FrameLayout) titleBar.findViewById(R.id.fl_back);
//            rlTitle = (RelativeLayout)titleBar.findViewById(R.id.rl_title);
//            statusView = (View) titleBar.findViewById(R.id.title_status);
//            tvRight = (TextView) titleBar.findViewById(R.id.tv_right);
//            tvTitle = (TextView) titleBar.findViewById(R.id.tv_title);
//            ivBack = (ImageView) titleBar.findViewById(R.id.iv_left);
//            ivRight = (ImageView) titleBar.findViewById(R.id.iv_right);
//            initStateHeight();
//            ivBack.setVisibility(View.VISIBLE);
//            flBack.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    finish();
//                }
//            });
//        }
//    }
//
//    protected void setTitle(String title) {
//        setTitle(title, null);
//    }
//
//    protected void setTitle(String title, String rightText) {
//        setTitle(true, title, rightText);
//    }
//
//    protected void setTitle(boolean showBackButton,String title) {
//        setTitle(showBackButton, title, null);
//    }
//
//
//    protected void setTitle(boolean hasBack,boolean hasTitle) {
//        mHasTitle = hasTitle;
//        if(!mHasTitle && titleBar!=null){
//            titleBar.setVisibility(View.GONE);
//        }
//        setTitle(hasBack, "", null);
//    }
//
//    protected void setTitle(boolean hasTitle) {
//        mHasTitle = hasTitle;
//        if(titleBar!=null && rlTitle!= null){
//            rlTitle.setVisibility(View.GONE);
//        }
//    }
//
//    protected void setTitle(boolean hasBack, String title, String rightText) {
//        if (flBack != null) {
//            flBack.setVisibility(hasBack ? View.VISIBLE : View.INVISIBLE);
//        }
//
//        if (tvTitle != null) {
//            if(!TextUtils.isEmpty(title)){
//                tvTitle.setText(title);
//            }
//        }
//
//        if (tvRight != null) {
//            tvRight.setVisibility(rightText == null ? View.INVISIBLE : View.VISIBLE);
//            if(!TextUtils.isEmpty(rightText)){
//                tvRight.setText(rightText);
//            }
//        }
//    }
//
//
//    private void initStateHeight() {
//        int statusHeight = AppUtils.getStatusHeight(getContext());
//        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusHeight);
//        statusView.setLayoutParams(l);
//    }


}
