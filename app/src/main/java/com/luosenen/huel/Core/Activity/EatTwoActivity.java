package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import com.luosenen.huel.Core.Adapter.EatThreeAdapter;
import com.luosenen.huel.Core.Adapter.EatTwoAdapter;
import com.luosenen.huel.Core.Adapter.LoveAdapter;
import com.luosenen.huel.Core.MyFiles.EatThreeFile;
import com.luosenen.huel.Core.MyFiles.EatTwoFile;
import com.luosenen.huel.Core.MyFiles.LoveFile;
import com.luosenen.huel.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class EatTwoActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);
        listView = findViewById(R.id.eatSList);

        BmobQuery<EatTwoFile> bmobQuery = new BmobQuery<EatTwoFile>();
        bmobQuery.findObjects(new FindListener<EatTwoFile>() {
            @Override
            public void done(List<EatTwoFile> list, BmobException e) {

                if (e == null) {
                    final String[] floot = new String[list.size()];
                    final String[] name = new String[list.size()];
                    final String[] price = new String[list.size()];
                    final BmobFile[] image = new BmobFile[list.size()];

                    for (int i = 0; i < list.size(); i++) {
                        name[i] = list.get(i).getName();
                        floot[i] = list.get(i).getFloot();
                        price[i] = String.valueOf(list.get(i).getPrice());
                        list.get(i).getIcon();
                        image[i] = list.get(i).getIcon();
                    }
                    listView.setAdapter(new EatTwoAdapter(EatTwoActivity.this, list));
                }
            }

        });

    }
}