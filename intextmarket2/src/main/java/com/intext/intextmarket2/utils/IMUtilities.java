package com.intext.intextmarket2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.intext.intextmarket2.dialogs.IMarketDialogs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public static Retrofit setRetrofit(String url){
        return new Retrofit.Builder()
                .baseUrl(url +"/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static JSONObject createIMarketTextObject(String message, Double latitude, Double longitude) throws JSONException {

        String _message = message.replaceAll("\\d","");
        String[] items = _message.split("[\\.\\,\\/\\?\\@\\'\\-\\!\\#\\$\\%\\^\\&\\*\\(\\)\\{\\}\\:\\;\\`\\+\\~\\£\\!\\_\\  ]+");

        JSONObject location = new JSONObject();
        JSONObject client = new JSONObject();

        location.put("latitude",latitude);
        location.put("longitude",longitude);

        client.put("name", "SDK-ADMIN");
        client.put("phone", "7871231234");

        JSONArray words = new JSONArray();

        for (String item : items) {
            item.replaceAll("\\w","");
            if (item.length() > 2) {

                JSONObject word = new JSONObject();

                word.put("word", item.replaceAll("\\W","").toLowerCase());
                word.put("firstLetter", item.substring(0, 1).toLowerCase());
                word.put("wordSize",item.length());

                words.put(word);
            }
        }

        JSONObject finalOBJ = new JSONObject();

        finalOBJ.put("words",words);
        finalOBJ.put("location",location);
        finalOBJ.put("client", client);

        return finalOBJ;
    }

    public static String autoPing(){
        return UUID.randomUUID().toString();
    }
}
