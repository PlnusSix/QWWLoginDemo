package com.kankan.qwwlogindemo.weibologin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.kankan.qwwlogindemo.R;
import com.kankan.qwwlogindemo.util.ImageManager;
import com.kankan.qwwlogindemo.wxapi.WXEntryActivity;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class WeiboLoginActivity extends AppCompatActivity {

    private static final String TAG = "WeiboLoginActivity";
    private AuthInfo mAuthInfo;
    private Oauth2AccessToken mAccessToken;
    private SsoHandler mSsoHandler;
    private TextView mToken, mWeiboJsonData, mUserInfo, mWeiboNickname;
    private ImageView mAvatar, mAvatar_large;

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
        mToken = (TextView) this.findViewById(R.id.tokenInfo);
        mWeiboJsonData = (TextView) this.findViewById(R.id.weiboJsonData);
        mAvatar = (ImageView) this.findViewById(R.id.weiboAvatar);
        mUserInfo = (TextView) this.findViewById(R.id.weiboUserInfo);
        mAvatar_large = (ImageView) this.findViewById(R.id.weiboAvatar_large);
        mWeiboNickname = (TextView) this.findViewById(R.id.weiboNickname);
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
        Toast.makeText(this, "注销登录操作", Toast.LENGTH_SHORT).show();
        mAuthInfo = null;
        mSsoHandler = null;
        mAccessToken = null;
        mToken.setText("");
        mWeiboJsonData.setText("");
        mUserInfo.setText("");
        mAvatar.setImageBitmap(null);
        mAvatar_large.setImageBitmap(null);
    }

    private void initWechatPage() {
        RxView.clicks(findViewById(R.id.wechatPage))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        WeiboLoginActivity.this.startActivity(
                                new Intent(WeiboLoginActivity.this, WXEntryActivity.class));
                    }
                });
    }

    /**
     * 主要的授权监听类，在此类里面直接可以获取用户的信息
     */
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            //从Bundle中解析Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            //从这里获取用户输入的电话号码信息
            String phoneNum = mAccessToken.getPhoneNum();
            String uid = mAccessToken.getUid();
            String token = mAccessToken.getToken();
            if (mAccessToken.isSessionValid()) {
                mToken.setText("token:" + token + ",uid:" + uid + ",phoneNum:" + phoneNum);
                //保存Token到SharedPreferences
                AccessTokenKeeper.writeAccessToken(WeiboLoginActivity.this, mAccessToken);
                Toast.makeText(WeiboLoginActivity.this, R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
                //请求用户信息
                requestUserInfo(mAccessToken);
            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = values.getString("code");
                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(WeiboLoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(WeiboLoginActivity.this, R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(WeiboLoginActivity.this, "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 请求微博用户信息
     *
     * @param accessToken
     */
    private void requestUserInfo(Oauth2AccessToken accessToken) {
        UsersAPI usersAPI = new UsersAPI(this, Contants.APP_KEY, accessToken);
        String uidString = accessToken.getUid();
        Long uid = Long.decode(uidString);
        usersAPI.show(uid, new RequestListener() {
            @Override
            public void onComplete(String s) {
                mWeiboJsonData.setText(s);
                parseJsonData(s);
            }

            @Override
            public void onWeiboException(WeiboException e) {
                Log.e(TAG, e.getMessage());
                Toast.makeText(WeiboLoginActivity.this, "获取失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 解析得到的用户信息json数据
     *
     * @param s
     */
    private void parseJsonData(String s) {
        try {
            JSONObject root = new JSONObject(s);
            String id = root.getString("id");
            String name = root.getString("name");
            String profile_image_url = root.getString("profile_image_url");
            String avatar_large = root.getString("avatar_large");
            mUserInfo.setText(id + "," + name);
            ImageManager.get().loadUrlIntoImageView(this, profile_image_url, mAvatar);
            ImageManager.get().loadUrlIntoImageView(this, avatar_large, mAvatar_large);
            mWeiboNickname.setText(name);
        } catch (JSONException e) {
            e.printStackTrace();
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
