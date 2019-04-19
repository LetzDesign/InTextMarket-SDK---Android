package com.intext.intextmarket2.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intext.intextmarket2.api.pojo.IMBusinessResponse;
import com.intext.intextmarket2.dialogs.IMarketDialogs;
import com.intext.intextmarket2.permissions.IMarketPermission;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

@SuppressLint("SimpleDateFormat")
public class IMUtilities {

    private static final long DIFF_TIME_CONSTANT = (1000 * 60 * 60 * 24);
    private static final DateFormat TOKEN_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void rootViewValidation(Context c, int root) {
        if (root == 0) {
            IMarketDialogs.genericDialog(
                    c,
                    "Root Container Error",
                    "The fragment container not exists...",
                    IMarketDialogs.IMDialogType.ERROR
            );
        }
    }

    public static boolean isNetworkConnected(Context c) {
        ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static Retrofit setRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url + "/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static String convertPojoToJson(Object pojo){
        Gson stringConverter = new GsonBuilder().create();
        return stringConverter.toJson(pojo);
    }

    public static IMBusinessResponse convertJsonToIMBusinessResponse(String json){
        Gson pojoConverter = new GsonBuilder().create();
        return pojoConverter.fromJson(json, IMBusinessResponse.class);
    }

    public static JSONObject createIMarketTextObject(
            Activity activity, Context context,
            String message, Double latitude, Double longitude) throws JSONException {

        String _message = message.replaceAll("\\d", "");
        String[] items = _message.split("[\\.\\,\\/\\?\\@\\'\\-\\!\\#\\$\\%\\^\\&\\*\\(\\)\\{\\}\\:\\;\\`\\+\\~\\£\\!\\_\\  ]+");

        JSONObject location = new JSONObject();
        JSONObject client = new JSONObject();

        location.put("latitude", latitude);
        location.put("longitude", longitude);

        String phone = IMUtilities.getPhoneNumber(activity, context);
        String name = "Letzer Cartagena";//TODO Change for get current user

        client.put("name", name);
        client.put("phone", phone);

        JSONArray words = new JSONArray();

        for (String item : items) {
            item.replaceAll("\\w", "");
            if (item.length() > 2) {

                JSONObject word = new JSONObject();

                word.put("word", item.replaceAll("\\W", "").toLowerCase());
                word.put("firstLetter", item.substring(0, 1).toLowerCase());
                word.put("wordSize", item.length());

                words.put(word);
            }
        }

        JSONObject finalOBJ = new JSONObject();

        finalOBJ.put("words", words);
        finalOBJ.put("location", location);
        finalOBJ.put("client", client);

        return finalOBJ;
    }

    public static String autoPing() {
        return UUID.randomUUID().toString();
    }

    public static String iWebViewUrlFormatter(String url, String ss, String sc){
        return url + "?ss=" + ss + "&sc=" + sc;
    }

    public static boolean isTokenNeedRefresh(String tokenInsertedAt){
        boolean refresh = false;
        try {
            Date tokenDate = TOKEN_DATE_FORMAT.parse(tokenInsertedAt);

            String strToday = new SimpleDateFormat("yyyy-MM-dd h:mm a")
                    .format(Calendar.getInstance().getTime());

            Date today = TOKEN_DATE_FORMAT.parse(strToday);

            long diff = (today.getTime() - tokenDate.getTime()) / DIFF_TIME_CONSTANT;

            if(diff > 0)
                refresh = true;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return refresh;
    }

    @SuppressLint("HardwareIds")
    private static String getPhoneNumber(Activity activity, Context context) {
        TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            IMarketPermission iMarketPermission = new IMarketPermission(activity,context);
            iMarketPermission.checkPermissions();
            return "";
        }
        //TODO CHECK HardwareIds Issue
        return tMgr.getLine1Number();
    }
}
