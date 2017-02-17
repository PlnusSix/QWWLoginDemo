package com.kankan.qwwlogindemo.qqlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.kankan.qwwlogindemo.R;
import com.kankan.qwwlogindemo.util.ImageManager;
import com.kankan.qwwlogindemo.util.RxBus;
import com.kankan.qwwlogindemo.util.RxBusEvent;
import com.kankan.qwwlogindemo.weibologin.WeiboLoginActivity;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Tencent mTencent;
    private String APP_Id = "1105894839";
    private BaseUiListener uiListener;
    private ImageView mAvatar_1, mAvatar_2;
    private TextView mNickname, mQQJsonData, mQQUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initQQLogin();
        initQQLogout();
        initWeiboPage();
    }

    private void initView() {
        uiListener = new BaseUiListener();
        mAvatar_1 = (ImageView) this.findViewById(R.id.avatar_1);
        mAvatar_2 = (ImageView) this.findViewById(R.id.avatar_2);
        mNickname = (TextView) this.findViewById(R.id.nickname);
        mQQJsonData = (TextView) this.findViewById(R.id.qqJsonData);
        mQQUserInfo = (TextView) this.findViewById(R.id.qqUserInfo);
    }

    private void initQQLogin() {
        RxView.clicks(findViewById(R.id.qqLogin))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        doQQLogin();
                    }
                });
    }

    private void doQQLogin() {
        mTencent = Tencent.createInstance(APP_Id, this.getApplicationContext());
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", uiListener);
        }
        RxBus.get().toObservableSticky(RxBusEvent.EventType.USEROBJ)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxBusEvent>() {
                    @Override
                    public void call(RxBusEvent rxBusEvent) {
                        mQQJsonData.setText(rxBusEvent.mEventContent.toString());
                        parseJsonData(rxBusEvent.mEventContent.toString());
                    }
                });
    }

    private void initQQLogout() {
        RxView.clicks(findViewById(R.id.qqLogout))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        doQQLogout();
                    }
                });
    }

    private void doQQLogout() {
        if (null != mTencent) {
            mQQJsonData.setText("");
            mQQUserInfo.setText("");
            mAvatar_1.setImageBitmap(null);
            mAvatar_2.setImageBitmap(null);
            mNickname.setText("");
            mTencent.logout(MainActivity.this);
            Toast.makeText(MainActivity.this, "已注销", Toast.LENGTH_SHORT).show();
        }
    }

    private void initWeiboPage() {
        RxView.clicks(findViewById(R.id.weiboPage))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        MainActivity.this.startActivity(new Intent(MainActivity.this, WeiboLoginActivity.class));
                    }
                });
    }

    private void parseJsonData(String s) {
        try {
            JSONObject root = new JSONObject(s);
            String openId = root.getString("openid");
            String access_token = root.getString("access_token");
            long expires_in = root.getLong("expires_in");
            mTencent.setOpenId(openId);
            mTencent.setAccessToken(access_token, expires_in + "");
            UserInfo userInfo = new UserInfo(this, mTencent.getQQToken());
            userInfo.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    mQQUserInfo.setText(o.toString());
                    parseQQUserInfo(o);
                }

                @Override
                public void onError(UiError uiError) {
                    Log.e(TAG, "onError():" + uiError.errorMessage);
                }

                @Override
                public void onCancel() {
                    Log.e(TAG, "onCancel()");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseQQUserInfo(Object o) {
        try {
            JSONObject root = new JSONObject(o.toString());
            String avatar_1Url = root.getString("figureurl_qq_1");
            String avatar_2Url = root.getString("figureurl_qq_2");
            String nickname = root.getString("nickname");
            ImageManager.get().loadUrlIntoImageView(this, avatar_1Url, mAvatar_1);
            ImageManager.get().loadUrlIntoImageView(this, avatar_2Url, mAvatar_2);
            mNickname.setText(nickname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, uiListener);
    }
}
