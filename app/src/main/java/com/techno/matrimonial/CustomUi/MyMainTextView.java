package com.techno.matrimonial.CustomUi;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.techno.matrimonial.R;

/**
 * Created by arbaz on 15/7/16.
 * for  main  text like whose come from server height weight etc..
 */
public class MyMainTextView extends TextView {

    public MyMainTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public MyMainTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
        setTextColor(this.getResources().getColor(R.color.my_txt_main_color));
    }

    public MyMainTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        /*Typeface customFont = FontCache.getTypeface("SourceSansPro-Regular.ttf", context);
        setTypeface(customFont);*/
        setTypeface(null, Typeface.BOLD);
    }
}