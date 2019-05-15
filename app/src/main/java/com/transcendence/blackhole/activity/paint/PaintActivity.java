package com.transcendence.blackhole.activity.paint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.lht.paintview.PaintView;
import com.lht.paintview.pojo.DrawShape;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.activity.paint.utils.PaintViewImageUtil;

import java.util.ArrayList;

public class PaintActivity extends AppCompatActivity
        implements View.OnClickListener, PaintView.OnDrawListener {

    final static String BITMAP_URI = "bitmap_uri";

    final static int WIDTH_WRITE = 2, WIDTH_PAINT = 40;

    PaintView mPaintView;

    ImageButton mBtnColor, mBtnStroke, mBtnUndo;
    boolean bRedOrBlue = true, bWriteOrPaint = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        mPaintView = (PaintView)findViewById(R.id.view_paint);
        mPaintView.setColorFromRes(R.color.paint_color_red);
        mPaintView.setTextColorFromRes(R.color.paint_color_red);
        mPaintView.setBgColor(Color.WHITE);
        mPaintView.setStrokeWidth(WIDTH_WRITE);
        mPaintView.setOnDrawListener(this);

        Uri uri = getIntent().getParcelableExtra(BITMAP_URI);
        Bitmap bitmap = PaintViewImageUtil.getBitmapByUri(this, uri);
        if (bitmap != null) {
            mPaintView.setBitmap(bitmap);
        }

        mBtnColor = (ImageButton)findViewById(R.id.btn_color);
        mBtnColor.setOnClickListener(this);
        mBtnStroke = (ImageButton)findViewById(R.id.btn_stroke);
        mBtnStroke.setOnClickListener(this);
        mBtnUndo = (ImageButton)findViewById(R.id.btn_undo);
        mBtnUndo.setEnabled(false);
        mBtnUndo.setOnClickListener(this);
    }

    public static void start(Context context, Bitmap bitmap) {
        Intent intent = new Intent();
        intent.setClass(context, PaintActivity.class);
        intent.putExtra(BITMAP_URI, PaintViewImageUtil.saveShareImage(context, bitmap));
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_color:
                colorChanged();
                break;
            case R.id.btn_stroke:
                strokeChanged();
                break;
            case R.id.btn_undo:
                mPaintView.undo();
                break;
        }
    }

    private void colorChanged() {
        bRedOrBlue = !bRedOrBlue;
        if (bRedOrBlue) {
            mPaintView.setColorFromRes(R.color.paint_color_red);
            mPaintView.setTextColorFromRes(R.color.paint_color_red);
            mBtnColor.setImageResource(R.mipmap.paintview_ic_red);
        }
        else {
            mPaintView.setColorFromRes(R.color.paint_color_blue);
            mPaintView.setTextColorFromRes(R.color.paint_color_blue);
            mBtnColor.setImageResource(R.mipmap.paintview_ic_blue);
        }
    }

    private void strokeChanged() {
        bWriteOrPaint = !bWriteOrPaint;
        if (bWriteOrPaint) {
            mPaintView.setStrokeWidth(WIDTH_WRITE);
            mBtnStroke.setImageResource(R.mipmap.paintview_ic_write);
        }
        else {
            mPaintView.setStrokeWidth(WIDTH_PAINT);
            mBtnStroke.setImageResource(R.mipmap.paintview_ic_paint);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
//                shareSingleImage(
//                        PaintViewImageUtil.saveShareImage(this, mPaintView.getBitmap(true)));
                break;
        }
        return true;
    }

    private void shareSingleImage(Uri imageUri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/*");
        startActivity(
                Intent.createChooser(shareIntent, getResources().getString(R.string.title_share)));

    }

    @Override
    public void afterPaintInit(int viewWidth, int viewHeight) {
//        mPaintView.setTextColor(Color.BLACK);
//        mPaintView.setTextSize(36);
//        mPaintView.addText("图表标题", -1, viewHeight - 50, PaintView.TextGravity.CENTER_HORIZONTAL);
    }

    @Override
    public void afterEachPaint(ArrayList<DrawShape> drawShapes) {
        setUndoEnable(drawShapes);
    }

    private void setUndoEnable(ArrayList<DrawShape> drawShapes) {
        if (drawShapes.size() == 0) {
            mBtnUndo.setEnabled(false);
        }
        else {
            mBtnUndo.setEnabled(true);
        }
    }
}
