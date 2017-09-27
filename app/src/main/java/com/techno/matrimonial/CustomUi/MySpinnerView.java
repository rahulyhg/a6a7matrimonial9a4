package com.techno.matrimonial.CustomUi;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.techno.matrimonial.R;

/**
 * Created by arbaz on 15/7/16.
 */
public class MySpinnerView extends Spinner {

    public MySpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSelectionByItemId(AdapterView<?> parent, long id){
        for (int i = 0; i < parent.getCount(); i++) {
            long itemIdAtPosition = parent.getItemIdAtPosition(i);
            if (itemIdAtPosition == id) {
                parent.setSelection(i);
                break;
            }
        }
    }

}