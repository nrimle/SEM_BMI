package com.example.demo2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        String category = (String) i.getSerializableExtra("category");

        TextView textViewDetail = (TextView) findViewById(R.id.textView_detail);
        textViewDetail.setText(String.format(Locale.GERMAN, "Details: %s", category));
    }
}
