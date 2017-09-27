package com.techno.matrimonial.Fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.techno.matrimonial.Adapter.EventsListAdapter;
import com.techno.matrimonial.Fragments.common.RootFragment;
import com.techno.matrimonial.R;

import java.util.ArrayList;

/**
 * Created by arbaz on 2/7/16.
 */
public class EventsFragment extends RootFragment {
    private RecyclerView recyclerView;
    private EventsListAdapter gAdapter;

    // Required empty public constructor
    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Fresco.initialize(getActivity());
        View rootView = inflater.inflate(
                R.layout.fragment_events, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_events);
        final ArrayList<String> arr = new ArrayList<String>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        arr.add("a");
        arr.add("b");
        arr.add("c");
        arr.add("e");
        arr.add("f");
        arr.add("g");

        gAdapter = new EventsListAdapter(arr, getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gAdapter);

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.nav_event));
    }
}
