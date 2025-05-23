package com.transcendence.greenstar.demo.dbnote;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author joephone
 * @date 2025/5/8 2:42
 * @description
 * @edition 1.0
 */
public class EmptyStateManager {

    private final TextView emptyView;
    private final RecyclerView recyclerView;

    public EmptyStateManager(TextView emptyView, RecyclerView recyclerView) {
        this.emptyView = emptyView;
        this.recyclerView = recyclerView;
    }

    public void toggleEmptyState(boolean isEmpty) {
        if (isEmpty) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

}