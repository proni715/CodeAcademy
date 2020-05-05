package com.example.codeacademy.objects;

import java.net.MalformedURLException;
import java.net.URL;

public class Links {
    private final String BASE_URL= "https://code-academy-ver2.herokuapp.com/";
    private final String COURSES = "courses/";
    private final String LESSONS = "lessons/";
    private final String USERS = "users/";
    private final String LOGIN = "login/";
    private  final String CURRENT_USER = "me/";


    public URL getCoursesURL(){
        URL url = null;
        try{
            url = new URL(this.BASE_URL+this.COURSES);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public URL getCourseURL(String courseId){
        URL url = null;
        courseId+="/";
        try{
            url = new URL(this.BASE_URL+this.COURSES+courseId);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public URL getLessonsURL(String courseId){
        URL url = null;
        courseId+="/";
        try{
            url = new URL(this.BASE_URL+this.COURSES+courseId+this.LESSONS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public URL getLessonURL(String courseId,String lessonId){
        URL url = null;
        courseId+="/";
        lessonId+="/";
        try{
            url = new URL(this.BASE_URL+this.COURSES+courseId+this.LESSONS+lessonId);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public URL getSignInURL(){
        URL url = null;
        try{
            url = new URL(this.BASE_URL+this.USERS+this.LOGIN);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public URL getSignUpURL(){
        URL url = null;
        try{
            url = new URL(this.BASE_URL+this.USERS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public URL getCurrentUserURL(){
        URL url = null;
        try{
            url = new URL(this.BASE_URL+this.USERS+this.CURRENT_USER);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


}
