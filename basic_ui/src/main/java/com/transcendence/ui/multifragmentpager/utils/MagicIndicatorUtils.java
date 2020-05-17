//package com.transcendence.ui.multifragmentpager.utils;
//
//import android.content.Context;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.text.Html;
//import android.util.TypedValue;
//import android.view.View;
//
//
//import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
//
//import com.transcendence.ui.R;
//import com.transcendence.ui.multifragmentpager.adapter.MultiFragmentPagerAdapter;
//
///**
// * @Author Joephone on 2020/4/26 16:42
// * @E-Mail Address：joephonechen@gmail.com
// * @Desc
// * @Edition 1.0
// * @EditionHistory
// */
//
//public class MagicIndicatorUtils {
//
//    public static <E, F extends Fragment> CommonNavigator commonNavigator(
//            MagicIndicator mi, ViewPager vp,
//            MultiFragmentPagerAdapter<E, F> adapter,
//            SimpleCallback<Integer> onClickCallback) {
//        CommonNavigator navigator = new CommonNavigator(vp.getContext());
//        final int padding16 = (int) DisplayInfoUtils.getInstance().dp2px(8);
//        navigator.setLeftPadding(padding16);
//        navigator.setRightPadding(padding16);
//        navigator.setAdapter(new CommonNavigatorAdapter() {
//            @Override
//            public int getCount() {
//                return adapter.getCount();
//            }
//
//            @Override
//            public IPagerTitleView getTitleView(Context context, final int index) {
//                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
//                simplePagerTitleView.setText(Html.fromHtml(adapter.getPageTitle(index).toString()));
//                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtils.getDimens(R.dimen.actionbarex_common_title_bar_text_size_def));
//                simplePagerTitleView.setSelectedColor(ResUtils.getColor(context, R.color.basic_ui_action_bar_text));
//                simplePagerTitleView.setNormalColor(ResUtils.getColor(context, R.color.basic_ui_action_bar_text_alpha));
//                int paddingH = (int) DisplayInfoUtils.getInstance().dp2px(8);
//                int paddingV = (int) DisplayInfoUtils.getInstance().dp2px(5);
//                simplePagerTitleView.setPadding(paddingH, paddingV, paddingH, paddingV);
//                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        vp.setCurrentItem(index);
//                        if (onClickCallback != null) {
//                            onClickCallback.onResult(index);
//                        }
//                    }
//                });
//                return simplePagerTitleView;
//            }
//
//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                return null;
//            }
//        });
//        mi.setNavigator(navigator);
//        ViewPagerHelper.bind(mi, vp);
//        return navigator;
//    }
//
//    public static CommonNavigator commonNavigator(
//            MagicIndicator mi, ViewPager vp,
//            FragmentPagerAdapter adapter,
//            SimpleCallback<Integer> onClickCallback) {
//        CommonNavigator navigator = new CommonNavigator(vp.getContext());
//        final int padding16 = (int) DisplayInfoUtils.getInstance().dp2px(8);
//        navigator.setLeftPadding(padding16);
//        navigator.setRightPadding(padding16);
//        navigator.setAdapter(new CommonNavigatorAdapter() {
//            @Override
//            public int getCount() {
//                return adapter.getCount();
//            }
//
//            @Override
//            public IPagerTitleView getTitleView(Context context, final int index) {
//                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
//                simplePagerTitleView.setText(Html.fromHtml(adapter.getPageTitle(index).toString()));
//                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtils.getDimens(R.dimen.actionbarex_common_title_bar_title_text_size_def));
//                simplePagerTitleView.setSelectedColor(ResUtils.getColor(context, R.color.basic_ui_action_bar_text));
//                simplePagerTitleView.setNormalColor(ResUtils.getColor(context, R.color.basic_ui_action_bar_text_alpha));
//                int paddingH = (int) DisplayInfoUtils.getInstance().dp2px(8);
//                int paddingV = (int) DisplayInfoUtils.getInstance().dp2px(5);
//                simplePagerTitleView.setPadding(paddingH, paddingV, paddingH, paddingV);
//                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        vp.setCurrentItem(index);
//                        if (onClickCallback != null) {
//                            onClickCallback.onResult(index);
//                        }
//                    }
//                });
//                return simplePagerTitleView;
//            }
//
//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                return null;
//            }
//        });
//        mi.setNavigator(navigator);
//        ViewPagerHelper.bind(mi, vp);
//        return navigator;
//    }
//
//}
