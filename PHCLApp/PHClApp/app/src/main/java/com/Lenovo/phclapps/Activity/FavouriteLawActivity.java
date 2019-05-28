package com.Lenovo.phclapps.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.Lenovo.phclapps.Adapter.LawAdapter;
import com.Lenovo.phclapps.Adapter.LawModel;
import com.Lenovo.phclapps.InternetCheck.InternetConnectionCheck;
import com.Lenovo.phclapps.R;
import com.Lenovo.phclapps.SharedPre.ConstantString;
import com.Lenovo.phclapps.SharedPre.SharedData;

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
import java.util.HashMap;

public class FavouriteLawActivity extends AppCompatActivity {
    //    ListView listView;
    String Content, category;
    LawAdapter adapter;
    TextView emptyview;

    ArrayList<LawModel> arrayList = new ArrayList<LawModel>();
    private ProgressDialog Dialogs;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_list);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Search");
        emptyview = findViewById(R.id.emptyview);
//        listView = (ListView) findViewById(R.id.listView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Bundle bundle = getIntent().getExtras();
        if (new InternetConnectionCheck(getApplicationContext()).isOnline()) {
            String url = ConstantString.URL + "get_all_fav.php?";
            new MainTasks().execute(url);
        } else
            Toast.makeText(getApplicationContext(), "Please Check the Internet Connection", Toast.LENGTH_LONG).show();
    }

    public class MainTasks extends AsyncTask<String, Void, String> {
        String data = "";
        private String Error = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SharedData sharedData = new SharedData(FavouriteLawActivity.this);
            try {
                data += URLEncoder.encode("stdId", "UTF-8") + "=" + sharedData.getId();

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
            if (Error != null) {
            } else {
                JSONObject jsonResponse;
                if (Content != null) {
                    try {
                        jsonResponse = new JSONObject(Content);

                        JSONArray categorylaw = jsonResponse.getJSONArray("result");

                        for (int i = 0; i < categorylaw.length(); i++) {
                            JSONObject user = categorylaw.getJSONObject(i);
                            LawModel model = new LawModel(user.getString("category_name"), user.getString("category_detail"), user.getString("category"));
                            model.setLawId(user.getInt("id"));
                            model.setIsfavt(true);
                            arrayList.add(model);
                        }

                        adapter = new LawAdapter(FavouriteLawActivity.this, arrayList, new LawAdapter.ClickListener() {
                            @Override
                            public void onFavClick(final int pos, LawModel model) {
                                Dialogs = new ProgressDialog(FavouriteLawActivity.this,R.style.MyAlertDialogStyle);
                                Dialogs.setMessage("Please wait..");
                                Dialogs.show();
                                Dialogs.setCancelable(false);

                                SharedData sharedData = new SharedData(FavouriteLawActivity.this);
                                new FavTask(String.valueOf(model.getLawId()), sharedData.getId()) {
                                    @Override
                                    protected void onPreExecute() {
                                        super.onPreExecute();
                                    }

                                    @Override
                                    protected void onPostExecute(String s) {
//                                        handleFavResponse(s, pos);
                                        super.onPostExecute(s);
                                    }
                                }.execute("", "");
                            }

                            @Override
                            public void onFavDeleteClick(final int pos, LawModel model) {
                                Dialogs = new ProgressDialog(FavouriteLawActivity.this,R.style.MyAlertDialogStyle);
                                Dialogs.setMessage("Please wait..");
                                Dialogs.show();
                                Dialogs.setCancelable(false);

                                SharedData sharedData = new SharedData(FavouriteLawActivity.this);
                                new DeleteFavTask(String.valueOf(model.getLawId()), sharedData.getId()) {
                                    @Override
                                    protected void onPreExecute() {
                                        super.onPreExecute();
                                    }

                                    @Override
                                    protected void onPostExecute(String s) {
                                        handleDeleteFavResponse(s, pos);
                                        super.onPostExecute(s);
                                    }
                                }.execute("", "");
                            }
                        });

                        //bind the adapter to the listview
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        if (arrayList.size() == 0) {
                            emptyview.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            recyclerView.setVisibility(View.VISIBLE);

                            emptyview.setVisibility(View.GONE);

                        }
                    } catch (JSONException e) {
                        emptyview.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);

                        e.printStackTrace();
                    }


                }
            }
        }
    }

    private void handleFavResponse(String s, int pos) {
        Dialogs.dismiss();
        if (s != null) {
            JSONObject jsonResponse;
            if (s != null) {
                try {
                    jsonResponse = new JSONObject(s);
                    String status = jsonResponse.getString("status");
                    String message = jsonResponse.getString("message");
                    Log.e("statusRegister", status);
                    Log.e("messageRegister", message);
                    if (message.equalsIgnoreCase("insert successfully")) {
                        arrayList.get(pos).setIsfavt(true);
                        adapter.notifyDataSetChanged();
                    }
                    Toast.makeText(FavouriteLawActivity.this, message, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(FavouriteLawActivity.this, "Error In favourite", Toast.LENGTH_LONG).show();

        }

    }

    private void handleDeleteFavResponse(String s, int pos) {
        Dialogs.dismiss();
        if (s != null) {
            JSONObject jsonResponse;
            if (s != null) {
                try {
                    jsonResponse = new JSONObject(s);
                    String status = jsonResponse.getString("status");
                    String message = jsonResponse.getString("message");
                    Log.e("statusRegister", status);
                    Log.e("messageRegister", message);
                    if (message.equalsIgnoreCase("Deleted successfully")) {
                        arrayList.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                    if (arrayList.size() == 0) {
                        emptyview.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {
                        emptyview.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                    Toast.makeText(FavouriteLawActivity.this, message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(FavouriteLawActivity.this, "Error In favourite", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu11, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (adapter != null && arrayList.size() > 0) {
                    if (TextUtils.isEmpty(s)) {
                        adapter.filter("");
//                        recyclerView.clea();
                    } else {
                        adapter.filter(s);

                    }
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
