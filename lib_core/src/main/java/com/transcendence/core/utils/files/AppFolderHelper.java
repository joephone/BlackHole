package com.transcendence.core.utils.files;

import android.os.Environment;


import com.transcendence.core.base.app.CoreApp;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.core.utils.storage.StorageUtil;

import java.io.File;

/**
 * <p>
 * App文件夹工具类
 */
public class AppFolderHelper {

    private static final String APP_FOLDER = "dffs";
    private static final String CRASH_DIR = "crash";
    private static final String REACT_NATIVE_DIR = "react_native";
    private static final String TRACE_DIR = "trace";
    public static final String HYBRID_DIR = "hybrid";
    public static final String REACT_DIR = "react";
    private static final String IMAGE_DIR = "images";
    private static final String PIC_DIR = "pics";
    private static final String DOWNLOAD_DIR = "download";
    private static final String SIGNATURE_DIR = "signature_draw";


    private static File appExternalDirectory = new File(Environment.getExternalStorageDirectory(), APP_FOLDER);
    private static File appFileDirectory = CoreApp.getInstance().getFilesDir();
    private static File appCacheDirectory = CoreApp.getInstance().getCacheDir();
    private static File hybridDirectory = new File(appFileDirectory, HYBRID_DIR);
    private static File reactDirectory = new File(appFileDirectory, REACT_DIR);
    private static File innerImagesDirectory = new File(appCacheDirectory, PIC_DIR);
    private static File crashDirectory = new File(appCacheDirectory, CRASH_DIR);
    private static File reactNativeDirectory = new File(appFileDirectory, REACT_NATIVE_DIR);
    private static File traceDirectory = new File(appFileDirectory, TRACE_DIR);
    private static File imageDirectory = new File(appFileDirectory, IMAGE_DIR);
    private static File downloadDirectory = new File(appFileDirectory, DOWNLOAD_DIR);
    private static File signatureDirectory = new File(appFileDirectory, SIGNATURE_DIR);


    static {
        createAppFiles();
    }

    private AppFolderHelper() {

    }

    public static File getAppExternalDirectory() {
        return appExternalDirectory;
    }

    public static String getAppFileDirectory() {
        return appFileDirectory.getAbsolutePath();
    }

    public static String getReactBundleDirectory() {
        return reactDirectory.getAbsolutePath();
    }

//    public static String getDownloadDirectory() {
//        return downloadDirectory.getAbsolutePath();
//    }

    public static File getCrashDirectory() {
        return crashDirectory;
    }

    public static File getSignatureDirectory() {
        return signatureDirectory;
    }

    public static File getImageDirectory() {
        return imageDirectory;
    }

    public static File getInnerImagesDirectory() {
        return innerImagesDirectory;
    }

    public static File getDownloadDirectory() {
        return downloadDirectory;
    }

    public static String getReactNativeDirectory() {
        return reactNativeDirectory.getAbsolutePath();
    }

    public static File getHybridDirectory() {
        return hybridDirectory;
    }

    public static File getTraceDirectory() {
        return traceDirectory;
    }


    /**
     * 创建指定文件目录
     *
     * @param directory file directory
     */
    private static void createFolders(File directory) {
        if (directory != null) {
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    LogUtils.d(directory.getAbsolutePath() + " 创建成功");
                }
            }
        }
    }

    /**
     * 创建相关模块的文件夹
     */
    private static void createAppFiles() {
        new Thread((new Runnable() {
            @Override
            public void run() {
                createFolders(hybridDirectory);
                if (StorageUtil.isExternalMemoryAvailable()) {
                    createFolders(appExternalDirectory);
                    createFolders(crashDirectory);
                    createFolders(traceDirectory);
                    createFolders(imageDirectory);
                    createFolders(reactNativeDirectory);
                    createFolders(downloadDirectory);
                    createFolders(reactDirectory);
                    createFolders(signatureDirectory);
                    createFolders(innerImagesDirectory);
                } else {
                    LogUtils.e("请检查SD卡");
                }
            }
        })).start();
    }

}
