package com.intext.intextmarket2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.intext.intextmarket2.dialogs.IMarketDialogs;

public class IMUtilities {

    public static void rootViewValidation(Context c, int root) {
        if(root == 0){
            IMarketDialogs.genericDialog(
                    c,
                    "Root Container Error",
                    "The fragment container not exists...",
                    IMarketDialogs.IMDialogType.ERROR
            );
        }
    }

    public static boolean isNetworkConnected(Context c){
        ConnectivityManager connectivityManager = (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
