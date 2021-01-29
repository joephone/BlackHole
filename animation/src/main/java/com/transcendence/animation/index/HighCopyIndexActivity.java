package com.transcendence.animation.index;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.animation.R;
import com.transcendence.animation.ui.highcopy.DrawTicketActivity;
import com.transcendence.core.base.activity.BaseActivity;
import com.transcendence.core.utils.StringUtils;

import java.util.List;

/**
 * @author Joephone on 2019/5/24 10:55
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  高仿动画索引
 */
public class HighCopyIndexActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    List<String> items;
    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }



    @Override
    public void init() {
        lvIndex = findViewById(R.id.lvIndex);
        initAdapter();

    }

    private void initAdapter() {
        items = StringUtils.getStringList(mActivity,R.array.high_copy_index);
        adapter = new ArrayAdapter<>(mActivity,android.R.layout.simple_list_item_1,items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(DrawTicketActivity.class);
                break;
            case 1:
//                startIntent(AddShopCartMainActivity.class);
                break;
            case 2:
//                startIntent(YouKuActivity.class);
                break;
            case 3:
//                startIntent(GalleryMainActivity.class);
                break;
            case 4:
//                startIntent(SpinnerMainActivity.class);
                break;
            case 5:
//                startIntent(RadarMainActivity.class);
                break;
            case 6:
                break;
            case 7:
//                startIntent(BiaoPanMainActivity.class);
                break;
            case 8:
//                startIntent(WaterfallMainActivity.class);
                break;
            case 9:
//                startIntent(RootblockMainActivity.class);
                break;
            case 10:
//                startIntent(WheelMainActivity.class);
                break;
            case 11:
//                startIntent(WidgetMainActivity.class);
                break;
            case 12:
//                startIntent(WeixinChatDemoActivity.class);
                break;
            case 13:
//                startIntent(ViberationMain.class);
                break;
            case 14:
//                startIntent(SlidingDrawerMainActivity.class);
                break;
            case 15:
//                startIntent(PathMenuMainActivity.class);
                break;
            case 16:
//                startIntent(TaobaoActivity.class);
                break;
            case 17:
//                startIntent(WaveMainActivity.class);
                break;
            case 18:
//                startIntent(TitanicMainActivity.class);
                break;

            default:
                break;
        }
    }
}
