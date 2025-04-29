package com.transcendence.core.utils.storage;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Author: yudeshui on 16/10/20.
 * <p>
 * 存储空间的工具类
 */
public class StorageUtil {

    private static DecimalFormat fileIntegerFormat = new DecimalFormat("#0");
    private static DecimalFormat fileDecimalFormat = new DecimalFormat("#0.#");

    /**
     * SDCARD是否存在
     */
    public static boolean isExternalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SDCARD剩余存储空间
     */
    public static long getAvailableExternalStorageSize() {
        if (isExternalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        }
        return 0L;
    }

//    /**
//     * 获取SDCARD总的存储空间
//     */
//    public static long getTotalExternalStorageSize() {
//        if (isExternalMemoryAvailable()) {
//            File path = Environment.getExternalStorageDirectory();
//            StatFs stat = new StatFs(path.getPath());
//            long blockSize = stat.getBlockSize();
//            long totalBlocks = stat.getBlockCount();
//            return totalBlocks * blockSize;
//        }
//        return 0L;
//    }
//
//    /**
//     * 获取系统总内存
//     *
//     * @param context 可传入应用程序上下文。
//     * @return 总内存大单位为B。
//     */
//    public static long getTotalMemorySize(Context context) {
//        String dir = "/proc/meminfo";
//        try {
//            FileReader fr = new FileReader(dir);
//            BufferedReader br = new BufferedReader(fr, 2048);
//            String memoryLine = br.readLine();
//            String subMemoryLine = memoryLine.substring(memoryLine.indexOf("MemTotal:"));
//            br.close();
//            return Integer.parseInt(subMemoryLine.replaceAll("\\D+", "")) * 1024l;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return 0L;
//    }


    /**
     * 单位换算
     *
     * @param size      单位为B
     * @param isInteger 是否返回取整的单位
     * @return 转换后的单位
     */
    public static String formatFileSize(long size, boolean isInteger) {
        DecimalFormat df = isInteger ? fileIntegerFormat : fileDecimalFormat;
        String fileSizeString = "0M";
        if (size < 1024 && size > 0) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1024 * 1024) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1024 * 1024 * 1024) {
            fileSizeString = df.format((double) size / (1024 * 1024)) + "M";
        } else {
            fileSizeString = df.format((double) size / (1024 * 1024 * 1024)) + "G";
        }
        return fileSizeString;
    }
}
