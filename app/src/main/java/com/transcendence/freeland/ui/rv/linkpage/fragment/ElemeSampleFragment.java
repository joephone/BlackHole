package com.transcendence.freeland.ui.rv.linkpage.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.transcendence.core.ui.rv.linkage.adapter.viewholder.LinkagePrimaryViewHolder;
import com.transcendence.core.ui.rv.linkage.adapter.viewholder.LinkageSecondaryFooterViewHolder;
import com.transcendence.core.ui.rv.linkage.adapter.viewholder.LinkageSecondaryHeaderViewHolder;
import com.transcendence.core.ui.rv.linkage.adapter.viewholder.LinkageSecondaryViewHolder;
import com.transcendence.core.ui.rv.linkage.bean.BaseGroupedItem;
import com.transcendence.core.ui.rv.linkage.contract.ILinkagePrimaryAdapterConfig;
import com.transcendence.core.ui.rv.linkage.contract.ILinkageSecondaryAdapterConfig;
import com.transcendence.freeland.R;
import com.transcendence.freeland.databinding.ActivityUiRvLinkpageFragmentElemeBinding;
import com.transcendence.core.ui.rv.linkage.LinkageRecyclerView;
import com.transcendence.freeland.ui.rv.linkpage.bean.ElemeGroupedItem;

import java.util.List;

/**
 * Create by KunMinX at 19/5/8
 */
public class ElemeSampleFragment extends Fragment {

  private static final int SPAN_COUNT_FOR_GRID_MODE = 2;
  private static final int MARQUEE_REPEAT_LOOP_MODE = -1;
  private static final int MARQUEE_REPEAT_NONE_MODE = 0;
  private ActivityUiRvLinkpageFragmentElemeBinding mBinding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.activity_ui_rv_linkpage_fragment_eleme, container, false);
    mBinding = ActivityUiRvLinkpageFragmentElemeBinding.bind(view);
    setHasOptionsMenu(true);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    initLinkageData(mBinding.linkage);
  }

  private void initLinkageData(LinkageRecyclerView linkage) {
    Gson gson = new Gson();
    List<ElemeGroupedItem> items = gson.fromJson(getString(R.string.eleme_json),
            new TypeToken<List<ElemeGroupedItem>>() {
            }.getType());

    linkage.init(items, new ElemePrimaryAdapterConfig(), new ElemeSecondaryAdapterConfig());
  }

  private static class ElemePrimaryAdapterConfig implements ILinkagePrimaryAdapterConfig {

    private Context mContext;

    public void setContext(Context context) {
      mContext = context;
    }

    @Override
    public int getLayoutId() {
      return R.layout.ui_rv_linkpage_default_adapter_primary;
    }

    @Override
    public int getGroupTitleViewId() {
      return R.id.tv_group;
    }

    @Override
    public int getRootViewId() {
      return R.id.layout_group;
    }

    @Override
    public void onBindViewHolder(LinkagePrimaryViewHolder holder, boolean selected, String title) {

      //TODO Tip: if position need, we suggest you get position by holder.getBindingAdapterPosition

      int position = holder.getAdapterPosition();

      //


      TextView tvTitle = ((TextView) holder.getGroupTitle());
      tvTitle.setText(title);

      tvTitle.setBackgroundColor(mContext.getResources().getColor(
              selected ? R.color.purple : R.color.white));
      tvTitle.setTextColor(ContextCompat.getColor(mContext,
              selected ? R.color.white : R.color.colorGray));
      tvTitle.setEllipsize(selected ? TextUtils.TruncateAt.MARQUEE : TextUtils.TruncateAt.END);
      tvTitle.setFocusable(selected);
      tvTitle.setFocusableInTouchMode(selected);
      tvTitle.setMarqueeRepeatLimit(selected ? MARQUEE_REPEAT_LOOP_MODE : MARQUEE_REPEAT_NONE_MODE);
    }

    @Override
    public void onItemClick(LinkagePrimaryViewHolder holder, View view, String title) {
      //TODO
    }
  }

  private static class ElemeSecondaryAdapterConfig implements
          ILinkageSecondaryAdapterConfig<ElemeGroupedItem.ItemInfo> {

    private Context mContext;

    public void setContext(Context context) {
      mContext = context;
    }

    @Override
    public int getGridLayoutId() {
      return 0;
    }

    @Override
    public int getLinearLayoutId() {
      return R.layout.activity_ui_rv_linkpage_adapter_eleme_secondary_linear;
    }

    @Override
    public int getHeaderLayoutId() {
      return R.layout.ui_rv_linkpage_default_adapter_secondary_header;
    }

    @Override
    public int getFooterLayoutId() {
      return 0;
    }

    @Override
    public int getHeaderTextViewId() {
      return R.id.secondary_header;
    }

    @Override
    public int getSpanCountOfGridMode() {
      return SPAN_COUNT_FOR_GRID_MODE;
    }

    @Override
    public void onBindViewHolder(LinkageSecondaryViewHolder holder,
                                 BaseGroupedItem<ElemeGroupedItem.ItemInfo> item) {

      ((TextView) holder.getView(R.id.iv_goods_name)).setText(item.info.getTitle());
      Glide.with(mContext).load(item.info.getImgUrl()).into((ImageView) holder.getView(R.id.iv_goods_img));
      holder.getView(R.id.iv_goods_item).setOnClickListener(v -> {
        //TODO
      });

      holder.getView(R.id.iv_goods_add).setOnClickListener(v -> {
        //TODO
      });
    }

    @Override
    public void onBindHeaderViewHolder(LinkageSecondaryHeaderViewHolder holder,
                                       BaseGroupedItem<ElemeGroupedItem.ItemInfo> item) {

      ((TextView) holder.getView(R.id.secondary_header)).setText(item.header);
    }

    @Override
    public void onBindFooterViewHolder(LinkageSecondaryFooterViewHolder holder,
                                       BaseGroupedItem<ElemeGroupedItem.ItemInfo> item) {

    }
  }

}
