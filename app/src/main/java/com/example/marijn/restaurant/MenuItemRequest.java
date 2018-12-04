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

public class MenuItemRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback activity;
    private String menuCategory;

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
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
        Log.d("gotMenuError", error.getMessage());

    }

    @Override
    public void onResponse(JSONObject response) {

        ArrayList<MenuItem> menu = new ArrayList<>();

        try {

            JSONArray menuArray = response.getJSONArray("items");

            for (int i = 0; i < menuArray.length(); i++) {

                JSONObject menuObject = menuArray.getJSONObject(i);

                String category = menuObject.getString("category");

            if (category.equals(menuCategory)) {

                String name = menuObject.getString("name");
                String description = menuObject.getString("description");
                String imageUrl = menuObject.getString("image_url");
                String price = menuObject.getString("price");

                menu.add(new MenuItem(name, description, imageUrl, price, category));
                activity.gotMenu(menu);
            }
        }

        } catch(JSONException e){
            e.printStackTrace();
            }
        }
    }
