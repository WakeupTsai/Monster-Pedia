package com.nctu_android.test;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Button;

//bag的activity
public class BagActivity extends Activity {

    //使用第一次作業顯示相簿的方法，將已收集的monster展現出來
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        Button backbtn = (Button)findViewById(R.id.backbtn);
        backbtn.setOnClickListener(back);

        Intent intent = getIntent();
        int [] ids = intent.getIntArrayExtra("KEY_IDS");
        int cols = intent.getIntExtra("KEY_COLUMNS", 3);

        Integer [] imglist = new Integer[ids.length];
        for (int i = 0; i < ids.length; i++) {
            imglist[i] = Integer.valueOf(ids[i]);
        }

        GridView gv = (GridView)findViewById(R.id.gv);
        gv.setNumColumns(cols);
        gv.setAdapter(new ImageAdapter(this, imglist));


    }

    public class ImageAdapter extends ArrayAdapter<Integer> {

        private Context mCtx;

        public ImageAdapter(Context c, Integer [] imglist) {
            super(c, 0, imglist);
            mCtx = c;
        }

        @Override
        public View getView(int position, View convertView,ViewGroup parent) {
            ImageView iv = new ImageView(mCtx);
            int resid = getItem(position);
            iv.setImageResource(resid);
            iv.setAdjustViewBounds(true);
            return iv;
        }
    }

    //如果使用者按下back按鈕，則關閉這個activity
    private View.OnClickListener back = new OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}