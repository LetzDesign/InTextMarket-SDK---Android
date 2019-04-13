package com.intext.intextmarket2.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.intext.intextmarket2.dialogs.IMarketDialogs;
import com.intext.intextmarket2.permissions.IMarketPermission;

/**
 * Created by Ing. Letzer Cartagena Negrón
 * InTextChat @2019
 * @author INTEXT SOFTWARE LLC
 *
 * Copyright (C) 2019 INTEXT SOFTWARE LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class IMLocation extends Service implements LocationListener {

    private Context context;
    private Activity activity;
    private LocationManager locationManager;
    private String provider;
    private IMarketPermission iMarketPermission;
    private boolean isNetworkOn;
    private Double latitude, longitude;

    public IMLocation(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        provider = LocationManager.GPS_PROVIDER;
    }

    public void init() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        isNetworkOn = locationManager.isProviderEnabled(provider);

        iMarketPermission = new IMarketPermission(activity, context);
        iMarketPermission.checkLocationPermission();

        getLocation();
    }


    private void getLocation() {
        if (isNetworkOn) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                iMarketPermission.checkLocationPermission();
            }else{
                locationManager.requestLocationUpdates(provider, 30, 0, this);
                Location location = locationManager.getLastKnownLocation(provider);

                if(location != null){
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }else{
                    IMarketDialogs.turnOnLocationAlertIntent(context);
                }
            }
        }else{
            IMarketDialogs.turnOnLocationAlertIntent(context);
        }
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        this.provider = provider;
        init();
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
