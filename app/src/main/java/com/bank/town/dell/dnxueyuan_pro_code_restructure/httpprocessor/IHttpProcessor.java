package com.bank.town.dell.dnxueyuan_pro_code_restructure.httpprocessor;

import java.util.Map;

/**
 * Created by dell on 2019/3/16.
 * //类比房产公司
 */

public interface IHttpProcessor {
    /**
     * 网络访问 post get del update put......
     *
     * */
   // void post(String url, params, 回调接口);
    void post(String url, Map<String,Object> params, ICallBack callBack);

    void get(String url, Map<String,Object> params, ICallBack callBack);












}
