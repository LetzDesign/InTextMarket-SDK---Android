package com.intext.intextmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.intext.intextmarket2.IMarketManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Init IMarket-SDK
        IMarketManager.initIMarketEmoji(this);
        initInTextMarket();

        setContentView(R.layout.activity_main);
    }

    private void initInTextMarket() {
        IMarketManager.builder(
                this.getSupportFragmentManager(),
                R.id.market_fragment
        );
    }
}
