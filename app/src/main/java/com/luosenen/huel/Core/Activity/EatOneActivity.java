package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import com.luosenen.huel.Core.Adapter.EatOneAdapter;
import com.luosenen.huel.Core.Adapter.LoveAdapter;
import com.luosenen.huel.Core.MyFiles.EatOneFile;
import com.luosenen.huel.Core.MyFiles.LoveFile;
import com.luosenen.huel.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class EatOneActivity extends Activity {

    private ListView listView;
    private Button oneB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        listView = findViewById(R.id.eatFList);
        oneB = findViewById(R.id.firstBack);
        oneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
            }
        });

        BmobQuery<EatOneFile> bmobQuery = new BmobQuery<EatOneFile>();
        bmobQuery.findObjects(new FindListener<EatOneFile>() {
            @Override
            public void done(List<EatOneFile> list, BmobException e) {

                if (e == null) {
                    final String[] floot = new String[list.size()];
                    final String[] name = new String[list.size()];
                    final String[] price = new String[list.size()];
                    final BmobFile[] image = new BmobFile[list.size()];

                    for (int i = 0; i < list.size(); i++) {
                        name[i] = list.get(i).getName();
                        floot[i] = list.get(i).getTitle();
                        price[i] = String.valueOf(list.get(i).getPrice());
                        list.get(i).getIcon();
                        image[i] = list.get(i).getIcon();
                    }
                    listView.setAdapter(new EatOneAdapter(EatOneActivity.this, list));
                }
            }

        });

    }
}