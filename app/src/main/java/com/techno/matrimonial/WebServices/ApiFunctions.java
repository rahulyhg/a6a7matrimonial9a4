package com.techno.matrimonial.WebServices;

import android.content.Context;

import com.google.gson.Gson;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.techno.matrimonial.Global.Global;
import com.techno.matrimonial.Listener.OnApiCallListener;
import com.techno.matrimonial.Model.Ids;
import com.techno.matrimonial.Model.common.Login;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by arbaz on 19/7/16.
 */
public class ApiFunctions {

    public OkHttpClient client;
    public Context context;
    public OnApiCallListener acListener;
    public Gson gson;
    public final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public ApiFunctions(Context context, OnApiCallListener acListener) {
        this.client = new OkHttpClient();
        this.context = context;
        this.acListener = acListener;
        this.gson = new Gson();

        try {
            client.setConnectTimeout(Api.ConnectionTimeout, TimeUnit.SECONDS);
            client.setWriteTimeout(Api.ConnectionTimeout, TimeUnit.SECONDS);
            client.setReadTimeout(Api.ConnectionTimeout, TimeUnit.SECONDS);
        } catch (Exception e) {
        }

        File cacheDirectory = new File(context.getCacheDir(), "http");
        int cacheSize = 10 * 1024 * 1024;
        try {
            Cache cache = new Cache(cacheDirectory, cacheSize);
            client.setCache(cache);
        } catch (Exception e) {
            //Log.v("Exception" + e.getMessage());
        }
    }

    public void executeRequest(final String url, Request request) {

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                acListener.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                int responseCode = response.code();
                String responseString = response.body().string();
                acListener.onSuccess(responseCode, responseString, url);

            }
        });
    }

    /*Api Function For LoginActivity*/
    public void userLogin(String url, Login login) {
        try {
            String jsonData = gson.toJson(login);
            RequestBody postData = RequestBody.create(JSON, jsonData);
            Request request = new Request.Builder().url(url).addHeader(Api.HTTP_HEADER, Global.getPreference("AccessToken", "")).post(postData).build();
            executeRequest(url, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Api Function For Candidate List*/
    public void candidateList(String url, String gender) {
        try {
            String mainUrl = url + "?" + gender;
            Request request = new Request.Builder().url(mainUrl).addHeader(Api.HTTP_HEADER, Global.getPreference("AccessToken", "")).get().build();
            executeRequest(url, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*Api Function For shorted List*/
    public void shortedCandidate(String url, Ids ids) {
        try {
            String jsonData = gson.toJson(ids);
            RequestBody postData = RequestBody.create(JSON, jsonData);
            Request request = new Request.Builder().url(url).post(postData).build();
            executeRequest(url, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
