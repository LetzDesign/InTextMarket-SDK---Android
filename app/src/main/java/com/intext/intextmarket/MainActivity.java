package com.intext.intextmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.intext.intextmarket2.IMarketManager;
import com.intext.intextmarket2.api.IMarketAPI;
import com.intext.intextmarket2.views.IBusinessFragment;
import com.intext.intextmarket2.views.IFunctionsFragment;
import com.intext.intextmarket2.views.IMarketFragment;

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

public class MainActivity extends AppCompatActivity implements IBusinessFragment.IMarketBusinessListener,
        IFunctionsFragment.IMarketFunctionsListener, IMarketFragment.IMarketListener {

    /**
     * INTEXT MARKET-SDK
     *
     * IMPLEMENTATION SAMPLE
     * InTextChat @2019
     * @author INTEXT SOFTWARE LLC
     */

    //Business Api access token!
    private String API_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Init IMarket Config object
        IMarketAPI.Config config = new IMarketAPI.Config();

        config.ACCOUNT_ID = 0; //use your account id
        config.ACCOUNT_KEY = ""; //use your account key
        config.API_SECRET = ""; //use your api secret key
        config.AUTH_END_POINT = "http://www.api.intextwords.com/api/v1"; //use your api auth end point

        //Init IMarket-SDK
        IMarketManager.init(
                this, //Activity Context
                config,  //IMarket Config Object
                getSupportFragmentManager(), //Activity Fragment Manager
                R.id.market_fragment //Fragment container layout ID
        );

        setContentView(R.layout.activity_main);
    }

    //Implement IMarket listeners (IFunctionsFragment.IMarketFunctionsListener and IMarketFragment.IMarketListener)
    @Override
    public void onCameraClick() {
        Toast.makeText(this, "On Fragment Camera click event.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttachClick() {
        Toast.makeText(this, "On Fragment Attachment click event.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAudioClick() {
        Toast.makeText(this, "On Fragment Audio click event.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onVideoClick() {
        Toast.makeText(this, "On Fragment Video click event.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSharedClick() {
        Toast.makeText(this, "On Fragment Shared click event.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendClick(String message) {
        Toast.makeText(this, "On Fragment Send message click event. \nMessage: "+ message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetApiToken(String token) {
        API_TOKEN = token;
        Toast.makeText(this,"Token: " + API_TOKEN, Toast.LENGTH_SHORT).show();
    }
}
