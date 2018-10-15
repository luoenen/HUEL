package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.luosenen.huel.Core.Adapter.ExpressAdapter;
import com.luosenen.huel.Core.MyFiles.ExpressFile;
import com.luosenen.huel.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ExpressActivity extends Activity {

    private ListView listView;
    private Button back,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        listView = findViewById(R.id.expressList);
        back = findViewById(R.id.expressBack);
        add = findViewById(R.id.toExpress);
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
                startActivity(new Intent(getApplicationContext(),AddExpressActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

        BmobQuery<ExpressFile> bmobQuery = new BmobQuery<ExpressFile>();
        bmobQuery.findObjects(new FindListener<ExpressFile>() {
            @Override
            public void done(List<ExpressFile> list, BmobException e) {

                if (e == null) {
                    final String[] name = new String[list.size()];
                    final String[] address = new String[list.size()];
                    final String[] price = new String[list.size()];
                    final String[] tell = new String[list.size()];

                    for (int i = 0; i < list.size(); i++) {
                        name[i] = list.get(i).getName();
                        address[i] = list.get(i).getAddress();
                        price[i] = String.valueOf(list.get(i).getPrice());
                        list.get(i).getIcon();
                        tell[i] = list.get(i).getTell();
                    }
                    listView.setAdapter(new ExpressAdapter(ExpressActivity.this, list));
                }
            }

        });

    }
}
