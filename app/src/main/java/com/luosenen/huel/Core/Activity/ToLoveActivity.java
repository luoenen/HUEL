package com.luosenen.huel.Core.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.luosenen.huel.Core.MyFiles.LoveFile;
import com.luosenen.huel.R;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class ToLoveActivity extends Activity {

    private Button beginLove,selectPhoto;
    private EditText school, name, tell, desc;
    private ImageView imageView;
    public static final int CHOOSE_PHOTO = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tolove);
        findView();
        initListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4以上使用这个方法处理图片
                        handleIMageOnKitKat(data);
                    } else {
                        handleIMageBeforKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    String imagePath = null;

    @TargetApi(19)
    private void handleIMageOnKitKat(Intent data) {
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的URI，则使用document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果不是document类型的URI，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        }
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(ToLoveActivity.this, "未得到图片", Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void handleIMageBeforKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }


    private void initListener() {
        selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent, CHOOSE_PHOTO);
            }
        });
        beginLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s_school, s_name, s_tell, s_desc, icon_path;
                s_school = school.getText().toString().trim();
                s_name = name.getText().toString().trim();
                s_desc = desc.getText().toString().toString();
                s_tell = tell.getText().toString().trim();
                icon_path = imagePath;
                final BmobFile bmobfile = new BmobFile(new File(icon_path));

                bmobfile.upload(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            LoveFile goods = new LoveFile();
                            if (s_school.equals("")||s_name.equals("")||s_desc.equals("")||s_tell.equals("")){
                                Toast.makeText(ToLoveActivity.this, "必须顺利找到Ta，列表属性必须填写完整", Toast.LENGTH_LONG).show();
                                return;
                            }
                            goods.setName(s_name);
                            goods.setSchool(s_school);
                            goods.setDesc(s_desc);
                            goods.setTell(s_tell);
                            goods.setIcon(bmobfile);
                            goods.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(ToLoveActivity.this, "成功表白", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(ToLoveActivity.this, LoveActivity.class));
                                    } else {
                                        Toast.makeText(ToLoveActivity.this, "未成功表白，失败原因" + e.toString(), Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(ToLoveActivity.this, MainActivity.class));
                                        return;

                                    }
                                }
                            });
                        }
                    }
                });

                finish();
            }
        });


    }

    private void findView() {
        name =findViewById(R.id.toLoveName);
        school =findViewById(R.id.toLoveSchool);
        tell =findViewById(R.id.toLoveTell);
        beginLove = findViewById(R.id.beginLove);
        selectPhoto = findViewById(R.id.selectPhoto);
        imageView = findViewById(R.id.picture);
        desc =findViewById(R.id.toLoveDesc);
    }
}
