package com.example.codeacademy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import com.example.codeacademy.objects.Course;
import com.example.codeacademy.objects.Courses;
import com.example.codeacademy.objects.Links;
import com.example.codeacademy.objects.ServerResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {

    LinearLayout liner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        liner = findViewById(R.id.linerLayout);
        new APIQueryTask().execute();
    }

    class APIQueryTask extends AsyncTask<Void,Void, ServerResponse> {

        @Override
        protected ServerResponse doInBackground(Void ... voids) {
            Links link = new Links();
            ServerResponse response = null;
            try {
                response = Requests.getRequest(link.getCoursesURL());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;

        }

        @Override
        protected void onPostExecute(ServerResponse response) {
            Courses json = JSON.getCourses(response.getResponseBody());
            final List<Course> courses = Arrays.asList(json.getRows());
            LinearLayout.LayoutParams lParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            lParams.gravity = Gravity.CENTER_HORIZONTAL;
            ///ТАРАССССС Тута Створення списку
            ////
            ////
            ////
            ////

            for(int i =0;i<courses.size();i++){
                ////////////
                ////////
                ////////////
                ////////

                ////А канкрєтніє тут
                CardView card = new CardView(CoursesActivity.this);
                TextView titleTextView = new TextView(CoursesActivity.this);
                TextView descriptionTextView = new TextView(CoursesActivity.this);
                titleTextView.setText(courses.get(i).getTitle());
                titleTextView.setId(i);
                titleTextView.setTextSize(40);
                descriptionTextView.setText(courses.get(i).getDescription());
                descriptionTextView.setTextSize(20);
                card.addView(titleTextView);
                card.addView(descriptionTextView);
                final int finalI = i;
                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CoursesActivity.this,CourseActivity.class);
                        intent.putExtra("Id",courses.get(finalI).getId());
                        startActivity(intent);
                    }
                });
                liner.addView(card);

            }


        }

    }
}
