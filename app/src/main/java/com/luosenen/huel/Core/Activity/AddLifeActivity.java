package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luosenen.huel.Core.MyFiles.ExpressFile;
import com.luosenen.huel.Core.MyFiles.LifeFile;
import com.luosenen.huel.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddLifeActivity extends Activity {

    private Button publish;
    private EditText name,desc,tell;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlife);
        publish = findViewById(R.id.sentLife);
        name = findViewById(R.id.addLifeName);
        tell = findViewById(R.id.addLifeTell);
        desc = findViewById(R.id.addLifeDesc);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s_name = name.getText().toString().trim();
                String s_tell = tell.getText().toString().trim();
                String s_desc = desc.getText().toString().trim();
                LifeFile lifeFile = new LifeFile();
                lifeFile.setName(s_name);
                lifeFile.setTell(s_tell);
                lifeFile.setPrice(Double.valueOf(s_desc));

                lifeFile.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null){
                            Toast.makeText(AddLifeActivity.this, "发布成功", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AddLifeActivity.this, MainActivity.class));
                            overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                            finish();
                        }else {
                            Toast.makeText(AddLifeActivity.this, "发布失败，失败原因"+e.toString(), Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
            }
        });

    }
}
