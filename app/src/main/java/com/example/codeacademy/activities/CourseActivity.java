package com.example.codeacademy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.codeacademy.JSON;
import com.example.codeacademy.R;
import com.example.codeacademy.Requests;
import com.example.codeacademy.objects.CourseById;
import com.example.codeacademy.objects.Links;
import com.example.codeacademy.objects.ServerResponse;

import java.io.IOException;

public class CourseActivity extends AppCompatActivity {

    String courseId;
    TextView courseNameTextView;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        courseId = getIntent().getStringExtra("Id");
        courseNameTextView = findViewById(R.id.courseNameTextView);
        linearLayout = findViewById(R.id.linerLayoutCourse);
        new APIQueryTask().execute(courseId);
    }

    class APIQueryTask extends AsyncTask<String,Void, ServerResponse> {

        @Override
        protected ServerResponse doInBackground(String ... id) {
            Links link = new Links();
            ServerResponse response = null;
            try {
                response = Requests.getRequest(link.getCourseURL(id[0]));

            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;

        }

        @Override
        protected void onPostExecute(ServerResponse response) {

            final CourseById courseById = JSON.getCourseById(response.getResponseBody());
            System.out.println(courseById.getCourse().getTitle());
            courseNameTextView.setText(courseById.getCourse().getTitle());

            LinearLayout.LayoutParams lParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            lParams.gravity = Gravity.CENTER_HORIZONTAL;
            ///ТАРАССССС Тута Створення списку
            ////
            ////
            ////
            ////

            for(int i =0;i<courseById.getLessons().getCount();i++){
                System.out.println(courseById.getLessons().getLessons()[i].getTitle());
                ////////////
                ////////
                ////////////
                ////////

                ////А канкрєтніє тут
                TextView textView = new TextView(CourseActivity.this);
                textView.setText(courseById.getLessons().getLessons()[i].getTitle());
                textView.setId(i);
                textView.setTextSize(25);
                ////Вишеееее
                //
                ////
                ////
                ////
                ////
                ////
                final int finali = i;
                textView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CourseActivity.this,LessonActivity.class);
                        intent.putExtra("Id",courseById.getLessons().getLessons()[finali].getId());
                        startActivity(intent);

                    }
                });
                textView.setGravity(Gravity.CENTER);
                linearLayout.addView(textView);

            }

        }


    }

}
