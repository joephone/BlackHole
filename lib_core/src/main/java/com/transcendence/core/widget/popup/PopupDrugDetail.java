package com.transcendence.core.widget.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.transcendence.core.R;


/**
 * Created by Joephone on 2018/8/24 14:40
 * E-Mail Address：joephonechen@gmail.com
 */

public class PopupDrugDetail extends PopupWindow {
    private TextView tvName,tvUniformSpec,tvUsageMethod;
    private View mMenuView;

    public PopupDrugDetail(Context context){
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popup_drug_detail, null);

//        init(bean);


        // 设置按钮监听

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupBottom);

        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);



        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x19000000);   //半透明  0xb0000000   透明 0x00FFFFFF
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                dismiss();
//                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
//                int y = (int) event.getY();
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) {
//                        dismiss();
//                    }
//                }
                return true;
            }
        });

    }

    //00%=FF（不透明）    5%=F2    10%=E5    15%=D8    20%=CC    25%=BF    30%=B2    35%=A5    40%=99    45%=8c    50%=7F
    //55%=72    60%=66    65%=59    70%=4c    75%=3F    80%=33    85%=21    90%=19    95%=0c    100%=00（全透明）

//    private void init(DrugDetailBean.ResultBean bean){
//        tvName = (TextView) mMenuView.findViewById(R.id.tvName);
////        tvUniformSpec = (TextView) mMenuView.findViewById(R.id.tvUniformSpec);
////        tvUsageMethod = (TextView) mMenuView.findViewById(R.id.tvUsageMethod);
//        if(bean!=null){
////            if(!StringUtils.isStringNull(bean.getName())){
////                tvName.setText("【药品名称】"+bean.getName());
////            }
////            if(!StringUtils.isStringNull(bean.getUniformSpec())){
////                tvUniformSpec.setText("【规格型号】"+bean.getUniformSpec());
////            }
////            if(!StringUtils.isStringNull(bean.getUsageMethod())){
////                tvUsageMethod.setText("【用法用量】"+bean.getUsageMethod());
////            }
//
//            if(!StringUtils.isStringNull(bean.getDetailed())){
//                tvName.setText(bean.getDetailed());
//            }
//        }
//    }


    public void showPopup(){
        // 在底部显示
        this.showAtLocation(mMenuView, Gravity.BOTTOM, 0, 0);
    }

}
