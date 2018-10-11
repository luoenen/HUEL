package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.luosenen.huel.Core.User.MyUser;
import com.luosenen.huel.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends Activity {

    private EditText edName,edPassword;
    private Button btRegister;
    private ProgressBar registerBar;
    private TextView app;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        app = findViewById(R.id.appName);
        AssetManager assets = getAssets();
        Typeface fromAsset = Typeface.createFromAsset(assets, "font/font.ttf");
        app.setTypeface(fromAsset);
        edName = findViewById(R.id.registerName);
        edPassword = findViewById(R.id.registerPassword);
        registerBar = findViewById(R.id.registerIng);
        btRegister = findViewById(R.id.register);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(edName.getText().toString().trim(),edPassword.getText().toString().trim());
            }
        });
    }

    public void register(String name,String password){
        registerBar.setVisibility(View.VISIBLE);
        MyUser user = new MyUser();
        user.setUsername(name);
        user.setPassword(password);
        user.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e==null){
                    registerBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"注册成功：",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }else {
                    registerBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"注册失败："+e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
