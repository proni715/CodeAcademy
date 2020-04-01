package com.example.codeacademy;

public class ServerResponse {

    private int responseCode;
    private String responseBody;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }


    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public ServerResponse(){

    }

    public ServerResponse(int code, String body){

        this.responseCode = code;
        this.responseBody = body;
    }

}
