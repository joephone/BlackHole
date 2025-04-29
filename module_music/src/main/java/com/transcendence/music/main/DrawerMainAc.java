package com.transcendence.music.main;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.transcendence.core.base.route.RoutePath;
import com.transcendence.core.ui.dialog.CardPickerDialog;
import com.transcendence.core.utils.CommonUtils;
import com.transcendence.core.utils.statusbarview.StatusBarUtil;
import com.transcendence.music.R;
import com.transcendence.music.databinding.ActivityMainDrawerBinding;
import com.transcendence.music.main.adapter.MenuItemAdapter;

/**
 * @author joephone
 * @date 2023/1/20
 * @desc
 */

@Route(path = RoutePath.Music.PAGER_MAIN,name = "其实这里还有一个参数")
public class DrawerMainAc extends AppCompatActivity implements CardPickerDialog.ClickListener {

    ActivityMainDrawerBinding dataBinding;
    ImageView mIvLeft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_drawer);

        initView();
        setUpDrawer();
//        statusBar();
    }


    private void statusBar() {
//        if(dataBinding!=null){
//            LogUtils.d("if dataBinding!=null");
//        }else {
//            LogUtils.d("else dataBinding==null");
//        }
//        if(dataBinding.drawerLayout!=null){
//            LogUtils.d("dataBinding.drawerLayout!=null");
//        }else {
//            LogUtils.d("dataBinding.drawerLayout==null");
//        }
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(DrawerMainAc.this, dataBinding.drawerLayout, CommonUtils.getColor(this, R.color.colorToolBar));
        ViewGroup.LayoutParams layoutParams = dataBinding.include.viewStatus.getLayoutParams();
        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
        dataBinding.include.viewStatus.setLayoutParams(layoutParams);
    }

    private void initView() {
        mIvLeft = findViewById(R.id.iv_left);
        mIvLeft.setOnClickListener(v -> {
            dataBinding.drawerLayout.openDrawer(Gravity.LEFT);
        });
    }

    private void setUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        dataBinding.lvLeftMenu.addHeaderView(inflater.inflate(R.layout.activity_main_drawer_nav_left, dataBinding.lvLeftMenu, false));
        dataBinding.lvLeftMenu.setAdapter(new MenuItemAdapter(this));
        dataBinding.lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        dataBinding.drawerLayout.closeDrawers();
                        break;
                    case 2:
                        CardPickerDialog dialog = new CardPickerDialog();
                        dialog.setClickListener(DrawerMainAc.this);
                        dialog.show(getSupportFragmentManager(), "theme");
                        dataBinding.drawerLayout.closeDrawers();
                        break;
//                    case 3:
//                        TimingFragment fragment3 = new TimingFragment();
//                        fragment3.show(getSupportFragmentManager(), "timing");
//                        dataBinding.drawerLayout.closeDrawers();
//                        break;
//                    case 4:
//                        BitSetFragment bfragment = new BitSetFragment();
//                        bfragment.show(getSupportFragmentManager(), "bitset");
//                        dataBinding.drawerLayout.closeDrawers();
//                        break;
//                    case 5:
//                        if (MusicPlayer.isPlaying()) {
//                            MusicPlayer.playOrPause();
//                        }
//                        unbindService();
//                        finish();
//                        dataBinding.drawerLayout.closeDrawers();
                }
            }
        });
    }

    @Override
    public void onConfirm(int currentTheme) {

    }
}
