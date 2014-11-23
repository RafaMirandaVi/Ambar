package com.example.jhordan.Ambar;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {

    EditText pass, name;
    String passTxt, nameTxt;
    Button btn;
    SessionManagement sessionM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionM = new SessionManagement(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("LOGIN","onCreate");

        name = (EditText)findViewById(R.id.LoginPersonText);
        pass = (EditText)findViewById(R.id.LoginPassText);
        btn = (Button)findViewById(R.id.LoginButtonLogin);


        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                nameTxt = name.getText().toString();
                passTxt = pass.getText().toString();

                if(nameTxt.trim().length() > 0 && passTxt.trim().length() > 0){
                    //nameTxt.equals("test") && passTxt.equals("test")
                    if(true){

                        // Creating user login session
                        // For testing i am stroing name, email as follow
                        // Use user real data
                        sessionM.createLoginSession(nameTxt,passTxt);
                        Log.d("LOGIN","Session created");

                        Intent intent = new Intent(getApplicationContext(),MyActivity.class);
                        startActivity(intent);
                        finish();

                    }else{
                        // username / password doesn't match
                        Toast.makeText(getApplicationContext(), "Wrong usr/pass", Toast.LENGTH_LONG).show();

                    }
                }else{
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    Toast.makeText(getApplicationContext(), "Enter usr and pass", Toast.LENGTH_LONG).show();
                }


            }

        });
    }
}
