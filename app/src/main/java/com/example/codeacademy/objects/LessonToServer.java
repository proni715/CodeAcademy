package com.example.codeacademy.objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class LessonToServer {

    @JsonProperty("course")
    String course;
    @JsonProperty("title")
    String title;
    @JsonProperty("avatar")
    String avatar = "avatar";
    @JsonProperty("description")
    String description;
    @JsonProperty("text")
    String text;
    @JsonProperty("photos")
    String photos = "bimba";
    @JsonProperty("attachments")
    String attachments = "bimba";

    public LessonToServer(String course, String title, String description, String text){

        this.course = course;
        this.title = title;
        this.description = description;
        this.text = text;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

}
