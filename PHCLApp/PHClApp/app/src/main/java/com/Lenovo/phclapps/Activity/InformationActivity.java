package com.Lenovo.phclapps.Activity;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InformationActivity extends AppCompatActivity {
    Button updateButton;
    String username, Content;
    EditText firnameEditText, lastnameEditText, contactEditText, emailEditText, birthEditText;
    Calendar c;
    int year, month, day;
    SharedData sharedData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_informationfrag);
        setTitle("Information");
        ActionBar actionBar = getSupportActionBar();
        sharedData=new SharedData(InformationActivity.this);
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        sharedData = new SharedData(InformationActivity.this);
        username = sharedData.getUsername();
        String name = sharedData.getName();
        String lastname = sharedData.getLastname();
        String mobile = sharedData.getMobile();
        String birthday = sharedData.getBirthday();
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        setTitle("Information");
        updateButton = (Button) findViewById(R.id.btun);
        firnameEditText = (EditText) findViewById(R.id.fir_name);
        lastnameEditText = (EditText) findViewById(R.id.last_name);
        contactEditText = (EditText) findViewById(R.id.con_num);
        emailEditText = (EditText) findViewById(R.id.email);
        birthEditText = (EditText) findViewById(R.id.birt_date);
        birthEditText.setText(birthday);
        emailEditText.setText(username);
        contactEditText.setText(mobile);
        lastnameEditText.setText(lastname);
        firnameEditText.setText(name);
// perform setOnClickListener on first Button
      /*  firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// display a message by using a Toast
                Toast.makeText(getActivity(), "First Fragment", Toast.LENGTH_LONG).show();
            }
        });*/
        birthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dd = new DatePickerDialog(InformationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                try {
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    //  String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                    String dateInString = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                    Date date = formatter.parse(dateInString);
                                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    birthEditText.setText(/*todate.getText().toString() + "\n" +*/ formatter.format(date).toString());
                                    birthEditText.setText(birthEditText.getText().toString() /*+ "\n" + formatter.format(date).toString()*/);
                                    // dates = Edate.getText().toString();
                                } catch (Exception ex) {
                                }
                            }
                        }, year, month, day);
                dd.show();
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = ConstantString.URL + "update_profile.php?";
                new MainTask().execute(url);
            }
        });

    }


    public class MainTask extends AsyncTask<String, Void, String> {
        String data = "";
        private String Error = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Start Progress Dialog (Message)
            try {
                data += URLEncoder.encode("name", "UTF-8") + "=" + firnameEditText.getText().toString();
                data += "&" + URLEncoder.encode("lastname", "UTF-8") + "=" + lastnameEditText.getText().toString();
                data += "&" + URLEncoder.encode("mobile", "UTF-8") + "=" + contactEditText.getText().toString();
                data += "&" + URLEncoder.encode("birthday", "UTF-8") + "=" + birthEditText.getText().toString();
                data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + username;
              /* data += URLEncoder.encode("email", "UTF-8") + "=" + "ansh@gmail.com";
               data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + "123456";*/
                Log.i("urlurlurlurl", "Dataaaaa" + data);
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
            if (Error != null) {
            } else {
                JSONObject jsonResponse;
                if (Content != null) {
                    try {
                        jsonResponse = new JSONObject(Content);
                        String status = jsonResponse.getString("status");
                        String message = jsonResponse.getString("message");
                        Log.e("status", status);
                        Log.e("message", message);
                        if (message.equals("profile update successfully")) {
                            Toast.makeText(InformationActivity.this, "Information Update Successfully", Toast.LENGTH_SHORT).show();
                            //finish();
                            sharedData.putdataNew(firnameEditText.getText().toString(),lastnameEditText.getText().toString(),contactEditText.getText().toString(),birthEditText.getText().toString());



                        } else {
                            //   Toast.makeText(Forgotpassword.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
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
