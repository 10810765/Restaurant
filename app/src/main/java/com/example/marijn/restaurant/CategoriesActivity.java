package com.example.marijn.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Make a request for the menu categories
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        // Instantiate an on list item click listener
        ListView listView = findViewById(R.id.categoriesList);
        listView.setOnItemClickListener(new ItemClickListener());

    }

    @Override // Method that handles a successful call to the API
    public void gotCategories(ArrayList<String> categories) {

        // Instantiate the adapter
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);

        // Get list view ID and attach the adapter to it
        ListView categoriesList = findViewById(R.id.categoriesList);
        categoriesList.setAdapter(categoriesAdapter);
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotCategoriesError(String message) {
        // Toast the error message to the screen
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Create an on category clicked listener
    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Get the string of the clicked item in the list view
            String clickedCategory = (String) parent.getItemAtPosition(position);

            // Pass the clicked category to the next activity
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("clickedCategory", clickedCategory);
            startActivity(intent);
        }
    }
}
