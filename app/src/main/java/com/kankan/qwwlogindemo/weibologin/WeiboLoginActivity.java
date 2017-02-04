package com.kankan.qwwlogindemo.weibologin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jakewharton.rxbinding.view.RxView;
import com.kankan.qwwlogindemo.R;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class WeiboLoginActivity extends AppCompatActivity {

    private static final String TAG = "WeiboLoginActivity";
    private AuthInfo mAuthInfo;
    private Oauth2AccessToken mAccessToken;
    private SsoHandler mSsoHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_login);

        initView();
        initWeiboLogin();
        initWeiboLogout();
        initWechatPage();
    }

    private void initView() {

    }

    private void initWeiboLogin() {
        RxView.clicks(findViewById(R.id.weiboLogin))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        doWeiboLogin();
                    }
                });
    }

    private void doWeiboLogin() {
        mAuthInfo = new AuthInfo(this, Contants.APP_KEY, Contants.REDIRECT_URL, Contants.SCOPE);
        mSsoHandler = new SsoHandler(this, mAuthInfo);
        mSsoHandler.authorize(new AuthListener());
    }

    private void initWeiboLogout() {
        RxView.clicks(findViewById(R.id.weiboLogout))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        doWeiboLogout();
                    }
                });
    }

    private void doWeiboLogout() {

    }

    private void initWechatPage() {

    }

    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle bundle) {
            //从Bundle中解析Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
            if (mAccessToken.isSessionValid()) {
                //保存Token到Sharepreference

            } else {
                //当注册的应用程序签名不正确时，就会收到Code
                Object code = bundle.getString("code", "");
                //收到码进行处理

            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Log.e(TAG, "onWeiboException():" + e.getMessage());
        }

        @Override
        public void onCancel() {
            Log.e(TAG, "onCancel()");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
