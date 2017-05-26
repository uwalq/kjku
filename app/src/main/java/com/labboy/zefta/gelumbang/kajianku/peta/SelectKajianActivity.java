package com.labboy.zefta.gelumbang.kajianku.peta;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.labboy.zefta.gelumbang.kajianku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SelectKajianActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener
       {

    public static final String SERVICE_URL = "https://kajianku.herokuapp.com/masjid.json";
    private GoogleMap mMap;

           LatLng point1 = new LatLng(19.03160394, 72.8676239);



           @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_kajian);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);



           }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//        BitmapDescriptor image = BitmapDescriptorFactory.fromResource(R.id.android_pay);
//        GroundOverlayOptions groundOverlay = new GroundOverlayOptions()
//                .image(image).position(point1, 500f).transparency(0.5f);
//        mMap.addGroundOverlay(groundOverlay);

        LatLng NEWARK = new LatLng(40.714086, -74.228697);

        GroundOverlayOptions newarkMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.ic_plusone_medium_off_client))
                .position(NEWARK, 8600f, 6500f);
        mMap.addGroundOverlay(newarkMap);

        setUpMap();

    }


    private void setUpMap() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    retrieveMaps();
                } catch (IOException e) {
                    Log.e("setupmap", "Cannot retrive Maps", e);
                    return;
                }
            }
        }).start();
    }


    private void retrieveMaps() throws IOException {
        HttpURLConnection conn = null;
        final StringBuilder json = new StringBuilder();
        try {
            // Connect to the web service
            URL url = new URL(SERVICE_URL);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Read the JSON data into the StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                json.append(buff, 0, read);
            }
        } catch (IOException e) {
            Log.e("Retrieve Maps", "Error connecting to service", e);
            throw new IOException("Error connecting to service", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        // Create markers for the city data.
        // Must run this on the UI thread since it's a UI operation.
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    createMarkersFromJson(json.toString());
                } catch (JSONException e) {
                    Log.e("Retrieve Maps", "Error processing JSON", e);
                }
            }
        });
    }

    private void createMarkersFromJson(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            // Create a marker for each city in the JSON data.
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title(jsonObj.getString("nama"))
                    .snippet(jsonObj.getString("alamat"))
                    .position(new LatLng(
                            jsonObj.getDouble("latitude"),
                            jsonObj.getDouble("longitude")
                    ))
            );

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    jsonObj.getDouble("latitude"),
                    jsonObj.getDouble("longitude")
            ), 8));
        }
    }








   @Override
   public void onLocationChanged(Location location) {

   }
       }
