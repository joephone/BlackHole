package com.transcendence.greenstar.demo.pdf.act;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.greenstar.R;
import com.transcendence.greenstar.demo.pdf.utils.LoadFileModel;
import com.transcendence.greenstar.demo.pdf.utils.Md5Tool;
import com.transcendence.greenstar.demo.pdf.utils.SuperFileView2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * @author joephone
 * @date 2023/8/4
 * @desc
 */
public class FileDisplayActivity extends AppAc {

    private SuperFileView2 mSuperFileView;
    private String filePath;
    private String pdfUrl;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 100;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_pdf_file_display;
    }

    @Override
    protected void initView() {
        setTitle("PDF");
        mSuperFileView = findViewById(R.id.pdfView);
        mSuperFileView.setOnGetFilePathListener(new SuperFileView2.OnGetFilePathListener() {
            @Override
            public void onGetFilePath(SuperFileView2 mSuperFileView2) {
                getFilePathAndShowFile(mSuperFileView2);
            }
        });

        // 检查是否已经授予权限
        if (checkStoragePermission()) {
            // 已经有权限，执行相关操作
            performStorageOperation();
        } else {
            // 请求权限
            requestStoragePermission();
        }


    }

    private void getFilePathAndShowFile(SuperFileView2 fileView) {
        if (getFilePath().contains("http")) {//网络地址要先下载
            downLoadFromNet(getFilePath(),fileView);
        } else {
            fileView.displayFile(new File(getFilePath()));
        }
    }

    public void setFilePath(String fileUrl) {
        this.filePath = fileUrl;
    }

    private String getFilePath() {
        return filePath;
    }

    private void downLoadFromNet(final String url, final SuperFileView2 mSuperFileView2) {
        LogUtils.d("downLoadFromNet");
        File cacheFile = getCacheFile(url);
        if (cacheFile.exists()) {
            if (cacheFile.length() <= 0) {
                LogUtils.d("删除空文件！！");
                cacheFile.delete();
                return;
            }
        }

        LoadFileModel.loadPdfFile(url, new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                LogUtils.d("下载文件-->onResponse");
                InputStream is = null;
                OutputStream fos = null;
                try {
                    ResponseBody responseBody = response.body();
                    is = responseBody.byteStream();

                    // 使用 MediaStore API 保存文件到 Downloads 目录
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Downloads.DISPLAY_NAME, getFileName(url));
                    values.put(MediaStore.Downloads.MIME_TYPE, "application/pdf");
                    values.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

                    Uri uri = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                        uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
                    }
                    if (uri != null) {
                        fos = getContentResolver().openOutputStream(uri);
                        if (fos != null) {
                            byte[] buf = new byte[2048];
                            int len;
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                            }
                            fos.flush();
                            LogUtils.d("文件下载成功,准备展示文件。");
                            mSuperFileView2.displayFile(new File(getRealPathFromUri(uri)));
                        }
                    }
                } catch (Exception e) {
                    LogUtils.d("文件下载异常 = " + e.toString());
                } finally {
                    try {
                        if (is != null) is.close();
                        if (fos != null) fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                LogUtils.d("文件下载失败");
                File file = getCacheFile(url);
                if (!file.exists()) {
                    LogUtils.d("删除下载失败文件");
                    file.delete();
                }
            }
        });
    }

    // 获取文件的真实路径
    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Downloads.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Downloads.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(columnIndex);
            cursor.close();
            return path;
        }
        return null;
    }

    /***
     * 获取缓存目录
     *
     * @param url
     * @return
     */
    private File getCacheDir(String url) {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/007/");
    }
    /***
     * 绝对路径获取缓存文件
     *
     * @param url
     * @return
     */
    private File getCacheFile(String url) {
        File cacheFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/007/"
                + getFileName(url));
        LogUtils.d( "缓存文件 = " + cacheFile.toString());
        return cacheFile;
    }

    /***
     * 根据链接获取文件名（带类型的），具有唯一性
     *
     * @param url
     * @return
     */
    private String getFileName(String url) {
        String fileName = Md5Tool.hashKey(url) + "." + getFileType(url);
        return fileName;
    }

    /***
     * 获取文件类型
     *
     * @param paramString
     * @return
     */
    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            LogUtils.d("paramString---->null");
            return str;
        }
        LogUtils.d("paramString:"+paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            LogUtils.d("i <= -1");
            return str;
        }


        str = paramString.substring(i + 1);
        LogUtils.d("paramString.substring(i + 1)------>"+str);
        return str;
    }

    // 检查是否已经授予读写权限
    private boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    // 请求读写权限
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                REQUEST_CODE_STORAGE_PERMISSION
        );
    }

    // 处理权限请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 权限被授予，执行相关操作
                performStorageOperation();
            } else {
                // 权限被拒绝，提示用户
                showPermissionDeniedMessage();
            }
        }
    }

    // 执行需要权限的操作
    private void performStorageOperation() {
        // 例如：读取或写入文件
        //        Intent intent = this.getIntent();
        pdfUrl = Global.PDF.url2;// getIntent().getStringExtra(Global.PUBLIC_INTENT_KEY.PDF_URL); //(String) intent.getSerializableExtra("path");

        if (!TextUtils.isEmpty(pdfUrl)) {
            LogUtils.d("文件path:" + pdfUrl);
            setFilePath(pdfUrl);
        }
        mSuperFileView.show();
    }

    // 提示用户权限被拒绝
    private void showPermissionDeniedMessage() {
        // 可以显示一个Toast或对话框
        Toast.makeText(this, "用户权限被拒绝", Toast.LENGTH_SHORT).show();
    }
}
