package com.intext.intextmarket2.api;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.intext.intextmarket2.IMarketManager;
import com.intext.intextmarket2.api.interfaces.IMarketApiAuth;
import com.intext.intextmarket2.api.interfaces.IMarketRequestBusiness;
import com.intext.intextmarket2.api.pojo.APIAuthResponse;
import com.intext.intextmarket2.api.pojo.IMBusinessResponse;
import com.intext.intextmarket2.db.IDBManager;
import com.intext.intextmarket2.dialogs.IMarketDialogs;
import com.intext.intextmarket2.utils.IMUtilities;
import com.intext.intextmarket2.views.IBusinessFragment;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    private IMarketApiAuth iMarketApiAuthService;
    private APIAuthResponse apiAuthResponse;
    private IMBusinessResponse imBusinessResponse;
    private IMarketRequestBusiness iMarketRequestBusiness;
    private Retrofit retrofit;
    private FragmentManager fragmentManager;
    private int layout;

    public IMarketAPI() {}

    public IMarketAPI(Context context) { this.context = context; }

    public IMarketAPI(Config config) {
        this.config = config;
    }

    public IMarketAPI(Context context, Config config) {
        this.config = config;
        this.context = context;
    }

    public IMarketAPI(Context context, Config config, FragmentManager fragmentManager, int layout) {
        this.config = config;
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.layout = layout;
    }

    public void apiAccountValidation(){
        //TODO Configure Server SSL - REMOVE CLEARTEXT = true from the Manifest!!!
        if(IMUtilities.isNetworkConnected(context)) {
            retrofit = IMUtilities.setRetrofit(INTEXTWORDS_BASE_URL + INTEXTWORDS_API_PREFIX + INTEXTWORDS_API_VERSION);
            iMarketApiAuthService = retrofit.create(IMarketApiAuth.class);

            Call<APIAuthResponse> call = iMarketApiAuthService
                    .getApplicationAccess(
                            config.ACCOUNT_KEY,
                            config.ACCOUNT_ID,
                            Config.API_KEY,
                            config.API_SECRET
                    );

            call.enqueue(new Callback<APIAuthResponse>() {
                @Override
                public void onResponse(Call<APIAuthResponse> call, Response<APIAuthResponse> response) {
                    if(response.code() == 200){
                        apiAuthResponse = response.body();
                        if(apiAuthResponse != null){
                            if(apiAuthResponse.getStatus() == 200){

                                IDBManager.init(context);
                                IDBManager.insertAccessData(
                                        apiAuthResponse.getToken(),
                                        apiAuthResponse.getAccountID()
                                );

                                IMarketManager.initIMarketEmoji(context);
                                IMarketManager.builder(fragmentManager, layout, apiAuthResponse.getToken());

                            }else{
                                IMarketDialogs.genericDialog(
                                        context,
                                        "INTextMarket API Auth Fail",
                                        "Please check your application credentials...",
                                        IMarketDialogs.IMDialogType.WARNING
                                );
                            }
                        }else{
                            IMarketDialogs.genericDialog(
                                    context,
                                    "INTextMarket API Auth Fail",
                                    "Please check your application credentials...",
                                    IMarketDialogs.IMDialogType.ERROR
                            );
                        }
                    }
                }

                @Override
                public void onFailure(Call<APIAuthResponse> call, Throwable t) {
                    IMarketDialogs.genericDialog(
                            context,
                            "Auth API Server Error",
                            "An error occurred, please try again or contact us.\n" +
                                    "Error: " + t.getMessage() + "\nCode: " + t.getStackTrace().toString(),
                            IMarketDialogs.IMDialogType.ERROR
                    );
                }
            });
        }
    }

    public synchronized void requestAvailableMarketsByLocation(final FragmentManager fragmentManager , JSONObject imarketObject){
        if(IMUtilities.isNetworkConnected(context)){

            retrofit = IMUtilities.setRetrofit(
                    INTEXTWORDS_BASE_URL + INTEXTWORDS_API_PREFIX +
                            INTEXTWORDS_API_VERSION
            );

            iMarketRequestBusiness = retrofit.create(IMarketRequestBusiness.class);

            Call<IMBusinessResponse> call = iMarketRequestBusiness.getInTextWordsBusiness(Config.API_KEY, imarketObject.toString());

            call.enqueue(new Callback<IMBusinessResponse>() {
                @Override
                public void onResponse(Call<IMBusinessResponse> call, Response<IMBusinessResponse> response) {
                    if(response.code() == 200){
                        imBusinessResponse = response.body();
                        if(imBusinessResponse != null){
                            if(imBusinessResponse.getService().getBusiness().size() > 0){

                                IDBManager.init(context);
                                IDBManager.insertMarketRequest(
                                        IMUtilities.convertPojoToJson(imBusinessResponse)
                                );

                                IBusinessFragment iBusinessFragment = new IBusinessFragment();
                                iBusinessFragment.setImBusinessResponse(imBusinessResponse);
                                iBusinessFragment.show(fragmentManager, "InText Market");

                            }else{
                                //TODO no markets available
                            }
                        }else {
                            //TODO response null
                        }
                    }else{
                        //TODO 404 error
                    }
                }

                @Override
                public void onFailure(Call<IMBusinessResponse> call, Throwable t) {
                    Log.d("WORDS API","fail: "+t.getMessage());
                }
            });

        }else{
            //TODO on network unavailable dialog or handler...
        }
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public static class Config{
        private static String API_KEY = "LmxY3Zkcwp8LJmi5UFlQlBQeWIgoTxIz/$kgfo$d09nsnUCtJlXIjIJfjgZOejgjnLK";
        public String API_SECRET;
        public String ACCOUNT_KEY;
        public int ACCOUNT_ID;
        public String AUTH_END_POINT;
    }
}
