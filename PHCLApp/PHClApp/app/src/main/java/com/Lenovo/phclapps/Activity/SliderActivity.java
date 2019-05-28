package com.Lenovo.phclapps.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import com.Lenovo.phclapps.Adapter.LawAdapter;
import com.Lenovo.phclapps.Adapter.LawModel;
import com.Lenovo.phclapps.Adapter.ListViewAdapter;
import com.Lenovo.phclapps.Adapter.Model;
import com.Lenovo.phclapps.InternetCheck.InternetConnectionCheck;
import com.Lenovo.phclapps.R;
import com.Lenovo.phclapps.SharedPre.ConstantString;
import com.Lenovo.phclapps.SharedPre.SharedData;
import com.Lenovo.phclapps.Splash.SpScreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SliderActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String Content;
    CategoryAdapter adapter;
    private RecyclerView recyclerView;

    ArrayList<Model> arrayList = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList.clear();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (new InternetConnectionCheck(getApplicationContext()).isOnline()) {
            String url = ConstantString.URL + "get_categoryname.php?";
            new MainTask().execute(url);
        } else
            Toast.makeText(getApplicationContext(), "Please Check the Internet Connection", Toast.LENGTH_LONG).show();
        setTitle("PHCL App");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public class MainTask extends AsyncTask<String, Void, String> {
        String data = "";
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(SliderActivity.this,R.style.MyAlertDialogStyle);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Dialog.setMessage("Please wait..");
            Dialog.show();
            Dialog.setCancelable(false);
            arrayList.clear();
            try {
                data += URLEncoder.encode("category", "UTF-8") + "=" + "";
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
            Dialog.dismiss();
            if (Error != null) {
            } else {
                JSONObject jsonResponse;
                if (Content != null) {
                    try {
                        jsonResponse = new JSONObject(Content);
                        JSONArray userdetails = jsonResponse.getJSONArray("result");
                        Log.e("userdetails", String.valueOf(userdetails) + userdetails.length());
                        for (int i = 0; i < userdetails.length(); i++) {
                            JSONObject user = userdetails.getJSONObject(i);
                            arrayList.add(new Model(user.getString("category_code"), user.getString("category_id"), user.getString("category_name")));
                            Log.e("modelCategories", String.valueOf(arrayList));
                        }
                        adapter = new CategoryAdapter(SliderActivity.this, arrayList);


                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Config.cateList = arrayList;
                    /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent in=new Intent(SliderActivity.this,CategoryDetails.class);
                            in.putExtra("category",arrayList.get(i).getDesc());
                            startActivity(in);
                        }
                    });*/


                }
            }
        }
    }
 /*   @Override
    protected void onResume() {
        if (new InternetConnectionCheck(getApplicationContext()).isOnline()) {
            String url = ConstantString.URL+"get_categoryname.php?";
            new MainTask().execute(url);
        } else
            Toast.makeText(getApplicationContext(), "Please Check the Internet Connection", Toast.LENGTH_LONG).show();
        super.onResume();
    }*/

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            //   finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            /*
                finishAffinity();
                finish();*/
            }
        }, 1000);
     /*   DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter.filter("");
                } else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Log.e("vikas", "vikassssssss");
                if (new InternetConnectionCheck(getApplicationContext()).isOnline()) {
                    String url = ConstantString.URL + "get_categoryname.php?";
                    new MainTask().execute(url);
                } else
                    Toast.makeText(getApplicationContext(), "Please Check the Internet Connection", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent in = new Intent(SliderActivity.this, InformationActivity.class);
            startActivity(in);
        }
        if (id == R.id.nav_slideshow) {
            Intent in = new Intent(SliderActivity.this, AboutActivity.class);
            startActivity(in);
        }
        if (id == R.id.fav) {
            Intent in = new Intent(SliderActivity.this, FavouriteLawActivity.class);
            startActivity(in);
        }

        if (id == R.id.nav_gallery) {
          SharedPreferences sp = getSharedPreferences("PHCLDATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor e = sp.edit();
            e.clear();
            e.commit();


            Intent i = new Intent(SliderActivity.this,LoginActivity.class);
            startActivity(i);

            finish();



        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
