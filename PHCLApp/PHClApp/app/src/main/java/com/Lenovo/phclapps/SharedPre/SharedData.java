package com.Lenovo.phclapps.SharedPre;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedData {
    String username="username";
    String password="password";
    String lastname="lastname";
    String mobile="mobile";
    String name="name";
    String birthday="birthday";
    SharedPreferences sharedPreferences ;
    //: {"status":1,"message":"login successfully","name":"cool","lastname":"Sharma",
    // "mobile":"1234567890","email":"coolram11114@gmail.com","birthday":"2018-06-29"}
    SharedPreferences.Editor editor;
    Context context;
    private String PREF_NAME = "PHCLAPPDATA";
    public SharedData(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public void putdata(String id,String username, String password,String lastname,String mobile,String name,String birthday) {
        editor.putString("username",username);
        editor.putString("password",password);
        editor.putString("lastname",lastname);
        editor.putString("id",id);
        editor.putString("mobile",mobile);
        editor.putString("name",name);
        editor.putString("birthday",birthday);
        editor.commit();
     }
    public void putdataNew(String firstname,String lastname,String mobile,String birthday) {
        editor.putString("name",firstname);
        editor.putString("lastname",lastname);
        editor.putString("mobile",mobile);
        editor.putString("birthday",birthday);
        editor.commit();
    }
    public String getId() {
        return sharedPreferences.getString("id","");
    }

    public String getUsername() {
        return sharedPreferences.getString("username","");
    }
    public String getPassword() {
        return sharedPreferences.getString("password","");
    }
    public String getLastname() {
        return sharedPreferences.getString("lastname","");
    }
    public String getMobile() {
        return sharedPreferences.getString("mobile","");
    }
    public String getName() {
        return sharedPreferences.getString("name","");
    }
    public String getBirthday() {
        return sharedPreferences.getString("birthday","");
    }


}
