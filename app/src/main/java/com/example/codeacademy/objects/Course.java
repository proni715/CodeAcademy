package com.example.codeacademy.objects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Course {



    private String id;
    private String user;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;

    public Course(){

    }

    public Course(String title, String description){

        this.title = title;
        this.description = description;
        this.avatar ="avatar";

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
