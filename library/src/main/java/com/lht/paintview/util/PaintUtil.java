package com.lht.paintview.util;

import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.lht.paintview.pojo.SerializablePaint;

/**
 * Created by lht-Mac on 2017/9/29.
 */

public class PaintUtil {

    /**
     * 测量文字
     * @param text
     * @return rect.width() for text width, rect.height() for text height
     */
    public static Rect measureText(SerializablePaint serializablePaint, String text) {
        Rect rect = new Rect();
        Paint paint = new Paint(serializablePaint);
        paint.setTextSize(serializablePaint.getActualTextSize());
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect;
    }

    /**
     * 获取两个触控点之间的距离
     * @param event
     * @return 两个触控点之间的距离
     */
    public static float getDistance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);

        return (float) Math.sqrt(x * x + y * y);
    }
}
