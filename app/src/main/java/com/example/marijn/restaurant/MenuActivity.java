package com.example.marijn.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemRequest.Callback {


    public ArrayList<MenuItem> menuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intentGet = getIntent();
        String clickedCategory = intentGet.getStringExtra("clickedCategory");

        MenuItemRequest request = new MenuItemRequest(this, clickedCategory);
        request.getMenuItem(this);

        ListView listView = findViewById(R.id.menuList);
        listView.setOnItemClickListener(new ItemClickListener());
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menu) {

        menuItem = menu;

        MenuItemAdapter menuAdapter = new MenuItemAdapter(this, R.layout.menu_row, menu);

        ListView menuList = findViewById(R.id.menuList);

        menuList.setAdapter(menuAdapter);
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            MenuItem clickedMenuItem = menuItem.get(position);

            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);

            Bundle bundle = new Bundle();

            bundle.putString("name", clickedMenuItem.getName());
            bundle.putString("description", clickedMenuItem.getDescription());
            bundle.putString("imageUrl", clickedMenuItem.getImageUrl());
            bundle.putString("price", clickedMenuItem.getPrice());
            bundle.putString("category", clickedMenuItem.getCategory());

            intent.putExtras(bundle);

            startActivity(intent);
        }
    }
}
