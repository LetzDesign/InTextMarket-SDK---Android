package com.intext.intextmarket2.api;

import android.content.Context;

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

public class IMarketAPI {

    private final String INTEXTWORDS_BASE_URL = "http://www.api.intextwords.com/";
    private final String INTEXTWORDS_API_PREFIX = "api/v";
    private final String INTEXTWORDS_API_VERSION = "1";

    private IMarketAPI.Config config;
    private Context context;

    public IMarketAPI(Config config) {
        this.config = config;
    }

    public IMarketAPI(Context context, Config config) {
        this.config = config;
        this.context = context;
    }

    public boolean apiAccountValidation(){
        return true;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public static class Config{
        public String API_KEY = "LmxY3Zkcwp8LJmi5UFlQlBQeWIgoTxIz/$kgfo$d09nsnUCtJlXIjIJfjgZOejgjnLK";
        public String API_SECRET;
        public String ACCOUNT_KEY;
        public int ACCOUNT_ID;
        public String AUTH_END_POINT;
    }
}
