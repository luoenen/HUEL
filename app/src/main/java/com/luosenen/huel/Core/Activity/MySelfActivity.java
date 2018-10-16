package com.luosenen.huel.Core.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.luosenen.huel.R;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;


public class MySelfActivity extends Activity {
    private Button sharQQ;

    public static Tencent mTencent;
    public static String mAppid="101508791";
    private BaseUiListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);
        if (mTencent == null) {
            mTencent = Tencent.createInstance(mAppid, this);
            listener = new BaseUiListener();
        }
        sharQQ = findViewById(R.id.shareQQ);
        sharQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qqShare(v);
            }
        });



    }

    public void qqShare(View view) {
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "云上财大");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,"Welcome To 河南财经政法大学\"云上财大\"社会学系技术支持部门");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,"https://www.huel.site/");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"https://p.qpic.cn/qqconnect/0/app_101508791_1539677114/100?max-age=2592000&t=0?362.3871387757305");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "HUEL");
        mTencent.shareToQQ(this, params, listener);
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            Toast.makeText(getApplicationContext(), "分享成功！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }
}
