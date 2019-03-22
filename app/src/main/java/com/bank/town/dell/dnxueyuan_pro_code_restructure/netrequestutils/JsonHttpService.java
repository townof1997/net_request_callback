package com.bank.town.dell.dnxueyuan_pro_code_restructure.netrequestutils;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dell on 2019/3/17.
 */

public class JsonHttpService implements IHttpService {
    private String url;
    private IHttpListener httpListener;
    private byte[] requestDate;
    HttpURLConnection urlConnection = null;
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    /*
    * 在run方法中调用，可以进行耗时操作
    * */
    @Override
    public void excute() {
        URL url = null;

        try {
            url = new URL(this.url);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setConnectTimeout(6000);
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setReadTimeout(3000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            OutputStream out = urlConnection.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            bos.write(requestDate);
            bos.flush();
            out.close();
            bos.close();

            InputStream in = null;
            Log.i("tuch", "响应码" + urlConnection.getResponseCode());
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = urlConnection.getInputStream();
                httpListener.onSuccess(in);
            } else {
                httpListener.onFaile();
                //Log.i("tuch","错误"+getContent(in));
            }
        } catch (MalformedURLException e) {
            httpListener.onFaile();
            e.printStackTrace();
        } catch (IOException e) {
            httpListener.onFaile();
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
    }

    @Override
    public void setRequest(byte[] requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public void setHttpCallBack(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }
}
