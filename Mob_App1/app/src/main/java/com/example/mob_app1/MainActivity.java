package com.example.mob_app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText EId ;
    EditText PW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        EId = findViewById(R.id.emailid);
        PW = findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailid = EId.getText().toString();
                String passwrd = PW.getText().toString();
                System.out.println(emailid);
                System.out.println(passwrd);
                if (emailid.isEmpty() && passwrd.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Enter Credentials...",Toast.LENGTH_LONG).show();
                }

                 else if(emailid.isEmpty() && !passwrd.isEmpty()){
                    EId.setError("Please enter email id");
                    EId.requestFocus();
                } else  if(passwrd.isEmpty() && !emailid.isEmpty()){
                    PW.setError("Please enter your password");
                    PW.requestFocus();
                }
                else if (emailid.equals("jaggu@gmail.com")&&  passwrd.equals("jaggu6")){
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_LONG).show();
                Intent user = new Intent(MainActivity.this, LogoutActivity.class);
                startActivity(user);}
                else{Toast.makeText(getApplicationContext(),
                        "Enter Right Credentials...",Toast.LENGTH_LONG).show();

                }



            }
        });
    }
}