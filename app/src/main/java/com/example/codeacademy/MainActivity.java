package com.example.codeacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = getSharedPreferences(getString(R.string.APP_PREFERENCES_NAME), Context.MODE_PRIVATE);
        if(mData.contains(getString(R.string.APP_PREFERENCES_NAME))){
            Intent intent = new Intent(MainActivity.this,CoursesActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(MainActivity.this,SignInActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

}
