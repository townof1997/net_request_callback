package com.bank.town.dell.dnxueyuan_pro_code_restructure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bank.town.dell.dnxueyuan_pro_code_restructure.bean.RequestData;
import com.bank.town.dell.dnxueyuan_pro_code_restructure.bean.ResponseData;
import com.bank.town.dell.dnxueyuan_pro_code_restructure.httpprocessor.HttpCallBack;
import com.bank.town.dell.dnxueyuan_pro_code_restructure.httpprocessor.HttpHelper;
import com.bank.town.dell.dnxueyuan_pro_code_restructure.netrequestutils.IDataListener;
import com.bank.town.dell.dnxueyuan_pro_code_restructure.netrequestutils.Volley;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String url = "http://www.baidu.com";
    HashMap<String,Object> params = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        HttpHelper.obtain().post(url, params, new HttpCallBack<ResponseData>() {
            @Override
            public void onSuccess(ResponseData responseData) {
                Toast.makeText(MainActivity.this, responseData.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String error) {
                super.onFailure(error);
            }
        });
        //可以放在线程池中请求
        Volley.sendJSONRequest(new RequestData("data", 12), url, ResponseData.class, new IDataListener<ResponseData>() {
            @Override
            public void onSuccess(ResponseData result) {
                Log.i("david", "onSuccess:" + result + "thread" + Thread.currentThread().getName());
            }

            @Override
            public void onError() {

            }
        });
    }
}
