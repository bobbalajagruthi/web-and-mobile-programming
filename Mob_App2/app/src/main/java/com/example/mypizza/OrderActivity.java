package com.example.mypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    private static final Integer PIZZA_PRICE = 5;
    private static final Integer ONION_PRICE = 3;
    private static final Integer TOMATOES_PRICE = 2;
    private static final Integer JALAPENOS_PRICE = 2;
    private static final Integer PEPPER_PRICE = 1;
    float totalPrice;
    Integer quantity = 1;
    String orderSummary;

    Button summary,order;

    // From the Layout
    EditText userName;
    TextView quantityTextView;
    CheckBox onion, tomatoes, jalapenos, greenPepper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        quantityTextView = findViewById(R.id.quantity_text_view);
        userName = findViewById(R.id.user_input);
        onion = findViewById(R.id.Onion);
        tomatoes = findViewById(R.id.Tomato);
        jalapenos = findViewById(R.id.Jalapenos);
        greenPepper = findViewById(R.id.GreenPeppers);
        summary = (Button)findViewById(R.id.Summary);
        order =(Button)findViewById(R.id.Order);

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isUserEmpty()) {
                     orderSummary = fetchDetails();
                    Intent intent = new Intent(OrderActivity.this, SummaryActivity.class);
                     intent.putExtra("orderSummary", orderSummary);
                    startActivity(intent);
                }
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUserEmpty()) {
                    orderSummary = fetchDetails();
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    // The intent does not have a URI, so declare the "text/plain" MIME type
                    emailIntent.setType("plain/text");
                    // Recipients
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jbz5t@mail.umkc.edu"});
                    // Adding Subject
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
                    // Adding the Order Summary Text
                    emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);
                    // Redirecting to Email Intent
                    startActivity(Intent.createChooser(emailIntent, ""));
                }
            }
        });

    }

    private boolean isUserEmpty(){
        // Checking If username is present or not
        String name = userName.getText().toString();
        if(name == null || name.isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Enter User Name...",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    private String fetchDetails() {
        boolean onionFlag = onion.isChecked();
        boolean tomatoesFlag = tomatoes.isChecked();
        boolean jalapenosFlag = jalapenos.isChecked();
        boolean pepperFlag = greenPepper.isChecked();

        // Get the Total Price.
         totalPrice = calculatePrice(onionFlag, tomatoesFlag, jalapenosFlag, pepperFlag, quantity);
        // Creating Order Summary
        return fetchOrderSummary(userName.getText().toString(), onionFlag, tomatoesFlag, jalapenosFlag, pepperFlag, quantity, totalPrice);
    }

    private String fetchOrderSummary(String userName, boolean onionFlag, boolean tomatoesFlag,
                                     boolean jalapenosFlag, boolean pepperFlag, int quantity, float totalPrice) {

        String str = "Dear" + userName +"\n"+"\n";

        str = str + "Ingredients included: ";

        if(onionFlag){
            str = str + "Onions" + "\n";
        }
        if(tomatoesFlag){
            str = str + "Tomatoes" + "\n";
        }
        if(jalapenosFlag){
            str = str + "Jalapenos" + "\n";
        }
        if(pepperFlag){
            str = str + "Green Peppers" + "\n";
        }

        str = str + "Quantity: "+ quantity + "\n" + "Price: " + totalPrice + "\n" + "\n" + "Thanks for your order";

        return str;
    }

    private float calculatePrice(boolean onion, boolean tomato, boolean jalapeno, boolean pepper, Integer quantity) {
        int basePrice = PIZZA_PRICE;
        if (onion) {
            basePrice += ONION_PRICE;
        }
        if (tomato) {
            basePrice += TOMATOES_PRICE;
        }
        if (jalapeno){
            basePrice +=JALAPENOS_PRICE;
        }
        if(pepper){
            basePrice += PEPPER_PRICE;
        }
        return quantity * basePrice;
    }


    public void increment(View view) {
        if (quantity < 20) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please select less than 20 Pizzas",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method decrements the quantity of pizza by one
     *
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please select at least one Pizza",Toast.LENGTH_LONG).show();

        }
    }

    // Displays the Quantity
    private void display(int number) {
        quantityTextView.setText("" + number);
    }



}