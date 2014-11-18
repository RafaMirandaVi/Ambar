package com.example.jhordan.Ambar;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


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

        name = (EditText)findViewById(R.id.LoginPersonText);
        pass = (EditText)findViewById(R.id.LoginPassText);
        btn = (Button)findViewById(R.id.LoginButtonLogin);


        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                nameTxt = name.getText().toString();
                passTxt = pass.getText().toString();

                sessionM.createLoginSession(nameTxt,passTxt);
                Log.d("Login","Session created");

                Intent intent = new Intent(getApplicationContext(),MyActivity.class);
                startActivity(intent);
                finish();

            }

        });
    }
}
