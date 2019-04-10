package com.intext.intextmarket2.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.intext.intextmarket2.IMarketManager;
import com.intext.intextmarket2.R;
import com.intext.intextmarket2.dialogs.IMarketDialogs;
import com.intext.intextmarket2.utils.IMUtilities;

//https://www.youtube.com/watch?v=b3u4FrD8lP8

public class IMarketFragment extends Fragment {

    private View IMarketRoot;
    private int root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        IMarketRoot = inflater.inflate(R.layout.fragment_imarket, container, false);

        Bundle bundle = this.getArguments();
        root = bundle.getInt("fragment_container", 0);

        IMUtilities.rootViewValidation(IMarketRoot.getContext(), root);

        initPressAndHoldListener();
        initFunctionsListener();

        return IMarketRoot;
    }

    private void initFunctionsListener() {
        ImageButton functionsButton = IMarketRoot.findViewById(R.id.functions_msg_id);
        functionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragmentEvent();
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

    private void replaceFragmentEvent() {
        IMarketManager.showIMarketFunctions(getFragmentManager(), root);
    }
}
