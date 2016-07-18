package com.example.ashok.trackify;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class track_activity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,LocationListener {


    public GoogleMap map;
    private GoogleApiClient mGoogleApiClient ;
    double latilast,longitlast;
    double updLat, updLng;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker locmarker;
    LatLng locll;
    boolean mRequestingLocationUpdates = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trackers_page);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }else{
            startLocationUpdates();
        }

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try{
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {

                latilast = mLastLocation.getLatitude();
                longitlast = mLastLocation.getLongitude();
                locll = new LatLng(latilast,longitlast);

                MarkerOptions locoptions = new MarkerOptions()
                        .title("You are here")
                        .position(locll);
                if (locmarker != null){
                    locmarker.remove();
                }
                locmarker = map.addMarker(locoptions);
            }
        }catch(SecurityException e){
        }
        if(mRequestingLocationUpdates){
            createLocationRequest();
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected void startLocationUpdates() {


        createLocationRequest();

        try{
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);


        }catch (SecurityException e){
            //code to ask for permission
        }


    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
}
