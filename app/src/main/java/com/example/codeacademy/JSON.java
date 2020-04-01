package com.example.codeacademy;

import android.util.Log;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JSON {

    final static String LOG_TAG= "debug";

    public static User getUser(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        User user = new User();
        try {
            user = mapper.readValue(json,User.class);
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG,"Parsing error");
        }
        return user;
    }

    public static Code400 getCode400(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        Code400 code = new Code400();
        try {

            code = mapper.readValue(json,Code400.class);
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG,"Parsing error");
        }
        return code;
    }

    public static String buildUser(User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(user);
        }catch (IOException e)
        {
            Log.e(LOG_TAG,"Parsing error");
        }
        return result;
    }
}
