package com.example.codeacademy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
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
    SharedPreferences mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        courseId = getIntent().getStringExtra("Id");
        courseNameTextView = findViewById(R.id.courseNameTextView);
        linearLayout = findViewById(R.id.linerLayoutCourse);
        mData = getSharedPreferences(getString(R.string.APP_PREFERENCES_NAME), Context.MODE_PRIVATE);
        new APIQueryTask().execute(courseId);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id ==R.id.action_createLesson)
        {
            Intent intent = new Intent(this, CreateLessonActivity.class);
            intent.putExtra("Id",courseId);
            startActivity(intent);
            this.finish();

        }
        if(id ==R.id.action_logOut)
        {
            if(mData.contains(getString(R.string.APP_PREFERENCES_NAME))){
                mData.edit().clear().commit();
            }

        }
        if(id ==R.id.action_registration)
        {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            this.finish();

        }
        return super.onOptionsItemSelected(item);
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
                CardView card = new CardView(CourseActivity.this);

                TextView titleTextView = new TextView(CourseActivity.this);
                titleTextView.setText(courseById.getLessons().getLessons()[i].getTitle());
                titleTextView.setId(i);
                titleTextView.setBackgroundColor(getResources().getColor(R.color.design_default_color_secondary_variant));
                titleTextView.setTextSize(25);
                TextView descriptionTextView = new TextView((CourseActivity.this));
                descriptionTextView.setText(courseById.getLessons().getLessons()[i].getDescription());
                descriptionTextView.setId(i);
                titleTextView.setTextSize(14);
                card.addView(titleTextView);
                card.addView(descriptionTextView);
                final int finali = i;
                titleTextView.setGravity(Gravity.CENTER);
                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CourseActivity.this,LessonActivity.class);
                        intent.putExtra("Id",courseById.getLessons().getLessons()[finali].getId());
                        startActivity(intent);
                    }
                });
                ////Вишеееее
                //
                ////
                ////
                ////
                ////
                ////


                linearLayout.addView(card);

            }

        }


    }

}
