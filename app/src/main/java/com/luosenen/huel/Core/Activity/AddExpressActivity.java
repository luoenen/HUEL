package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luosenen.huel.Core.MyFiles.ExpressFile;
import com.luosenen.huel.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddExpressActivity extends Activity {

    private Button publish;
    private EditText name,address,price,tell;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpress);
        publish = findViewById(R.id.sentExpress);
        name = findViewById(R.id.expressName);
        address = findViewById(R.id.expressAddress);
        tell = findViewById(R.id.expressTell);
        price = findViewById(R.id.expressPrice);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s_name = name.getText().toString().trim();
                String s_address = address.getText().toString().trim();
                String s_tell = tell.getText().toString().trim();
                String s_price = price.getText().toString().trim();
                ExpressFile expressFile = new ExpressFile();
                expressFile.setName(s_name);
                expressFile.setAddress(s_address);
                expressFile.setTell(s_tell);
                expressFile.setPrice(Double.valueOf(s_price));

                expressFile.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null){
                            Toast.makeText(AddExpressActivity.this, "快递信息发布成功，请等待短信有人接单", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AddExpressActivity.this, MainActivity.class));
                            overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                            finish();
                        }else {
                            Toast.makeText(AddExpressActivity.this, "快递信息发布失败，失败原因"+e.toString(), Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
            }
        });

    }
}
