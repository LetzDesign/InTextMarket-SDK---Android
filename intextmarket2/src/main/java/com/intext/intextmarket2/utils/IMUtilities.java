package com.intext.intextmarket2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.intext.intextmarket2.dialogs.IMarketDialogs;

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
