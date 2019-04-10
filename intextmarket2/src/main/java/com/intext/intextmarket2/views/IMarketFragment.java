package com.intext.intextmarket2.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.intext.intextmarket2.R;
import com.intext.intextmarket2.dialogs.IMarketDialogs;

//https://www.youtube.com/watch?v=b3u4FrD8lP8

public class IMarketFragment extends Fragment {

    private View IMarketRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        IMarketRoot = inflater.inflate(R.layout.fragment_imarket, container, false);

        initPressAndHoldListener();
        initFunctionsListener();

        return IMarketRoot;
    }

    private void initFunctionsListener() {
        ImageButton functionsButton = IMarketRoot.findViewById(R.id.functions_msg_id);

        functionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMarketDialogs.genericDialog(
                        IMarketRoot.getContext(),
                        "Testing Dialog Angeliz",
                        "Angeliz... ejele",
                        IMarketDialogs.IMDialogType.HELP
                );
            }
        });
    }

    private void initPressAndHoldListener() {
        ImageButton sendButton = IMarketRoot.findViewById(R.id.send_msg_id);

        sendButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                IMarketDialogs.genericDialog(
                        IMarketRoot.getContext(),
                        "Press and hold Event trigger...",
                        "Calling API",
                        IMarketDialogs.IMDialogType.SUCCESS
                );
                return false;
            }
        });
    }
}
