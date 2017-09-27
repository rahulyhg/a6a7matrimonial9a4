package com.techno.matrimonial.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.techno.matrimonial.R;
import com.techno.matrimonial.Screens.CandidateDetailsActivity;

import java.util.ArrayList;

/**
 * Created by arbaz on 2/7/16.
 */
public class SortListCandidatesAdapter extends RecyclerView.Adapter<SortListCandidatesAdapter.MyViewHolder> {
    private ArrayList<String> arrayList;
    Context context;
    View view;
    Button btn_more_info;
    private SimpleDraweeView circleImage;
    RelativeLayout relativeLayout_sortlist;

    public SortListCandidatesAdapter(ArrayList<String> maincat_list, Context context) {
        this.arrayList = maincat_list;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        /*private final Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ...
        }*/


        public MyViewHolder(View view) {
            super(view);
            circleImage = (SimpleDraweeView) view.findViewById(R.id.boys_profile);
            relativeLayout_sortlist = (RelativeLayout) view.findViewById(R.id.layout_sortlist_more);
            final Uri imageUri = Uri.parse("http://img2.timeinc.net/people/i/2014/news/140831/zach-woods-768.jpg");
            circleImage.setImageURI(imageUri);


            relativeLayout_sortlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent iNext = new Intent(context, CandidateDetailsActivity.class);
                    context.startActivity(iNext);
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_sortlisted_candidates, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(SortListCandidatesAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
