package com.intext.intextmarket2.db;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.intext.intextmarket2.db.model.IMAccess;
import com.intext.intextmarket2.utils.IMUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
                .build();
    }

    public static void insertAccessData(final String token){
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

                        IMAccess imAccess = new IMAccess(
                                IMUtilities.autoPing(),
                                token,
                                insertAt
                        );

                        imDataBase.daoAccess()
                                .insertIMAccess(imAccess);
                    }
                });
                Looper.loop();
            }
        }).start();
    }
}

