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
import com.example.codeacademy.objects.Course;
import com.example.codeacademy.objects.Courses;
import com.example.codeacademy.objects.Links;
import com.example.codeacademy.objects.ServerResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {

    LinearLayout liner;
    SharedPreferences mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        liner = findViewById(R.id.linerLayout);
        mData = getSharedPreferences(getString(R.string.APP_PREFERENCES_NAME), Context.MODE_PRIVATE);
        new APIQueryTask().execute();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.courses_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id ==R.id.action_createCourse)
        {
            Intent intent = new Intent(this, CreateCourseActivity.class);
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
            CardView.LayoutParams cardParams = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            cardParams.gravity = Gravity.LEFT;
            lParams.gravity = Gravity.CENTER_HORIZONTAL;
            ///ТАРАССССС Тута Створення списку
            ////
            ////
            ////
            ////

            for(int i =0;i<courses.size();i++){
                System.out.println(courses.get(i).getTitle());
                ////////////
                ////////
                ////////////
                ////////
                CardView card = new CardView(CoursesActivity.this);
                card.setBackgroundColor(getResources().getColor(R.color.design_default_color_secondary_variant));
                card.setLayoutParams(cardParams);
                ////А канкрєтніє тут
                TextView titleTextView = new TextView(CoursesActivity.this);
                TextView descriptionTextView = new TextView(CoursesActivity.this);
                titleTextView.setText(courses.get(i).getTitle());
                titleTextView.setId(i);
                titleTextView.setTextSize(25);
                titleTextView.setGravity(Gravity.TOP);
                descriptionTextView.setText(courses.get(i).getDescription());
                descriptionTextView.setId(i);
                descriptionTextView.setPadding(2,10,0,0);
                descriptionTextView.setGravity(Gravity.CENTER);
                descriptionTextView.setTextSize(14);
                card.addView(titleTextView);
                card.addView(descriptionTextView);
                final int finali = i;
                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CoursesActivity.this, CourseActivity.class);
                        intent.putExtra("Id",courses.get(finali).getId());
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


                liner.addView(card);

            }


        }

    }
}
