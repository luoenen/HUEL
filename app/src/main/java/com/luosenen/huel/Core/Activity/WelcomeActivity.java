package com.luosenen.huel.Core.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.luosenen.huel.R;

import cn.bmob.v3.Bmob;

public class WelcomeActivity extends AppCompatActivity {

    private Handler handler;
    private static int TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bmob.initialize(this, "6fffef8fa4da75745b78ae17cfb3bb46");
        setContentView(R.layout.activity_welcome);
        startLogin(TIME);

    }

    public void startLogin(int time){
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
            }
        },time);
    }
}
