package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.luosenen.huel.Core.User.MyUser;
import com.luosenen.huel.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText edName,edPassword;
    private Button btRegister,btLogin;
    private ProgressBar longinBar;
    private TextView app;
    private CheckBox box;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        if (userInfo!=null){
            Toast.makeText(getApplicationContext(),"登录成功：",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            finish();
        }
        app = findViewById(R.id.appName);
        AssetManager assets = getAssets();
        Typeface fromAsset = Typeface.createFromAsset(assets, "font/font.ttf");
        app.setTypeface(fromAsset);
        edName = findViewById(R.id.loginName);
        edPassword = findViewById(R.id.loginPassword);
        longinBar = findViewById(R.id.loginIng);
        btRegister = findViewById(R.id.loginRegister);
        btLogin = findViewById(R.id.login);
        btRegister.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        box = findViewById(R.id.loginCheckBox);
        preferences = getSharedPreferences("user",Context.MODE_PRIVATE);
        editor = preferences.edit();
        edName.setText(preferences.getString("name",""));
        edPassword.setText(preferences.getString("pass",""));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                longinBar.setVisibility(View.VISIBLE);
                MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
                if (userInfo!=null){

                    Toast.makeText(getApplicationContext(),"登录成功：",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }else {
                    final String name = edName.getText().toString().trim();
                    final String pass = edPassword.getText().toString().trim();
                    if (name.equals(null) || pass.equals(null)) {
                        return;
                    }
                    MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(pass);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            if (e == null) {
                                if (box.isChecked()) {
                                    editor.putString("name", name);
                                    editor.putString("pass", pass);
                                    editor.commit();
                                } else {
                                    editor.putString("pass", "");
                                    editor.clear();
                                    editor.commit();
                                }
                                longinBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "登录成功：", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                                finish();
                            } else {
                                longinBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "登录失败：" + e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                break;

            case R.id.loginRegister:
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                break;

        }
    }
}
