package com.transcendence.blackhole.demo.other.act;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.demo.activation.view.GitHubContributionView;

/**
 * @author Joephone on 2019/8/27 11:35
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class GitHubContributionActivity extends TitleBarActivity {

    private GitHubContributionView table;

    @Override
    public int getLayoutId() {
        return R.layout.activity_other_github_contrbution;
    }

    @Override
    public void init() {
        setTitle("仿GitHub的提交活跃表格");
        table = findViewById(R.id.custom);
        table.setData(2016,12,9,2);
        table.setData(2016,11,9,1);
        table.setData(2016,10,5,10);
        table.setData(2016,8,9,3);
        table.setData(2016,4,20,2);
        table.setData(2016,12,13,3);
        table.setData(2016,12,14,3);
        table.setData(2017,2,15,4);
    }
}
