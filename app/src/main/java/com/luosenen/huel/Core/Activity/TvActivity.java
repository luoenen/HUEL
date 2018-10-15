package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.luosenen.huel.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class TvActivity extends Activity implements AdapterView.OnItemClickListener {

    private Button tvBack;
    private ListView listView;
    private static String s2[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);
        tvBack = findViewById(R.id.TvBack);
        listView = findViewById(R.id.TvList);

        byte b1[] = getTv().getBytes();
        String str1 = null;
        try {
            str1 = new String(b1,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        byte b2[] = getUrl().getBytes();
        String str2 = null;
        try {
            str2 = new String(b2,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String s1[] = str1.split("\n");

        s2 = str2.split("\n");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                TvActivity.this, android.R.layout.simple_list_item_1, s1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    public String getTv(){
        InputStream is = TvActivity.this.getResources().openRawResource(R.raw.tv);//把文件转换为输入流

        StringBuffer response = new StringBuffer();				   //创建StringBuffer实例
        BufferedReader br = new BufferedReader(new InputStreamReader(is));	//根据is创建缓冲字符输入流
        String s = null;							//创建s变量
        try {							//try语句捕获异常
            while ((s = br.readLine()) != null) {		//把这一行的值赋值给变量s，并判断是否有值
                response.append(s);						//把值添加进StringBuffer
                response.append("\n");					//再添加一个换行符
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block			//catch异常处理
            e.printStackTrace();		//得到错误的实例， 调用方法在命令行打印程序出错的位置及原因
        } finally {				//finally try语句大多数情况下都会执行的代码块
            try {
                if (is != null) {		//如果文件输入流不为空
                    is.close();			//调用close函数关掉输入流
                }
                if (br != null) {		//如果缓冲字符输入流不为空
                    br.close();			//调用close函数关掉缓冲字符输入流
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return response.toString();
    }

    public String getUrl(){
        InputStream is = TvActivity.this.getResources().openRawResource(R.raw.url);//把文件转换为输入流
        StringBuffer response = new StringBuffer();				   //创建StringBuffer实例
        BufferedReader br = new BufferedReader(new InputStreamReader(is));	//根据is创建缓冲字符输入流
        String s = null;							//创建s变量
        try {							//try语句捕获异常
            while ((s = br.readLine()) != null) {		//把这一行的值赋值给变量s，并判断是否有值
                response.append(s);						//把值添加进StringBuffer
                response.append("\n");					//再添加一个换行符
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block			//catch异常处理
            e.printStackTrace();		//得到错误的实例， 调用方法在命令行打印程序出错的位置及原因
        } finally {				//finally try语句大多数情况下都会执行的代码块
            try {
                if (is != null) {		//如果文件输入流不为空
                    is.close();			//调用close函数关掉输入流
                }
                if (br != null) {		//如果缓冲字符输入流不为空
                    br.close();			//调用close函数关掉缓冲字符输入流
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return response.toString();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){

            default:
                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("url",s2[position]);
                startActivity(intent);
        }
    }
}
