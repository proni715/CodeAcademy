package com.example.codeacademy.objects;

public class ServerResponse {

    private int responseCode;
    private String responseBody;

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }


    public ServerResponse(){

    }

    public ServerResponse(int code, String body){

        this.responseCode = code;
        this.responseBody = body;
    }

}
