package com.transcendence.wan.utils;

import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

/**
 * @Author Joephone on 2020/4/26 0:09
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AnimatorUtils {

    public static void doIntAnim(final TextView target, int to, long duration) {
        String fromStr = target.getText().toString();
        int from;
        try {
            from = Integer.parseInt(fromStr);
        } catch (NumberFormatException e) {
            from = 0;
        }
        doIntAnim(target, from, to, duration);
    }

    public static void doIntAnim(final TextView target, int from, int to, long duration) {
        ValueAnimator animator = ValueAnimator.ofInt(from, to);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                if (target != null) {
                    target.setText(String.format("%d", value));
                }
            }
        });
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }


}
