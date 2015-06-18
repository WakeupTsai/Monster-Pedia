package com.nctu_android.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends Activity {
    private Button login;
    private String result="0";
    private String account;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(loginBtn);
    }

    OnClickListener loginBtn = new OnClickListener() {
        @Override
        //username,password
        public void onClick(View v) {

            //postData();

            Thread thread = new Thread(){
                public void run(){

                    result = postData();
                }
            };
            thread.start();
            try {
                thread.join();

            } catch (InterruptedException e) {
                result = "0";
            }

            //Toast t = Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT);
            //t.show();

            if (Integer.parseInt(result)==1) {
                Toast t = Toast.makeText(LoginActivity.this, "log in", Toast.LENGTH_SHORT);
                t.show();
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MapsActivity.class);
                startActivity(intent);
            }
            else {
                Toast t = Toast.makeText(LoginActivity.this, "try again", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    };



    public String postData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://apppp.ngrok.io/api/user/login");



        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            EditText accountText = (EditText) findViewById(R.id.account);
            account = accountText.getText().toString();

            EditText passwordText = (EditText) findViewById(R.id.password);
            password = passwordText.getText().toString();

            nameValuePairs.add(new BasicNameValuePair("username", account));
            nameValuePairs.add(new BasicNameValuePair("password", password));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            String result = EntityUtils.toString(response.getEntity());
            return result;

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Fail";

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Fail";
        }
    }
}
