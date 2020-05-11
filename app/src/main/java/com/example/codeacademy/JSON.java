package com.example.codeacademy;

import android.util.Log;

import com.example.codeacademy.objects.Course;
import com.example.codeacademy.objects.CourseById;
import com.example.codeacademy.objects.Courses;
import com.example.codeacademy.objects.Error;
import com.example.codeacademy.objects.Lesson;
import com.example.codeacademy.objects.LessonToServer;
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

    public static Course getCourse(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        Course course = new Course();
        try {
            course = mapper.readValue(json,Course.class);
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG,"Parsing error");
        }
        return course;
    }

    public static CourseById getCourseById(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        CourseById course = new CourseById();
        try {
            course = mapper.readValue(json,CourseById.class);
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG,"Parsing error");
        }
        return course;
    }

    public static Lesson getLesson(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        Lesson lesson = new Lesson();
        try {
            lesson = mapper.readValue(json,Lesson.class);
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG,"Parsing error");
        }
        return lesson;
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

    public static String buildCourse(Course course) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(course);
        }catch (IOException e)
        {
            Log.e(LOG_TAG,"Parsing error");
        }
        return result;
    }

    public static String buildLesson(LessonToServer lesson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(lesson);
        }catch (IOException e)
        {
            Log.e(LOG_TAG,"Parsing error");
        }
        return result;
    }
}
