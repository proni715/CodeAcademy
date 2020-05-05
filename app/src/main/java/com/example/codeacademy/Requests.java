package com.example.codeacademy;

import com.example.codeacademy.objects.ServerResponse;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Requests {

    public static ServerResponse getRequest(URL url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        ServerResponse result = new ServerResponse(response.code(),response.body().string());
        return result;
    }

    public static ServerResponse getRequestWithHeaders(URL url, String header) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization",header)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        ServerResponse result = new ServerResponse(response.code(),response.body().string());
        return result;
    }



    public static ServerResponse postRequest(URL url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        ServerResponse result = new ServerResponse(response.code(), response.body().string());
        return  result;
    }
}
