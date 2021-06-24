package com.transcendence.core.widget.custom.circlemenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.transcendence.core.R;

/**
 * @author Joephone on 2019/8/29 11:05
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class CircleImageView extends AppCompatImageView {
    // The name of the view
    private String name;

    public CircleImageView(Context context) {
        this(context,null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(attrs!=null){
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CircleImageView);
            this.name = array.getString(R.styleable.CircleImageView_name);
            array.recycle();
        }
    }

    /**
     * Return the name of the view.
     * @return Returns the name of the view.
     */
    public String getName(){
        return name;
    }

    /**
     * Set the name of the view.
     * @param name The name to be set for the view.
     */
    public void setName(String name){
        this.name = name;
    }
}
