package com.example.emerson.buttonrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button butt = (Button) findViewById(R.id.butt);
        final TextView text = (TextView) findViewById(R.id.text);
        final String TAG = "HOpwfully";

        butt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://api.wunderground.com/api/675c9ef92882d0cd/conditions/q/FL/Miami.json";

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                double temp = 0;
                                try {
                                    JSONObject respond = new JSONObject(response);
                                    respond = respond.getJSONObject("current_observation");
                                    //Object object = respond.get("temp_f");
                                    //temp = (double) object;
                                    temp = respond.getDouble("temp_f");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                text.setText(""+temp);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        text.setText("That didn't work!");
                    }
                });
// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });
    }
}
