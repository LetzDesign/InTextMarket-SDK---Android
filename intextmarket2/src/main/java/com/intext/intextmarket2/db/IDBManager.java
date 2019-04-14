package com.intext.intextmarket2.db;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.intext.intextmarket2.db.model.IMAccess;
import com.intext.intextmarket2.db.model.IMTempMarkets;
import com.intext.intextmarket2.utils.IMUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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

public class IDBManager {

    private static final String DATABASE_NAME = "imarket_db";
    private static IMDataBase imDataBase;

    public static void init(Context context){
        imDataBase = Room.databaseBuilder(context,
                IMDataBase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    //Access
    public static void insertAccessData(final String token, final int accountId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        @SuppressLint("SimpleDateFormat") String insertAt = new SimpleDateFormat("h:mm a")
                                .format(Calendar.getInstance().getTime());

                        imDataBase.daoAccess()
                                .deleteIMAccessData();

                        IMAccess imAccess = new IMAccess();

                        imAccess.setAccountId(accountId);
                        imAccess.setId(IMUtilities.autoPing());
                        imAccess.setInserted_at(insertAt);
                        imAccess.setToken(token);

                        imDataBase.daoAccess()
                                .insertIMAccess(imAccess);
                    }
                });
                Looper.loop();
            }
        }).start();
    }

    public static int countAccessRow(){
        return imDataBase.daoAccess()
                .countAccessData();
    }

    public static void deleteAccessTable(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imDataBase.daoAccess()
                                .deleteIMAccessData();
                    }
                });
                Looper.loop();
            }
        }).start();
    }

    public static void deleteAccessRow(final IMAccess imAccess){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imDataBase.daoAccess()
                                .deleteIMAccess(imAccess);
                    }
                });
                Looper.loop();
            }
        }).start();
    }

    public static IMAccess selectAllAccessData(){
        return imDataBase.daoAccess()
                .getIMAccessData();
    }

    //Markets requests
    public static void insertMarketRequest(final String json){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        @SuppressLint("SimpleDateFormat") String insertAt = new SimpleDateFormat("h:mm a")
                                .format(Calendar.getInstance().getTime());

                        IMTempMarkets imTempMarkets = new IMTempMarkets();

                        imTempMarkets.setId(IMUtilities.autoPing());
                        imTempMarkets.setJson(json);
                        imTempMarkets.setInserted_at(insertAt);

                        imDataBase.daoMarkets()
                                .insertMarketsRequest(imTempMarkets);
                    }
                });
                Looper.loop();
            }
        }).start();
    }

    public static List<IMTempMarkets> selectAllMarketsResquested(){
        return imDataBase.daoMarkets()
                .selectAllMarketRequested();
    }

    public static void deleteAllMarketsRequests(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imDataBase.daoMarkets()
                                .deleteAllIMarkersRequests();
                    }
                });
                Looper.loop();
            }
        }).start();
    }

    public static int countIMarketsRequestRows(){
        return imDataBase.daoMarkets()
                .countIMarketsRequests();
    }
}

