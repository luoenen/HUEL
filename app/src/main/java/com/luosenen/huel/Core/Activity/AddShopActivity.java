package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luosenen.huel.Core.MyFiles.ExpressFile;
import com.luosenen.huel.Core.MyFiles.ShopFile;
import com.luosenen.huel.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddShopActivity extends Activity {

    private Button publish;
    private EditText name,address,price,tell;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshop);
        publish = findViewById(R.id.sentShop);
        name = findViewById(R.id.shopName);
        address = findViewById(R.id.shopAddress);
        tell = findViewById(R.id.shopTell);
        price = findViewById(R.id.shopPrice);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s_name = name.getText().toString().trim();
                String s_address = address.getText().toString().trim();
                String s_tell = tell.getText().toString().trim();
                String s_price = price.getText().toString().trim();
                ShopFile expressFile = new ShopFile();
                expressFile.setName(s_name);
                expressFile.setAddress(s_address);
                expressFile.setTell(s_tell);
                expressFile.setPrice(Double.valueOf(s_price));

                expressFile.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null){
                            Toast.makeText(AddShopActivity.this, "购物信息发布成功，请等待短信有人接单", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AddShopActivity.this, MainActivity.class));
                            overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                            finish();
                        }else {
                            Toast.makeText(AddShopActivity.this, "快递信息发布失败，失败原因"+e.toString(), Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
            }
        });

    }
}
