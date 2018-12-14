package com.example.marijn.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList menu;

    public MenuItemAdapter(@NonNull Context context, int resource,  @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.menu = objects;
    }

    @NonNull
    @Override // Method that will be called every time a new list item (menu item) is to be displayed
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        // If the convert view is null, inflate a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_row, parent, false);
        }

        // Get the ID's of the name and price (TextView) and the picture (ImageView)
        TextView Name = convertView.findViewById(R.id.name);
        TextView Price = convertView.findViewById(R.id.price);
        ImageView picture = convertView.findViewById(R.id.image);

        // Get the index of the menu item that we want to display
        MenuItem menuItem = (MenuItem) menu.get(position);

        // Set the name and price of the dish
        Name.setText(menuItem.getName());
        Price.setText("€ " + menuItem.getPrice());

        // Load image from the internet into an image view using Picasso
        Picasso.get().load(menuItem.getImageUrl()).resize(100, 100).into(picture);

        return convertView;
    }
}



