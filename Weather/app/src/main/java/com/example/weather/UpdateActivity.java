package com.example.weather;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class UpdateActivity extends AppCompatActivity {

    private WeatherData newWeatherData;
    String url = "https://www.metaweather.com/api/location/";
    String id;
    String location;
    TextView updateTitle;
    Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateTitle = (TextView)findViewById(R.id.updateTitle);
        delete = (Button)findViewById(R.id.delete);

        id = getIntent().getStringExtra("id");
        location = getIntent().getStringExtra("location");

        String finalURL = url + id + "/";
        String finalLocation = location + " Weekly Forecast";

        ActionBar ab = getSupportActionBar();
        ab.setTitle(location);

        updateTitle.setText(location + " Weekly Forecast");
        standardJsonObject(finalURL);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();

            }
        });
    }

    public void standardJsonObject(String url) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray consolidated_weather_list = response.getJSONArray("consolidated_weather");
                    Gson gson1 = new Gson();
                    newWeatherData = (WeatherData) gson1.fromJson(String.valueOf((response)), WeatherData.class);

                    RecyclerView recyclerView = findViewById(R.id.RV);
                    MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(newWeatherData.getWeathers());
                    recyclerView.setAdapter(myRecyclerViewAdapter);
                    LinearLayoutManager linearLayoutManager;
                    linearLayoutManager= new LinearLayoutManager(UpdateActivity.this, RecyclerView.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateActivity.this, "Volley Error ", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue rQueue1 = Volley.newRequestQueue(UpdateActivity.this);
        rQueue1.add(request);
    }// end of standardJsonObject(String url)


    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + location + "?");
        builder.setMessage("Are you sure want to delete " + location + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){

                Database myDB  = new Database(UpdateActivity.this);
                myDB.deleteOneEntry(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){

            }
        });
        builder.create().show();
    }


}