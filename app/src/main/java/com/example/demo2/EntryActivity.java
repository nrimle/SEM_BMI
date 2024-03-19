package com.example.demo2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        EditText heightInput = (EditText) findViewById(R.id.edittext_height);
        EditText weightInput = (EditText) findViewById(R.id.edittext_weight);

        Button button = findViewById(R.id.button_calc);
        button.setOnClickListener(v -> {

            double height = 0;
            double weight = 0;
            String heightString = heightInput.getText().toString();
            if (!heightString.isEmpty())
                try {
                    height = Double.parseDouble(heightString);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            String weightString = weightInput.getText().toString();
            if (!weightString.isEmpty())
                try {
                    weight = Double.parseDouble(weightString);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("measurement", (Serializable) new Measurement(height, weight));
            startActivity(intent);
        });
    }
}
