package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.luosenen.huel.Core.Adapter.FindAdapter;
import com.luosenen.huel.Core.MyFiles.FindFile;
import com.luosenen.huel.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FindActivity extends Activity {

    private ListView listView;
    private Button tofind,fBack;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        listView = findViewById(R.id.findList);
        tofind = findViewById(R.id.toFind);
        fBack = findViewById(R.id.FindBack);
        BmobQuery<FindFile> bmobQuery = new BmobQuery<FindFile>();
        bmobQuery.findObjects(new FindListener<FindFile>() {
            @Override
            public void done(List<FindFile> list, BmobException e) {

                if (e == null) {
                    final String[] name = new String[list.size()];
                    final String[] desc = new String[list.size()];
                    final String[] tell = new String[list.size()];
                    final BmobFile[] image = new BmobFile[list.size()];

                    for (int i = 0; i < list.size(); i++) {
                        name[i] = list.get(i).getName();
                        desc[i] = list.get(i).getDesc();
                        tell[i] = list.get(i).getTell();
                        list.get(i).getIcon();
                        image[i] = list.get(i).getIcon();
                    }
                    listView.setAdapter(new FindAdapter(FindActivity.this, list));
                }
            }

        });
        tofind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ToFindActivity.class));
                finish();
            }
        });

    }
    }
