package com.intext.intextmarket2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.intext.intextmarket2.db.model.IMAccess;

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
public interface DaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIMAccess(IMAccess imAccess);

    @Query("SELECT * FROM IMAccess ORDER BY inserted_at DESC")
    IMAccess getIMAccessData();

    @Delete
    void deleteIMAccess(IMAccess imAccess);

    @Query("DELETE FROM IMAccess")
    void deleteIMAccessData();

    @Query("SELECT COUNT(*) FROM IMAccess")
    int countAccessData();
}
