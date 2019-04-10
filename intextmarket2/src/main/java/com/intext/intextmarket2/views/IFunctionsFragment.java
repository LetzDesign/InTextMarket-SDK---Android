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
    private IMarketFunctionsListener iMarketFunctionsListener;

    public IFunctionsFragment(){}

    public interface IMarketFunctionsListener{
        void onCameraClick();
        void onAttachClick();
        void onAudioClick();
        void onVideoClick();
        void onSharedClick();
    }

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(getActivity() instanceof IMarketFunctionsListener)
            iMarketFunctionsListener = (IMarketFunctionsListener)getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInterfaceCallbacks(view);
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

    private void initInterfaceCallbacks(View v) {
        ImageButton cameraBtn, attachBtn, audioBtn,
                videoBtn, shareBtn;

        cameraBtn = v.findViewById(R.id.icamera_msg_id);
        attachBtn = v.findViewById(R.id.iattach_msg_id);
        audioBtn = v.findViewById(R.id.iaudio_msg_id);
        videoBtn = v.findViewById(R.id.ivideo_msg_id);
        shareBtn = v.findViewById(R.id.ishare_msg_id);

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

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iMarketFunctionsListener != null)
                    iMarketFunctionsListener.onSharedClick();
            }
        });
    }
}
