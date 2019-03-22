package com.bank.town.dell.dnxueyuan_pro_code_restructure.httpprocessor;

import android.os.Handler;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by dell on 2019/3/16.
 */

public class OkHttpProcessor implements IHttpProcessor {
    private OkHttpClient mOKHttpClient;
    private Handler myHandler;

    public OkHttpProcessor(){
        mOKHttpClient=new OkHttpClient();
        myHandler=new Handler();
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        RequestBody requestBody = appendBody(params);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        mOKHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String result = response.body().string();
                if(response.isSuccessful()) {

                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(result);
                        }
                    });

                } else {
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailure(result);
                        }
                    });
                }

            }
        });
    }

    private RequestBody appendBody(Map<String,Object> params) {
        FormBody.Builder body = new FormBody.Builder();
        if(params == null || params.isEmpty()) {
            return  body.build();
        }
        for (Map.Entry<String,Object> entry: params.entrySet()) {
            body.add(entry.getKey(),entry.getValue().toString());

        }
        return body.build();
    }
    @Override
    public void get(String url, Map<String, Object> params, ICallBack callBack) {

    }
}
