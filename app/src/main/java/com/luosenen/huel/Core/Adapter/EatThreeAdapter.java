package com.luosenen.huel.Core.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luosenen.huel.Core.MyFiles.EatOneFile;
import com.luosenen.huel.Core.MyFiles.EatThreeFile;
import com.luosenen.huel.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;

public class EatThreeAdapter extends BaseAdapter {

    private Context context ;
    private List<EatThreeFile> list;
    public EatThreeAdapter(Context context, List<EatThreeFile> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            String school,college,address,title,desc,name,account,tell,sex,floot;
            double price;
            BmobFile icon;

            school = list.get(position).getSchool();
            college = list.get(position).getCollege();
            address = list.get(position).getAddress();
            title = list.get(position).getTitle();
            desc = list.get(position).getDesc();
            name = list.get(position).getName();
            account = list.get(position).getAccount();
            tell = list.get(position).getTell();
            icon = list.get(position).getIcon();
            price = list.get(position).getPrice();
            sex = list.get(position).getSex();
            floot = list.get(position).getFloot();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_f_s_t_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.eatIcon);
            viewHolder.tv_floot = (TextView) convertView.findViewById(R.id.eatFloot);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.eatName);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.eatPrice);
            convertView.setTag(viewHolder);

            new Thread(new Runnable() {
                @Override
                public void run() {

                    final Bitmap bitmap=getPicture(list.get(position).getIcon().getFileUrl());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    viewHolder.iv_icon.post(new Runnable() {
                        @Override
                        public void run() {
                            viewHolder.iv_icon.setImageBitmap(bitmap);
                        }
                    });
                }
            }).start();
            viewHolder.tv_floot.setText(floot);
            viewHolder.tv_name.setText(name);
            viewHolder.tv_price.setText(desc);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder{

        TextView tv_school;
        TextView tv_college;
        TextView tv_address;
        TextView tv_title;
        TextView tv_desc;
        TextView tv_name;
        TextView tv_account;
        TextView tv_tell;
        TextView tv_sex;
        TextView tv_price;
        ImageView iv_icon;
        TextView tv_floot;
    }
    public Bitmap getPicture(String path){
        Bitmap bm=null;
        try{
            URL url=new URL(path);
            URLConnection connection=url.openConnection();
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            bm= BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bm;
    }
}
