package com.example.componentstry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://10.0.2.2:5000";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                url,
                (JSONObject response) ->
                        Log.e("res", "res: " + response.toString())
                ,
                (VolleyError error) ->
                        Log.e("res", "error: " + error.toString())
        );


        // Add the request to the RequestQueue.
        RequestHandler.getInstance(getApplicationContext());
        RequestHandler.getInstance().add(jsonObjectRequest);
    }
}