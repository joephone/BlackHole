package com.transcendence.greenstar.demo.dbnote.act;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.greenstar.R;
import com.transcendence.greenstar.demo.dbnote.EmptyStateManager;
import com.transcendence.greenstar.demo.dbnote.NoteDialogManager;
import com.transcendence.greenstar.demo.dbnote.adapter.NotesAdapter;
import com.transcendence.greenstar.demo.dbnote.bean.Note;
import com.transcendence.greenstar.demo.dbnote.db.DatabaseHelper;
import com.transcendence.greenstar.demo.dbnote.listener.MyDividerItemDecoration;
import com.transcendence.greenstar.demo.dbnote.listener.RecyclerTouchListener;
import com.transcendence.greenstar.demo.dbnote.resp.NotesRepository;
import com.transcendence.greenstar.demo.dbnote.resp.NotesViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author joephone
 * @date 2023/5/8
 * @desc
 * @edition  1.1  加入Repository ViewModel  NoteDialogManager
 */
public class DBNoteActivity extends AppAc {
    private NotesAdapter mAdapter;
    private LinearLayout container;
    private RecyclerView recyclerView;
    private TextView noNotesView;

    private NotesRepository repository;
    private NotesViewModel mViewModel;
    private EmptyStateManager emptyStateManager;
    private NoteDialogManager dialogManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_db_note;
    }

    @Override
    protected void initView() {
        initViews();
        initDatabase();
        observeData();
    }

    private void initViews() {
        setTitle("DB Note拆分版");
        container = findViewById(R.id.ll_container);
        recyclerView = findViewById(R.id.rv);
        noNotesView = findViewById(R.id.empty_notes_view);
        setRightImage(R.drawable.ic_add_white_24dp);
    }

    private void initDatabase() {
        repository = new NotesRepository(this);
        mViewModel = new NotesViewModel();
        mViewModel.init(repository);

        emptyStateManager = new EmptyStateManager(noNotesView, recyclerView);
        dialogManager = new NoteDialogManager(this, mViewModel);

        mAdapter = new NotesAdapter(this, mViewModel.notesLiveData.getValue());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {}

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));

        mIvRight.setOnClickListener(v -> dialogManager.showNoteDialog(false, null, -1));
    }


    private void observeData() {
        mViewModel.notesLiveData.observe(this, notes -> {
            LogUtils.d("LiveData更新，数据量: " + notes.size());
//            mAdapter.notifyDataSetChanged();
            if (mAdapter == null) {
                // 首次初始化 Adapter
                mAdapter = new NotesAdapter(this, notes);
                recyclerView.setAdapter(mAdapter);
            } else {
                // 后续更新数据
                mAdapter.updateData(notes);
            }
            emptyStateManager.toggleEmptyState(notes.isEmpty());
        });
    }

    private void showActionsDialog(int position) {
        LogUtils.d("showActionsDialog");
        new AlertDialog.Builder(this)
                .setTitle("Choose option")
                .setItems(new CharSequence[]{"Edit", "Delete"}, (dialog, which) -> {
                    if (which == 0) {
                        dialogManager.showNoteDialog(true, mViewModel.notesLiveData.getValue().get(position), position);
                    } else {
                        mViewModel.deleteNote(mViewModel.notesLiveData.getValue().get(position));
                    }
                })
                .show();
    }

}
