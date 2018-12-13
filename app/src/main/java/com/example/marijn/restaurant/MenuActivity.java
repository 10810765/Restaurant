package com.example.marijn.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class MenuActivity extends AppCompatActivity implements MenuItemRequest.Callback {


    public ArrayList<MenuItem> menuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Retrieve the clicked category from the previous activity
        Intent intentGet = getIntent();
        String clickedCategory = intentGet.getStringExtra("clickedCategory");

        // Make a request for the menu items of the clicked category
        MenuItemRequest request = new MenuItemRequest(this, clickedCategory);
        request.getMenuItem(this);

        // Instantiate an on list item click listener
        ListView listView = findViewById(R.id.menuList);
        listView.setOnItemClickListener(new ItemClickListener());
    }

    @Override // Method that handles a successful call to the API
    public void gotMenu(ArrayList<MenuItem> menu) {
        menuItem = menu;

        // Instantiate the adapter
        MenuItemAdapter menuAdapter = new MenuItemAdapter(this, R.layout.menu_row, menu);

        // Get list view ID and attach the adapter to it
        ListView menuList = findViewById(R.id.menuList);
        menuList.setAdapter(menuAdapter);
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotMenuError(String message) {
        // Toast the error message to the screen
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Create an on menu item clicked listener
    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Get the MenuItem object of the clicked item in the list view
            MenuItem clickedMenuItem = menuItem.get(position);

            // Create a new bundle
            Bundle bundle = new Bundle();

            // Put menu item information into the bundle
            bundle.putString("name", clickedMenuItem.getName());
            bundle.putString("description", clickedMenuItem.getDescription());
            bundle.putString("imageUrl", clickedMenuItem.getImageUrl());
            bundle.putString("price", clickedMenuItem.getPrice());
            bundle.putString("category", clickedMenuItem.getCategory());

            // Pass the Bundle to the next activity
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
