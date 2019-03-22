package com.bank.town.dell.dnxueyuan_pro_code_restructure.netrequestutils;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Created by dell on 2019/3/17.
 */

public class HttpTask implements Runnable {

    IHttpListener httpListener;
    IHttpService iHttpService;
    protected <T> HttpTask(T requestInfo,String url,IHttpListener httpListener,IHttpService iHttpService){
        this.httpListener = httpListener;
        this.iHttpService = iHttpService;
        this.iHttpService.setUrl(url);
        //设置关系
        iHttpService.setHttpCallBack(httpListener);
        //设置请求参数 json
        if(requestInfo != null) {
            String jsonContent = JSON.toJSONString(requestInfo);
            try {
                this.iHttpService.setRequest(jsonContent.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }








    @Override
    public void run() {
        //这里进行网络请求
        iHttpService.excute();

    }
}
