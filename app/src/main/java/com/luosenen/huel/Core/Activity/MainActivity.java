package com.luosenen.huel.Core.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.luosenen.huel.R;

public class MainActivity extends Activity implements View.OnClickListener {

     String[]  permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private Button btFirst,btSecond,btThird,btLove,index,school,myself;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPower();
        btLove = findViewById(R.id.love);
        btFirst = findViewById(R.id.first);
        btSecond = findViewById(R.id.second);
        btThird = findViewById(R.id.third);
        index = findViewById(R.id.index);
        school = findViewById(R.id.school);
        myself = findViewById(R.id.myself);
        index.setOnClickListener(this);
        school.setOnClickListener(this);
        myself.setOnClickListener(this);
        btFirst.setOnClickListener(this);
        btSecond.setOnClickListener(this);
        btThird.setOnClickListener(this);
        btLove.setOnClickListener(this);
    }

    public void requestPower() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        1);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.love:
                startActivity(new Intent(getApplicationContext(),LoveActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;

            case R.id.first:
                startActivity(new Intent(getApplicationContext(),EatOneActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;
            case R.id.second:
                startActivity(new Intent(getApplicationContext(),EatTwoActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;
            case R.id.third:
                startActivity(new Intent(getApplicationContext(),EatThreeActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;

            case R.id.index:

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;

            case R.id.school:
                startActivity(new Intent(getApplicationContext(),SchoolActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;

            case R.id.myself:
                startActivity(new Intent(getApplicationContext(),MySelfActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;
        }
    }
}
