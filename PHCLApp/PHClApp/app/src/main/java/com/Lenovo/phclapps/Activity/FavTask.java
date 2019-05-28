package com.Lenovo.phclapps.Activity;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.Lenovo.phclapps.SharedPre.ConstantString;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class FavTask extends AsyncTask<String, Void, String> {
    String data = "", Content;
    String catId;
    String stdId;

    private String Error = null;

    public FavTask(String catId, String stdId) {
        this.catId = catId;
        this.stdId = stdId;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try {
            data += URLEncoder.encode("studid", "UTF-8") + "=" + stdId;
            data += "&" + URLEncoder.encode("catdId", "UTF-8") + "=" + catId;
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

    }

    @Override
    protected String doInBackground(String... strings) {
        BufferedReader reader = null;

        // Send data
        try {
            String urll = ConstantString.URL + "insert_fav.php?";

            // Defined URL where to send data
            URL url = new URL(urll);
            // Send POST data request
            Log.i("urlurl", "url" + url);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(
                    conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the server response
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "");
            }


            // Append Server Response To Content String
            Content = sb.toString();
            Log.e("Content", Content);

        } catch (Exception ex) {
            Error = ex.getMessage();
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {
            }
        }
        if(Error!=null){
            return  null;
        }else {
            return Content;
        }
//        return ;
    }


}
