package com.example.hp.vf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private View RootView;
    private RecyclerView mRecyclerView;
    private String gst;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView=inflater.inflate(R.layout.fragment_detail, container, false);
        mRecyclerView = (RecyclerView)RootView.findViewById(R.id.recyler_detail);
        mRecyclerView.setHasFixedSize(true);
        Bundle bundle=this.getArguments();
        if(bundle!=null){
             gst=bundle.getString("Name");
        }


        return RootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Background_detail background_detail=new Background_detail(getActivity(),gst);
        background_detail.execute();



    }









}
