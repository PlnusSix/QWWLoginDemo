package com.kankan.qwwlogindemo.qqlogin;

import android.util.Log;

import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

/**
 * Author: liuyanguo
 * Date: 2017/2/4
 * Time: 14:30
 * Description:
 */

public class BaseApiListener implements IRequestListener {

    private static final String TAG = "BaseApiListener";

    @Override
    public void onComplete(JSONObject jsonObject) {
        Log.e(TAG, "BaseApiListener onComplete():" + jsonObject.toString());
    }

    @Override
    public void onIOException(IOException e) {
        Log.e(TAG, "BaseApiListener onIOException():" + e.getMessage());
    }

    @Override
    public void onMalformedURLException(MalformedURLException e) {
        Log.e(TAG, "BaseApiListener onMalformedURLException():" + e.getMessage());
    }

    @Override
    public void onJSONException(JSONException e) {
        Log.e(TAG, "BaseApiListener onJSONException():" + e.getMessage());
    }

    @Override
    public void onConnectTimeoutException(ConnectTimeoutException e) {
        Log.e(TAG, "BaseApiListener onConnectTimeoutException():" + e.getMessage());
    }

    @Override
    public void onSocketTimeoutException(SocketTimeoutException e) {
        Log.e(TAG, "BaseApiListener onSocketTimeoutException():" + e.getMessage());
    }

    @Override
    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException e) {
        Log.e(TAG, "BaseApiListener onNetworkUnavailableException():" + e.getMessage());
    }

    @Override
    public void onHttpStatusException(HttpUtils.HttpStatusException e) {
        Log.e(TAG, "BaseApiListener onHttpStatusException():" + e.getMessage());
    }

    @Override
    public void onUnknowException(Exception e) {
        Log.e(TAG, "BaseApiListener onUnknowException():" + e.getMessage());
    }
}
