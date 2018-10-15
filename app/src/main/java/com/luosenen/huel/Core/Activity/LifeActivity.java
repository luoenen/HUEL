package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.luosenen.huel.Core.Adapter.ExpressAdapter;
import com.luosenen.huel.Core.Adapter.LifeAdapter;
import com.luosenen.huel.Core.MyFiles.ExpressFile;
import com.luosenen.huel.Core.MyFiles.LifeFile;
import com.luosenen.huel.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LifeActivity extends Activity {

    private ListView listView;
    private Button back,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        listView = findViewById(R.id.lifeList);
        back = findViewById(R.id.lifeBack);
        add = findViewById(R.id.toLife);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddLifeActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

        BmobQuery<LifeFile> bmobQuery = new BmobQuery<LifeFile>();
        bmobQuery.findObjects(new FindListener<LifeFile>() {
            @Override
            public void done(List<LifeFile> list, BmobException e) {

                if (e == null) {
                    final String[] address = new String[list.size()];
                    final String[] desc = new String[list.size()];
                    final String[] tell = new String[list.size()];

                    for (int i = 0; i < list.size(); i++) {
                        address[i] = list.get(i).getAddress();
                        desc[i] = list.get(i).getDesc();
                        list.get(i).getIcon();
                        tell[i] = list.get(i).getTell();
                    }
                    listView.setAdapter(new LifeAdapter(LifeActivity.this, list));
                }
            }

        });

    }
}
