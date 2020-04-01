package com.example.codeacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
            try {
                System.out.println(JSON.buildUser(users[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
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

            System.out.println(response.getResponseCode()+" fuck "+response.getResponseBody());
            if(response.getResponseCode()==201){
                Toast.makeText(getApplicationContext(),"Registration succesfull",Toast.LENGTH_LONG).show();
            }
            else if(response.getResponseCode()==400){
                Code400 code = null;
                code = JSON.getCode400(response.getResponseBody());
                Toast.makeText(getApplicationContext(),code.message,Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Please, check all data",Toast.LENGTH_LONG).show();
            }

        }



    }
}
