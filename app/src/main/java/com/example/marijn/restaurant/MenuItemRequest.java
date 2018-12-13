package com.example.marijn.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class MenuItemRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    // Various variables
    private Context context;
    private Callback activity;
    private String menuCategory;

    // Notify the activity that instantiated the request through callback
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuList);
        void gotMenuError(String message);
    }

    public MenuItemRequest(Context context, String menuCategory) {
        this.context = context;
        this.menuCategory = menuCategory;
    }

    public void getMenuItem (Callback activity) {
        this.activity = activity;
        // Create a new request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // Create a JSON object request and add it to the queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonObjectRequest);

    }

    @Override // Handle on API error response
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
        Log.d("gotMenuError", error.getMessage());

    }

    @Override // Handle on API response
    public void onResponse(JSONObject response) {

        // Instantiate array list
        ArrayList<MenuItem> menu = new ArrayList<>();

        try {

            JSONArray menuArray = response.getJSONArray("items");

            // Loop over the JSON array and extract the strings in it
            for (int i = 0; i < menuArray.length(); i++) {
                JSONObject menuObject = menuArray.getJSONObject(i);
                String category = menuObject.getString("category");

            // Only get the menu item information of the clicked category
            if (category.equals(menuCategory)) {

                String name = menuObject.getString("name");
                String description = menuObject.getString("description");
                String imageUrl = menuObject.getString("image_url");
                String price = menuObject.getString("price");

                // Add the information to the menu array list
                menu.add(new MenuItem(name, description, imageUrl, price, category));

            }
            // Pass the array list back to the activity that requested it
            activity.gotMenu(menu);
        }

        } catch(JSONException e){
            // If an error occurs, print the error
            e.printStackTrace();
            }
        }
    }
