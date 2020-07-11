package com.example.mypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    TextView orderSummary;
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        orderSummary = findViewById(R.id.orderSummary);
        orderSummary.setText(Html.fromHtml("<br/><h2><u><b>Your Order Summary</b></u></h2><br/>"));
        if(getIntent() != null){
            orderSummary.append(getIntent().getStringExtra("orderSummary"));
        }else{
            orderSummary.setText("You have no orders !!");
        }
        orderSummary.append(Html.fromHtml("<br/>"));
        orderSummary.setVisibility(View.VISIBLE);

        goBack = (Button)findViewById(R.id.GoBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SummaryActivity.this, OrderActivity.class);
                startActivity(i);
            }
        });



    }
}