package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Class creates a gps tracker which, mainly, should return the location of the device
 * 
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */
public class GPSTracker implements LocationListener {
    private Context context;

    /**
     * constructor
     *
     * @param context
     */
    public GPSTracker(Context context) {
        this.context = context;
    }

    /**
     * method returns the location of the device
     *
     * @return
     */
    public Location getLocation() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Permission is not granted", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGPSEnabled) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, this);
            Location l = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
            Toast.makeText(context, "Please enable GPS", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    /**
     * necessary method if LocationListener is implemented but this method is not used
     *
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {

    }

    /**
     * necessary method if LocationListener is implemented but this method is not used now
     *
     * @param provider
     * @param status
     * @param extras
     */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    /**
     * necessary method if LocationListener is implemented but this method is not used
     *
     * @param provider
     */
    @Override
    public void onProviderEnabled(String provider) {

    }

    /**
     * necessary method if LocationListener is implemented but this method is not used
     *
     * @param provider
     */
    @Override
    public void onProviderDisabled(String provider) {

    }
}
