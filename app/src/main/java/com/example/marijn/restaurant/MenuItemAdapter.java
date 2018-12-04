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



public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList menu;

    public MenuItemAdapter(@NonNull Context context, int resource,  @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.menu = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_row, parent, false);
        }

        TextView Name = convertView.findViewById(R.id.name);
        TextView Price = convertView.findViewById(R.id.price);
        ImageView Image = convertView.findViewById(R.id.image);

        MenuItem menuItem = (MenuItem) menu.get(position);

        Name.setText(menuItem.getName());
        Price.setText("€" + menuItem.getPrice());

        // Turn url into an image view using Picasso
        // Source: https://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
        Picasso.get().load(menuItem.getImageUrl()).resize(100, 100).into(Image);

        return convertView;
    }
}



