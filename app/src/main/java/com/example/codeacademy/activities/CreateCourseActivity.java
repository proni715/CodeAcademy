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
import com.example.codeacademy.objects.Links;
import com.example.codeacademy.objects.ServerResponse;
import com.example.codeacademy.objects.Token;
import com.example.codeacademy.objects.User;

import java.io.IOException;

public class CreateCourseActivity extends AppCompatActivity {

    EditText titleCourse;
    EditText descriptionCourse;
    Button createCourse;
    SharedPreferences mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        titleCourse = findViewById(R.id.titleCourseEditTextCreateCourse);
        descriptionCourse = findViewById(R.id.descriptionCourseEditTextCreateCourse);
        createCourse = findViewById(R.id.createCourseButtonCreateCourse);
        mData = getSharedPreferences(getString(R.string.APP_PREFERENCES_NAME), Context.MODE_PRIVATE);
        createCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!titleCourse.getText().toString().isEmpty()&&!descriptionCourse.getText().toString().isEmpty()&&mData.contains(getString(R.string.APP_PREFERENCES_NAME))){
                    Course course = new Course(titleCourse.getText().toString(),descriptionCourse.getText().toString());
                    new APIQueryTask().execute(course);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please, enter your data and Log In",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    class APIQueryTask extends AsyncTask<Course,Void, ServerResponse> {

        @Override
        protected ServerResponse doInBackground(Course... courses) {
            Links link = new Links();
            ServerResponse response = null;
            try {
                response = Requests.postRequest(link.getCoursesURL(), JSON.buildCourse(courses[0]),mData.getString(getString(R.string.APP_PREFERENCES_NAME),""));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(ServerResponse response) {

            if(response.getResponseCode()==201){
                Course course = JSON.getCourse(response.getResponseBody());
                Intent intent = new Intent(CreateCourseActivity.this,CoursesActivity.class);
                startActivity(intent);
                CreateCourseActivity.this.finish();
            }
            else{
                Error error = JSON.getError(response.getResponseBody());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }



    }
}
