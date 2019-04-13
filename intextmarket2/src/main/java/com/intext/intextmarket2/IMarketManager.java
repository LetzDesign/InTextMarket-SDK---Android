package com.intext.intextmarket2;

import android.content.Context;
import android.os.Bundle;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.intext.intextmarket2.api.IMarketAPI;
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

public class IMarketManager {

    public static void init(Context context, IMarketAPI.Config config, FragmentManager fragmentManager, int layout){
        IMarketAPI iMarketAPI = new IMarketAPI(context, config, fragmentManager, layout);
        iMarketAPI.apiAccountValidation();
    }

    public static void builder(FragmentManager fragmentManager, int fragmentContainer, String token){

        Bundle bundle = new Bundle();
        bundle.putInt("fragment_container", fragmentContainer);
        bundle.putString("api_token", token);
        IMarketFragment iMarketFragment = new IMarketFragment();
        iMarketFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainer, iMarketFragment);
        fragmentTransaction.commit();
    }

    public static void showIMarketFunctions(FragmentManager fragmentManager, int fragmentContainer, String token){

        Bundle bundle = new Bundle();
        bundle.putInt("fragment_container", fragmentContainer);
        bundle.putString("api_token", token);
        IFunctionsFragment iFunctionsFragment = new IFunctionsFragment();
        iFunctionsFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainer, iFunctionsFragment);
        fragmentTransaction.commit();
    }

    public static void initIMarketEmoji(Context context){
        EmojiCompat.Config config = new BundledEmojiCompatConfig(context);
        EmojiCompat.init(config);
    }
}
