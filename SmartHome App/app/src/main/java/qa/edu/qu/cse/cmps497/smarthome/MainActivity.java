package qa.edu.qu.cse.cmps497.smarthome;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RequestQueue mRequestQueue;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String OnUrl ="http://10.0.2.2:7070/LEDon";
        final String OffUrl ="http://10.0.2.2:7070/LEDoff";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView text = (TextView) findViewById(R.id.textView);

        final Button button = (Button) findViewById(R.id.Light);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, OnUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                text.setText("Response is: "+ response.substring(0,150));
                                Log.v("BUTTON","pressed button");
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        text.setText("Light is now on");
                    }
                });
// Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

        final Button buttonoff = (Button) findViewById(R.id.Lightoff);
        buttonoff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, OffUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                text.setText("Response is: "+ response.substring(0,150));
                                Log.v("BUTTON","pressed button");
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        text.setText("Light is now off");
                    }
                });
// Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
