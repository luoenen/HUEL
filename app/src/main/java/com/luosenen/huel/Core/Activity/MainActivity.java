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
import android.widget.TextView;
import android.widget.Toast;

import com.luosenen.huel.Core.MyFiles.Life;
import com.luosenen.huel.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends Activity implements View.OnClickListener {

     String[]  permissions = new String[]{
             Manifest.permission.CAMERA,
             Manifest.permission.WRITE_EXTERNAL_STORAGE,
             Manifest.permission.CALL_PHONE,
             Manifest.permission.READ_EXTERNAL_STORAGE,
             Manifest.permission.WRITE_EXTERNAL_STORAGE,
             Manifest.permission.ACCESS_FINE_LOCATION,
             Manifest.permission.ACCESS_COARSE_LOCATION,
             Manifest.permission.READ_PHONE_STATE,
             Manifest.permission.ACCESS_WIFI_STATE,
             Manifest.permission.ACCESS_NETWORK_STATE,
             Manifest.permission.WRITE_EXTERNAL_STORAGE,
             Manifest.permission.INTERNET,
             Manifest.permission.CHANGE_WIFI_STATE
    };

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static TextView life;
    private Button btFirst,btSecond,btThird,btLove,index,school,myself,express,shop,car,find,lifeb,tv;
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
        life = findViewById(R.id.lifeText);
        express = findViewById(R.id.fourth);
        shop = findViewById(R.id.fifth);
        car = findViewById(R.id.seventh);
        find = findViewById(R.id.find);
        lifeb = findViewById(R.id.talk);
        tv = findViewById(R.id.sixth);
        tv.setOnClickListener(this);
        lifeb.setOnClickListener(this);
        find.setOnClickListener(this);
        car.setOnClickListener(this);
        shop.setOnClickListener(this);
        express.setOnClickListener(this);
        express.setOnClickListener(this);
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

                startActivity(new Intent(getApplicationContext(),BookActivity.class));
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
            case R.id.fourth:
                startActivity(new Intent(getApplicationContext(),ExpressActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;
            case R.id.fifth:
                startActivity(new Intent(getApplicationContext(),ShopActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;
            case R.id.seventh:
                startActivity(new Intent(getApplicationContext(),CarActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;
            case R.id.find:
                startActivity(new Intent(getApplicationContext(),FindActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;

            case R.id.talk:
                startActivity(new Intent(getApplicationContext(),LifeActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;

            case R.id.sixth:
                startActivity(new Intent(getApplicationContext(),TvActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;
        }
    }
}
