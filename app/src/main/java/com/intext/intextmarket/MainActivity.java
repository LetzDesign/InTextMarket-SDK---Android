package com.intext.intextmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.intext.intextmarket2.IMarketManager;
import com.intext.intextmarket2.api.IMarketAPI;
import com.intext.intextmarket2.dialogs.IMarketDialogs;
import com.intext.intextmarket2.views.IFunctionsFragment;
import com.intext.intextmarket2.views.IMarketFragment;

public class MainActivity extends AppCompatActivity implements IFunctionsFragment.IMarketFunctionsListener, IMarketFragment.IMarketListener {

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
                    this.getSupportFragmentManager(), //use your activity fragment manager
                    R.id.market_fragment //use your activity fragment container layout id
            );
        }else{
            //IMarket Dialog - example purpose only...
            IMarketDialogs.genericDialog(
                    this,
                    "InText-Market API Auth Error.",
                    "An auth error occurred, please contact us.",
                    IMarketDialogs.IMDialogType.ERROR
            );
        }

        setContentView(R.layout.activity_main);
    }

    //Implement IMarket listeners (IFunctionsFragment.IMarketListener and IMarketFragment.IMarketListener)
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
}
