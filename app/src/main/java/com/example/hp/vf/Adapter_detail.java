package com.example.hp.vf;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suraj on 23-08-2018.
 */
public class Adapter_detail extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {


private Context mContext;
        List<ListItem> consolidatedList = new ArrayList<>();

public Adapter_detail(Context context, List<ListItem> consolidatedList) {
        this.mContext=context;
        this.consolidatedList = consolidatedList;
    //System.out.println("Called Detail");

        }


@Override
public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,  int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

        case ListItem.TYPE_GENERAL:
        View v1 = inflater.inflate(R.layout.detail_row, parent,
        false);
        viewHolder = new GeneralViewHolder(v1,mContext,consolidatedList);
        break;

        case ListItem.TYPE_DATE:
        View v2 = inflater.inflate(R.layout.detail_header, parent, false);
        viewHolder = new DateViewHolder(v2);
        break;
        }

        return viewHolder;
        }



    @Override
public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {

        case ListItem.TYPE_GENERAL:
            GeneralItem_Detail generalItem_detail   = (GeneralItem_Detail) consolidatedList.get(position);
            GeneralViewHolder generalViewHolder= (GeneralViewHolder) viewHolder;
            //generalViewHolder.txtTitle.setText(generalItem.getPojoOfJsonArray().getGst_name());
            generalViewHolder.gst_name.setText(generalItem_detail.getPojoOfJsonArray().getBill_Number());
            generalViewHolder.gst_number.setText(generalItem_detail.getPojoOfJsonArray().getPayment_status());
            generalViewHolder.type.setText("");
            generalViewHolder.balance.setText(generalItem_detail.getPojoOfJsonArray().getPayable_Amount());
            if(generalItem_detail.getPojoOfJsonArray().getType()=="credit".toLowerCase()){
                generalViewHolder.balance.setTextColor(Color.rgb(104,202,58));
            }
            else{
                generalViewHolder.balance.setTextColor(Color.rgb(255,64,129));
            }



            break;

            case ListItem.TYPE_DATE:
                DateItem dateItem = (DateItem) consolidatedList.get(position);
                DateViewHolder dateViewHolder = (DateViewHolder) viewHolder;

                dateViewHolder.txtTitle.setText(dateItem.getDate());
                Toast.makeText(mContext,dateItem.getDate(),Toast.LENGTH_SHORT);
                // Populate date item data here

                break;
        }
}





    // ViewHolder for date row item
    class DateViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTitle;

        public DateViewHolder(View v) {
            super(v);
            this.txtTitle = (TextView) v.findViewById(R.id.detail_date);

        }
    }

    // View holder for general row item
    class GeneralViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id, gst_name, gst_number, mobile, type, balance;
        private Context mContext;
        List<ListItem> consolidatedList = new ArrayList<>();

        public GeneralViewHolder(View itemView,Context ctx,List<ListItem> consolidatedList) {
            super(itemView);
            this.mContext=ctx;
            this.consolidatedList=consolidatedList;
            itemView.setOnClickListener(this);
            gst_name = (TextView) itemView.findViewById(R.id.detail_gst_name);
            gst_number = (TextView) itemView.findViewById(R.id.detail_gst);
            type = (TextView) itemView.findViewById(R.id.detail_gst_type);
            balance = (TextView) itemView.findViewById(R.id.detail_balance);

        }

        @Override
        public void onClick(View v) {
//            int position=getAdapterPosition();
//            GeneralItem generalItem   = (GeneralItem) consolidatedList.get(position);
//            Toast.makeText(this.mContext, generalItem.getPojoOfJsonArray().getGst_name(), Toast.LENGTH_SHORT).show();
//            Bundle bundle = new Bundle();
//            bundle.putString("Name",generalItem.getPojoOfJsonArray().getGst_name() ); // Put anything what you want
//
//            DetailFragment fragment2 = new DetailFragment();
//            fragment2.setArguments(bundle);
//
//            FragmentTransaction fragmentTransaction=mContext.getApplicationContext().getSupportFragmentManager().beginTransaction();
//
//            FragmentTransaction fragmentTransaction= ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container, fragment2).addToBackStack(null);
//            fragmentTransaction.commit();
           ////System.out.println("Name is"+generalItem.getPojoOfJsonArray().getGst_name());
        }
    }

    @Override
    public int getItemViewType(int position) {
       // //System.out.println("position"+position+":"+consolidatedList.get(position).getType());
        return consolidatedList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return consolidatedList != null ? consolidatedList.size() : 0;
    }

}

