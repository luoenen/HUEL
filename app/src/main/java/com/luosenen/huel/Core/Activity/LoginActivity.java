package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
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
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText edName, edPassword;
    private Button btRegister, btLogin, btQQ;
    private ProgressBar longinBar;
    private TextView app;
    private CheckBox box;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static final String TAG = "LoginActivity";
    private static final String APP_ID = "101508791";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        if (userInfo != null) {
            Toast.makeText(getApplicationContext(), "登录成功：", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            finish();
        }

        mTencent = Tencent.createInstance(APP_ID,LoginActivity.this.getApplicationContext());
        mIUiListener = new BaseUiListener();

        app = findViewById(R.id.appName);
        AssetManager assets = getAssets();
        Typeface fromAsset = Typeface.createFromAsset(assets, "font/font.ttf");
        app.setTypeface(fromAsset);
        edName = findViewById(R.id.loginName);
        edPassword = findViewById(R.id.loginPassword);
        longinBar = findViewById(R.id.loginIng);
        btRegister = findViewById(R.id.loginRegister);
        btLogin = findViewById(R.id.login);
        btQQ = findViewById(R.id.loginQQ);
        btQQ.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        box = findViewById(R.id.loginCheckBox);
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = preferences.edit();
        edName.setText(preferences.getString("name", ""));
        edPassword.setText(preferences.getString("pass", ""));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                longinBar.setVisibility(View.VISIBLE);
                MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
                if (userInfo != null) {

                    Toast.makeText(getApplicationContext(), "登录成功：", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
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
                                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
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
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                break;
            case R.id.loginQQ:
                mTencent.login(LoginActivity.this,"all", mIUiListener);


                break;
        }
    }

    private class BaseUiListener implements IUiListener {

        Map<String ,String> map = new HashMap<>();
        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;


            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                map.put("user",openID);
                MyUser user = new MyUser();

                user.setUsername(openID);
                user.setPassword(openID);
                user.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {

                    }
                });
                user.login(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e==null){
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    }
                });
                mTencent.setOpenId(openID);


                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {


                        Log.e(TAG,"登录成功"+response.toString());



                    }
                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }
                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
        }

        public String ret(){

            return map.get("user");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}