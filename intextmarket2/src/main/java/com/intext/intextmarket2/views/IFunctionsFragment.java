package com.intext.intextmarket2.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.intext.intextmarket2.IMarketManager;
import com.intext.intextmarket2.R;
import com.intext.intextmarket2.location.IMLocation;
import com.intext.intextmarket2.permissions.IMarketPermission;
import com.intext.intextmarket2.utils.IMUtilities;

import java.util.Objects;

/**
 * Created by Ing. Letzer Cartagena Negron
 * InTextChat @2019
 * @author INTEXT SOFTWARE LLC
 *
 * Copyright (C) 2019 INTEXT SOFTWARE LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class IFunctionsFragment extends Fragment {

    private static final String FRAGMENT_CONTAINER_ARG = "fragment_container";
    private static final String API_TOKEN_ARG = "api_token";

    private View IMarketRoot;
    private int root;
    private String API_TOKEN;
    private IMarketFunctionsListener iMarketFunctionsListener;

    public IFunctionsFragment(){}

    public static IFunctionsFragment newInstance(int container, String apiToken){
        IFunctionsFragment iFunctionsFragment = new IFunctionsFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_CONTAINER_ARG, container);
        args.putString(API_TOKEN_ARG, apiToken);
        iFunctionsFragment.setArguments(args);
        return iFunctionsFragment;
    }

    public interface IMarketFunctionsListener{
        void onCameraClick();
        void onAttachClick();
        void onAudioClick();
        void onVideoClick();
        void onSearchClick();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            root = Objects.requireNonNull(getArguments()).getInt("fragment_container", 0);
            API_TOKEN = getArguments().getString("api_token", "");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        IMarketRoot = inflater.inflate(R.layout.fragment_ifunctions, container, false);

        IMUtilities.rootViewValidation(IMarketRoot.getContext(), root);

        Objects.requireNonNull(getActivity()).getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        initPermissions();
        initCloseAndBackFunctionFragment();

        IMLocation imLocation = new IMLocation(getContext(), getActivity());
        imLocation.init();

        Toast.makeText(
                getContext(),
                "Latitude: " + imLocation.getLatitude()
                        + "\nLongitude: " + imLocation.getLongitude(),
                Toast.LENGTH_SHORT
        ).show();

        return IMarketRoot;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IMarketFunctionsListener)
            iMarketFunctionsListener = (IMarketFunctionsListener)context;
        else{
            throw new RuntimeException(context.toString()
                    + " must implement IMarketFunctionsListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInterfaceCallbacks(view);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iMarketFunctionsListener = null;
    }

    private void initCloseAndBackFunctionFragment() {
        ImageButton close = IMarketRoot.findViewById(R.id.iclose_msg_id);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMarketManager.builder(Objects.requireNonNull(getFragmentManager()), root, API_TOKEN);
            }
        });
    }

    private void initInterfaceCallbacks(View v) {
        ImageButton cameraBtn, attachBtn, audioBtn,
                videoBtn, searchBtn;

        cameraBtn = v.findViewById(R.id.icamera_msg_id);
        attachBtn = v.findViewById(R.id.iattach_msg_id);
        audioBtn = v.findViewById(R.id.iaudio_msg_id);
        videoBtn = v.findViewById(R.id.ivideo_msg_id);
        searchBtn = v.findViewById(R.id.isearch_msg_id);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iMarketFunctionsListener != null)
                    iMarketFunctionsListener.onCameraClick();
            }
        });

        attachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iMarketFunctionsListener != null)
                    iMarketFunctionsListener.onAttachClick();
            }
        });

        audioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iMarketFunctionsListener != null)
                    iMarketFunctionsListener.onAudioClick();
            }
        });

        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iMarketFunctionsListener != null)
                    iMarketFunctionsListener.onVideoClick();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iMarketFunctionsListener != null)
                    iMarketFunctionsListener.onSearchClick();
            }
        });
    }

    private void initPermissions() {
        IMarketPermission iMarketPermission = new IMarketPermission(getActivity(), getContext());
        iMarketPermission.checkPermissions();
    }
}
