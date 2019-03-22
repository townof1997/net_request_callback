package com.bank.town.dell.dnxueyuan_pro_code_restructure.netrequestutils;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dell on 2019/3/17.
 */

public class JsonHttpListener<T> implements IHttpListener {
    private IDataListener<T> dataListener;
    Class<T> responceClass;
    private Handler handler = new Handler(Looper.getMainLooper());
    public JsonHttpListener(IDataListener<T> dataListener, Class<T> responceClass) {
        this.dataListener = dataListener;
        this.responceClass = responceClass;
    }
    //结果做为参数出来 json input--转换-->string
    @Override
    public void onSuccess(InputStream inputStream) {
        String content = getContent(inputStream);


        //内容--->解析成封装对象  回调
        final T responce = JSON.parseObject(content, responceClass);

        //线程切换
        handler.post(new Runnable() {
            @Override
            public void run() {
                // 回调的方法发生在子线程
                dataListener.onSuccess(responce);
            }
        });

    }

//    public abstract onSuccess();
    private String getContent(InputStream inputStream) {
        String content = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line =reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error=" + e.toString());;
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    public void onFaile() {

    }
}
