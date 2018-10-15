package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.luosenen.huel.Core.Adapter.LoveAdapter;
import com.luosenen.huel.Core.Adapter.UniversityAdapter;
import com.luosenen.huel.Core.MyFiles.LoveFile;
import com.luosenen.huel.Core.MyFiles.UniversityFile;
import com.luosenen.huel.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SchoolActivity extends Activity {

    private Button uBack,uAdd;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        uBack = findViewById(R.id.schoolBack);
        uAdd = findViewById(R.id.toUniversity);
        listView = findViewById(R.id.schoolList);
        BmobQuery<UniversityFile> bmobQuery = new BmobQuery<UniversityFile>();
        bmobQuery.findObjects(new FindListener<UniversityFile>() {
            @Override
            public void done(List<UniversityFile> list, BmobException e) {

                if (e == null) {
                    final String[] college = new String[list.size()];
                    final String[] name = new String[list.size()];
                    final String[] desc = new String[list.size()];
                    final String[] tell = new String[list.size()];
                    final BmobFile[] image = new BmobFile[list.size()];

                    for (int i = 0; i < list.size(); i++) {
                        college[i] = list.get(i).getSchool();
                        name[i] = list.get(i).getName();
                        desc[i] = list.get(i).getDesc();
                        tell[i] = list.get(i).getTell();
                        list.get(i).getIcon();
                        image[i] = list.get(i).getIcon();
                    }
                    listView.setAdapter(new UniversityAdapter(SchoolActivity.this, list));
                }
            }

        });

        uAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ToSchoolActivity.class));
                finish();
            }
        });

        uBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapActivity.class));
                finish();
            }
        });
    }
    }