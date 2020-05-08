package com.example.codeacademy.objects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)


public class Lessons {
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Lesson[] getLessons() {
        return lessons;
    }

    public void setLessons(Lesson[] lessons) {
        this.lessons = lessons;
    }

    private int count;
    private Lesson[]lessons;
}
