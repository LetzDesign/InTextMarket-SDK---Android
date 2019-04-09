package com.intext.intextmarket2.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.intext.intextmarket2.R;

//https://www.youtube.com/watch?v=b3u4FrD8lP8

public class IMarketFragment extends Fragment {

    private ImageButton sendButton;
    private View IMarketRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        IMarketRoot = inflater.inflate(R.layout.fragment_imarket, container, false);

        initPressAndHoldListener();

        return IMarketRoot;
    }

    private void initPressAndHoldListener() {
        sendButton = IMarketRoot.findViewById(R.id.send_msg_id);

        sendButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("FRAG LONG_CLICK","Execute press and hold event...");
                return false;
            }
        });
    }
}
