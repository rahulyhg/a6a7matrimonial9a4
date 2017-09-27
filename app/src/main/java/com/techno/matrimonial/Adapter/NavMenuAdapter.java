package com.techno.matrimonial.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techno.matrimonial.Model.common.LeftMenuMain;
import com.techno.matrimonial.R;

import java.util.ArrayList;

/**
 * Created by arbaz on 17/12/16.
 */

public class NavMenuAdapter extends RecyclerView.Adapter<NavMenuAdapter.ViewHolder> {
    ArrayList<LeftMenuMain> leftMenuMainArrayList;
    Context context;
     LeftMenuMain leftMenuMain;

    public NavMenuAdapter(ArrayList<LeftMenuMain> leftMenuMainArrayList, Context context) {
        this.leftMenuMainArrayList = leftMenuMainArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.left_menu_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        leftMenuMain = leftMenuMainArrayList.get(position);
        holder.ivPMenuImg.setImageResource(leftMenuMain.getImgResID());
        holder.tvPMenuTxt.setText(leftMenuMain.getItemName());
        holder.tvPMenuCount.setText(leftMenuMain.getShortlist_count());

    }

    @Override
    public int getItemCount() {
        return leftMenuMainArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public ImageView ivPMenuImg;
        public TextView tvPMenuTxt;
        public TextView tvPMenuCount;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivPMenuImg = (ImageView) view.findViewById(R.id.ivPMenuImg);
            tvPMenuTxt = (TextView) view.findViewById(R.id.tvPMenuTxt);
            tvPMenuCount = (TextView) view.findViewById(R.id.tvPMenuCount);

        }


    }
}
