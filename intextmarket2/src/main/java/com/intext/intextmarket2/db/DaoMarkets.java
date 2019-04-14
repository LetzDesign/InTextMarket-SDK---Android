package com.intext.intextmarket2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.intext.intextmarket2.db.model.IMTempMarkets;

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

@Dao
public interface DaoMarkets {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMarketsRequest(IMTempMarkets imTempMarkets);

    @Query("DELETE FROM IMTempMarkets")
    void deleteAllIMarkersRequests();

    @Query("DELETE FROM IMTempMarkets WHERE id = :id")
    void deleteMarketsFirstRequest(int id);

    @Query("SELECT * FROM IMTempMarkets")
    List<IMTempMarkets> selectAllMarketRequested();

    @Query("SELECT * FROM IMTempMarkets WHERE id = :id")
    IMTempMarkets selectMarketRequestById(int id);

    @Query("SELECT COUNT(*) FROM IMTempMarkets")
    int countIMarketsRequests();

    @Query("SELECT id FROM IMTempMarkets LIMIT 1")
    int selectFirstMarketRequestId();

    @Query("SELECT message FROM IMTempMarkets WHERE id = :id")
    String selectMarketRequestMessageById(int id);

    @Query("SELECT message FROM IMTempMarkets WHERE id = (SELECT MAX(ID) FROM IMTempMarkets)")
    String selectMarketRequestLastMessage();

    @Query("SELECT * FROM IMTempMarkets WHERE id = (SELECT MAX(ID) FROM IMTempMarkets)")
    IMTempMarkets selectLastMarketInserted();
}
