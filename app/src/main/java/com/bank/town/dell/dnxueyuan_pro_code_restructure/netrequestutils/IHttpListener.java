package com.bank.town.dell.dnxueyuan_pro_code_restructure.netrequestutils;

import java.io.InputStream;

/**
 * Created by dell on 2019/3/17.
 * 自行请求
 */

public interface IHttpListener {
    //接收上一个接口的结果
    void onSuccess(InputStream inputStream);
    //
    void onFaile();


}
