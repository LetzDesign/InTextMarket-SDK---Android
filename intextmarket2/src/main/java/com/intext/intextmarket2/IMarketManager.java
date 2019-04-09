package com.intext.intextmarket2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.intext.intextmarket2.views.IMarketFragment;

public class IMarketManager {

    public static void builder(FragmentManager fragmentManager, int layout){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, new IMarketFragment());
        fragmentTransaction.commit();
    }
}
