package com.bank.town.dell.dnxueyuan_pro_code_restructure.httpprocessor;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2019/3/16.
 * 相当于业务员（代理类）来代理（volley,xUitls,okHttp)完成请求
 */

public class HttpHelper implements IHttpProcessor {
    //有房的人
    private static  IHttpProcessor mIHttpProcessor = null;
    //存入请求参数
    private Map<String, Object> params;
    private static  HttpHelper instance;
    public static  HttpHelper obtain() {
        synchronized (HttpHelper.class) {
            if (instance == null) {
                instance = new HttpHelper();
            }
        }
        return instance;
    }
    private HttpHelper(){
        params = new HashMap<>();
    }
    //定义一个用于设置代理的接口（接口去找有房卖的人）
    public static void init(IHttpProcessor httpProcessor) {
        mIHttpProcessor = httpProcessor;
    }
    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        //http://www.aaa.bb.cc/index
        //?&user=1&pwd=2
        String finalUrl = appendParams(url,params);
        mIHttpProcessor.post(finalUrl,params,callBack);
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallBack callBack) {

    }

    public static String appendParams(String url,Map<String, Object> params) {
        if(params == null || params.isEmpty()){
            return url;
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        if (urlBuilder.indexOf("?") <= 0) {
            urlBuilder.append("?");
        } else {
            if(!urlBuilder.toString().endsWith("?")) {

            }
        }
        for (Map.Entry<String,Object> entry: params.entrySet()) {
            urlBuilder.append("&"+entry.getKey()).append("=").append(encode(entry.getValue().toString()));
        }
        Log.i("jett",urlBuilder.toString());
        return urlBuilder.toString();
    }
    private static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw  new RuntimeException();
        }
    }
}
