package com.example.codeacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

public class SignInActivity extends AppCompatActivity {

    static EditText login;
    static EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        login = (EditText) findViewById(R.id.emailSignInEditText);
        password = (EditText) findViewById(R.id.passwordSignInEditText);
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
                response = Requests.postRequest(link.getSignInURL(),JSON.buildUser(users[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(ServerResponse response) {

            System.out.println(response.getResponseCode()+" /fuck/ "+ response.getResponseBody());
            if(response.getResponseCode()==201){

                Toast.makeText(getApplicationContext(),"LoggedIn",Toast.LENGTH_LONG).show();
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
