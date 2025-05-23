package com.transcendence.greenstar.demo.dbnote.resp;

import android.content.Context;
import android.widget.Toast;

import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.greenstar.demo.dbnote.bean.Note;
import com.transcendence.greenstar.demo.dbnote.db.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author joephone
 * @date 2025/5/8 2:38
 * @description
 * @edition 1.0
 */
public class NotesRepository {
    private final DatabaseHelper db;
    private final Context mContext;

    private static final String NOTES_ASSETS_FILE = "initial_notes.json"; // assets中的初始数据文件
    private static final String NOTES_LOCAL_FILE = "user_notes.json"; // 本地存储的数据文件

    public NotesRepository(Context context) {
        this.mContext = context;
        this.db  = new DatabaseHelper(mContext);
        checkAndLoadInitialData(); // 初始化时自动检查并加载数据
    }

    private void checkAndLoadInitialData() {
        if (db.getNotesCount() == 0) {
            LogUtils.d("数据库为空时加载初始数据");
            loadInitialNotesFromAssets();
        }
    }

    public List<Note> getAllNotes() {
        return db.getAllNotes();
    }

    public long insertNote(String title, String content) {
        return db.insertNote(title, content);
    }

    public void updateNote(Note note) {
        db.updateNote(note);
    }

    public void deleteNote(Note note) {
        db.deleteNote(note);
    }

    public Note getNote(long id) {
        return db.getNote(id);
    }

    /**
     * 从assets加载初始笔记数据，如果文件不存在则创建默认文件
     */
    public void loadInitialNotesFromAssets() {
        try {
            if (!isAssetsFileExists(NOTES_ASSETS_FILE)) {
                createDefaultNotesFile();
                return;
            }

            InputStream is = mContext.getAssets().open(NOTES_ASSETS_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String title = obj.optString("title", "默认标题");
                String content = obj.optString("content", "默认内容");
                db.insertNote(title, content);
            }

            saveNotesToLocalFile();
        } catch (IOException | JSONException e) {
            LogUtils.e( "Error loading notes" + e);
        }
    }

    /**
     * 将当前笔记列表保存到本地文件
     */
    public void saveNotesToLocalFile() {
        try {
            JSONArray jsonArray = new JSONArray();
            List<Note> notes = db.getAllNotes();

            for (Note note : notes) {
                JSONObject obj = new JSONObject();
                obj.put("title", note.getTitle());
                obj.put("content", note.getContent());
                obj.put("timestamp", note.getTimestamp());
                jsonArray.put(obj);
            }

            FileOutputStream fos = mContext.openFileOutput(NOTES_LOCAL_FILE, Context.MODE_PRIVATE);
            fos.write(jsonArray.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            LogUtils.e( "Error saving notes" + e);
        }
    }

    private void createDefaultNotesFile() {
        try {
            JSONArray defaultNotes = new JSONArray();
            JSONObject note1 = new JSONObject();
            note1.put("title", "欢迎使用笔记应用");
            note1.put("content", "这是您的第一条笔记,使用提示：长按笔记可以删除或编辑");
            defaultNotes.put(note1);

            for (int i = 0; i < defaultNotes.length(); i++) {
                JSONObject obj = defaultNotes.getJSONObject(i);
                db.insertNote(obj.getString("title"), obj.getString("content"));
            }

            saveNotesToLocalFile();
        } catch (Exception e) {
            LogUtils.e( "Error creating default notes" + e);
        }
    }

    private boolean isAssetsFileExists(String filename) {
        try {
            InputStream is = mContext.getAssets().open(filename);
            is.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}