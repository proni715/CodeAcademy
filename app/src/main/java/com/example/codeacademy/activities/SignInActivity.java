package com.example.codeacademy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codeacademy.JSON;
import com.example.codeacademy.R;
import com.example.codeacademy.Requests;
import com.example.codeacademy.objects.Error;
import com.example.codeacademy.objects.Links;
import com.example.codeacademy.objects.ServerResponse;
import com.example.codeacademy.objects.Token;
import com.example.codeacademy.objects.User;

import java.io.IOException;

public class SignInActivity extends AppCompatActivity {

    static EditText login;
    static EditText password;
    TextView signUp;

    SharedPreferences mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        login = (EditText) findViewById(R.id.emailSignInEditText);
        password = (EditText) findViewById(R.id.passwordSignInEditText);
        signUp = findViewById(R.id.signUpTextViewSignIn);
        mData = getSharedPreferences(getString(R.string.APP_PREFERENCES_NAME), Context.MODE_PRIVATE);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickButton (View view) throws IOException {

        String logString = login.getText().toString();
        String passString = password.getText().toString();
        if(!logString.isEmpty()&&!passString.isEmpty()){
            User user = new User(logString,passString);
            new APIQueryTask().execute(user);
        }else{
            Toast.makeText(getApplicationContext(),"Please enter your data",Toast.LENGTH_LONG).show();
        }
    }

    class APIQueryTask extends AsyncTask<User,Void, ServerResponse> {

        @Override
        protected ServerResponse doInBackground(User... users) {
            Links link = new Links();
            ServerResponse response = null;
            try {
                response = Requests.postRequest(link.getSignInURL(), JSON.buildUser(users[0]),mData.getString(getString(R.string.APP_PREFERENCES_NAME),""));

            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;


        }

        @Override
        protected void onPostExecute(ServerResponse response) {

            if(response.getResponseCode()==201){
                Token token = JSON.getToken(response.getResponseBody());
                SharedPreferences.Editor editor = mData.edit();
                editor.putString(getString(R.string.APP_PREFERENCES_NAME),token.getToken());
                editor.apply();
                Toast.makeText(getApplicationContext(),"You authorized",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignInActivity.this, CoursesActivity.class);
                startActivity(intent);

            }else if(response.getResponseCode()==401){
                Toast.makeText(getApplicationContext(),"Check email or password",Toast.LENGTH_LONG).show();
            }
            else{
                Error error = JSON.getError(response.getResponseBody());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }
}
