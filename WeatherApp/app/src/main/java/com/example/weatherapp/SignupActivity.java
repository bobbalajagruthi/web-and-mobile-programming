package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private static final String SIGNUP_ACTIVITY_TAG="SignupActivity";
    TextView signupEmail,signupPassword,userName;
    Button signup;
    FirebaseFirestore frstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupEmail = findViewById(R.id.Signup_Email);
        signupPassword= findViewById(R.id.Signup_Password);
        userName= findViewById(R.id.Name);
        signup= findViewById(R.id.Signup);
        frstore = FirebaseFirestore.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("SignupActivity","I am here....0");
                String name = userName.getText().toString();
                String email = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();
                Log.i("username", name);
                Log.i("useremail", email);
                Log.i("userpw", password);
                Log.i("SignupActivity","I am here....1");
                DocumentReference documentReference = frstore.collection("user_details").document(email);
                Map<String,Object> user = new HashMap<>();
                user.put("uName",name);
                user.put("uEmail",email);
                user.put("uPassword",password);
                Log.i("SignupActivity","I am here...2");
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("SignupActivity","I am here....3");
                        Toast.makeText(SignupActivity.this,"User Creadted & Saved Successfully!!!!",Toast.LENGTH_LONG).show();

                    }
                });
                Intent u = new Intent(SignupActivity.this,MainActivity.class);
                startActivity(u);

            }
        });

    }
}