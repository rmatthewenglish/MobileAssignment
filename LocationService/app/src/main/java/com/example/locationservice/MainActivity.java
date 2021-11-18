package com.example.locationservice;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

    public class MainActivity extends AppCompatActivity {
    double latitude ;
    double longitude;
    double latit;
    double longit;
    String address;
    EditText Latitude,Longitude,ID,Address,Update,Delete;
    DatabaseHandler helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Latitude = (EditText) findViewById(R.id.latitude);
        Longitude = (EditText) findViewById(R.id.longitude);
        EditText search = (EditText) findViewById(R.id.inputSearch);
        EditText result = (EditText) findViewById(R.id.resultSearch);
        final Button button = findViewById(R.id.button_id);
        DatabaseHandler dbHandler = new DatabaseHandler(this,null,null,1);
        button.setOnClickListener(v -> {
            latit = Double.parseDouble(Latitude.getText().toString());
            longit = Double.parseDouble(Longitude.getText().toString());

            if (search != null ) {
                result.setText((CharSequence) dbHandler.findCoords(search.toString()));
            }

            try {
                findAddress(latit,longit);
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

    }

    protected void findAddress(double latitude, double longitude) throws IOException {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this);

        DatabaseHandler dbHandler = new DatabaseHandler(this,null,null,1);
        addresses = geocoder.getFromLocation(latitude, longitude, 1);
        String address = addresses.get(0).getAddressLine(0);
        Toast.makeText(getApplicationContext(), address, Toast.LENGTH_LONG).show();

        Addresses location = new Addresses(address,Double.toString(latit), Double.toString(longit));

        dbHandler.addLocation(location);

        }
    }