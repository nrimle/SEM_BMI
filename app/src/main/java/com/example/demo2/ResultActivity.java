package com.example.demo2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String name = prefs.getString("name", "n/a");

        Intent i = getIntent();
        Measurement measurement = (Measurement) i.getSerializableExtra("measurement");

        TextView textViewBmi = (TextView) findViewById(R.id.textView_bmi);
        TextView textViewHeight = (TextView) findViewById(R.id.textView_height);
        TextView textViewWeight = (TextView) findViewById(R.id.textView_weight);
        TextView textViewCategory = (TextView) findViewById(R.id.textView_category);
        TextView textViewName = (TextView) findViewById(R.id.textView_user);
        assert measurement != null;
        textViewName.setText(String.format("%s: %s", getString(R.string.textView_user_text), name));
        textViewBmi.setText(String.format("%s: %f", getString(R.string.textView_bmi_text), measurement.getBMI()));
        textViewCategory.setText(String.format("%s: %s", getString(R.string.textView_category_text), measurement.getCategory()));
        textViewHeight.setText(String.format("%s: %f", getString(R.string.textView_height_text), measurement.getHeight()));
        textViewWeight.setText(String.format("%s: %f", getString(R.string.textView_weight_text), measurement.getWeight()));
    }
}