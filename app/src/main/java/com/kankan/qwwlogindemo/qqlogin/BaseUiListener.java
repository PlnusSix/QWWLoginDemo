package com.kankan.qwwlogindemo.qqlogin;

import android.util.Log;

import com.kankan.qwwlogindemo.util.RxBus;
import com.kankan.qwwlogindemo.util.RxBusEvent;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/**
 * Author: liuyanguo
 * Date: 2017/2/4
 * Time: 14:29
 * Description:
 */

public class BaseUiListener implements IUiListener {

    private static final String TAG = "BaseUiListener";

    @Override
    public void onComplete(Object o) {
        RxBus.get().post(new RxBusEvent(RxBusEvent.EventType.USEROBJ, o));
        Log.e(TAG, "onComplete():" + o.toString());
    }

    @Override
    public void onError(UiError uiError) {
        Log.e(TAG, "onError() code:" + uiError.errorCode + ", msg:"
                + uiError.errorMessage + ", detail:" + uiError.errorDetail);
    }

    @Override
    public void onCancel() {
        Log.e(TAG, "onCancel()");
    }
}