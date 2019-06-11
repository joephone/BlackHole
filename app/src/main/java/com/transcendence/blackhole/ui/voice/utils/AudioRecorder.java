package com.transcendence.blackhole.ui.voice.utils;

import android.media.MediaRecorder;

import com.transcendence.blackhole.global.Global;
import com.transcendence.blackhole.utils.L;

import java.io.File;
import java.io.IOException;

/**
 * @author Joephone
 */
public class AudioRecorder {
    /**
     * 采样率
     */
    private static int SAMPLE_RATE_IN_HZ = 8000;

    private MediaRecorder mMediaRecorder;
    private String mPath;

    public AudioRecorder(String path) {
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        }
        this.mPath = sanitizePath(path);
    }

    private String sanitizePath(String path) {
        L.d("amr path---"+Global.ABSOLUTE_PATH
                + "/voiceRecord/" + path + ".amr");
        return Global.ABSOLUTE_PATH
                + "/voiceRecord/" + path + ".amr";
    }

    public void start() throws IOException {
        String state = android.os.Environment.getExternalStorageState();
        if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
            throw new IOException("SD Card is not mounted,It is  " + state
                    + ".");
        }
        File directory = new File(mPath).getParentFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Path to file could not be created");
        }
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        // recorder.setAudioChannels(AudioFormat.CHANNEL_CONFIGURATION_MONO);
        mMediaRecorder.setAudioSamplingRate(SAMPLE_RATE_IN_HZ);
        mMediaRecorder.setOutputFile(mPath);
        mMediaRecorder.prepare();
        mMediaRecorder.start();

    }

    public void stop() throws IOException {
//        if(mMediaRecorder.isPlaying()){
            mMediaRecorder.stop();
            mMediaRecorder.release();
//        }
    }

    public double getAmplitude() {
        if (mMediaRecorder != null) {
            return (mMediaRecorder.getMaxAmplitude());
        } else {
            return 0;
        }
    }
}