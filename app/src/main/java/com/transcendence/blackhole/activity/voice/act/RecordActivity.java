package com.transcendence.blackhole.activity.voice.act;

import android.Manifest;
import android.app.Dialog;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.activity.voice.utils.AudioRecorder;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.L;
import com.transcendence.blackhole.utils.permission.PermissionPool;

import java.io.File;
import java.io.IOException;

/**
 * @author Joephone on 2019/6/10 11:22
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class RecordActivity extends TitleBarActivity {

    // private static final int MAX_RECORD_TIME = 30; // 最长录制时间，单位秒，0为无时间限制
    private static final int MIN_RECORD_TIME = 1; // 最短录制时间，单位秒，0为无时间限制

    private static final int RECORD_OFF = 0; // 不在录音
    private static final int RECORD_ON = 1; // 正在录音
    private static final String RECORD_FILENAME = "record0033"; // 录音文件名

    private Button mBtnStartRecord;
    private Button mBtnPlayRecord;
    private TextView mTvRecordTxt;
    private TextView mTvRecordPath;
    private TextView mTvRecordDialogTxt;
    private ImageView mIvRecVolume;

    private Dialog mRecordDialog;
    private AudioRecorder mAudioRecorder;
    private MediaPlayer mMediaPlayer;
    private Thread mRecordThread;

    private int recordState = 0; // 录音状态
    private float recodeTime = 0.0f; // 录音时长
    private double voiceValue = 0.0; // 录音的音量值
    private boolean playState = false; // 录音的播放状态
    private boolean moveState = false; // 手指是否移动
    private float downY;


    @Override
    public int getLayoutId() {
        return R.layout.record_audio;
    }

    @Override
    public void init() {
        onPermissionRequest(PermissionPool.RECORD_AUDIO, Manifest.permission.RECORD_AUDIO);
    }

    @Override
    protected void onPermissionsGranted(int requestCode) {
        super.onPermissionsGranted(requestCode);
        mBtnPlayRecord = (Button) findViewById(R.id.record_play);
        mTvRecordTxt = (TextView) findViewById(R.id.record_time);
        mTvRecordPath = (TextView) findViewById(R.id.record_path);
        mBtnStartRecord = (Button) findViewById(R.id.record_start);
        // 播放
        mBtnPlayRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playState) {
                    mMediaPlayer = new MediaPlayer();
                    try {
                        mMediaPlayer.setDataSource(getAmrPath());
                        mMediaPlayer.prepare();
                        mBtnPlayRecord.setText("正在播放");
                        playState = true;
                        mMediaPlayer.start();

                        // 设置播放结束时监听
                        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                if (playState) {
                                    mBtnPlayRecord.setText("播放声音");
                                    playState = false;
                                }
                            }
                        });
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    if (mMediaPlayer.isPlaying()) {
                        mMediaPlayer.stop();
                        playState = false;
                    } else {
                        playState = false;
                    }
                    mBtnPlayRecord.setText("播放录音");
                }
            }
        });

        // 录音
        mBtnStartRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // 按下按钮
                        if (recordState != RECORD_ON) {
                            downY = event.getY();
                            deleteOldFile();
                            mAudioRecorder = new AudioRecorder(RECORD_FILENAME);
                            recordState = RECORD_ON;
                            try {
                                mAudioRecorder.start();
                                recordTimethread();
                                showVoiceDialog(0);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE: // 滑动手指
                        float moveY = event.getY();
                        if (moveY - downY > 50) {
                            moveState = true;
                            showVoiceDialog(1);
                        }
                        if (moveY - downY < 20) {
                            moveState = false;
                            showVoiceDialog(0);
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP: // 松开手指
                        if (recordState == RECORD_ON) {
                            recordState = RECORD_OFF;
                            if (mRecordDialog.isShowing()) {
                                mRecordDialog.dismiss();
                            }
                            try {
                                mAudioRecorder.stop();
                                mRecordThread.interrupt();
                                voiceValue = 0.0;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (!moveState) {
                                if (recodeTime < MIN_RECORD_TIME) {
                                    showWarnToast("时间太短  录音失败");
                                } else {
                                    mTvRecordTxt.setText("录音时间："
                                            + ((int) recodeTime));
                                    mTvRecordPath.setText("文件路径：" + getAmrPath());
                                }
                            }
                            moveState = false;
                        }
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPermissionsDenied(int requestCode) {
        super.onPermissionsGranted(requestCode);
        ToastUtils.show("你有点苕吧，出克不准录音");
        finish();
    }

    // 删除老文件
    void deleteOldFile() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "blackhole/voiceRecord/" + RECORD_FILENAME + ".amr");
        L.d("file.getPath---"+file.getPath());
        L.d("file.getAbsolutePath---"+file.getAbsolutePath());
        if (file.exists()) {
            file.delete();
        }
    }

    // 录音时显示Dialog
    void showVoiceDialog(int flag) {
        if (mRecordDialog == null) {
            mRecordDialog = new Dialog(RecordActivity.this, R.style.DialogStyle);
            mRecordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mRecordDialog.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mRecordDialog.setContentView(R.layout.record_dialog);
            mIvRecVolume = (ImageView) mRecordDialog.findViewById(R.id.record_dialog_img);
            mTvRecordDialogTxt = (TextView) mRecordDialog.findViewById(R.id.record_dialog_txt);
        }
        switch (flag) {
            case 1:
                mIvRecVolume.setImageResource(R.mipmap.record_cancel);
                mTvRecordDialogTxt.setText("松开手指可取消录音");
                break;

            default:
                mIvRecVolume.setImageResource(R.mipmap.record_animate_01);
                mTvRecordDialogTxt.setText("向下滑动可取消录音");
                break;
        }
        mTvRecordDialogTxt.setTextSize(14);
        mRecordDialog.show();
    }

    // 录音时间太短时Toast显示
    void showWarnToast(String toastText) {
        Toast toast = new Toast(RecordActivity.this);
        LinearLayout linearLayout = new LinearLayout(RecordActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(20, 20, 20, 20);

        // 定义一个ImageView
        ImageView imageView = new ImageView(RecordActivity.this);
        imageView.setImageResource(R.mipmap.record_voice_to_short); // 图标

        TextView mTv = new TextView(RecordActivity.this);
        mTv.setText(toastText);
        mTv.setTextSize(14);
        mTv.setTextColor(Color.WHITE);// 字体颜色

        // 将ImageView和ToastView合并到Layout中
        linearLayout.addView(imageView);
        linearLayout.addView(mTv);
        linearLayout.setGravity(Gravity.CENTER);// 内容居中
        linearLayout.setBackgroundResource(R.mipmap.record_bg);// 设置自定义toast的背景

        toast.setView(linearLayout);
        toast.setGravity(Gravity.CENTER, 0, 0);// 起点位置为中间
        toast.show();
    }

    // 获取文件手机路径
    private String getAmrPath() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "WifiChat/voiceRecord/" + RECORD_FILENAME + ".amr");
        return file.getAbsolutePath();
    }

    // 录音计时线程
    void recordTimethread() {
        mRecordThread = new Thread(recordThread);
        mRecordThread.start();
    }

    // 录音Dialog图片随声音大小切换
    void setDialogImage() {
        if (voiceValue < 600.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_01);
        } else if (voiceValue > 600.0 && voiceValue < 1000.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_02);
        } else if (voiceValue > 1000.0 && voiceValue < 1200.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_03);
        } else if (voiceValue > 1200.0 && voiceValue < 1400.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_04);
        } else if (voiceValue > 1400.0 && voiceValue < 1600.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_05);
        } else if (voiceValue > 1600.0 && voiceValue < 1800.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_06);
        } else if (voiceValue > 1800.0 && voiceValue < 2000.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_07);
        } else if (voiceValue > 2000.0 && voiceValue < 3000.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_08);
        } else if (voiceValue > 3000.0 && voiceValue < 4000.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_09);
        } else if (voiceValue > 4000.0 && voiceValue < 6000.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_10);
        } else if (voiceValue > 6000.0 && voiceValue < 8000.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_11);
        } else if (voiceValue > 8000.0 && voiceValue < 10000.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_12);
        } else if (voiceValue > 10000.0 && voiceValue < 12000.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_13);
        } else if (voiceValue > 12000.0) {
            mIvRecVolume.setImageResource(R.mipmap.record_animate_14);
        }
    }

    // 录音线程
    private Runnable recordThread = new Runnable() {

        @Override
        public void run() {
            recodeTime = 0.0f;
            while (recordState == RECORD_ON) {
                // 限制录音时长
                // if (recodeTime >= MAX_RECORD_TIME && MAX_RECORD_TIME != 0) {
                // imgHandle.sendEmptyMessage(0);
                // } else
                {
                    try {
                        Thread.sleep(150);
                        recodeTime += 0.15;
                        // 获取音量，更新dialog
                        if (!moveState) {
                            voiceValue = mAudioRecorder.getAmplitude();
                            recordHandler.sendEmptyMessage(1);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    public Handler recordHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setDialogImage();
        }
    };
}
