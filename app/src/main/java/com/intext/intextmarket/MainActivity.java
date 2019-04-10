package com.intext.intextmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.intext.intextmarket2.IMarketManager;
import com.intext.intextmarket2.api.IMarketAPI;
import com.intext.intextmarket2.dialogs.IMarketDialogs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Init IMarket Config object
        IMarketAPI.Config config = new IMarketAPI.Config();

        config.API_KEY = ""; //use your api key
        config.ACCOUNT_ID = 0; //use your account id
        config.ACCOUNT_KEY = ""; //use your account key
        config.API_SECRET = ""; //use your api secret key
        config.AUTH_END_POINT = ""; //use your api auth end point

        //Init IMarket-SDK
        if(IMarketManager.init(this, config )){
            IMarketManager.initIMarketEmoji(this);
            IMarketManager.builder(
                    this.getSupportFragmentManager(), //activity fragment manager
                    R.id.market_fragment //activity fragment container layout id
            );
        }else{
            IMarketDialogs.genericDialog(
                    this,
                    "InText-Market API Auth Error.",
                    "An auth error occurred, please contact us.",
                    IMarketDialogs.IMDialogType.ERROR
            );
        }

        setContentView(R.layout.activity_main);
    }
}
