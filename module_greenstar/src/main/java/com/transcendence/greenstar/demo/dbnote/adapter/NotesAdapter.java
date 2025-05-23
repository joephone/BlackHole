package com.transcendence.greenstar.demo.dbnote.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.greenstar.R;
import com.transcendence.greenstar.demo.dbnote.bean.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author joephone
 * @date 2023/5/8
 * @desc
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Note> mNotesList;

    // 新增数据更新方法
    public void updateData(List<Note> newNotes) {
        mNotesList = newNotes; // 关键！更新数据引用
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView content;
        public TextView dot;
        public TextView timestamp;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tv_title);
            content = view.findViewById(R.id.tv_content);
            dot = view.findViewById(R.id.dot);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }


    public NotesAdapter(Context context, List<Note> notesList) {
        this.mContext = context;
        this.mNotesList = notesList;
        LogUtils.d("notesList:"+(notesList!=null ? notesList.size():0));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_demo_db_note_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note note = mNotesList.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(note.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return mNotesList!=null ? mNotesList.size():0;
    }

    /**
     * 返回原始的时间戳字符串（yyyy-MM-dd HH:mm:ss格式）
     * 仅做格式验证，不进行实际转换
     * @param dateStr 输入的时间字符串，必须符合 yyyy-MM-dd HH:mm:ss 格式
     * @return 原始字符串（如果格式有效），否则返回空字符串
     */
    private String formatDate(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return "";
        }

        // 验证格式但不转换
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        format.setLenient(false); // 严格模式

        try {
            // 仅验证格式，不实际使用解析结果
            format.parse(dateStr);
            return dateStr; // 格式正确时返回原始字符串
        } catch (ParseException e) {
            LogUtils.e("Invalid date format: " + dateStr + e);
            return ""; // 格式无效时返回空
        }
    }
}
