package com.intext.intextmarket2;

import android.content.Context;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.intext.intextmarket2.views.IMarketFragment;

public class IMarketManager {

    public static void builder(FragmentManager fragmentManager, int layout){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, new IMarketFragment());
        fragmentTransaction.commit();
    }

    public static void initIMarketEmoji(Context c){
        EmojiCompat.Config config = new BundledEmojiCompatConfig(c);
        EmojiCompat.init(config);
    }
}
