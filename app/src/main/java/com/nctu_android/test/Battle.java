package com.nctu_android.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;


public class Battle extends ActionBarActivity {

    String challengeId;
    private ImageView Image1,Image2;
    private int battle = 0;
    String AA;
    String BB;

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

        AA = intent.getStringExtra("A");
        String A = AA.substring(1);

        BB = intent.getStringExtra("B");
        String B = BB.substring(1);



        try {

            String uri = "@drawable/b"+A;
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Image1= (ImageView)findViewById(R.id.imageA);
            Drawable res = getResources().getDrawable(imageResource);
            Image1.setImageDrawable(res);

            uri = "@drawable/b"+B;
            imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Image2= (ImageView)findViewById(R.id.imageB);
            res = getResources().getDrawable(imageResource);
            Image2.setImageDrawable(res);

        }catch (Exception e) {
        }

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
            Log.d("Battle", "{\"challengeId\":\"" + challengeId + "\"," + "\"skill\":" + "\"" + "paper" + "\"}");

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
            Log.d("Battle", "{\"challengeId\":\"" + challengeId + "\"," + "\"skill\":" + "\"" + "scissor" + "\"}");

            //someone challenge
            mSocket.on("resultBattle", onResultBattle);

        }
    };

    //如果使用者按下stone按鈕，則關閉這個activity
    private View.OnClickListener stone = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            attemptSend("sendBattle", "{\"challengeId\":\"" + challengeId + "\"," + "\"skill\":" + "\"" + 2 + "\"}");
            Log.d("Battle", "{\"challengeId\":\"" + challengeId + "\"," + "\"skill\":" + "\"" + "stone" + "\"}");

            //someone challenge
            mSocket.on("resultBattle", onResultBattle);

        }
    };

    //socker get
    public Emitter.Listener onResultBattle = new Emitter.Listener() {
        private String result;

        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        battle = battle + 1 ;
                        JSONObject jo = new JSONObject(args[0].toString());
                        result  = jo.getString("result");

                        Toast t = Toast.makeText(Battle.this, result, Toast.LENGTH_SHORT);
                        t.show();

                        Log.d("Battle",result);

                        Log.d("Battle","AA="+AA);
                        Log.d("Battle","BB="+BB);

                        Intent intent = new Intent();
                        intent.setClass(Battle.this, MapsActivity.class);
                        intent.putExtra("battleresult",result);

                        intent.putExtra("A",AA);
                        intent.putExtra("B",BB);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(intent);

                        onDestroy();

                    } catch (JSONException e) {
                        Log.d("Battle",e.toString());
                        return;
                    }

                }
            });


        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mSocket.disconnect();
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
