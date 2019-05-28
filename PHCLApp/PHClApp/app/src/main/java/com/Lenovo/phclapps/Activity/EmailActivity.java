package com.Lenovo.phclapps.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.Lenovo.phclapps.R;
import com.Lenovo.phclapps.SharedPre.ConstantString;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;


public class EmailActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaring EditText
    private EditText editTextEmail;
    private EditText editTextSubject;
    private EditText editTextMessage;
    String Content;
Context context;
String email;
    //Send button
    private Button buttonSend;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

         setTitle("Forgot Password");
        //Initializing the views
        editTextEmail = (EditText) findViewById(R.id.editText);
context=this;
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        buttonSend = (Button) findViewById(R.id.button);

        //Adding click listener
        buttonSend.setOnClickListener(this);
    }

private  void checkEmail(){
    String url = ConstantString.URL+"forgot_password.php?";
       new EmailTast().execute(url);
}
    private void sendEmail() {


        //Getting content for email
          email = editTextEmail.getText().toString().trim();


     //   String msg = "" + n;
        String sourceString = "<b>" + n + "</b> ";
        Html.fromHtml(sourceString);

        Spanned durationSpanned;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            durationSpanned = Html.fromHtml(String.valueOf(n), Html.FROM_HTML_MODE_LEGACY);
        } else {
            durationSpanned = Html.fromHtml(String.valueOf(n));
        }

        SpannableStringBuilder str = new SpannableStringBuilder("" + n);
        str.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), 0, durationSpanned.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
 /*       TextView tv=new TextView(context);
        tv.setText(str);*/
        if(editTextEmail.getText().toString().length()==0){
            editTextEmail.setError("Enter Your Valid Email Address");
            editTextEmail.requestFocus();
        }
      else {
            SendMail sm = new SendMail(this, email, "Password Reset Request <PhCL Reference App>", "\n" +
                    "Hi there,\n" +
                    "\n" +
                    "You've just requested to change your password, this is your 6 digit code:\n" +
                    //       "\n "+n);
                    //   "\n "+Html.fromHtml("<u>Jignesh</u><b>"+n+"</b>" ));
                    "\n " + n);
      /*  (Typeface.SANS_SERIF,
                (Typeface.BOLD);*/


    /*    As fromHtml( sourceString) is deprecated in API 24 ,
                you have to use next code :*/


            Log.i("Sent Successfully", "Sent ");
            ;
            //Executing sendmail to send email
            sm.execute();
        }
    }

    @Override
    public void onClick(View v) {
    /*    AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Success!")
                .setMessage("Check the 6-digit verification number on your email.")
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
              //  .setIcon(android.R.drawable.ic_dialog_alert)

                .show();*/

        checkEmail();
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
    public  class EmailTast extends AsyncTask<String,Void,String>{
        String data="";
        private String Error = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Start Progress Dialog (Message)

            Random generator = new Random();
            n = 1000000;
            n = generator.nextInt(n);
            try
            {
                data += URLEncoder.encode("email", "UTF-8") + "=" + editTextEmail.getText().toString().trim();;
                data += "&" + URLEncoder.encode("otp", "UTF-8") + "=" + n;
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
                Log.e("Content",Content);

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
            super.onPostExecute(s);
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
                        if (message.equals("successfully send otp on your email address")){
                            sendEmail();
                             Toast.makeText(EmailActivity.this, "successfully send otp on your email address", Toast.LENGTH_SHORT).show();
                            /*Intent in=new Intent(EmailActivity.this,SliderActivity.class);
                            startActivity(in);
                            finish();*/
                        }else {
                            Toast.makeText(EmailActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
}
}