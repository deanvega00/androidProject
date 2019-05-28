package com.Lenovo.phclapps.Activity;


import com.Lenovo.phclapps.Adapter.Model;

import java.util.List;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/*class Config
{
    public static final String EMAIL ="coolram1114@gmail.com";
    public static final String PASSWORD ="ramsharma123";
}*/
class Config extends Authenticator {
    String user;
    String pw;
    public static List<Model> cateList;
    public Config (String username, String password)
    {
        super();
        this.user = username;
        this.pw = password;
    }
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(user, pw);
    }
}