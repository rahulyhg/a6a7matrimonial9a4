package com.techno.matrimonial.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.techno.matrimonial.Global.AppDialog;
import com.techno.matrimonial.Global.Constants;
import com.techno.matrimonial.Global.Global;
import com.techno.matrimonial.Global.Log;
import com.techno.matrimonial.Listener.OnApiCallListener;
import com.techno.matrimonial.Model.Ids;
import com.techno.matrimonial.Model.boys_girls.CandidateListMain;
import com.techno.matrimonial.Model.common.ErrorMessageMain;
import com.techno.matrimonial.R;
import com.techno.matrimonial.Screens.LoginActivity;
import com.techno.matrimonial.WebServices.Api;
import com.techno.matrimonial.WebServices.ApiFunctions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by arbaz on 2/7/16.
 */
public class CandidatesListAdapter extends RecyclerView.Adapter<CandidatesListAdapter.ViewHolder> implements OnApiCallListener {

    ArrayList<CandidateListMain> candidateListMainArrayList;
    ArrayList<CandidateListMain> candidateListFilterList;
    CandidateListMain candidateListMain;
    Context context;
    ApiFunctions apiFunctions;
    Activity activity;
    ErrorMessageMain errorMessageMain;
    Button btnBRsortlist;

    public CandidatesListAdapter(ArrayList<CandidateListMain> candidateListMainArrayList, Context context) {
        this.candidateListMainArrayList = candidateListMainArrayList;
        this.context = context;
        this.candidateListFilterList = new ArrayList<CandidateListMain>();
        this.candidateListFilterList.addAll(candidateListMainArrayList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidates_boy_row, parent, false);
        activity = (Activity) context;
        apiFunctions = new ApiFunctions(context, this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CandidatesListAdapter.ViewHolder holder, int position) {
        candidateListMain = candidateListMainArrayList.get(position);
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


                holder.sdBRProfile.setImageURI(uri);
                holder.tvBRName.setText(candidateListMain.getFull_name());
                holder.tvBRHeight.setText(candidateListMain.getHeight());
                holder.tvBRWeight.setText(candidateListMain.getWeight());
                holder.tvBRDOB.setText(final_date);

                holder.tvBRage.setText(" -" + candidateListMain.getAge());
                holder.tvBRcast.setText("N/A");
                holder.tvBRlocation.setText(candidateListMain.getLives_in());

                if (candidateListMain.getIs_short_listed() == Constants.IS_SHORTLISTED) {
                    btnBRsortlist.setBackgroundResource(R.color.btn_sortlisted);
                     btnBRsortlist.setText(context.getResources().getString(R.string.btn_shortlisted));
                    btnBRsortlist.setEnabled(false);
                } else {
                    btnBRsortlist.setBackgroundResource(R.color.btn_login);
                    btnBRsortlist.setText(context.getResources().getString(R.string.btn_shortlist));
                    btnBRsortlist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String id = String.valueOf(candidateListMain.getId());
                            Ids ids = new Ids(id);
                            AppDialog.showProgressDialog(context);
                            apiFunctions.shortedCandidate(Api.MainUrl + Api.ActionCandidateShortedList, ids);
                        }
                    });
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return candidateListMainArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        SimpleDraweeView sdBRProfile;
        TextView tvBRName, tvBRHeight, tvBRWeight, tvBRDOB, tvBRage, tvBRcast, tvBRlocation;
        //Button btnBRsortlist;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            sdBRProfile = (SimpleDraweeView) view.findViewById(R.id.sdBRProfile);

            tvBRName = (TextView) view.findViewById(R.id.tvBRName);
            tvBRHeight = (TextView) view.findViewById(R.id.tvBRHeight);
            tvBRWeight = (TextView) view.findViewById(R.id.tvBRWeight);
            tvBRDOB = (TextView) view.findViewById(R.id.tvBRDOB);
            tvBRage = (TextView) view.findViewById(R.id.tvBRage);
            tvBRcast = (TextView) view.findViewById(R.id.tvBRcast);
            tvBRlocation = (TextView) view.findViewById(R.id.tvBRlocation);

            btnBRsortlist = (Button) view.findViewById(R.id.btn_boys_sortlist);
        }
    }

    //For Search
    public void searchRecord(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        candidateListMainArrayList.clear();
        if (charText.length() == 0) {
            candidateListMainArrayList.addAll(candidateListFilterList);
        } else {
            for (CandidateListMain candidateListMain : candidateListFilterList) {
                    if (candidateListMain.getFull_name().toLowerCase(Locale.getDefault()).contains(charText)
                        || candidateListMain.getFather_name().toLowerCase(Locale.getDefault()).contains(charText)
                        || candidateListMain.getLast_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    candidateListMainArrayList.add(candidateListMain);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onSuccess(final int responseCode, String responseString, String url) {
        Log.e("Shortlisted Success" + responseString);
        AppDialog.dismissProgressDialog();
        Gson gson = new Gson();
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(responseString);
            if (responseCode == Api.ResponseOk) {

            } else if (responseCode == Api.ResponseUnauthorized) {
                JSONObject getFirst = jsonObject.getJSONObject(Api.error);
                errorMessageMain = gson.fromJson(getFirst.toString(), ErrorMessageMain.class);
            } else {
                //If Response Code Except(200 and 401)
            }
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (responseCode == Api.ResponseOk) {
                        btnBRsortlist.setBackgroundResource(R.color.btn_sortlisted);
                        btnBRsortlist.setText(context.getResources().getString(R.string.btn_shortlisted));
                        btnBRsortlist.setEnabled(false);

                    } else if (responseCode == Api.ResponseUnauthorized) {
                        Global.clearPreferences();
                        activity.startActivity(new Intent(context, LoginActivity.class));
                        AppDialog.showAlertDialog(context, null, errorMessageMain.getDescription(), context.getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                    } else {
                        AppDialog.showAlertDialog(context, null, errorMessageMain.getDescription(), context.getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
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
        Log.e("Shortlisted Failure" + errorMessage);
        AppDialog.dismissProgressDialog();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppDialog.showAlertDialog(context, null, errorMessage,
                        context.getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
            }
        });
    }



}