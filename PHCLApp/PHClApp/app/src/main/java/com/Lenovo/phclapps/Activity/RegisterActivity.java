package com.Lenovo.phclapps.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class RegisterActivity extends AppCompatActivity {
    EditText firstEditText, lastEditText, contactEditText, emailEditText, passEditText, comEditText, BirEditText, idEditText, idpass, course;
    Button signButton;
    String firstname, lastname, contactno, stdId, email, password, confirtpass, Birthdate, Content, Course;
    Calendar c;
    int year, month, day;
    SharedData sharedData;
    private EditText etstdId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        signButton = (Button) findViewById(R.id.btun);
        // idEditText=(EditText)findViewById(R.id.id);
        firstEditText = (EditText) findViewById(R.id.fir_name);
        //  idpass=(EditText)findViewById(R.id.id_pass);
        lastEditText = (EditText) findViewById(R.id.last_name);
        contactEditText = (EditText) findViewById(R.id.con_num);
        emailEditText = (EditText) findViewById(R.id.email);
        passEditText = (EditText) findViewById(R.id.password);
        comEditText = (EditText) findViewById(R.id.com_pass);
        BirEditText = (EditText) findViewById(R.id.birt_date);
        etstdId = (EditText) findViewById(R.id.stdId);
        course = (EditText) findViewById(R.id.course);

        BirEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dd = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                try                     //0000-00-00
                                {

                                    Calendar calendar =Calendar.getInstance();
                                    int currentYear= calendar.get(Calendar.YEAR);
                                    if( (currentYear-year) > 17 ){
                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                        //  String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                        String dateInString = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                        Date date = formatter.parse(dateInString);
                                        formatter = new SimpleDateFormat("yyyy-MM-dd");
                                        BirEditText.setText(/*todate.getText().toString() + "\n" +*/ formatter.format(date).toString());
                                        BirEditText.setText(BirEditText.getText().toString() /*+ "\n" + formatter.format(date).toString()*/);
                                        // dates = Edate.getText().toString();
                                }
                                } catch (Exception ex) {
                                }
                            }
                        }, year, month, day);
                dd.show();
            }
        });
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                firstname = firstEditText.getText().toString().trim();
                lastname = lastEditText.getText().toString().trim();
                contactno = contactEditText.getText().toString().trim();
                email = emailEditText.getText().toString().trim();
                stdId = etstdId.getText().toString();
                password = passEditText.getText().toString().trim();
                confirtpass = comEditText.getText().toString().trim();
                Birthdate = BirEditText.getText().toString().trim();
                Course = course.getText().toString();



                if (etstdId.getText().toString().length() != 0) {
                    if (firstEditText.getText().toString().length() != 0) {
                        if (lastEditText.getText().toString().length() != 0) {
                            if (isValidEmail(emailEditText.getText().toString())) {
                                if (contactEditText.getText().length() == 11) {
                                    if (passEditText.getText().toString().length() != 0 && passEditText.getText().toString().length() > 7) {
                                        if (comEditText.getText().toString().equals(passEditText.getText().toString())) {
                                            if (BirEditText.getText().toString().length() != 0 ) {

                                                if (course.getText().toString().length() != 0) {
                                                    if (new InternetConnectionCheck(getApplicationContext()).isOnline()) {
                                                        String url = ConstantString.URL + "user_signup.php?";
                                                        new MainTask().execute(url);
                                                    } else
                                                        Toast.makeText(getApplicationContext(), "Please Check the Internet Connection", Toast.LENGTH_LONG).show();
                                                }else{
                                                    course.setError("Select Course");
                                                    course.requestFocus();
                                                }

                                            } else {
                                                BirEditText.setError("Enter Your Birthdate");
                                                BirEditText.requestFocus();
                                            }

                                        } else {
                                            comEditText.setError("Must be the same as Password");
                                            comEditText.requestFocus();
                                        }


                                    } else {
                                        passEditText.setError("Password must be minimum of 8 character");
                                        passEditText.requestFocus();
                                    }

                                } else {
                                    contactEditText.setError("Please enter correct phone Number");
                                    contactEditText.requestFocus();
                                }

                            } else {
                                emailEditText.setError("Enter Your Email Address");
                                emailEditText.requestFocus();
                            }


                        } else {
                            lastEditText.setError("Enter Your last Name");
                            lastEditText.requestFocus();
                        }
                    } else {
                        firstEditText.setError("Enter Your First Name");
                        firstEditText.requestFocus();
                    }


                } else {
                    etstdId.setError("Enter Your Student Id");
                    etstdId.requestFocus();
                }
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(RegisterActivity.this);
                final CharSequence items[] = new CharSequence[] {"College of Law", "College of Criminology"};
                adb.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface d, int n) {
                        course.setText(items[n]);
                        d.dismiss();
                    }

                });
                adb.setNegativeButton("Cancel", null);
                adb.setTitle("Select Course");
                adb.show();
            }
        });

        SharedPreferences sp = getSharedPreferences("PHCLDATA", MODE_PRIVATE);
        etstdId.setText(sp.getString("student_id", ""));
//        etstdId.setEnabled(false);
    }

    public class MainTask extends AsyncTask<String, Void, String> {
        String data = "";
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(RegisterActivity.this,R.style.MyAlertDialogStyle);

        protected void onPreExecute() {
            // NOTE: You can call UI Element here.
            // Start Progress Dialog (Message)
            Dialog.setMessage("Please wait..");
            Dialog.show();
            Dialog.setCancelable(false);
            try {
                data += URLEncoder.encode("email", "UTF-8") + "=" + email;
                data += "&" + URLEncoder.encode("studid", "UTF-8") + "=" + stdId;
                data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + firstname;
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + password;
                data += "&" + URLEncoder.encode("mobile", "UTF-8") + "=" + contactno;
                data += "&" + URLEncoder.encode("lastname", "UTF-8") + "=" + lastname;
                data += "&" + URLEncoder.encode("course", "UTF-8") + "=" + Course;
                data += "&" + URLEncoder.encode("token", "UTF-8") + "=" + getSharedPreferences("PHCLDATA", MODE_PRIVATE).getString("token", "");
                data += "&" + URLEncoder.encode("birthday", "UTF-8") + "=" + BirEditText.getText().toString();
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
                Log.e("Content>>>>>>>>>>>>>>>", Content);

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

            if (Error != null) {
            } else {
                Dialog.dismiss();
                JSONObject jsonResponse;
                if (Content != null) {
                    try {
                        jsonResponse = new JSONObject(Content);
                        String status = jsonResponse.getString("status");
                        String message = jsonResponse.getString("message");
                        Log.e("statusRegister", status);
                        Log.e("messageRegister", message);
                        if (message.equals("insert successfully")) {
                            Toast.makeText(RegisterActivity.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(in);
                            finish();
                        } else {
//                            if (message.equals("email already exist")) {
                                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
//                            } else if (message.equals("Student Id is incorrect")) {
//                                Toast.makeText(RegisterActivity.this, "Student Id is incorrect", Toast.LENGTH_SHORT).show();
//                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
