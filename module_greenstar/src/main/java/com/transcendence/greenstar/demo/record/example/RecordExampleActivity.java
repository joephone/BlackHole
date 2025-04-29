package com.transcendence.greenstar.demo.record.example;

import android.Manifest;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.greenstar.R;
import com.transcendence.greenstar.demo.record.history.DBManager;
import com.transcendence.greenstar.demo.record.manager.AudioRecordButton;
import com.transcendence.greenstar.demo.record.manager.MediaManager;
import com.transcendence.greenstar.demo.record.utils.PermissionHelper;

import java.util.ArrayList;
import java.util.List;

public class RecordExampleActivity extends AppAc {
    private ListView mEmLvRecodeList;
    private AudioRecordButton mEmTvBtn;
    List<Record> mRecords;
    ExampleAdapter mExampleAdapter;
    PermissionHelper mHelper;
    //db
    private DBManager mgr;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_demo_record_example);
//        initView();
//
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_record_example;
    }

    @Override
    protected void initView() {
        mEmLvRecodeList = (ListView) findViewById(R.id.em_lv_recodeList);
        mEmTvBtn = (AudioRecordButton) findViewById(R.id.em_tv_btn);

        initData();
        initAdapter();
        initListener();
    }

    private void initData() {
        mRecords = new ArrayList<>();
        //初始化DBManager
        mgr = new DBManager(this);
    }

    private void initAdapter() {
        LogUtils.d("initAdapter");
        mExampleAdapter = new ExampleAdapter(this, mRecords);
        mEmLvRecodeList.setAdapter(mExampleAdapter);

        //开始获取数据库数据
        List<Record> records = mgr.query();
        if (records == null || records.isEmpty()) return;
        for (Record record : records) {
            LogUtils.d( "initAdapter: " + record.toString());
        }
        mRecords.addAll(records);
        mExampleAdapter.notifyDataSetChanged();
        mEmLvRecodeList.setSelection(mRecords.size() - 1);
    }

    private void initListener() {
        LogUtils.d("initListener");
        mEmTvBtn.setHasRecordPromission(false);
        //        授权处理
        mHelper = new PermissionHelper(this);

        mHelper.requestPermissions("请授予[录音]，[读写]权限，否则无法录音",
                new PermissionHelper.PermissionListener() {
                    @Override
                    public void doAfterGrand(String... permission) {
                        mEmTvBtn.setHasRecordPromission(true);
                        mEmTvBtn.setAudioFinishRecorderListener((seconds, filePath) -> {
                            LogUtils.d("doAfterGrand");
                            Record recordModel = new Record();
                            recordModel.setSecond((int) seconds <= 0 ? 1 : (int) seconds);
                            recordModel.setPath(filePath);
                            recordModel.setPlayed(false);
                            mRecords.add(recordModel);
                            mExampleAdapter.notifyDataSetChanged();
                            mEmLvRecodeList.setSelection(mRecords.size() - 1);

                            //添加到数据库
                            mgr.add(recordModel);
                        });
                    }

                    @Override
                    public void doAfterDenied(String... permission) {
                        mEmTvBtn.setHasRecordPromission(false);
                        Toast.makeText(RecordExampleActivity.this, "请授权,否则无法录音", Toast.LENGTH_SHORT).show();
                    }
                }, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    //直接把参数交给mHelper就行了
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mHelper.handleRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onPause() {
        MediaManager.release();//保证在退出该页面时，终止语音播放
        super.onPause();
    }

    public DBManager getMgr() {
        return mgr;
    }

    public void setMgr(DBManager mgr) {
        this.mgr = mgr;
    }
}
