package com.transcendence.greenstar.demo.luckypanel;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.widget.Button;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.greenstar.R;
import com.transcendence.greenstar.demo.luckypanel.view.LuckyMonkeyPanelView;

import java.util.HashMap;
import java.util.Random;

public class LuckyPanelAc extends AppAc {

    private MediaPlayer mPlayer;
    private SoundPool mSound;
    private HashMap<Integer, Integer> soundPoolMap;

    private MediaPlayer mPlayer2;
    private SoundPool mSound2;
    private HashMap<Integer, Integer> soundPoolMap2;

    private boolean isSound;

    private LuckyMonkeyPanelView lucky_panel;
    private Button btn_action;
    private int randomNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_luckypanel;
    }

    @Override
    protected void initView() {
        lucky_panel = (LuckyMonkeyPanelView) findViewById(R.id.lucky_panel);
        btn_action = (Button) findViewById(R.id.btn_action);
        initSounds();
        initSounds2();

        btn_action.setOnClickListener(v -> {
            if (!lucky_panel.isGameRunning()) {
                randomNum = new Random().nextInt(3000);
                lucky_panel.startGame();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isSound = true;
                        tryToStopPanel();
                    }
                },randomNum);
            } else {
                tryToStopPanel();
            }
        });
    }

    private void tryToStopPanel() {
        if(isSound){
//            playSound(1,0,1);
            openRawMusic();
        }
        int stayIndex = new Random().nextInt(8);
        LogUtils.d("====stay===" + stayIndex);
        lucky_panel.tryToStop(stayIndex);
        stopSound2();
    }

    /**
     * 初始化声音
     */
    private void initSounds() {
        // 设置播放音效
        mPlayer = MediaPlayer.create(this, R.raw.money_in);
        // 第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        mSound = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<>();
        soundPoolMap.put(1, mSound.load(this, R.raw.money_in, 1));
        //可以在后面继续put音效文件
    }

    /**
     * 初始化声音
     */
    private void initSounds2() {
        // 设置播放音效
        mPlayer2 = MediaPlayer.create(this, R.raw.nine_grid_lottery);
        // 第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        mSound2 = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap2 = new HashMap<>();
        soundPoolMap2.put(1, mSound2.load(this, R.raw.nine_grid_lottery, 1));
        //可以在后面继续put音效文件
    }

    /**
     * soundPool播放
     *
     * @param sound
     *            播放第一个
     * @param loop
     *            是否循环
     */
    private void playSound(int sound, int loop,int index) {
        AudioManager mgr = (AudioManager) this
                .getSystemService(Context.AUDIO_SERVICE);
        // 获取系统声音的当前音量
        float currentVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        // 获取系统声音的最大音量
        float maxVolume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 获取当前音量的百分比
        float volume = currentVolume / maxVolume;

        // 第一个参数是声效ID,第二个是左声道音量，第三个是右声道音量，第四个是流的优先级，最低为0，第五个是是否循环播放，第六个播放速度(1.0 =正常播放,范围0.5 - 2.0)
        if(index == 1){
            mSound.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
        } else {
            mSound2.play(soundPoolMap2.get(sound), volume, volume, 1, loop, 1f);
        }

    }

    private void stopSound2() {
        mSound2.stop(0);
    }

    /*** 打开raw目录下的音乐mp3文件*/
    private void openRawMusic() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.nine_grid_lottery);
        if(mediaPlayer!=null){
            mediaPlayer.start();
        }
    }
}
