package com.example.marijn.restaurant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        ListView listView = findViewById(R.id.categoriesList);
        listView.setOnItemClickListener(new ItemClickListener());

    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        Toast.makeText(this, categories.get(0), Toast.LENGTH_LONG).show();

        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);

        ListView categoriesList = findViewById(R.id.categoriesList);

        categoriesList.setAdapter(categoriesAdapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String clickedCategory = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);

            intent.putExtra("clickedCategory", clickedCategory);

            startActivity(intent);
        }
    }
}
