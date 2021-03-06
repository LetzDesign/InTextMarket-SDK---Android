package com.intext.intextmarket2.api.interfaces;

import com.intext.intextmarket2.api.pojo.APIAuthResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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

public interface IMarketApiAuth {

    @FormUrlEncoded
    @POST("application/auth")
    Call<APIAuthResponse>getApplicationAccess(
            @Field("accountKey") String accountKey,
            @Field("accountID") int accountID,
            @Field("apikey") String apiKey,
            @Field("apisecret") String apiSecret
    );
}
