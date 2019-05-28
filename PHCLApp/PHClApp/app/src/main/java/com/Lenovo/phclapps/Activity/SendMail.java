package com.Lenovo.phclapps.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;


public class SendMail extends AsyncTask<Void,Void,Void>
{

    //Declaring Variables
    private Context context;
    private Session session;
    final String EMAIL ="phclapp10@gmail.com";
    final String PASSWORD =" @l03e1t3";
   /* //as0417716@gmail.com
    digital@2018
    phclapp10@gmail.com
    @l03e1t3
*/
    //Information to send email
    private String email;
    private String subject;
    private String message;

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public SendMail(Context context, String email, String subject, String message)
    {
        //Initializing variables
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context,"Sending message","Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
        Toast.makeText(context,"Message Sent",Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Success!")
                .setMessage("Check the 6-digit verification number on your email.")
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        Intent in=new Intent(context,Forgotpassword.class);
                        context.startActivity(in);
                        }
                })
                //  .setIcon(android.R.drawable.ic_dialog_alert)
               .show();
        //sendEmail();

    }

    @Override
    protected Void doInBackground(Void... params)
    {
      /*  try
        {
            SSLContext sslcontext = SSLContext.getInstance("TLSv1");
            sslcontext.init(null,null,null);
            SSLSocketFactory NoSSLv3Factory = new NoSSLv3SocketFactory(sslcontext.getSocketFactory());
            HttpsURLConnection.setDefaultSSLSocketFactory(NoSSLv3Factory);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //errorWrite.getStackTrace(e);
        }*/
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");//ssl
        // props.put("mail.smtp.socketFactory.port", "587");//tsl
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        // props.put("mail.smtp.port", "587");
        props.put("mail.smtp.port", "465");

       // Session session = Session.getInstance(props, new Config(EMAIL, PASSWORD));
        Session session = Session.getDefaultInstance(props, new Config(EMAIL, PASSWORD));
        //Creating a new session
       /* session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                    }
                });*/

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress(EMAIL));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adding subject
            mm.setSubject(subject);
            //Adding message
            mm.setText(message);

            //Sending email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}