package com.example.demo2.config;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo2.EntryActivity;
import com.example.demo2.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button changeUnits = findViewById(R.id.button_change_units);
        changeUnits.setOnClickListener(v -> {
            Toast.makeText(SettingsActivity.this, "Not yet implemented",
                    Toast.LENGTH_LONG).show();
        });

        Button manageUsers = findViewById(R.id.button_manage_users);
        manageUsers.setOnClickListener(v -> {
            startActivity(new Intent(this, UserActivity.class));
        });
    }
}