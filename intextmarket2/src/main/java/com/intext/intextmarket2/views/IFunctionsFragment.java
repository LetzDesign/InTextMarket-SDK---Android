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
import com.intext.intextmarket2.utils.IMUtilities;

public class IFunctionsFragment extends Fragment {

    private View IMarketRoot;
    private int root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        IMarketRoot = inflater.inflate(R.layout.fragment_ifunctions, container, false);

        Bundle bundle = this.getArguments();
        root = bundle.getInt("fragment_container", 0);

        IMUtilities.rootViewValidation(IMarketRoot.getContext(), root);

        initCloseAndBackFunctionFragment();

        return IMarketRoot;
    }

    private void initCloseAndBackFunctionFragment() {
        ImageButton close = IMarketRoot.findViewById(R.id.iclose_msg_id);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMarketManager.builder(getFragmentManager(), root);
            }
        });
    }
}
