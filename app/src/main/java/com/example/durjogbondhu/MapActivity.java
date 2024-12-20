package com.example.durjogbondhu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    private final int PERMISSION_ID= 42;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mappage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


private void getLastLocation(){
    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_ID);
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return;
    }
    Task<Location> task = fusedLocationProviderClient.getLastLocation();
    task.addOnSuccessListener(new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            if(location!=null){
                currentLocation=location;
                mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync((OnMapReadyCallback) MapActivity.this);
            }
        }
    });
}

public void onMapReady(@NonNull GoogleMap googleMap) {
    mMap = googleMap;
    mMap.getUiSettings().setZoomControlsEnabled(true);
    mMap.getUiSettings().setCompassEnabled(true);
    LatLng bangladesh = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
//        mMap.addMarker(new MarkerOptions().position(bangladesh).title("Bangladesh"));
    MarkerOptions markerOptions = new MarkerOptions().position(bangladesh).title("Bangladesh");
    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
    mMap.addMarker(markerOptions);
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bangladesh,10));

}

@Override
public void onPointerCaptureChanged(boolean hasCapture) {
    super.onPointerCaptureChanged(hasCapture);
}

public void OnRequestPermissionsResult(int RequestCode,@NonNull String[] permissions,@NonNull int[] grantResults){
    super.onRequestPermissionsResult(RequestCode,permissions,grantResults);
    if(RequestCode == PERMISSION_ID){
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            getLastLocation();
        }
        else {
            Toast.makeText(this,"Enter Correct Location",Toast.LENGTH_LONG).show();
        }
    }

}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.map_list,menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if(item.getItemId()==R.id.none){
        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
    }
    if(item.getItemId()==R.id.normal){
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
    if(item.getItemId()==R.id.hybrid){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
    if(item.getItemId()==R.id.terrain){
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }
    if(item.getItemId()==R.id.satellite){
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
    return super.onOptionsItemSelected(item);
}

}