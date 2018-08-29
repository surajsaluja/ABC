package com.example.hp.vf;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends RunTimePermission {

    private static int time_out=2000;
    private static final int REQUEST_PERMISSION = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        window.setAttributes(winParams);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        requestAppPermissions(new String[]{
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.CALL_PHONE
                },
                R.string.msg, REQUEST_PERMISSION);



    }

    public void chk_internet(){
        ConnectivityManager con=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=con.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected())
        {
            //  new getdataonline().execute();
            Snackbar.make(findViewById(android.R.id.content), "Internet Connected", Snackbar.LENGTH_SHORT).show();
            pass();
        }
        else{
            //Toast.makeText(getApplicationContext(), "Internet not Connected", Toast.LENGTH_LONG);
            Snackbar.make(findViewById(android.R.id.content), "Internet Not Connected", Snackbar.LENGTH_INDEFINITE).show();
            chk_internet();

        }
    }

    public void pass(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent pass = new Intent(MainActivity.this,StartActivity.class);
                startActivity(pass);
                finish();
            }
        }, time_out);
    }




    @Override
    public void onPermissionsGranted(int requestCode) {
        //Do anything when permisson granted
        Snackbar.make(findViewById(android.R.id.content), "Permissions Granted", Snackbar.LENGTH_SHORT).show();
        chk_internet();
    }

}
