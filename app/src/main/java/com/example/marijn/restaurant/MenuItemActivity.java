package com.example.marijn.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();

        TextView name = findViewById(R.id.nameView);
        ImageView image = findViewById(R.id.imageView);
        TextView category = findViewById(R.id.categoryView);
        TextView description = findViewById(R.id.descriptionView);
        TextView price = findViewById(R.id.priceView);

        // Turn url into an image view using Picasso
        // Source: https://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
        Picasso.get().load(intent.getStringExtra("imageUrl")).into(image);

        name.setText(intent.getStringExtra("name"));
        category.setText(intent.getStringExtra("category"));
        description.setText(intent.getStringExtra("description"));
        price.setText("€" + intent.getStringExtra("price"));
    }
}
