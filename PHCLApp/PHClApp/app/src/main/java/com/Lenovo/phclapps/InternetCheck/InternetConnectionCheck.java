package com.Lenovo.phclapps.InternetCheck;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



public  class InternetConnectionCheck {Context context;

    public InternetConnectionCheck(Context context) {
        this.context = context;
    }

    public  boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting(); }
}
/*

               if (new InternetConnectionCheck(getApplicationContext()).isOnline()) {
            String str = ConstantString.URL+"PostTherapySchedule?";
            new LongOperation().execute(str);
        } else
            Toast.makeText(getApplicationContext(), "Please Check the Internet Connection", Toast.LENGTH_LONG).show();*/
