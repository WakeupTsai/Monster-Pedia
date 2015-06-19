package com.nctu_android.test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;


public class Battle extends ActionBarActivity {

    String challengeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        ImageButton paperbtn = (ImageButton)findViewById(R.id.paper);
        paperbtn.setOnClickListener(paper);

        ImageButton scissorbtn = (ImageButton)findViewById(R.id.scissor);
        scissorbtn.setOnClickListener(scissor);

        ImageButton stonebtn = (ImageButton)findViewById(R.id.stone);
        stonebtn.setOnClickListener(stone);

        Intent intent = getIntent();
        challengeId = intent.getStringExtra("challengeId");
        Log.d("DEBUG",challengeId);
    }

    //socket
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://140.113.66.20:5000");
        } catch (URISyntaxException e) {}
    }

    //socket send
    private void attemptSend(String key, String value) {

        mSocket.emit(key, value);
    }

    //如果使用者按下paper按鈕，則關閉這個activity
    private View.OnClickListener paper = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            attemptSend("sendBattle","{\"challengeId\":\""+challengeId+"\","+"\"skill\":"+"\""+0+"\"}");
            Log.d("DEBUG", "{\"challengeId\":\"" + challengeId + "\"," + "\"skill\":" + "\"" + 0 + "\"}");

            //someone challenge
            mSocket.on("resultBattle", onResultBattle);

            //finish();
        }
    };

    //如果使用者按下scissor按鈕，則關閉這個activity
    private View.OnClickListener scissor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            attemptSend("sendBattle", "{\"challengeId\":\"" + challengeId + "\"," + "\"skill\":" + "\"" + 1 + "\"}");
            Log.d("DEBUG", "{\"challengeId\":\"" + challengeId + "\"," + "\"skill\":" + "\"" + 1 + "\"}");

            //someone challenge
            mSocket.on("resultBattle", onResultBattle);

        }
    };

    //如果使用者按下stone按鈕，則關閉這個activity
    private View.OnClickListener stone = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            attemptSend("sendBattle", "{\"challengeId\":\"" + challengeId + "\"," + "\"skill\":" + "\"" + 2 + "\"}");
            Log.d("DEBUG", "{\"challengeId\":\"" + challengeId + "\"," + "\"skill\":" + "\"" + 2 + "\"}");

            //someone challenge
            mSocket.on("resultBattle", onResultBattle);

        }
    };

    //socker get
    public Emitter.Listener onResultBattle = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jo = new JSONObject(args[0].toString());
                        final String result  = jo.getString("result");

                        Log.d("DEBUG",result);

                        Toast t = Toast.makeText(Battle.this, result, Toast.LENGTH_SHORT);
                        t.show();

                        Intent intent = new Intent();
                        intent.setClass(Battle.this, MapsActivity.class);
                        startActivity(intent);

                    } catch (JSONException e) {
                        Log.d("DEBUG",e.toString());
                        return;
                    }

                }
            });
        }
    };

}
