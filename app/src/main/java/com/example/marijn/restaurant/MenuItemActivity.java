package com.example.marijn.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // Get various ID's
        TextView name = findViewById(R.id.nameView);
        ImageView image = findViewById(R.id.imageView);
        TextView category = findViewById(R.id.categoryView);
        TextView description = findViewById(R.id.descriptionView);
        TextView price = findViewById(R.id.priceView);

        // Retrieve clicked menu item Bundle from the previous activity
        Intent intent = getIntent();

        // Turn url into an image view using Picasso
        // Source: https://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
        Picasso.get().load(intent.getStringExtra("imageUrl")).into(image);

        // Set the name, category, description and price of the dish
        name.setText(intent.getStringExtra("name"));
        category.setText(intent.getStringExtra("category"));
        description.setText(intent.getStringExtra("description"));
        price.setText("€ " + intent.getStringExtra("price"));
    }
}
