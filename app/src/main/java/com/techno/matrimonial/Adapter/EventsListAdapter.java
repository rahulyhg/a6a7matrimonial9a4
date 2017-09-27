package com.techno.matrimonial.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.techno.matrimonial.R;
import com.techno.matrimonial.Screens.EventsDetailsActivity;

import java.util.ArrayList;

/**
 * Created by arbaz on 2/7/16.
 */
public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.MyViewHolder> {
    private ArrayList<String> arrayList;
    Context context;
    View view;
    ImageView btn_event_details;


    public EventsListAdapter(ArrayList<String> maincat_list, Context context) {
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
            btn_event_details= (ImageView) view.findViewById(R.id.btn_event_details);
            btn_event_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent iNext = new Intent(context, EventsDetailsActivity.class);
                    context.startActivity(iNext);
                }
            });


        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_event_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(EventsListAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
