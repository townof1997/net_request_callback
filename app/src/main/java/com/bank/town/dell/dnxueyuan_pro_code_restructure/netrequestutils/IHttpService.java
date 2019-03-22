package com.bank.town.dell.dnxueyuan_pro_code_restructure.netrequestutils;

/**
 * Created by dell on 2019/3/17.
 */

public interface IHttpService {

    void setUrl(String url);

    void excute();

    void setRequest(byte[] requestData);

    void setHttpCallBack(IHttpListener httpListener);
}
