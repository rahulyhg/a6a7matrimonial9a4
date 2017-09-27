package com.techno.matrimonial.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techno.matrimonial.Fragments.common.RootFragment;
import com.techno.matrimonial.R;

/**
 * Created by arbaz on 2/7/16.
 */
public class SettingsFragment extends RootFragment {


    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_settings, container, false);

        return rootview;
    }
    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.nav_settings));
    }
}
