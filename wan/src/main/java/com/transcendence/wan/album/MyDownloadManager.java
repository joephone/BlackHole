package com.transcendence.wan.album;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.transcendence.core.utils.L;
import com.transcendence.wan.album.act.ImageViewerActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Joephone on 2021/4/25 0025 上午 9:13
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class MyDownloadManager {
    public static final int EXTERNAL_STORAGE_REQ_CODE = 10 ;
        private static final int REQUEST_EXTERNAL_STORAGE = 1;
        private static String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE };

        /**
         * Checks if the app has permission to write to device storage
         *
         * If the app does not has permission then the user will be prompted to
         * grant permissions
         *
         * @param activity
         */
        public static void verifyStoragePermissions(Activity activity) {
            // Check if we have write permission
            int permission = ActivityCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE);
            }

        }


        private static final String TAG = "MyDownloadManager";
        private File downloadDir; // 文件保存路径
        private static MyDownloadManager instance; // 单例
        private String fileName;

        // 单线程任务队列
        public static Executor executor;
        private static final ThreadFactory sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            public Thread newThread(Runnable r) {
                return new Thread(r, "MyDownloadManager #" + mCount.getAndIncrement());
            }
        };
        private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<>(128);
        public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 1, 1,
                TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);


        public MyDownloadManager() {
            // 初始化下载路径
            downloadDir = new File("/sdcard/");
            if (!downloadDir.exists()) {
                downloadDir.mkdirs();
            }
            executor = new SerialExecutor();
        }

/**
 * 顺序执行下载任务
 */
private static class SerialExecutor implements Executor {
    final ArrayDeque<Runnable> mTasks = new ArrayDeque<Runnable>();
    Runnable mActive;

    public synchronized void execute(final Runnable r) {
        mTasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (mActive == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((mActive = mTasks.poll()) != null) {
            THREAD_POOL_EXECUTOR.execute(mActive);
        }
    }
}

    /**
     * 获取单例对象
     *
     * @return
     */
    public static MyDownloadManager getInstance() {
        if (instance == null) {
            instance = new MyDownloadManager();
        }
        return instance;
    }

    /**
     * 添加下载任务
     *
     * @param path
     */
    public void addDownloadTask(final String path) {
        L.d("进入run00");
        executor.execute(new Runnable() {


            @Override
            public void run() {
                L.d("进入run");
                download(path);

            }
        });
    }

    /**
     * 下载文件
     *
     * @param path
     */
    private void download(String path)  {
        String fileName = ImageViewerActivity.MD5(path);

        File savePath = new File(downloadDir, fileName); // 下载文件路径
        //File finallyPath = new File(downloadDir, fileName + ".png"); // 下载完成后加入
        if (savePath.exists()) { // 文件存在则已下载
            Log.i(TAG, "file is existed");
            return;
        }
        // 如果是Wifi则开始下载
        if (savePath.exists() && savePath.delete()) { // 如果之前存在文件，证明没有下载完成，删掉重新创建
            savePath = new File(downloadDir, fileName);
            Log.i(TAG, "download 111111");
        }
        Log.i(TAG, "download start");
        try {
            byte[] bs = new byte[1024];
            int len;
            URL url = new URL(path);
            InputStream is = url.openStream();

            OutputStream os = new FileOutputStream(savePath,true);
            Log.i(TAG, "download 22222");


            while ((len = is.read(bs)) != -1) {

                os.write(bs, 0, len);
            }
            is.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //if (savePath.renameTo(finallyPath)) { // 下载完成后重命名为
        // Log.i(TAG, "download end");

        //  }


    }

    /**
     * 添加删除任务
     *
     * @param path
     */
    public void addDeleteTask(final String path) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                delete(path);
            }
        });
    }

    /**
     * 删除本地文件
     *
     * @param path
     */
    private void delete(String path) {
        String fileName = ImageViewerActivity.MD5(path);
        File savePath = new File(downloadDir, fileName + ".png");
        Log.i(TAG, savePath.getPath());
        if (savePath.exists()) {
            if (savePath.delete()) {
                Log.i(TAG, "file is deleted");
            }
        }
    }

    /**
     * 返回下载路径
     *
     * @return
     */
    public File getDownloadDir() {
        return downloadDir;
    }
    public String getFileName(String path){
        String f =ImageViewerActivity.MD5(path);
        return f;
    }
}
