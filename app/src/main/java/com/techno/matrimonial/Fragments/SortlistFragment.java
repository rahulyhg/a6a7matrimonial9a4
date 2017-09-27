package com.techno.matrimonial.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.techno.matrimonial.Adapter.CandidatesListAdapter;
import com.techno.matrimonial.Fragments.common.RootFragment;
import com.techno.matrimonial.Global.AppDialog;
import com.techno.matrimonial.Global.Global;
import com.techno.matrimonial.Global.Log;
import com.techno.matrimonial.Listener.OnApiCallListener;
import com.techno.matrimonial.Listener.RecyclerItemClickListener;
import com.techno.matrimonial.Model.boys_girls.CandidateListMain;
import com.techno.matrimonial.Model.common.ErrorMessageMain;
import com.techno.matrimonial.R;
import com.techno.matrimonial.Screens.CandidateDetailsActivity;
import com.techno.matrimonial.Screens.LoginActivity;
import com.techno.matrimonial.WebServices.Api;
import com.techno.matrimonial.WebServices.ApiFunctions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by arbaz on 2/7/16.
 */
public class SortlistFragment extends RootFragment implements OnApiCallListener {
    RecyclerView rvShortListed;
    TextView tvShortedEmptyTxt;
    FloatingActionButton fab_boys;
    EditText etSearch;

    //For Adapter
    CandidatesListAdapter candidatesListAdapter;
    ArrayList<CandidateListMain> candidateListMainArrayList;

    //For Api Call
    ApiFunctions apiFunctions;
    CandidateListMain candidateListMain;
    Gson gson = new Gson();
    ErrorMessageMain errorMessageMain;

    public SortlistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiFunctions = new ApiFunctions(getActivity(), this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sortlist, container, false);

        rvShortListed = (RecyclerView) rootView.findViewById(R.id.rvShortListed);
        tvShortedEmptyTxt = (TextView) rootView.findViewById(R.id.tvShortedEmptyTxt);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        rvShortListed.setLayoutManager(mLayoutManager1);


        //Api Call Here
        if (Global.isNetworkAvailable(getActivity())) {
//            AppDialog.showProgressDialog(getActivity());
//            apiFunctions.candidateList(Api.MainUrl + Api.ActionCandidateList, Constants.MALE);

        } else {
            AppDialog.noNetworkDialog(getActivity(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.nav_shortlist));
    }

    @Override
    public void onSuccess(final int responseCode, String responseString, String url) {
        Log.e("Shorted List Success" + responseString);
        AppDialog.dismissProgressDialog();
        JSONObject jsonObject;
        candidateListMainArrayList = new ArrayList<>();
        try {
            jsonObject = new JSONObject(responseString);
            if (responseCode == Api.ResponseOk) {
                JSONArray jsonArray = jsonObject.getJSONArray(Api.data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject getFirstObj = jsonArray.getJSONObject(i);
                    candidateListMain = gson.fromJson(getFirstObj.toString(), CandidateListMain.class);
                    candidateListMainArrayList.add(candidateListMain);


                }
            } else if (responseCode == Api.ResponseUnauthorized) {
                JSONObject getFirst = jsonObject.getJSONObject(Api.error);
                errorMessageMain = gson.fromJson(getFirst.toString(), ErrorMessageMain.class);

            } else {

            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (responseCode == Api.ResponseOk) {
                        candidatesListAdapter = new CandidatesListAdapter(candidateListMainArrayList, getActivity());
                        rvShortListed.setAdapter(candidatesListAdapter);
                        //Recycler View Click
                        rvShortListed.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                candidateListMain = candidateListMainArrayList.get(position);
                                Intent iBoyDetails = new Intent(getActivity(), CandidateDetailsActivity.class);
                                iBoyDetails.putExtra("CandidateDetails", candidateListMain);
                                startActivity(iBoyDetails);

                            }
                        }));

                    } else if (responseCode == Api.ResponseUnauthorized) {
                        Global.clearPreferences();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        AppDialog.showAlertDialog(getActivity(), null, errorMessageMain.getDescription(),
                                getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                    } else {
                        AppDialog.showAlertDialog(getActivity(), null, errorMessageMain.getDescription(),
                                getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(final String errorMessage) {
        Log.e("Shorted List Failure" + errorMessage);
        AppDialog.dismissProgressDialog();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppDialog.showAlertDialog(getActivity(), null, errorMessage,
                        getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
            }
        });
    }
}
