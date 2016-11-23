package com.khalilayache.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView quantityTextView;
    TextView priceTextView;
    int quantity;
    double priceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        priceTextView = (TextView) findViewById(R.id.price_text_view);
    }


    public void submitOrder(View view) {
        quantity = Integer.parseInt(quantityTextView.getText().toString());
        String message;
        if (quantity > 0)
            message = "Total: " + NumberFormat.getCurrencyInstance().format(quantity * 5) + "\nThank You!";
        else
            message = "Free";

        displayMessage(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        quantityTextView.setText(String.valueOf(number));
    }

    private void displayPrice(int number) {
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void decrement(View view) {
       quantity = Integer.parseInt(quantityTextView.getText().toString());
        if(quantity != 0){
            display(quantity - 1);
        }

    }

    public void incecrement(View view) {
        int value = Integer.parseInt(quantityTextView.getText().toString());
        display(value + 1);
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}
