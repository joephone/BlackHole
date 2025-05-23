package com.transcendence.greenstar.demo.dbnote.resp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.greenstar.demo.dbnote.bean.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joephone
 * @date 2025/5/8 2:40
 * @description
 * @edition 1.0
 */
public class NotesViewModel extends ViewModel {
    public MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();
    private NotesRepository repository;

    public void init(NotesRepository repository) {
        this.repository = repository;
        loadNotes();
    }

    private void loadNotes() {
        List<Note> notes = repository.getAllNotes();
        notesLiveData.postValue(notes); // 直接更新LiveData
    }

    // 其他操作（add/update/delete）也直接操作LiveData的值
    public void addNote(String title, String content) {
        long id = repository.insertNote(title, content);
        Note newNote = repository.getNote(id);
        if (newNote != null) {
            // 关键修复：创建全新的ArrayList
            List<Note> newList = new ArrayList<>();
            if (notesLiveData.getValue() != null) {
                newList.addAll(notesLiveData.getValue()); // 复制旧数据
            }
            newList.add(0, newNote); // 添加新数据
            notesLiveData.postValue(newList); // 传入新对象
            LogUtils.d("强制更新LiveData，List地址: " + System.identityHashCode(newList));
        }
    }

    public void updateNote(Note note) {
        repository.updateNote(note);
        loadNotes();
    }

    public void deleteNote(Note note) {
        repository.deleteNote(note);
        loadNotes();
    }

    public Note getNote(long id) {
        Note n = repository.getNote(id);
        loadNotes();
        return n;

    }
}