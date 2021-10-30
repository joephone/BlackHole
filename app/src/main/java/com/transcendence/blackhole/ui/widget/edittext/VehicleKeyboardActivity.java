package com.transcendence.blackhole.ui.widget.edittext;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.ui.widget.edittext.utils.VehicleKeyboardCarUtils;
import com.transcendence.core.base.activity.TitleBarActivity;

import java.util.Random;

/**
 * @Author Joephone on 2021/10/27 0027 下午 3:15
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class VehicleKeyboardActivity extends TitleBarActivity implements View.OnClickListener, View.OnTouchListener {

    private EditText etPlateNumber;
    private VehicleKeyboardCarUtils keyboardUtil;
    private ImageView imgIcon;//新能源icon 车牌号8位显示  默认隐藏
    private Button btnSubmit;
    private RelativeLayout rlPlateNumber;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_widget_edittext_vehicle_keyboard;
    }

    @Override
    protected void init() {
        setTitle("车辆录入专用输入键盘");

        etPlateNumber = findViewById(R.id.etPlateNumber);
        keyboardUtil = new VehicleKeyboardCarUtils(this, etPlateNumber);
        imgIcon = findViewById(R.id.imgIcon);
        btnSubmit = findViewById(R.id.btnSubmit);
        rlPlateNumber = findViewById(R.id.rlPlateNumber);
        btnSubmit.setEnabled(false);
        etPlateNumber.setOnTouchListener(this);

        etPlateNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                checkViewState(text);
                if (s.length() == 8){
                    rlPlateNumber.setBackgroundResource(R.drawable.activity_widget_edittext_vehicle_keyboard_btn_round_black);
                    imgIcon.setVisibility(View.VISIBLE);
                }else{
                    rlPlateNumber.setBackgroundResource(R.drawable.activity_widget_edittext_vehicle_keyboard_btn_round_green);
                    imgIcon.setVisibility(View.GONE);
                }
                if (text.contains("港") || text.contains("澳") || text.contains("学") ){
                    etPlateNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
                }else{
                    etPlateNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnSubmit.setOnClickListener(this);

        testArray();
    }

    //获取随机数
    private int[] array = new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9};
    private void testArray() {
        TextView textView = findViewById(R.id.textView);
        String str = "";
        for (int i = 0; i < array.length; i++){
            int index = new Random().nextInt(array.length);
            if (str.contains(array[index] + "")){
                i--;
            }else{
                if (i == 4){
                    str = str + " ";
                }else {
                    str = str + array[index];
                }
            }
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        textView.setText(str);
    }

    /**
     * 检查状态
     */
    private void checkViewState(String s) {
        if (s.length() >= 7) {
            btnSubmit.setEnabled(true);
            return;
        }
        btnSubmit.setEnabled(false);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.etPlateNumber:
                keyboardUtil.hideSystemKeyBroad();
                keyboardUtil.hideSoftInputMethod();
                if (!keyboardUtil.isShow())
                    keyboardUtil.showKeyboard();
                break;
            default:
                if (keyboardUtil.isShow())
                    keyboardUtil.hideKeyboard();
                break;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (keyboardUtil.isShow()) {
            keyboardUtil.hideKeyboard();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit://添加车牌
                etPlateNumber.setText("");
                btnSubmit.setEnabled(false);
                break;
        }
    }
}
