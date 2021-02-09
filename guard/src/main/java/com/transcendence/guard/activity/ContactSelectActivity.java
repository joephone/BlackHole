package com.transcendence.guard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.transcendence.guard.R;
import com.transcendence.guard.adapter.ContactAdapter;
import com.transcendence.guard.domian.ContactInfo;
import com.transcendence.guard.utils.ContactInfoParser;

import java.util.List;

/**
 * @Author Joephone on 2021/2/9 0009 下午 4:07
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class ContactSelectActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView mLv;
    private ContactAdapter mAdapter;
    private List<ContactInfo> systemContacts;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 10:
                    if(systemContacts!=null){
                        mAdapter = new ContactAdapter(systemContacts,ContactSelectActivity.this);
                        mLv.setAdapter(mAdapter);
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_select);

        init();
    }

    private void init() {
        mLv = findViewById(R.id.lv_contact);
        new Thread(new Runnable() {
            @Override
            public void run() {
                systemContacts = ContactInfoParser.getSystemContact(ContactSelectActivity.this);
                systemContacts.addAll(ContactInfoParser.getSystemContact(ContactSelectActivity.this));
                handler.sendEmptyMessage(10);
            }
        }).start();

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactInfo item = (ContactInfo) mAdapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("phone",item.getPhone());
                setResult(0,intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 10:

                break;
        }

    }
}
