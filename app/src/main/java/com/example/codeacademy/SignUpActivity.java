package com.example.codeacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codeacademy.objects.Error;
import com.example.codeacademy.objects.Links;
import com.example.codeacademy.objects.ServerResponse;
import com.example.codeacademy.objects.User;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {
     EditText email;
     EditText name;
     EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = (EditText) findViewById(R.id.emailSignUpEditText);
        name = (EditText) findViewById(R.id.nameSignUpEditText);
        password = (EditText) findViewById(R.id.passwordSignUpEditText);
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
                response = Requests.postRequest(link.getSignUpURL(),JSON.buildUser(users[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(ServerResponse response) {

            if(response.getResponseCode()==201){
                Toast.makeText(getApplicationContext(),"Registration succesfull",Toast.LENGTH_LONG).show();
            }
            else {
                Error code = JSON.getError(response.getResponseBody());
                Toast.makeText(getApplicationContext(),code.getMessage(),Toast.LENGTH_LONG).show();
            }
        }



    }
}
