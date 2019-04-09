package com.intext.intextmarket2.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.intext.intextmarket2.R;

public class IFunctionsFragment extends Fragment {

    private View IMarketRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        IMarketRoot = inflater.inflate(R.layout.fragment_ifunctions, container, false);

        return IMarketRoot;
    }
}
