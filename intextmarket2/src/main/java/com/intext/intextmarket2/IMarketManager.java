package com.intext.intextmarket2;

import android.content.Context;
import android.os.Bundle;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.intext.intextmarket2.views.IFunctionsFragment;
import com.intext.intextmarket2.views.IMarketFragment;

public class IMarketManager {

    public static void builder(FragmentManager fragmentManager, int layout){

        Bundle bundle = new Bundle();
        bundle.putInt("fragment_container", layout);
        IMarketFragment iMarketFragment = new IMarketFragment();
        iMarketFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, iMarketFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void showIMarketFunctions(FragmentManager fragmentManager, int layout){

        Bundle bundle = new Bundle();
        bundle.putInt("fragment_container", layout);
        IFunctionsFragment iFunctionsFragment = new IFunctionsFragment();
        iFunctionsFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, iFunctionsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void initIMarketEmoji(Context c){
        EmojiCompat.Config config = new BundledEmojiCompatConfig(c);
        EmojiCompat.init(config);
    }
}
