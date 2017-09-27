package com.techno.matrimonial.Screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.techno.matrimonial.Adapter.CandidatesDetailsTabAdapter;
import com.techno.matrimonial.Global.Constants;
import com.techno.matrimonial.Global.Global;
import com.techno.matrimonial.Model.boys_girls.CandidateListMain;
import com.techno.matrimonial.R;

/**
 * Created by arbaz on 4/7/16.
 */
public class CandidateDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView tbTitle;
    ImageView tbIvBack;

    SimpleDraweeView sdBDProfile;
    TextView tvBDName, tvBDAge, tvBDLocation;
    TabLayout tlDetailTab;
    ViewPager vpDetail;


    CandidatesDetailsTabAdapter candidatesDetailsTabAdapter;
    Bundle bDetails;
    CandidateListMain candidateListMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boys_details);
        //getting data here
        bDetails = new Bundle(getIntent().getExtras());
        candidateListMain = (CandidateListMain) bDetails.getSerializable("CandidateDetails");
        bindHere();
        clickHere();
        //set Data Here
        setData();
    }

    public void bindHere() {

        //Custom Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tbTitle = (TextView) toolbar.findViewById(R.id.tbTitle);
        tbIvBack = (ImageView) toolbar.findViewById(R.id.tbIvBack);
        tbTitle.setText(getString(R.string.txt_boy_details));
        tbIvBack.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sdBDProfile = (SimpleDraweeView) findViewById(R.id.sdBDProfile);
        tvBDName = (TextView) findViewById(R.id.tvBDName);
        tvBDAge = (TextView) findViewById(R.id.tvBDAge);
        tvBDLocation = (TextView) findViewById(R.id.tvBDLocation);

        tlDetailTab = (TabLayout) findViewById(R.id.tlDetailTab);
        vpDetail = (ViewPager) findViewById(R.id.vpDetail);

        //Set TabView Pager
        candidatesDetailsTabAdapter = new CandidatesDetailsTabAdapter(CandidateDetailsActivity.this, getSupportFragmentManager(), 2, candidateListMain);
        vpDetail.setAdapter(candidatesDetailsTabAdapter);
        vpDetail.setOffscreenPageLimit(1);
        tlDetailTab.setupWithViewPager(vpDetail);


    }

    public void clickHere() {
        tbIvBack.setOnClickListener(this);
    }

    public void setData() {
        try {
            if (candidateListMain != null) {
                //For Image
                String profile_url = candidateListMain.getProfile_media();
                profile_url = profile_url.replace(Constants.LocalHost, Constants.DefaultIP);
                final Uri profile_uri = Uri.parse(profile_url);

                //For DOB
                long date;
                String final_date;
                date = Long.parseLong(candidateListMain.getDate_of_birth());
                final_date = Global.parseAndGetDate(date);


                sdBDProfile.setImageURI(profile_uri);
                tvBDName.setText(candidateListMain.getFull_name());
                tvBDAge.setText(String.valueOf(candidateListMain.getAge()));
                tvBDLocation.setText(candidateListMain.getLives_in());

                if (candidateListMain.getProfile_media() != null) {
                    sdBDProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent iProfileView = new Intent(CandidateDetailsActivity.this, ImageZoomActivity.class);
                            iProfileView.setData(profile_uri);
                            iProfileView.putExtra("Name", candidateListMain.getFirst_name());
                            startActivity(iProfileView);
//                            startActivity(new Intent(CandidateDetailsActivity.this, ImageZoomActivity.class).setData(profile_uri));
                        }
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tbIvBack:
                finish();
                break;
            default:
                break;
        }
    }
}