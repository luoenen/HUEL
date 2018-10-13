package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import com.luosenen.huel.Core.Adapter.LoveAdapter;
import com.luosenen.huel.Core.MyFiles.LoveFile;
import com.luosenen.huel.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoveActivity extends Activity {

    private ListView listView;
    private Button toLove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_love);
        listView = findViewById(R.id.loveList);

        toLove = findViewById(R.id.toLove);
        BmobQuery<LoveFile> bmobQuery = new BmobQuery<LoveFile>();
        bmobQuery.findObjects(new FindListener<LoveFile>() {
            @Override
            public void done(List<LoveFile> list, BmobException e) {

                if (e == null) {
                    final String[] school = new String[list.size()];
                    final String[] name = new String[list.size()];
                    final String[] desc = new String[list.size()];
                    final String[] tell = new String[list.size()];
                    final BmobFile[] image = new BmobFile[list.size()];

                    for (int i = 0; i < list.size(); i++) {
                        school[i] = list.get(i).getSchool();
                        name[i] = list.get(i).getName();
                        desc[i] = list.get(i).getDesc();
                        tell[i] = list.get(i).getTell();
                        list.get(i).getIcon();
                        image[i] = list.get(i).getIcon();
                    }
                    listView.setAdapter(new LoveAdapter(LoveActivity.this, list));
                }
            }

        });
        toLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ToLoveActivity.class));
                finish();
            }
        });

    }
}