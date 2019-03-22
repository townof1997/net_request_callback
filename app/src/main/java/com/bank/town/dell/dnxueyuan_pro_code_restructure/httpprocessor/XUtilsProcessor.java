package com.bank.town.dell.dnxueyuan_pro_code_restructure.httpprocessor;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by dell on 2019/3/16.
 */

public class XUtilsProcessor implements IHttpProcessor {
    public XUtilsProcessor(MyApplication app) {
        x.Ext.init(app);
    }
    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        RequestParams requestParams = new RequestParams(url);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    @Override
    public void get(String url, Map<String, Object> params, ICallBack callBack) {

    }
}
