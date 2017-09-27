package com.techno.matrimonial.Fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techno.matrimonial.Global.Constants;
import com.techno.matrimonial.Global.Global;
import com.techno.matrimonial.Model.boys_girls.CandidateListMain;
import com.techno.matrimonial.R;

/**
 * Created by arbaz on 11/7/16.
 */
public class PersonalInfoFragmentTab extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    Bundle bPersonalDetail;
    CandidateListMain candidateListMain;

    TextView tvCandidateDOB,
            tvCandidateTime,
            tvCandidateBirthplace,
            tvCandidateLocation,
            tvCandidateMother,
            tvCandidateFather,
            tvCandidateHSC,
            tvCandidateBachelors,
            tvCandidateMaster,
            tvCandidateProfessional;


    public PersonalInfoFragmentTab() {
    }

    public static PersonalInfoFragmentTab newInstance(CandidateListMain candidateListMain) {
        PersonalInfoFragmentTab fragment = new PersonalInfoFragmentTab();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, candidateListMain);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bPersonalDetail = getArguments();
            candidateListMain = (CandidateListMain) bPersonalDetail.getSerializable(ARG_PARAM1);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_info_tab, container, false);

        tvCandidateDOB = (TextView) view.findViewById(R.id.tvCandidateDOB);
        tvCandidateTime = (TextView) view.findViewById(R.id.tvCandidateTime);
        tvCandidateBirthplace = (TextView) view.findViewById(R.id.tvCandidateBirthplace);
        tvCandidateLocation = (TextView) view.findViewById(R.id.tvCandidateLocation);
        tvCandidateMother = (TextView) view.findViewById(R.id.tvCandidateMother);
        tvCandidateFather = (TextView) view.findViewById(R.id.tvCandidateFather);
        tvCandidateHSC = (TextView) view.findViewById(R.id.tvCandidateHSC);
        tvCandidateBachelors = (TextView) view.findViewById(R.id.tvCandidateBachelors);
        tvCandidateMaster = (TextView) view.findViewById(R.id.tvCandidateMaster);
        tvCandidateProfessional = (TextView) view.findViewById(R.id.tvCandidateProfessional);

        try {
            if (candidateListMain != null) {
                //For Image
                String profile_url, final_url;
                profile_url = candidateListMain.getProfile_media();
                final_url = profile_url.replace(Constants.LocalHost, Constants.DefaultIP);
                Uri uri = Uri.parse(final_url);
                //For DOB
                long date;
                String final_date;
                date = Long.parseLong(candidateListMain.getDate_of_birth());
                final_date = Global.parseAndGetDate(date);



                tvCandidateDOB.setText(final_date);
                tvCandidateTime.setText(candidateListMain.getTime_of_birth());
                tvCandidateBirthplace.setText(candidateListMain.getBirth_place());
                tvCandidateLocation.setText(candidateListMain.getLives_in());
                tvCandidateMother.setText(candidateListMain.getMother_name());
                tvCandidateFather.setText(candidateListMain.getFather_name());
                tvCandidateHSC.setText(candidateListMain.getEducation_group());
                tvCandidateBachelors.setText(candidateListMain.getEducation_degree());
//                    tvCandidateMaster.setText(candidateListMain.);
//                    tvCandidateProfessional.setText(candidateListMain.);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
