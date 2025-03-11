package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        EditText edtweight = findViewById(R.id.edtweight);
        EditText edtheightin = findViewById(R.id.edtheightinch);
        EditText edtheightftt = findViewById(R.id.edtheightfeet);
        Button edt_calc = findViewById(R.id.edtcalculate);
        TextView result = findViewById(R.id.edtresult);



        // Set click listener
        edt_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate inputs
                if (edtweight.getText().toString().isEmpty() ||
                        edtheightin.getText().toString().isEmpty() ||
                        edtheightftt.getText().toString().isEmpty()) {
                    result.setText(getString(R.string.please_enter_all_fields));
                    return;
                }

                try {
                    int weight = Integer.parseInt(edtweight.getText().toString());
                    int heightinc = Integer.parseInt(edtheightin.getText().toString());
                    int heightft = Integer.parseInt(edtheightftt.getText().toString());

                    // Calculate BMI
                    float totalInches = (heightft * 12) + heightinc;
                    double totalCm = totalInches * 2.54;
                    double totalMeters = totalCm / 100;
                    double bmi = weight / (totalMeters * totalMeters);

                    // Display result
                    // will excecute if you are over weight
                    if (bmi > 25) {
                       startActivity(new Intent(MainActivity.this, OverWeight.class));
                       finish();
                       //will excecute if you are under weight
                    } else if (bmi < 18) {
                        startActivity(new Intent(MainActivity.this, UnderWeight.class));
                        finish();
                        //will excecute if you are fit
                    } else {
                        startActivity(new Intent(MainActivity.this, youarefit.class));
                        finish();
                    }
                } catch (NumberFormatException e) {
                    result.setText(getString(R.string.invalid_input_please_enter_numeric_values));
                }
            }
        });
    }
}
