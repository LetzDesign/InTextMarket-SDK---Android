package com.intext.intextmarket2.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.intext.intextmarket2.db.model.IMAccess;
import com.intext.intextmarket2.db.model.IMTempMarkets;

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

@Database(entities = {IMAccess.class, IMTempMarkets.class}, version = 5, exportSchema = false)
public abstract class IMDataBase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
    public abstract DaoMarkets daoMarkets();
}