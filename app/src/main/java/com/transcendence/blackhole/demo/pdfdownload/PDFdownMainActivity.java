package com.transcendence.blackhole.demo.pdfdownload;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.transcendence.blackhole.R;
import com.transcendence.core.global.Global;

public class PdfDownMainActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    PermissionUtils permissionUtils;

    TextInputLayout urlTextInputLayout;
    EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_download_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("PDF");

        permissionUtils = new PermissionUtils();

        urlTextInputLayout = (TextInputLayout) findViewById(R.id.urlTextInputLayout);
        urlEditText = (EditText) findViewById(R.id.urlEditText);
        urlEditText.setText(Global.PDF.url);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (permissionUtils.checkPermission(PdfDownMainActivity.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                    if (urlEditText.getText().toString().length() > 0) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlEditText.getText().toString())));
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Snackbar.make(urlTextInputLayout, "Permission Granted, Now you can write storage.", Snackbar.LENGTH_LONG).show();
                } else {
                    //Snackbar.make(urlTextInputLayout, "Permission Denied, You cannot access storage.", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
}
