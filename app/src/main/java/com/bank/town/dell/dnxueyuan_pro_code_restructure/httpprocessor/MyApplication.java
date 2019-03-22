package com.bank.town.dell.dnxueyuan_pro_code_restructure.httpprocessor;

import android.app.Application;

/**
 * Created by dell on 2019/3/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        HttpHelper.init(new VolleyProcessor(this));
//        HttpHelper.init(new OkHttpProcessor());
        HttpHelper.init(new XUtilsProcessor(this));
    }
}
