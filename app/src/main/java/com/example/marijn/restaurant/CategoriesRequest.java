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
public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback activity;

    // Notify the activity that instantiated the request through callback
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // Method will attempt to retrieve the categories from the API
    public void getCategories(Callback activity) {
        this.activity = activity;
        // Create a new request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // Create a JSON object request and add it to the queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override // Handle on API error response
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
        Log.d("gotCategoriesError", error.getMessage());
    }

    @Override // Handle on API response
    public void onResponse(JSONObject response) {

        // Instantiate array list
        ArrayList<String> categoriesArrayList = new ArrayList<>();

        try {

            JSONArray categories = response.getJSONArray("categories");

            // Loop over the JSON array and extract the strings in it
            for (int i = 0; i < categories.length(); i++) {
                categoriesArrayList.add(categories.getString(i));
            }
            // Pass the array list back to the activity that requested it
            activity.gotCategories(categoriesArrayList);

        } catch (JSONException e) {
            // If an error occurs, print the error
            e.printStackTrace();
        }
    }
}
