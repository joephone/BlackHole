package com.transcendence.logcat.manager;

import android.app.Activity;

import com.transcendence.core.global.Global;
import com.transcendence.core.utils.DateUtils;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.core.utils.sp.SPUtils;
import com.transcendence.logcat.Logcat;
import com.transcendence.logcat.SharedPrefHelper;

import java.io.File;

/**
 * @author joephone
 * @date 2023/6/26
 * @desc 全日志管理
 */
public class LogManager {

    private SharedPrefHelper mSharedPrefHelper;

    private static LogManager mInstance = null;

    private LogManager() {
    }

    public static LogManager getInstance() {
        synchronized (LogManager.class) {
            if(null == mInstance) {
                mInstance = new LogManager();
            }
            return mInstance;
        }
    }

    /**
     * 启用Logcat，启用后将会有一个悬浮按钮入口，强烈建议在Activity的onResume中调用
     * @param activity
     */
    public void startInit(Activity activity) {
        initLog(activity);
    }

    private void initLog(final Activity activity) {

        long lastLaunchTime = SPUtils.getInstance().getLong(Global.SP_KEY.LAUNCH_TIME);
        long currentTime = System.currentTimeMillis();
        long days = DateUtils.day(currentTime - lastLaunchTime);
        LogUtils.d("lastLaunchTime--"+DateUtils.getInstance().formatLong2Date(lastLaunchTime));
        LogUtils.d("上次启动天数"+days);
        SPUtils.getInstance().save(Global.SP_KEY.LAUNCH_TIME,currentTime);

        File traverseFile = new File(Global.DIRECTORY.FULL_LOG);
        traverseFolder(traverseFile);

        mSharedPrefHelper = SharedPrefHelper.newInstance(activity, Logcat.SP_LOGCAT_CONFIG);
        mSharedPrefHelper.saveBoolean("logcat_enabled", true);
        Logcat.enableLogcat(activity);
    }

    //遍历FullLog文件
    private void traverseFolder(File folder) {
        LogUtils.d("traverseFolder");
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // 递归遍历子文件夹
                        traverseFolder(file);
                    } else {
                        // 对文件进行操作，例如输出文件名
                        LogUtils.d("对文件进行操作，输出文件名"+file.getName());
                        // 可以在这里进行其他操作，如判断文件是否超过三天并删除等
                        long createTime = file.lastModified();
                        long currentTime = System.currentTimeMillis();

                        long timeDifference = currentTime - createTime;
                        long daysDifference = timeDifference / (1000 * 60 * 60 * 24);

                        if (daysDifference >= Global.FULL_LOG_MAX_KEEP_DAYS) {
                            // 文件创建时间超过三天，进行文件删除操作
                            boolean isDeleted = file.delete();
                            if (isDeleted) {
                                // 执行删除操作成功
                                LogUtils.d(file.getName()+"删除成功");
                            } else {
                                // 执行删除操作失败
                                LogUtils.d(file.getAbsolutePath()+"删除失败");
                            }
                        } else {
                            LogUtils.d("无有需要删除的日志");
                        }
                    }
                }
            }
        } else {
            LogUtils.d("!folder.isDirectory()");
        }
    }

}
