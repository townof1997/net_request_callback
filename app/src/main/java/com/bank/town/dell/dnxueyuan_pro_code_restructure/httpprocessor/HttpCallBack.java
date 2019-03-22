package com.bank.town.dell.dnxueyuan_pro_code_restructure.httpprocessor;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by dell on 2019/3/16.
 */

public abstract class HttpCallBack<Result> implements ICallBack {


    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();

        //得到调用者用什么样的javabean来接收数据
        Class<?> clz = analysisClassInfo(this);
        //转换对象 Result就是响应的结果对象
        Result objResult = (Result) gson.fromJson(result, clz);
        onSuccess(objResult);
    }
    /**泛型和Object有什么不同
     *T 表示各种类型
     *object 往往需要得到class字节码的时候
     * */
    public static  Class<?> analysisClassInfo(Object object) {
        //getGenericSuperclass()包含原始类，参数化类型，数组类型，类型变量，基本类型都可以得到
        Type genType =  object.getClass().getGenericSuperclass();
        // 获取参数化类型
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();

        return (Class<?>)params[0];















    }































    public abstract void onSuccess(Result result);









    @Override
    public void onFailure(String error) {







    }
}
