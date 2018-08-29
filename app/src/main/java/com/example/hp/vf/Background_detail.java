package com.example.hp.vf;

import android.app.Activity;
import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by suraj on 23-08-2018.
 */
public class Background_detail extends AsyncTask<Void,Detail,Void> {
        String url_string;
        ProgressDialog progressDialog;
        String user_data;
        StringBuilder stringBuilder=new StringBuilder();
private List<Detail> myOptions = new ArrayList<>();
        List<ListItem> consolidatedList = new ArrayList<>();
        Context context;
        Activity activity;
private RecyclerView mRecyclerView;
        Adapter_detail adapter;
        RecyclerView.LayoutManager layoutManager;
        ArrayList<Detail> arrayList=new ArrayList<>();
        String gst;
        private List<Date> date_new=new ArrayList<>();
        int k=0;
        TextView count_tv;
        int count_final;



        public Background_detail(Context context,String gst){
        this.context=context;
        activity=(Activity)context;

            this.gst=gst;
        }
@Override
protected void onPreExecute() {
       // System.out.println("background called");
        //url_string="http://api.railwayapi.com/v2/pnr-status/pnr/2350025533/apikey/9jj3hddb3m/";
        url_string="https://vinodfabs.000webhostapp.com/details_firm.php";
        mRecyclerView = (RecyclerView) activity.findViewById(R.id.recyler_detail);
        count_tv =(TextView)activity.findViewById(R.id.count_rows_detail);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
//        adapter = new Adapter_detail(context, consolidatedList);
//        mRecyclerView.setAdapter(adapter);
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Details For "+gst+" Loading");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        }

@Override
protected Void doInBackground(Void... params) {
        try{
        URL url=new URL(url_string);
     //   System.out.println(url_string);
        HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data= URLEncoder.encode("gst_number", "UTF-8")+"="+URLEncoder.encode(gst,"UTF-8");
               // System.out.println(data);
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
        InputStream inputStream=httpURLConnection.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

        while ((user_data=bufferedReader.readLine())!=null){
        stringBuilder.append(user_data + "\n");

        }
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
       System.out.println("Details" + stringBuilder.toString().trim());
        JSONObject jsonObject = new JSONObject(stringBuilder.toString().trim());
        JSONArray jsonArray = jsonObject.getJSONArray("result");

        String Date,Bill_Number,Payable_Amount,Payment_status,type;
        int count=0;
                JSONObject JO = jsonArray.getJSONObject(count);
                if(Integer.parseInt(JO.getString("id"))==0){
                        k=0;
                        System.out.println("no data found");
                }
                else {
                        while (count < jsonArray.length()) {


                                        Date = JO.getString("Date");
                                        Bill_Number = JO.getString("Bill_Number");
                                        Payable_Amount = JO.getString("Payable_Amount");
                                        Payment_status = JO.getString("Payment_status");
                                        type = JO.getString("Type");
                                        Detail detail = new Detail(Date, Bill_Number, Payable_Amount, Payment_status, type);
                                        publishProgress(detail);
                                        count++;
                                        Log.d("count", Integer.toString(count));
                                        k=1;
                                }
                                //myOptions.add(new Gst(id, gst_name, gst_number, mobile, type, balance));

                        }
                count_final=count;


        }
        catch (Exception e){
       System.out.println("exception 1 "+e);
        progressDialog.dismiss();
        }

        return null;
        }

@Override
protected void onProgressUpdate(Detail... values) {
        // System.out.println("progress Update called");
        if(k==1) {
                myOptions.add(values[0]);
                //adapter.notifyDataSetChanged();
        }
        }

@Override
protected void onPostExecute(Void aVoid) {

        if (k == 1) {
                HashMap<String, List<Detail>> groupedHashMap = groupDataIntoHashMap(myOptions);

        String dt3[] = new String[groupedHashMap.size()];
        // System.out.println("grouped hash map " + groupedHashMap.size());
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        for (String char1 : groupedHashMap.keySet()) {
                try {
                        Date date2 = format.parse(char1);
                        System.out.println("date 2" + date2);
                        date_new.add(date2);
                        System.out.println("date 2 size" + date_new.size());

                } catch (ParseException e) {
                        e.printStackTrace();
                }
        }
        Collections.sort(date_new);
        int i = 0;

        for (Date d : date_new) {
                System.out.println("Date in date_new" + d);
        }
        for (Date dt : date_new) {
                DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

                //to convert Date to String, use format method of SimpleDateFormat class.
                String strDate = dateFormat.format(dt);
                System.out.println("Date" + i + " " + strDate);
                System.out.println("i" + i);
                dt3[i] = strDate;
                System.out.println("in loop1 " + dt3[i]);
                i++;
        }
        for (String d1 : dt3) {
                System.out.println("in loop2 " + d1);
        }
        for (String date : dt3) {
                //System.out.println("KEY"+date);
                DateItem dateItem = new DateItem();
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            String strDate = dateFormat.format(date);
                dateItem.setDate(date);
                consolidatedList.add(dateItem);


                for (Detail pojoOfJsonArray : groupedHashMap.get(date)) {
                        GeneralItem_Detail generalItem = new GeneralItem_Detail();
                        generalItem.setPojoOfJsonArray(pojoOfJsonArray);//setBookingDataTabs(bookingDataTabs);
                        consolidatedList.add(generalItem);
                }

        }
        adapter = new Adapter_detail(context, consolidatedList);
        mRecyclerView.setAdapter(adapter);
                count_tv.setText(count_final + " Rows Found for GST " + gst);
                activity.findViewById(R.id.card_count_detail).setVisibility(View.GONE);
        progressDialog.dismiss();
}else{
                count_tv.setText("No Details Found for GST "+gst);
                progressDialog.dismiss();
        }
        }

private HashMap<String, List<Detail>> groupDataIntoHashMap(List<Detail> listOfPojosOfJsonArray) {


        HashMap<String, List<Detail>> groupedHashMap = new HashMap<>();
        for (Detail pojoOfJsonArray : listOfPojosOfJsonArray) {
//        String hashMapKey = pojoOfJsonArray.getDate();
                String hashMapKey=null;
                Date d=null;
                SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
                try {
                         d=format.parse(pojoOfJsonArray.getDate());


                } catch (ParseException e) {
                        e.printStackTrace();
                }
                DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

                //to convert Date to String, use format method of SimpleDateFormat class.
                hashMapKey=dateFormat.format(d);
        if (groupedHashMap.containsKey(hashMapKey)) {
        // The key is already in the HashMap; add the pojo object
        // against the existing key.
        groupedHashMap.get(hashMapKey).add(pojoOfJsonArray);
        } else {
        // The key is not there in the HashMap; create a new key-value pair
        List<Detail> list = new ArrayList<>();
        list.add(pojoOfJsonArray);
        groupedHashMap.put(hashMapKey, list);
        }
        }

        return groupedHashMap;
        }

        }
