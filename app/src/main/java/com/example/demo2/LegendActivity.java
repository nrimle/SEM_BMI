package com.example.demo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo2.config.SettingsActivity;

import java.io.Serializable;

public class LegendActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_general, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legend);

        Measurement measurement = new Measurement();

        ListView listView = findViewById(R.id.list_view_app);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, measurement.getCategories());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Object listItem = listView.getItemAtPosition(position);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("category", (Serializable) listItem);
            startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.menu_general_item_home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (id == R.id.menu_general_item_rating) {
            startActivity(new Intent(this, LegendActivity.class));
            return true;
        } else if (id == R.id.menu_general_item_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }
}
