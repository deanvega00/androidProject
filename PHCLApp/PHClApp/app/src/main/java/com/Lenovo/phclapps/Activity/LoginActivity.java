package com.Lenovo.phclapps.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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



public class LoginActivity extends AppCompatActivity {
    Button loginbutton;
    String Content;
    EditText emailEditText, passEditText;
    String email, pass, token;
    TextView signTextView, forgotpwdTextView;
    SharedData sharedData;
    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbutton = (Button) findViewById(R.id.login);
        emailEditText = (EditText) findViewById(R.id.edtname);
        passEditText = (EditText) findViewById(R.id.edtpass);
        signTextView = (TextView) findViewById(R.id.signup);
        emailEditText.setText("");
        passEditText.setText("");
        /*emailEditText.setText("");
        emailEditText
        passEditText.setText("");*/
        sp = getSharedPreferences("PHCLDATA", MODE_PRIVATE);
        if (sp.contains("username") && sp.contains("password")) {
            startActivity(new Intent(LoginActivity.this, SliderActivity.class));
            finish();
        }
        forgotpwdTextView = (TextView) findViewById(R.id.forgotpwd);
        forgotpwdTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, EmailActivity.class);
                startActivity(intent);

            }
        });
        signTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Input Token");

                final EditText input = new EditText(LoginActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        token = input.getText().toString();
                        String url = ConstantString.URL + "check_token.php?";
                        new CheckTokenTask().execute(url);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

                /*Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);*/
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailEditText.getText().toString().trim();
                pass = passEditText.getText().toString().trim();
                if (emailEditText.getText().toString().length() != 0) {
                    if (passEditText.getText().toString().length() != 0) {
                        if (new InternetConnectionCheck(getApplicationContext()).isOnline()) {
                            String url = ConstantString.URL + "user_login.php?";
                            new MainTask().execute(url);
                        } else
                            Toast.makeText(getApplicationContext(), "Please Check the Internet Connection", Toast.LENGTH_LONG).show();

                    } else {
                        passEditText.setError("Enter Your Password");
                        passEditText.requestFocus();
                    }

                } else {
                    emailEditText.setError("Enter Your Student ID");
                    emailEditText.requestFocus();
                }


            }
        });
    }

    public class MainTask extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String data = "";
        private String Error = null;

        protected void onPreExecute() {
            progressDialog = new ProgressDialog(LoginActivity.this,R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please Wait"); // set message
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
            progressDialog.show();   // NOTE: You can call UI Element here.

            // Start Progress Dialog (Message)
            try {
                data += URLEncoder.encode("email", "UTF-8") + "=" + email;
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + pass;
              /* data += URLEncoder.encode("email", "UTF-8") + "=" + "ansh@gmail.com";
               data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + "123456";*/
                Log.i("urlurlurlurl", "Dataaaaa" + data);
            } catch (UnsupportedEncodingException e) {

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
                Log.e("Content", Content);

            } catch (Exception ex) {
                Error = ex.getMessage();
            } finally {
                try {
                    reader.close();
                } catch (Exception ex) {
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (Error != null) {
            } else {
                JSONObject jsonResponse;
                if (Content != null) {
                    try {
                        jsonResponse = new JSONObject(Content);
                        //{"status":1,"message":"login successfully","name":"cool","lastname":"Sharma","mobile":"1234567890",
                        // "email":"coolram11114@gmail.com","birthday":"2018-06-29"}
                        String status = jsonResponse.getString("status");
                        String message = jsonResponse.getString("message");
                        if (message.equals("login successfully")) {

                            String name = jsonResponse.getString("name");
                            String id = jsonResponse.getString("id");

                            String lastname = jsonResponse.getString("lastname");
                            String mobile = jsonResponse.getString("mobile");
                            String email = jsonResponse.getString("email");
                            String birthday = jsonResponse.getString("birthday");
                            Log.e("status", status);
                            Log.e("message", message);
                            SharedPreferences.Editor e = sp.edit();
                            e.putString("username", email);
                            e.putString("password", pass);
                            e.commit();
                            sharedData = new SharedData(LoginActivity.this);
                            sharedData.putdata(id,email, "0000", lastname, mobile, name, birthday);
                            Toast.makeText(LoginActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(LoginActivity.this, SliderActivity.class);
                            startActivity(in);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid ID or Password or Inactive", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public class CheckTokenTask extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String data = "";
        private String Error = null;

        protected void onPreExecute() {
            progressDialog = new ProgressDialog(LoginActivity.this,R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please Wait"); // set message
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
            progressDialog.show();   // NOTE: You can call UI Element here.

            // Start Progress Dialog (Message)
            try {
                data += URLEncoder.encode("token", "UTF-8") + "=" + token;

                Log.i("urlurlurlurl", "Dataaaaa" + data);
            } catch (UnsupportedEncodingException e) {

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
                Log.e("Content", Content);

            } catch (Exception ex) {
                Error = ex.getMessage();
            } finally {
                try {
                    reader.close();
                } catch (Exception ex) {
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (Error != null) {
            } else {
                JSONObject jsonResponse;
                if (Content != null) {
                    try {
                        jsonResponse = new JSONObject(Content);
                        //{"status":1,"message":"login successfully","name":"cool","lastname":"Sharma","mobile":"1234567890",
                        // "email":"coolram11114@gmail.com","birthday":"2018-06-29"}
                        String status = jsonResponse.getString("status");
                        String message = jsonResponse.getString("message");
                        if (message.equals("check token successfully")) {

                            String id = jsonResponse.getString("id");

                            Log.e("id", id);
                            Log.e("message", message);
                            SharedPreferences.Editor e = sp.edit();
                            e.putString("student_id", id);
                            e.putString("token", token);
                            e.commit();
                            Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
                            startActivity(in);
                            finish();
                        } else {
                            if (message != null && !message.equals("")){
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoginActivity.this, "Invalid Token", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
