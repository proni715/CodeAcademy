package com.example.codeacademy;

import java.io.IOException;
import java.net.URL;

public class Requests {

    public static MyResponse getResponseFromURL(URL url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        MyResponse result = new MyResponse(response.code(),response.body().string());
        return result;

    }
}
