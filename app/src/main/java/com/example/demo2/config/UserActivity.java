package com.example.demo2.config;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.demo2.R;
import com.example.demo2.helpers.DatabaseConnection;

import java.util.List;

public class UserActivity extends AppCompatActivity {

    private DatabaseConnection databaseConnection;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private List<String> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        databaseConnection = new DatabaseConnection(this);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        editor = prefs.edit();
        spinner = findViewById(R.id.spinner);

        databaseConnection.open();
        entries = databaseConnection.selectUsers();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, entries);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(prefs.getString("name", "")));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String entry = entries.get(position);
                saveNameToPrefs(entry);
                Log.i("UserActivity", "Selected User: " + entry);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button newUser = findViewById(R.id.button_new_user);
        newUser.setOnClickListener(view -> showNewUserDialog());
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void saveNameToPrefs(String name) {
        editor.putString("name", name);
        editor.apply();
    }

    private void showNewUserDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.prompt_dialog, null);

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("Confirm", (dialog, id) -> {
                    EditText editText = dialogView.findViewById(R.id.text_new_user);
                    String text = editText.getText().toString();
                    databaseConnection.insertUser(text);
                    entries = databaseConnection.selectUsers();
                    adapter.clear();
                    adapter.addAll(entries);
                    saveNameToPrefs(text);
                    spinner.setSelection(adapter.getPosition(text));
                    showToast("Created new User: " + text);
                    Log.i("UserActivity", "Name: " + text);
                })
                .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel())
                .create()
                .show();
    }
}
