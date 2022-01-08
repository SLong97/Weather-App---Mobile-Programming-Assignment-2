package com.example.weather;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText enterLocation, enterWOEID;
    Button saveButton;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        enterLocation = findViewById(R.id.enterLocation);
        enterWOEID = findViewById(R.id.enterWOEID);
        saveButton = findViewById(R.id.saveButton);
        spinner = findViewById(R.id.spinner);

        int max = 5;
        int min = 1;


        //Only used when making a random selection if a user has failed to make one themselves, other wise ignored.
        String[] Thelocations = {"London", "Paris", "Dublin", "Milan", "Rome", "Sofia"};
        String[]  Theid= {"44418", "615702", "560743", "718345", "721943", "839722"};

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database myDB = new Database(AddActivity.this);
                if(enterLocation.getText().toString().isEmpty() && enterWOEID.getText().toString().isEmpty()){
                    String spinnerLocation = spinner.getSelectedItem().toString();

                    if(spinnerLocation.equals("London")){
                        String theID = "44418";
                        myDB.addLocation(spinnerLocation, theID);
                    }
                    else if (spinnerLocation.equals("Paris")){
                        String theID = "615702";
                        myDB.addLocation(spinnerLocation, theID);
                    }
                    else if (spinnerLocation.equals("Dublin")){
                        String theID = "560743";
                        myDB.addLocation(spinnerLocation, theID);
                    }
                    else if (spinnerLocation.equals("Milan")){
                        String theID = "718345";
                        myDB.addLocation(spinnerLocation, theID);
                    }
                    else if (spinnerLocation.equals("Rome")){
                        String theID = "721943";
                        myDB.addLocation(spinnerLocation, theID);
                    }
                    else if (spinnerLocation.equals("Sofia")){
                        String theID = "839722";
                        myDB.addLocation(spinnerLocation, theID);
                    }
                    else if (spinnerLocation.equals("Choose a location...")){
                        String loc = Thelocations[randomNum];
                        String id = Theid[randomNum];
                        myDB.addLocation(loc, id);
                    }
                }
                else {
                    myDB.addLocation(enterLocation.getText().toString(), enterWOEID.getText().toString());
                }
            }
        });



        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.location_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}