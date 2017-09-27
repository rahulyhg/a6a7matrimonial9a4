package com.techno.matrimonial.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.techno.matrimonial.Adapter.CandidatesListAdapter;
import com.techno.matrimonial.Fragments.common.RootFragment;
import com.techno.matrimonial.Global.AppDialog;
import com.techno.matrimonial.Global.Constants;
import com.techno.matrimonial.Global.Global;
import com.techno.matrimonial.Global.Log;
import com.techno.matrimonial.Listener.OnApiCallListener;
import com.techno.matrimonial.Listener.RecyclerItemClickListener;
import com.techno.matrimonial.Model.boys_girls.CandidateListMain;
import com.techno.matrimonial.Model.common.ErrorMessageMain;
import com.techno.matrimonial.R;
import com.techno.matrimonial.Screens.CandidateDetailsActivity;
import com.techno.matrimonial.Screens.FiltreActivity;
import com.techno.matrimonial.Screens.LoginActivity;
import com.techno.matrimonial.WebServices.Api;
import com.techno.matrimonial.WebServices.ApiFunctions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by arbaz on 2/7/16.
 */
public class BoysFragment extends RootFragment implements OnApiCallListener {
    RecyclerView rvBoys;
    TextView tvBLEmptyTxt;
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


    public BoysFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiFunctions = new ApiFunctions(getActivity(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_boys, container, false);

        rvBoys = (RecyclerView) rootView.findViewById(R.id.rvBoys);
        tvBLEmptyTxt = (TextView) rootView.findViewById(R.id.tvBLEmptyTxt);
        etSearch = (EditText) rootView.findViewById(R.id.etSearch);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        rvBoys.setLayoutManager(mLayoutManager1);

        //Button Filter
        fab_boys = (FloatingActionButton) rootView.findViewById(R.id.fab_boys);
        //EditText Search
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = etSearch.getText().toString().toLowerCase(Locale.getDefault());
                candidatesListAdapter.searchRecord(text);

            }
        });
        //Filter Button click
        fab_boys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FiltreActivity.class));
            }
        });

        //For Filter button Hide and show
        rvBoys.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab_boys.isShown())
                    fab_boys.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    fab_boys.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        //Api Call Here
        if (Global.isNetworkAvailable(getActivity())) {
            AppDialog.showProgressDialog(getActivity());
            apiFunctions.candidateList(Api.MainUrl + Api.ActionCandidateList, Constants.MALE);

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
        setTitle(getString(R.string.nav_boys));
    }

    @Override
    public void onSuccess(final int responseCode, String responseString, String url) {
        Log.e("Boys List Success" + responseString);
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
                        rvBoys.setAdapter(candidatesListAdapter);
                        //Recycler View Click
                        rvBoys.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
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
        Log.e("Boys List Failure" + errorMessage);
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
