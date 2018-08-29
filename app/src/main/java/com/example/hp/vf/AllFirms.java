package com.example.hp.vf;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class AllFirms extends Fragment {
    View RootView;
    private RecyclerView mRecyclerView;
    private Adapter adapter;
    Activity activity;
    Context context;

    public AllFirms() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView= inflater.inflate(R.layout.fragment_all_firms, container, false);
        mRecyclerView = (RecyclerView)RootView.findViewById(R.id.recyler_gst);
        mRecyclerView.setHasFixedSize(true);
        return RootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Background_gst background_gst=new Background_gst(getActivity());
        background_gst.execute();



    }


}
