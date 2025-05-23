package com.transcendence.greenstar.demo.dbnote;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.greenstar.R;
import com.transcendence.greenstar.demo.dbnote.act.DBNoteActivity;
import com.transcendence.greenstar.demo.dbnote.bean.Note;
import com.transcendence.greenstar.demo.dbnote.resp.NotesViewModel;

/**
 * @author joephone
 * @date 2025/5/8 2:41
 * @description
 * @edition 1.0
 */
public class NoteDialogManager {
    private final Context mContext;
    private final NotesViewModel viewModel;

    public NoteDialogManager(Context context, NotesViewModel viewModel) {
        this.mContext = context;
        this.viewModel = viewModel;
    }

    public void showNoteDialog(boolean shouldUpdate, Note note, int position) {
        LogUtils.d("showNoteDialog");
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_demo_db_note_dialog, null);
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilderUserInput = new androidx.appcompat.app.AlertDialog.Builder(mContext);
        alertDialogBuilderUserInput.setView(view);

        EditText etTitle = view.findViewById(R.id.et_title);
        EditText etContent = view.findViewById(R.id.et_content);

        // 设置对话框内容和按钮

        alertDialogBuilderUserInput.setPositiveButton(shouldUpdate ? "Update" : "Save", (dialog, which) -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();

            if (shouldUpdate) {
                note.setTitle(title);
                note.setContent(content);
                viewModel.updateNote(note);
            } else {
                viewModel.addNote(title, content);
            }
        });


        final androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();
    }

//    private void showNoteDialog(final boolean shouldUpdate, final Note note, final int position) {
//        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
//        View view = layoutInflaterAndroid.inflate(R.layout.activity_demo_db_note_dialog, null);
//
//        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilderUserInput = new androidx.appcompat.app.AlertDialog.Builder(DBNoteActivity.this);
//        alertDialogBuilderUserInput.setView(view);
//
//        final EditText inputNote = view.findViewById(R.id.note);
//        TextView dialogTitle = view.findViewById(R.id.dialog_title);
//        dialogTitle.setText(!shouldUpdate ? "New Note" : "Edit Note");
//
//        if (shouldUpdate && note != null) {
//            inputNote.setText(note.getNote());
//        }
//        alertDialogBuilderUserInput
//                .setCancelable(false)
//                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialogBox, int id) {
//
//                    }
//                })
//                .setNegativeButton("cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialogBox, int id) {
//                                dialogBox.cancel();
//                            }
//                        });
//
//        final androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilderUserInput.create();
//        alertDialog.show();
//
//        alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Show toast message when no text is entered
//                if (TextUtils.isEmpty(inputNote.getText().toString())) {
//                    Toast.makeText(DBNoteActivity.this, "Enter note!", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//                    alertDialog.dismiss();
//                }
//
//                // check if user updating note
//                if (shouldUpdate && note != null) {
//                    // update note by it's id
//                    updateNote(inputNote.getText().toString(), position);
//                } else {
//                    // create new note
//                    createNote(inputNote.getText().toString());
//                }
//            }
//        });
//    }
}