package com.khalilayache.justjava;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView quantityTextView;
    TextView priceTextView;
    CheckBox whippedCreamCheck;
    CheckBox chocolateCheck;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        whippedCreamCheck = (CheckBox) findViewById(R.id.checkbox_whippedcream);
        chocolateCheck = (CheckBox) findViewById(R.id.checkbox_chocolate);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
    }


    public void submitOrder(View view) {
        int quantity = Integer.parseInt(quantityTextView.getText().toString());
        boolean isWhippedCream = whippedCreamCheck.isChecked();
        int coffeePrice = 5;

        String whippedCream = getString(R.string.no);
        if (isWhippedCream) {
            whippedCream = getString(R.string.yes);
            coffeePrice++;
        }
        boolean isChocolate = chocolateCheck.isChecked();
        String chocolate = getString(R.string.no);
        if (isChocolate) {
            chocolate = getString(R.string.yes);
            coffeePrice = coffeePrice + 2;
        }


        String message;
        String subject;
        if (quantity > 0) {
            message =  getString(R.string.name) + ": " + nameEditText.getText().toString() +
                    "\n" + getString(R.string.add_whippedCream)+ " " + whippedCream +
                    "\n" + getString(R.string.add_chocolate)+ " " + chocolate +
                    "\n" + getString(R.string.quantity)+ " "+ quantity +
                    "\n" + getString(R.string.total)+ " " + NumberFormat.getCurrencyInstance().format(quantity * coffeePrice) +
                    "\n" + getString(R.string.thank_you);

            subject = getString(R.string.orde_to)+ " " + nameEditText.getText().toString();
            String emailTo = "teste_de_email@justjava.com";
            displayMessage(message, subject, emailTo);
        }


    }

    private void display(int number) {
        quantityTextView.setText(String.valueOf(number));
    }

    public void decrement(View view) {
        int quantity = Integer.parseInt(quantityTextView.getText().toString());
        if(quantity != 0){
            display(quantity - 1);
        }

    }

    public void increment(View view) {
        int value = Integer.parseInt(quantityTextView.getText().toString());
        if (value < 100)
            display(value + 1);
    }

    private void displayMessage(String message,String subject, String emailTo) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:" + emailTo));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);

        }
    }
}
