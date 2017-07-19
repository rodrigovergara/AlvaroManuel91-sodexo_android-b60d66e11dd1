package sodexo.pe.com.sodexo.data.datasource.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;

/**
 * Created by edwin.velasquez on 22/07/2016.
 */
public class GpsService extends IntentService implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    public static final String LOCATION_ACTION = "LOCATION ACTION";
    public static final String LOCATION = "LOCATION";
    private static GoogleApiClient googleApiClient;
    private static Location mLocation;
    private LocationRequest locationRequest;

    public GpsService() {
        super(GpsService.class.getName());
    }

    public static Location getDisplayLocation() {
        try {
            return mLocation == null ? LocationServices.FusedLocationApi.getLastLocation(googleApiClient) : mLocation;
        } catch (SecurityException sex) {
            sex.printStackTrace();
            return null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    private void locationServiceUpdate() {
        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (googleApiClient == null) {
            buildGoogleApiClient();
        }
        if (!googleApiClient.isConnected()) {
            googleApiClient.connect();
        }

        if (locationRequest != null)
            locationServiceUpdate();

        return START_STICKY;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000); // 5 seg
        locationRequest.setFastestInterval(10000); // 5 seg
        locationServiceUpdate();
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
        if (locationRequest != null)
            locationServiceUpdate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public void onLocationChanged(Location location) {
        SodexoApplication.clientLocation = new LatLng(location.getLatitude(), location.getLongitude());
        Log.d("GPSSERVICE", "Calculate location");
        sendBroadcast(new Intent().putExtra("type", 1).setAction(getString(R.string.intent_filter_broadcast)));
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (googleApiClient == null) {
            buildGoogleApiClient();
        }
        if (!googleApiClient.isConnected()) {
            googleApiClient.connect();
        }
        if (locationRequest != null)
            locationServiceUpdate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
}
