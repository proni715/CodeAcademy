package com.example.codeacademy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codeacademy.JSON;
import com.example.codeacademy.R;
import com.example.codeacademy.Requests;
import com.example.codeacademy.objects.CourseById;
import com.example.codeacademy.objects.Error;
import com.example.codeacademy.objects.Lesson;
import com.example.codeacademy.objects.Links;
import com.example.codeacademy.objects.ServerResponse;

import java.io.IOException;

public class LessonActivity extends AppCompatActivity {

    TextView titleTextView;
    TextView textTextView;
    SharedPreferences mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        titleTextView = findViewById(R.id.lessonTitleTextView);
        textTextView = findViewById(R.id.lessonTextTextView);
        mData = getSharedPreferences(getString(R.string.APP_PREFERENCES_NAME), Context.MODE_PRIVATE);
        String lessonId = getIntent().getStringExtra("Id");
        new APIQueryTask().execute(lessonId);
    }

    class APIQueryTask extends AsyncTask<String,Void, ServerResponse> {

        @Override
        protected ServerResponse doInBackground(String ... id) {
            Links link = new Links();
            ServerResponse response = null;
            try {
                response = Requests.getRequestWithHeaders(link.getLessonURL(id[0]),mData.getString(getString(R.string.APP_PREFERENCES_NAME),""));

            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;

        }

        @Override
        protected void onPostExecute(ServerResponse response) {

            System.out.println(response.getResponseBody());

            if(response.getResponseCode()==200) {

                Lesson lesson = JSON.getLesson(response.getResponseBody());
                titleTextView.setText(lesson.getTitle());
                textTextView.setText(lesson.getText());


            }
            else{
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }


        }


    }
}
