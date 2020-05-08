package com.example.codeacademy.objects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

public class Courses {

    private int count;
    public  Course [] rows;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Course [] getRows() {
        return rows;
    }

    public void setRows(Course [] rows) {
        this.rows = rows;
    }


}
