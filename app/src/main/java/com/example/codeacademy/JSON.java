package com.example.codeacademy;

import android.util.Log;

import com.example.codeacademy.objects.Courses;
import com.example.codeacademy.objects.Error;
import com.example.codeacademy.objects.Token;
import com.example.codeacademy.objects.User;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JSON {

    final static String LOG_TAG= "debug";

    public  static Token getToken(String json){
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        Token token = new Token();
        try {
            token = mapper.readValue(json,Token.class);
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG,"Parsing error");
        }
        return token;
    }

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

    public static Courses getCourses(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        Courses courses = new Courses();
        try {
            courses = mapper.readValue(json,Courses.class);
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG,"Parsing error");
        }
        return courses;
    }

    public static Error getError(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        Error code = new Error();
        try {

            code = mapper.readValue(json, Error.class);
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
