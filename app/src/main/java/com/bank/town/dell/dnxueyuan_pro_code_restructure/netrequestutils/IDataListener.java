package com.bank.town.dell.dnxueyuan_pro_code_restructure.netrequestutils;

/**
 * Created by dell on 2019/3/17.
 * 回调 调用层的接口
 */

public interface IDataListener<T> {
    void onSuccess(T result);
    void onError();
}
