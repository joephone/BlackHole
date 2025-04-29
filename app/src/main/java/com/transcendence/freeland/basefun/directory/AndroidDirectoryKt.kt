package com.transcendence.freeland.basefun.directory

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.TextView
import com.transcendence.core.base.activity.AppAc
import com.transcendence.core.utils.log.LogUtils
import com.transcendence.freeland.R
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import android.content.ActivityNotFoundException
import com.transcendence.core.global.Global
import com.transcendence.core.permission.PermissionHelper


/**
 * @author joephone
 * @date 2023/6/20
 * @desc
 */
class AndroidDirectoryKt : AppAc(){

    var mContextCacheDirTv: TextView? = null
    var mContextFileDirTv:TextView? =null
    var mContextExternalFileDirTv:TextView? =null
    var mContextExternalCacheDirTv:TextView? =null
    var mPermissionHelper:PermissionHelper? =null

    /**
     * 获取布局 ID
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_basefun_directory
    }

    /**
     * 初始化数据
     */
    override fun initView() {
        mPermissionHelper = PermissionHelper(mActivity)
        mContextCacheDirTv = findViewById(R.id.tv_context_getcachedir)
        mContextFileDirTv = findViewById(R.id.tv_context_getfilesdir)
        mContextExternalFileDirTv = findViewById(R.id.tv_context_geternalfilesdir)
        mContextExternalCacheDirTv = findViewById(R.id.tv_context_geternalcachedir)
        initData(mActivity)
        //context.getExternalFilesDir
    }

    private fun initData(context: Context) {
        mContextCacheDirTv?.text =  "该方法的得到的路径是"+context.getCacheDir()
        mContextFileDirTv?.text =  "该方法的得到的路径是"+context.getFilesDir()
        mContextExternalFileDirTv?.text =  "该方法的得到的路径是"+context.getExternalFilesDir(
            Global.FILE_TYPE)
        mContextExternalCacheDirTv?.text =  "该方法的得到的路径是"+context.getExternalCacheDir()
        mContextCacheDirTv?.setOnClickListener {
            LogUtils.d("mContextCacheDirTv?.setOnClickListener")
            saveLogToFile(context.getCacheDir())
        }
        mContextFileDirTv?.setOnClickListener {
            LogUtils.d("mContextFileDirTv?.setOnClickListener")
            saveLogToFile(context.getFilesDir())
        }
        mContextExternalFileDirTv?.setOnClickListener {
            LogUtils.d("mContextExternalFileDirTv?.setOnClickListener")
            context.getExternalFilesDir(Global.FILE_TYPE)?.let {
                    it1 -> saveLogToFile(it1)
            }
        }
        mContextExternalCacheDirTv?.setOnClickListener {
            LogUtils.d("mContextExternalCacheDirTv?.setOnClickListener")
            context.getExternalCacheDir()?.let {
                    it1 -> saveLogToFile(it1)
                LogUtils.d("it1----"+it1)
            }
//            saveLogToFile(context.getCacheDir())
        }
    }


    /**
     * 保存日志到本地
     */
    @Throws(IOException::class)
    fun saveLogToFile(directory: File): File? {
        LogUtils.d("saveLogToFile")
        if (!directory!!.isDirectory) {
            directory.delete()
        }
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val file = File(
            directory, SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(
                Date()
            ) + ".txt"
        )
        if (!file.isFile) {
            LogUtils.d("file.delete")
            file.delete()
        }
        if (!file.exists()) {
            LogUtils.d("file.createNewFile")
            file.createNewFile()
        }
        showMsg("创建成功")
        val writer = BufferedWriter(
            OutputStreamWriter(
                FileOutputStream(file, false)
            )
        )
//        for (info in data) {
            writer.write("test".replace("\n", "\r\n").toString() + "\r\n\r\n")
//        }
        writer.flush()
        try {
            writer.close()
        } catch (ignored: IOException) {
        }

        //调用系统文件管理器打开指定路径目录
//        var intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.setDataAndType(Uri.fromFile(file.getParentFile()), "file/*.txt")
//        intent.addCategory(Intent.CATEGORY_OPENABLE)
//        startActivity(intent)
        val directoryPath = file.parentFile.absolutePath // 指定路径目录
////        LogUtils.d("directoryPath---"+directoryPath)
////        val intent = Intent(Intent.ACTION_GET_CONTENT)
////        val uri = Uri.parse(directoryPath)
////        intent.setDataAndType(uri, "resource/folder")
////        startActivity(intent)
//        openAssignFolder(directoryPath)

        return file
    }


    private fun openAssignFolder(path: String) {
        mPermissionHelper?.requestStoragePermissions {
            val file = File(path)
            if (null == file || !file.exists()) {
                LogUtils.d("目录为空，应该return")
            }
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setDataAndType(Uri.fromFile(file), "file/*")
            try {
                startActivity(intent)
                //            startActivity(Intent.createChooser(intent,"选择浏览工具"));
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }

    }
}

/**

Intent intent = new Intent(Intent.ACTION_VIEW);
Uri uri = Uri.fromFile(file);
intent.addCategory(Intent.CATEGORY_DEFAULT);

打开图片文件

intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri, "image/*");

打开PDF文件

intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri, "application/pdf");

打开文本文件

intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri, "text/plain");

打开音频文件

intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
intent.putExtra("oneshot", 0);
intent.putExtra("configchange", 0);
intent.setDataAndType(uri, "audio/*");

打开视频文件

intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
intent.putExtra("oneshot", 0);
intent.putExtra("configchange", 0);
intent.setDataAndType(uri, "video/*");

打开CHM文件

intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri, "application/x-chm");

打开apk文件

intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri, "application/vnd.android.package-archive");

打开PPT文件

intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri, "application/vnd.ms-powerpoint");

打开Excel文件

intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri, "application/vnd.ms-excel");

打开Word文件

intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri, "application/msword");
 */