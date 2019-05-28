package com.Lenovo.phclapps.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.Lenovo.phclapps.InternetCheck.InternetConnectionCheck;
import com.Lenovo.phclapps.R;
import com.Lenovo.phclapps.SharedPre.ConstantString;
import com.Lenovo.phclapps.SharedPre.SharedData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Forgotpassword extends AppCompatActivity {
    String username,otpnumber,newpass,Content;

    EditText otpEditText,newpassEditText;
    Button subButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        setTitle("Forgot Password");
        otpEditText=(EditText)findViewById(R.id.editText2);
        newpassEditText=(EditText)findViewById(R.id.editText3);
        subButton=(Button) findViewById(R.id.button2);
        ActionBar actionBar = getSupportActionBar();
        SharedData sharedData=new SharedData(this);
        username = sharedData.getUsername();
        Log.e("username",username);
     /*   if (sharedData!=null) {
            username = sharedData.getUsername();
            Log.e("username",username);
        }*/
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MainTask().execute("");
                otpnumber = otpEditText.getText().toString().trim();
                newpass  =  newpassEditText.getText().toString().trim();
                if(otpEditText.getText().toString().length()==0){
                    otpEditText.setError("Enter OTP Number");
                    otpEditText.requestFocus();
                }
                if(newpassEditText.getText().toString().length()==0){
                    newpassEditText.setError("Enter Your New Password");
                    newpassEditText.requestFocus();
                }
                if (new InternetConnectionCheck(getApplicationContext()).isOnline()) {
                     String url = ConstantString.URL+"update_password.php?";
                     new MainTask().execute(url);
                } else
                    Toast.makeText(getApplicationContext(), "Please Check the Internet Connection", Toast.LENGTH_LONG).show();

            }
        });
        }
        public class MainTask extends AsyncTask<String,Void,String>{
            String data="";
            private String Error = null;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Start Progress Dialog (Message)
                try
                {
                    data += URLEncoder.encode("email", "UTF-8") + "=" + username;
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + newpass;
                    data += "&" + URLEncoder.encode("otp", "UTF-8") + "=" + otpnumber;
              /* data += URLEncoder.encode("email", "UTF-8") + "=" + "ansh@gmail.com";
               data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + "123456";*/
                    Log.i("urlurlurlurl", "Dataaaaa" + data);
                }
                catch (UnsupportedEncodingException e)
                {

                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                BufferedReader reader = null;

                // Send data
                try {

                    // Defined URL where to send data
                    URL url = new URL(strings[0]);
                    // Send POST data request
                    Log.i("url", "url" + url);
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
                    Log.e("ContentDDDDDDDDDDDDDDDD",Content);

                } catch (Exception ex) {
                    Error = ex.getMessage();
                } finally {
                    try {
                        reader.close();
                    }

                    catch (Exception ex) {
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (Error != null) {
                } else {
                    JSONObject jsonResponse;
                    if (Content != null) {
                        try {
                            jsonResponse = new JSONObject(Content);
                            String status = jsonResponse.getString("status");
                            String message = jsonResponse.getString("message");
                            Log.e("status",status);
                            Log.e("message",message);
                            if (message.equals("successfully change your password")){
                                Toast.makeText(Forgotpassword.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                Intent in=new Intent(Forgotpassword.this,LoginActivity.class);
                                startActivity(in);
                                finish();
                            }else {
                                Toast.makeText(Forgotpassword.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            case android.R.id.home:

                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
