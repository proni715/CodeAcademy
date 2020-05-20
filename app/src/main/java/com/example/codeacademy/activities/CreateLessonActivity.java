package com.example.codeacademy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codeacademy.JSON;
import com.example.codeacademy.R;
import com.example.codeacademy.Requests;
import com.example.codeacademy.objects.Course;
import com.example.codeacademy.objects.Error;
import com.example.codeacademy.objects.Lesson;
import com.example.codeacademy.objects.LessonToServer;
import com.example.codeacademy.objects.Links;
import com.example.codeacademy.objects.ServerResponse;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.IOException;

public class CreateLessonActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText descriptionEditText;
    EditText textEditText;
    String COURSE_ID;
    Button createCourse;
    SharedPreferences mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lesson);
        COURSE_ID =  getIntent().getStringExtra("Id");
        mData = getSharedPreferences(getString(R.string.APP_PREFERENCES_NAME), Context.MODE_PRIVATE);
        titleEditText = findViewById(R.id.titleEditTextCreateLesson);
        descriptionEditText = findViewById(R.id.descriptionEditTextCreateLesson);
        textEditText = findViewById(R.id.textEditTextCreateLesson);
        createCourse = findViewById(R.id.createLessonButtonCreateLesson);
        createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!titleEditText.getText().toString().isEmpty()&&!descriptionEditText.getText().toString().isEmpty()&&!textEditText.getText().toString().isEmpty()){
                    LessonToServer lesson = new LessonToServer(COURSE_ID,titleEditText.getText().toString()
                            ,descriptionEditText.getText().toString(),textEditText.getText().toString());
                    new APIQueryTask().execute(lesson);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please, enter your data", Toast.LENGTH_LONG);
                }
            }
        });

    }

    class APIQueryTask extends AsyncTask<LessonToServer,Void, ServerResponse> {

        @Override
        protected ServerResponse doInBackground(LessonToServer... lessons) {
            Links link = new Links();
            ServerResponse response = null;
            try {
                response = Requests.postRequest(link.getLessonsURL(), JSON.buildLesson(lessons[0]),mData.getString(getString(R.string.APP_PREFERENCES_NAME),""));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(ServerResponse response) {

            if(response.getResponseCode()==201){
                Lesson lesson = JSON.getLesson(response.getResponseBody());
                Intent intent = new Intent(CreateLessonActivity.this,CourseActivity.class);
                intent.putExtra("Id",COURSE_ID);
                startActivity(intent);
                CreateLessonActivity.this.finish();
            }
            else{
                Error error = JSON.getError(response.getResponseBody());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }



    }
}
