package com.example.codeacademy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class SignUpActivity extends AppCompatActivity {
     EditText email;
     EditText name;
     EditText password;
    SharedPreferences mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = (EditText) findViewById(R.id.emailSignUpEditText);
        name = (EditText) findViewById(R.id.nameSignUpEditText);
        password = (EditText) findViewById(R.id.passwordSignUpEditText);
        mData = getSharedPreferences(getString(R.string.APP_PREFERENCES_NAME), Context.MODE_PRIVATE);
    }

    public void onClickButton(View view){
        String email = this.email.getText().toString();
        String name = this.name.getText().toString();
        String password = this.password.getText().toString();
        if(!email.isEmpty()&&!name.isEmpty()&&!password.isEmpty()){
            User user = new User(email,name,password);
            new APIQueryTask().execute(user);
        }else{
            Toast.makeText(getApplicationContext(),"Please enter your data",Toast.LENGTH_LONG);
        }
    }

    class APIQueryTask extends AsyncTask<User,Void, ServerResponse> {

        @Override
        protected ServerResponse doInBackground(User... users) {
            Links link = new Links();
            ServerResponse response = null;
            try {
                response = Requests.postRequest(link.getSignUpURL(), JSON.buildUser(users[0]));
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
                Toast.makeText(getApplicationContext(),"You register and authorized",Toast.LENGTH_LONG).show();

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
