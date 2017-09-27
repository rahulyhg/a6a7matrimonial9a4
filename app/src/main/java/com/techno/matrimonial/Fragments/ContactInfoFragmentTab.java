package com.techno.matrimonial.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techno.matrimonial.Fragments.common.RootFragment;
import com.techno.matrimonial.Model.boys_girls.CandidateListMain;
import com.techno.matrimonial.R;

/**
 * Created by arbaz on 11/7/16.
 */
public class ContactInfoFragmentTab extends RootFragment {

    private static final String ARG_PARAM1 = "param1";
    Bundle bPersonalDetail;
    CandidateListMain candidateListMain;

    TextView tvCandidateName,
            tvCandidateRelation,
            tvCandidatePhone,
            tvCandidateEmail,
            tvCandidateCity;

    public ContactInfoFragmentTab() {
        // Required empty public constructor
    }

    public static ContactInfoFragmentTab newInstance(CandidateListMain candidateListMain) {
        ContactInfoFragmentTab fragment = new ContactInfoFragmentTab();
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
        View view = inflater.inflate(R.layout.fragment_contact_info_tab, container, false);
        LinearLayout llContacts, llContactDisplay;
        TextView tvEmptyContact;
        llContacts = (LinearLayout) view.findViewById(R.id.llContacts);
        tvEmptyContact = (TextView) view.findViewById(R.id.tvEmptyContact);
        try {
            if (candidateListMain != null) {
                int size = candidateListMain.getContact().size();
                if (size > 0) {
                    llContacts.setVisibility(View.VISIBLE);
                    tvEmptyContact.setVisibility(View.GONE);

                    for (int i = 0; i < size; i++) {
                        llContactDisplay = (LinearLayout) View.inflate(getActivity(), R.layout.contact_info_layout, null);
                        ((TextView) llContactDisplay.findViewById(R.id.tvRelationName)).setText(candidateListMain.getContact().get(i).getRelation());
                        ((TextView) llContactDisplay.findViewById(R.id.tvCandidateName)).setText(candidateListMain.getContact().get(i).getName());
                        ((TextView) llContactDisplay.findViewById(R.id.tvCandidatePhone)).setText(candidateListMain.getContact().get(i).getPhone_no());
                        ((TextView) llContactDisplay.findViewById(R.id.tvCandidateEmail)).setText(candidateListMain.getContact().get(i).getEmail());
                        ((TextView) llContactDisplay.findViewById(R.id.tvCandidateCity)).setText(candidateListMain.getContact().get(i).getCity());
                        llContacts.addView(llContactDisplay);
                    }
                } else {
                    llContacts.setVisibility(View.GONE);
                    tvEmptyContact.setVisibility(View.VISIBLE);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

}
