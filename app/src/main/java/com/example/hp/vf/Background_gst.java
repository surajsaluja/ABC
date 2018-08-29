package com.example.hp.vf;

import android.app.Activity;
import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by suraj on 15-08-2018.
 */
public class Background_gst extends AsyncTask<Void,Gst,Void> {
String url_string;
    ProgressDialog progressDialog;
    String user_data;
    StringBuilder stringBuilder=new StringBuilder();
    private List<Gst> myOptions = new ArrayList<>();
    List<ListItem> consolidatedList = new ArrayList<>();
    Context context;
    Activity activity;
    private RecyclerView mRecyclerView;
    Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Gst> arrayList=new ArrayList<>();

    public Background_gst(Context context){
        this.context=context;
        activity=(Activity)context;
    }
    @Override
    protected void onPreExecute() {
        //System.out.println("background called");
        //url_string="http://api.railwayapi.com/v2/pnr-status/pnr/2350025533/apikey/9jj3hddb3m/";
        url_string="https://vinodfabs.000webhostapp.com/get_user.php";
        mRecyclerView = (RecyclerView) activity.findViewById(R.id.recyler_gst);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Gst List is Loading");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
try{
    URL url=new URL(url_string);
    //System.out.println(url_string);
    HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
    // httpURLConnection.setRequestMethod("POST");
    //httpURLConnection.setDoOutput(true);
    InputStream inputStream=httpURLConnection.getInputStream();
    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

    while ((user_data=bufferedReader.readLine())!=null){
        stringBuilder.append(user_data + "\n");

    }
    bufferedReader.close();
    inputStream.close();
    httpURLConnection.disconnect();
    //System.out.println(stringBuilder.toString().trim());
    JSONObject jsonObject = new JSONObject(stringBuilder.toString().trim());
    JSONArray jsonArray = jsonObject.getJSONArray("result");

    String id, gst_name, gst_number, mobile, type, balance;
int count=0;

    while (count < jsonArray.length()) {
        JSONObject JO = jsonArray.getJSONObject(count);
        id = JO.getString("id");
        gst_name = JO.getString("Name");
        gst_number = JO.getString("Gst");
        mobile = JO.getString("Mobile");
        type = JO.getString("type");
        balance = "15600";
         //myOptions.add(new Gst(id, gst_name, gst_number, mobile, type, balance));
Gst gst=new Gst(id, gst_name, gst_number, mobile, type, balance);

        publishProgress(gst);
        count++;
        Log.d("count", Integer.toString(count));
        Log.d("balance",gst_name);

    }

}
catch (Exception e){
    System.out.println("exception"+e);
        progressDialog.dismiss();
}

        return null;
    }

    @Override
    protected void onProgressUpdate(Gst... values) {
       // //System.out.println("progress Update called");
        myOptions.add(values[0]);

        //adapter.notifyDataSetChanged();
        Collections.sort(myOptions);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        for(int i=0;i<myOptions.size();i++){
           // System.out.println("gst names "+myOptions.get(i).getGst_name());
        }

        HashMap<String, List<Gst>> groupedHashMap = groupDataIntoHashMap(myOptions);
        String dates[]=new String[groupedHashMap.size()];
        int i=0;
        String temp;
        for(String char1 : groupedHashMap.keySet()){
            dates[i]=char1;
            i++;
        }

        for (i = 0; i<groupedHashMap.size();i++){
            for(int j=i+i;j<groupedHashMap.size();j++){
                if(dates[i].compareTo(dates[j])>0){
                    temp=dates[i];
                    dates[i]=dates[j];
                    dates[j]=temp;
                }
            }
        }

        for(String a:dates){
            //System.out.println("dates"+a);
        }

        for (String date : dates) {
           // System.out.println("KEY DATE"+date);
            DateItem dateItem = new DateItem();
            dateItem.setDate(date);
            consolidatedList.add(dateItem);


            for (Gst pojoOfJsonArray : groupedHashMap.get(date)) {
                GeneralItem generalItem = new GeneralItem();
                generalItem.setPojoOfJsonArray(pojoOfJsonArray);//setBookingDataTabs(bookingDataTabs);
               // System.out.println("gst num "+pojoOfJsonArray.getGst_number());
                consolidatedList.add(generalItem);
            }

        }
        adapter = new Adapter(context, consolidatedList);
        mRecyclerView.setAdapter(adapter);
       // System.out.println("consolidated lists "+consolidatedList.size());
        progressDialog.dismiss();
            }

private HashMap<String, List<Gst>> groupDataIntoHashMap(List<Gst> listOfPojosOfJsonArray) {


        HashMap<String, List<Gst>> groupedHashMap = new HashMap<>();
        for (Gst pojoOfJsonArray : listOfPojosOfJsonArray) {
            String hashMapKey = pojoOfJsonArray.getGst_name().toString().substring(0,1);
            //System.out.println("key "+hashMapKey);

            if (groupedHashMap.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                groupedHashMap.get(hashMapKey).add(pojoOfJsonArray);
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                List<Gst> list = new ArrayList<>();
                list.add(pojoOfJsonArray);
                groupedHashMap.put(hashMapKey, list);
            }
        }
        return groupedHashMap;
    }
}
