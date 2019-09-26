
package com.transcendence.blackhole.ui.widget.custom.segmentview;

import android.widget.RadioButton;
import android.widget.Toast;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.widget.custom.IosSegmentView;

/**
 * @author Administrator
 */
public class IosSegmentViewActivity extends TitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_widget_custom_segment_view;
    }

    @Override
    protected void init() {
        setTitle("Segment");
        IosSegmentView rGroup = (IosSegmentView) findViewById(R.id.rg);
        // 设置纵向排列 VERTICAL
        rGroup.setOrientation(IosSegmentView.HORIZONTAL);
        // 设置tabs
        rGroup.setTabs(new String[] {
                "主页", "朋友圈", "搜索"
        });
        // 设置点击事件
        rGroup.setOnItemCheckedListener(new IosSegmentView.OnItemCheckedListener() {

            @Override
            public void onCheck(RadioButton button, int position, String title) {
                Toast.makeText(getApplicationContext(),
                        "checked id = " + position + ", title = " + title,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}
