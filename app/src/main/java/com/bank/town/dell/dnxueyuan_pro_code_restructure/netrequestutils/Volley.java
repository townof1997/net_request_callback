package com.bank.town.dell.dnxueyuan_pro_code_restructure.netrequestutils;

/**
 * Created by dell on
 * 2019/3/17.
 *
 * 给调用层打交道的
 */

public class Volley {
    /**
     * url
     * 请求对象
     * 回调调用层的接口
     * 返回的Responce M
     *
     * json 不一样 对象类型不一样 M
     * */
    public static <T, M> void sendJSONRequest(T requestInfo, String url, Class<M> reponce,IDataListener<M> iDataListener){
        //策略模式
        IHttpService httpService = new JsonHttpService();

        IHttpListener httpListener = new JsonHttpListener<>(iDataListener,reponce);

        HttpTask httpTask = new HttpTask(requestInfo,url,httpListener,httpService);

        ThreadPoolManager.getInstance().excute(httpTask);
    }
}
