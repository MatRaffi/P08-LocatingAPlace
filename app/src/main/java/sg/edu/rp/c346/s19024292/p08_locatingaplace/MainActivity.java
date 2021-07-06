package sg.edu.rp.c346.s19024292.p08_locatingaplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_center = new LatLng(1.3668128388589884, 103.79994907926454);

                //Set the position
                LatLng poi_333 = new LatLng(1.350596266774528, 103.8704707426851);

                //Place a marker
                Marker north = map.addMarker(new MarkerOptions()
                        .position(poi_333)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654\n Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.star)));
                north.setTag(0);

                //Set the position
                LatLng poi_3a = new LatLng(1.2978116642289266, 103.84750705542783);

                //Place a marker
                Marker central = map.addMarker(new MarkerOptions()
                        .position(poi_3a)
                        .title("HQ - Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542\n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                central.setTag(1);
                //Set the position
                LatLng poi_555 = new LatLng(1.3488072179755954, 103.93578604008547);

                //Place a marker
                Marker east = map.addMarker(new MarkerOptions()
                        .position(poi_555)
                        .title("HQ - East")
                        .snippet("Block 555, Tampines Ave 3, 287788\n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
               east.setTag(2);

                //UI settings
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_center,10));
                UiSettings ui = map.getUiSettings();
                map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                //Show current location on the map
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS Access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
                }
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getApplicationContext(),marker.getTitle(),Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }

        });

        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);

        LatLng poi_333 = new LatLng(1.350596266774528, 103.8704707426851);
        LatLng poi_3a = new LatLng(1.2978116642289266, 103.84750705542783);
        LatLng poi_555 = new LatLng(1.3488072179755954, 103.93578604008547);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_333,15));
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_3a,15));
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_555,15));
                }
            }
        });

    }
}