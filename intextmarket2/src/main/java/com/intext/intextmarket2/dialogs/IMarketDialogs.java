package com.intext.intextmarket2.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

import com.intext.intextmarket2.R;
import com.intext.intextmarket2.permissions.IMarketPermission;

import java.util.ArrayList;

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

public class IMarketDialogs {

    public static void genericDialog(Context c, String title, String message, IMDialogType type){

        AlertDialog alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setIcon( getAlertIcon(type) );
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    public static void locationNotHavePermission(Context c, final IMarketPermission iMarketPermission){
        AlertDialog alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle("Search Markets, Anything and More...");
        alertDialog.setMessage("To be able to use this functionality you must grant the location permission.\n\n" +
                "Thank you!");
        alertDialog.setIcon( getAlertIcon(IMDialogType.HELP) );
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                iMarketPermission.checkLocationPermission();
            }
        });

        alertDialog.show();
    }

    public static void turnOnLocationAlertIntent(final Context c){
        AlertDialog alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle("IMarket Location Unavailable");
        alertDialog.setMessage("Please verify your network status. " +
                "Please try again!");
        alertDialog.setIcon(R.drawable.ic_round_location_off_24px);

        alertDialog.setButton(
                AlertDialog.BUTTON_POSITIVE,
                "Turn On Location",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        c.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                }
        );

        alertDialog.setButton(
                AlertDialog.BUTTON_NEGATIVE,
                "CLOSE",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                    }
                }

        );

        alertDialog.show();
    }

    private static int getAlertIcon(IMDialogType type){

        int icon = R.drawable.ic_round_help_24px;

        switch (type){
            case ERROR:
                icon = R.drawable.ic_round_error_24px;
                break;
            case WARNING:
                icon = R.drawable.ic_round_warning_24px;
                break;
            case SUCCESS:
                icon = R.drawable.ic_round_check_circle_24px;
                break;
            case HELP:
                break;
        }

        return icon;
    }

    public enum IMDialogType{
        ERROR,
        WARNING,
        SUCCESS,
        HELP,
    }
}
