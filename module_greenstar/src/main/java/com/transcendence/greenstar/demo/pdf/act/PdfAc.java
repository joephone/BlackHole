package com.transcendence.greenstar.demo.pdf.act;

import android.net.Uri;

import com.github.barteksc.pdfviewer.PDFView;
import com.transcendence.core.base.activity.AppAc;
import com.transcendence.greenstar.R;

/**
 * @author joephone
 * @date 2023/8/4
 * @desc
 */
public class PdfAc extends AppAc {

    PDFView mPdfView;
    String pdfFileName;
    Uri uri;
    Integer pageNumber = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_pdf;
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initView() {

    }
}
