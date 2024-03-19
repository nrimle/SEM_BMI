package com.example.demo2.config;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.demo2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<String> entries = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                entries);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String entry = entries.get(position);
                Toast.makeText(UserActivity.this, "Selected User: " + entry,
                        Toast.LENGTH_LONG).show();
                Log.i("UserActivity", "Selected User: " + entry);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button newUser = (Button) findViewById(R.id.button_new_user);
        newUser.setOnClickListener(view -> {

            View dialogView = getLayoutInflater().inflate(R.layout.prompt_dialog, null);

            new AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setPositiveButton("Confirm", (dialog, id) -> {
                        EditText editText = dialogView.findViewById(R.id.text_new_user);
                        String text = editText.getText().toString();
                        adapter.add(text);
                        Toast.makeText(UserActivity.this, "Name: " + text,
                                Toast.LENGTH_LONG).show();
                        Log.i("UserActivity", "Name: " + text);
                    })
                    .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel())
                    .create()
                    .show();
        });
    }
}
