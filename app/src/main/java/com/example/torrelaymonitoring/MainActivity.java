package com.example.torrelaymonitoring;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;


public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private TextView relayName;
    private TextView responseTime;
    private TextView isResponsive;
    private TextView isRunning;
    private TextView isConnected;
    private TextView isGuard;
    private ImageView statusImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI
        statusText=(TextView)findViewById(R.id.status_text);
        relayName=(TextView)findViewById(R.id.name);
        responseTime=(TextView)findViewById(R.id.time);
        isResponsive=(TextView)findViewById(R.id.responsive);
        isRunning=(TextView)findViewById(R.id.running);
        isConnected=(TextView)findViewById(R.id.connections);
        isGuard=(TextView)findViewById(R.id.guard_status);

        statusImage =(ImageView)findViewById(R.id.status_image);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // For debugging token
        String newToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TAG", "New Token:" + newToken );
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Get request:
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://torrelaymonitoring.appspot.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String newResponse = response.replaceAll("[^A-Za-z0-9 ]", "");

                        String[] tokens = newResponse.split("( )");

                        relayName.setText(tokens[7]);
                        if (tokens[15].equals("1")){
                            responseTime.setText(tokens[15] + " minute ago");
                        } else
                            responseTime.setText(tokens[15] + " minutes ago");
                        isResponsive.setText(tokens[11]);
                        isRunning.setText(tokens[9]);
                        isConnected.setText(tokens[1]);
                        isGuard.setText(tokens[13]);

                        if (tokens[11].equals("false") || tokens[9].equals("false")) {
                            statusImage.setImageResource(R.drawable.unhappy_face);
                        } else if (Integer.parseInt(tokens[1]) < 3) {
                            statusImage.setImageResource(R.drawable.neutral_face);
                        } else
                            statusImage.setImageResource(R.drawable.happy_face);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                statusText.setText("Error fetching status!");
            }


        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void refreshView(View view) {
        onStart();
    }
}

