package com.example.codeacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codeacademy.objects.Course;
import com.example.codeacademy.objects.Courses;
import com.example.codeacademy.objects.Error;
import com.example.codeacademy.objects.Links;
import com.example.codeacademy.objects.ServerResponse;
import com.example.codeacademy.objects.Token;
import com.example.codeacademy.objects.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Response;

public class CoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
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

            Courses courses  = JSON.getCourses(response.getResponseBody());
            ScrollView scrollView = new ScrollView(CoursesActivity.this);
            scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Course [] course = courses.getRows();
            LinearLayout linearLayout = new LinearLayout(CoursesActivity.this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.CENTER);
            for(int i =0;i<courses.getCount();i++){
                System.out.println(course[i].getTittle());
                TextView textView = new TextView(CoursesActivity.this);
                textView.setText(course[i].getTittle());
                textView.setId(i);
                linearLayout.addView(textView);
            }
            scrollView.addView(linearLayout);

        }

    }
}
