package e.plass.acceuilwayfinding;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements LocationListener {
    private static final int PERMS_CALL_ID = 1234;
    private LocationManager lm;
    private MapFragment mapFragment;
    private GoogleMap googleMap;

    //public abstract void addOnBackStackChangedListener (FragmentManager.OnBackStackChangedListener listener);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);
    }

    @Override
    //@SuppressWarnings("MissingPermission")
    protected void onResume() {
        super.onResume();
        checkPermission();


    }

    private  void checkPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            },PERMS_CALL_ID );
            return;
        }
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }

        if(lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,10000,0,this);
        }

        if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,10000,0,this);
        }
        loadMap();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (lm != null){
            lm.removeUpdates(this);
        }
    }
    @SuppressWarnings("MissingPermission")
    private void loadMap(){
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapActivity.this.googleMap = googleMap;
                googleMap.moveCamera(CameraUpdateFactory.zoomBy(15));
                googleMap.setMyLocationEnabled(true);
                googleMap.addMarker(new MarkerOptions().position(new LatLng(78.369,123.45698)).title("gessiia group"));
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMS_CALL_ID){
            checkPermission();
        }
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Toast.makeText(this,"Latitude: "+ latitude+"Longitude: "+ longitude,Toast.LENGTH_LONG).show();

        if (googleMap != null){
            LatLng googleLocation = new LatLng(latitude,longitude);
            googleMap.moveCamera( CameraUpdateFactory.newLatLng(googleLocation));
        }
    }
}

