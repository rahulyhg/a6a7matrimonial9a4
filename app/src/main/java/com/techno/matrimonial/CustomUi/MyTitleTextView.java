package com.techno.matrimonial.CustomUi;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.techno.matrimonial.R;

/**
 * Created by arbaz on 15/7/16.
 * for  default text like height weight etc..
 */
public class MyTitleTextView extends TextView {

    public MyTitleTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public MyTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
        setTextColor(this.getResources().getColor(R.color.my_txt_title_color));

    }

    public MyTitleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);

    }

    private void applyCustomFont(Context context) {
        /*Typeface customFont = FontCache.getTypeface("SourceSansPro-Regular.ttf", context);
        setTypeface(customFont);*/
    }
}