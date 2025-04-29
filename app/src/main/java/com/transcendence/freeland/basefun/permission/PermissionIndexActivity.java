package com.transcendence.freeland.basefun.permission;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.core.content.FileProvider;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.global.Global;
import com.transcendence.core.image.BigImageKt;
import com.transcendence.core.permission.PermissionHelper;
import com.transcendence.core.utils.StringUtils;
import com.transcendence.core.utils.files.AppFolderHelper;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.greenstar.R;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Joephone on 2023/6/10 12:05
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  权限列表序列
 * @Edition 1.0
 * @EditionHistory
 */

public class PermissionIndexActivity extends AppAc implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    PermissionHelper mPermissionHelper;

    private int REQUEST_CODE = 1234;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    protected void initView() {
        setTitle("权请序列");
        lvIndex = findViewById(R.id.lvIndex);

        List<String> items = StringUtils.getStringListAndIndex(mActivity, R.array.permission_index_item);
        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);

        mPermissionHelper = new PermissionHelper(mActivity);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                mPermissionHelper.requestStoragePermissions(new PermissionHelper.PermissionCallback() {
                    @Override
                    public void onPermissionComplete(Boolean isGranted) {
                        if(isGranted){
                            showMsg("恭喜你");
                        } else {
                            showMsg("请打开存储权限");
                        }
                    }
                });
                break;
            case 1:
                mPermissionHelper.requestLocationPermissions(new PermissionHelper.PermissionCallback() {
                    @Override
                    public void onPermissionComplete(Boolean isGranted) {
                        if(isGranted){
                            showMsg("恭喜你");
                        } else {
                            showMsg("请打开定位权限");
                        }
                    }
                });
                break;
            case 2:
                mPermissionHelper.requestCameraPermissions(new PermissionHelper.PermissionCallback() {
                    @Override
                    public void onPermissionComplete(Boolean isGranted) {
                        if(isGranted){
                            takePhoto();
                        } else {
                            showMsg("请打开相机权限");
                        }
                    }
                });
                break;
            case 3:
                mPermissionHelper.requestDialCallPermissions(new PermissionHelper.PermissionCallback() {
                    @Override
                    public void onPermissionComplete(Boolean isGranted) {
                        if(isGranted){
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + 123456789));
                            startActivity(intent);
                        } else {
                            showMsg("请打开电话权限");
                        }
                    }
                });
                break;
        }
    }

    private Uri imageUri;
    /**
     * 调用相机
     */
    private void takePhoto() {
        LogUtils.d("takePhoto");
        // 指定拍照存储位置的方式调起相机
        String filePath = AppFolderHelper.getInnerImagesDirectory() .getPath() + File.separator;
        String fileName = "IMG_" + DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";

        String packageName = getApplicationContext().getPackageName();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //通过FileProvider创建一个content类型的Uri
            imageUri = FileProvider.getUriForFile(this, packageName + ".fileprovider", new File(filePath + fileName));
        } else {
            imageUri =  Uri.fromFile(new File(filePath + fileName));
        }

//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        startActivityForResult(intent, REQUEST_CODE);


        // 选择图片（不包括相机拍照）,则不用成功后发刷新图库的广播
//        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//        i.addCategory(Intent.CATEGORY_OPENABLE);
//        i.setType("image/*");
//        startActivityForResult(Intent.createChooser(i, "Image Chooser"), REQUEST_CODE);

        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        Intent Photo = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        Intent chooserIntent = Intent.createChooser(Photo, "Image Chooser");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Parcelable[]{captureIntent});
        startActivityForResult(chooserIntent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            // 经过上边(1)、(2)两个赋值操作，此处即可根据其值是否为空来决定采用哪种处理方法
//            if (mUploadCallbackBelow != null) {
//                chooseBelow(resultCode, data);
//            } else if (mUploadCallbackAboveL != null) {
//                chooseAbove(resultCode, data);
//            } else {
//                Toast.makeText(this, "发生错误", Toast.LENGTH_SHORT).show();
//            }
//            iv.setImageURI(imageUri);
                startActivity(new Intent(mActivity, BigImageKt.class).putExtra(Global.PUBLIC_INTENT_KEY.BIG_IMAGE,imageUri.toString()));
        }

    }
}
