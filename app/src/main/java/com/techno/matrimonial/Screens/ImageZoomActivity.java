package com.techno.matrimonial.Screens;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.techno.matrimonial.CustomUi.frescoFiles.ZoomableDraweeView;
import com.techno.matrimonial.R;

/**
 * Created by arbaz on 27/8/16.
 */
public class ImageZoomActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tbTitle;
    ImageView tbIvBack;

    ZoomableDraweeView view;
    Bundle bGetName;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri data = getIntent().getData();
        if (null == data) {
            throw new IllegalArgumentException("Image Not Found");
        }
        setContentView(R.layout.custom_zoom_img);
        bGetName = new Bundle(getIntent().getExtras());
        name = (String) bGetName.get("Name");
        //Custom Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tbTitle = (TextView) toolbar.findViewById(R.id.tbTitle);
        tbIvBack = (ImageView) toolbar.findViewById(R.id.tbIvBack);
        tbTitle.setText(name);
        tbIvBack.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.setFinishOnTouchOutside(false);

        view = (ZoomableDraweeView) findViewById(R.id.zoomable);

        DraweeController ctrl = Fresco.newDraweeControllerBuilder().setUri(
                data).setTapToRetryEnabled(true).build();
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
                .setProgressBarImage(new ProgressBarDrawable())
                .build();
        view.setController(ctrl);
        view.setHierarchy(hierarchy);

        tbIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}