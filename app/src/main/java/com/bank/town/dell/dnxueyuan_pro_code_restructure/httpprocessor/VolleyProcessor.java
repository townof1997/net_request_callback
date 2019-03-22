package com.bank.town.dell.dnxueyuan_pro_code_restructure.httpprocessor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by dell on 2019/3/16.
 */

public class VolleyProcessor implements IHttpProcessor {
    private static RequestQueue mQueue = null;
    public  VolleyProcessor(Context context) {
        mQueue= Volley.newRequestQueue(context);
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.onFailure(error.toString());
                    }
                }
        );
        mQueue.add(stringRequest);
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallBack callBack) {

    }
}
