package com.intext.intextmarket2.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Ing. Letzer Cartagena Negron
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

public class IMarketPermission {

    private static final int PERMISSION_CAMERA = 1;
    private static final int PERMISSION_FINE_LOCATION = 2;
    private static final int PERMISSION_FINE_CLOCATION = 6;
    private static final int PERMISSION_REC_AUDIO = 3;
    private static final int PERMISSION_TO_ALL = 444;
    private static final int PERMISSION_TO_SMS = 4;
    private static final int PERMISSION_TO_CONTACTS = 5;

    private Activity activity;
    private Context context;
    private String permission;
    private String[] permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS
    };

    public IMarketPermission(Activity activity, Context context){
        this.activity = activity;
        this.context = context;
    }

    public void permission(String permission){
        this.permission = permission;
    }

    public void permissions(String[] permissions){
        this.permissions = null;
        this.permissions = permissions;
    }

    public void checkPermission(){
        if(ContextCompat.checkSelfPermission(context, permission)
            != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(activity,
                    new String[]{permission},
                    getGlobalPermissionID()
            );
        }
    }

    public void checkPermissions(){
        if(!hasPermissions()){
            ActivityCompat.requestPermissions(activity,
                    permissions,
                    PERMISSION_TO_ALL
            );
        }
    }

    private boolean hasPermissions() {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void checkLocationPermission(){
        if(!locationHasPermission()){
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    PERMISSION_FINE_LOCATION
            );
        }
    }

    public boolean locationHasPermission(){
        if (context != null) {
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    private int getGlobalPermissionID() {

        int returnPermission;

        switch (permission){
            case Manifest.permission.ACCESS_FINE_LOCATION:
                returnPermission = PERMISSION_FINE_LOCATION;
                break;
            case Manifest.permission.ACCESS_COARSE_LOCATION:
                returnPermission = PERMISSION_FINE_CLOCATION;
                break;
            case Manifest.permission.RECORD_AUDIO:
                returnPermission = PERMISSION_REC_AUDIO;
                break;
            case Manifest.permission.READ_SMS:
                returnPermission = PERMISSION_TO_SMS;
                break;
            case Manifest.permission.READ_CONTACTS:
                returnPermission = PERMISSION_TO_CONTACTS;
                break;
            default:
                returnPermission = PERMISSION_CAMERA;
                break;
        }

        return returnPermission;
    }
}
