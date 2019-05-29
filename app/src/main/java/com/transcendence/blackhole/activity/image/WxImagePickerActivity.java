package com.transcendence.blackhole.activity.image;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;

//package com.transcendence.blackhole.activity.image;
//
//import android.content.Intent;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.AdapterView;
//
//import com.lzy.imagepicker.ImagePicker;
//import com.lzy.imagepicker.bean.ImageItem;
//import com.lzy.imagepicker.ui.ImageGridActivity;
//import com.transcendence.blackhole.R;
//import com.transcendence.blackhole.activity.image.adapter.ImagePickerAdapter;
//import com.transcendence.blackhole.activity.image.dialog.SelectDialog;
//import com.transcendence.blackhole.base.activity.TitleBarActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Joephone on 2019/5/24 17:56
// * @E-Mail Address：joephonechen@gmail.com
// * @Desc
// */
//
public class WxImagePickerActivity extends TitleBarActivity {  //implements ImagePickerAdapter.OnRecyclerViewItemClickListener

    public static final int IMAGE_ITEM_ADD = -1;    //已添加的照片数量
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

//    private ImagePickerAdapter adapter;
//    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 8;               //允许选择图片最大数

    @Override
    public int getLayoutId() {
        return R.layout.activity_wx_imagepicker;
    }


    @Override
    public void init() {
        setTitle("图片选择");

//        initRv();
    }

//    private void initRv() {
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
//        selImageList = new ArrayList<>();
//        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
//        adapter.setOnItemClickListener(this);
//
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);
//    }
//
//    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
//        SelectDialog dialog = new SelectDialog(this, R.style.transparentFrameWindowStyle,
//                listener, names);
//        if (!this.isFinishing()) {
//            dialog.show();
//        }
//        return dialog;
//    }
//
//    @Override
//    public void onItemClick(View view, int position) {
//        switch (position) {
//            case IMAGE_ITEM_ADD:
//                List<String> names = new ArrayList<>();
//                names.add("拍照");
//                names.add("相册");
//                showDialog(new SelectDialog.SelectDialogListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        switch (position) {
//                            case 0: // 直接调起相机
//                                /**
//                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
//                                 *
//                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
//                                 *
//                                 * 如果实在有所需要，请直接下载源码引用。
//                                 */
//                                //打开选择,本次允许选择的数量
//                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
//                                Intent intent = new Intent(WxImagePickerActivity.this, ImageGridActivity.class);
//                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
//                                startActivityForResult(intent, REQUEST_CODE_SELECT);
//                                break;
//                            case 1:
//                                //打开选择,本次允许选择的数量
//                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
//                                Intent intent1 = new Intent(WxImagePickerActivity.this, ImageGridActivity.class);
//                                /* 如果需要进入选择的时候显示已经选中的图片，
//                                 * 详情请查看ImagePickerActivity
//                                 * */
////                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
//                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
//                                break;
//                            default:
//                                break;
//                        }
//
//                    }
//                }, names);
//
//
//                break;
//            default:
//                //打开预览
////                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
////                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
////                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
////                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
////                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
//                break;
//        }
//    }
}
