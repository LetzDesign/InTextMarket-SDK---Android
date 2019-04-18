package com.intext.intextmarket2.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.intext.intextmarket2.R;
import com.intext.intextmarket2.api.IMarketAPI;
import com.intext.intextmarket2.api.pojo.IMBusinessResponse;
import com.intext.intextmarket2.api.pojo.SharedBusinessObject;
import com.intext.intextmarket2.db.IDBManager;
import com.intext.intextmarket2.views.adapters.IMarketsAdapter;

import java.util.List;

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

public class IBusinessFragment extends DialogFragment implements IMarketsAdapter.IMarketAdapterListener {

    private static final String MSG_ARGS = "message";
    private static final String JSON_ARGS = "json";

    private IMarketBusinessListener iMarketBusinessListener;
    private View IBusinessRoot;
    private IMBusinessResponse imBusinessResponse;
    private String json, message;

    public interface IMarketBusinessListener {
        void onMarketsListShare(List<SharedBusinessObject> marketsList);
        void onSingleMarketShare(SharedBusinessObject market);
    }

    public IBusinessFragment() {}

    public static IBusinessFragment newInstance(String json, String message){
        IBusinessFragment iBusinessFragment = new IBusinessFragment();
        Bundle args = new Bundle();
        args.putString(JSON_ARGS, json);
        args.putString(MSG_ARGS, message);
        iBusinessFragment.setArguments(args);
        return iBusinessFragment;
    }

    public void setImBusinessResponse(IMBusinessResponse imBusinessResponse) {
        this.imBusinessResponse = imBusinessResponse;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            json = getArguments().getString("json");
            message = getArguments().getString("message");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        IBusinessRoot = inflater.inflate(R.layout.fragment_ibusiness, container, false);

        initRecycleSearchResultView();
        initSaveSearchButton();
        initShareSearchButton();

        return IBusinessRoot;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IMarketBusinessListener) {
            iMarketBusinessListener = (IMarketBusinessListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IMarketBusinessListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iMarketBusinessListener = null;
    }

    @Override
    public void onSingleShareClick(SharedBusinessObject market) {
        if(iMarketBusinessListener != null)
            iMarketBusinessListener.onSingleMarketShare(market);
    }

    private void initRecycleSearchResultView() {
        RecyclerView recyclerView = IBusinessRoot.findViewById(R.id.business_recycle_fragment_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new IMarketsAdapter(getActivity(), getContext(), imBusinessResponse, this));
    }

    private void initSaveSearchButton(){
        final Button saveSearchBtn = IBusinessRoot.findViewById(R.id.isave_makert_search);
        saveSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDBManager.init(getContext());
                IDBManager.insertMarketHistory(json, message);
                transformSaveHistoryBtn(saveSearchBtn);
            }
        });
    }

    @SuppressLint("Range")
    private void transformSaveHistoryBtn(Button saveSearchBtn){
        saveSearchBtn.setEnabled(false);
        saveSearchBtn.setAlpha(0x3f);
        Drawable top = getResources().getDrawable(R.drawable.ic_round_check_circle_24px);
        saveSearchBtn.setCompoundDrawablesWithIntrinsicBounds(null, top,null,null);
        saveSearchBtn.setText("Saved!");
    }

    private void initShareSearchButton(){
        Button shareSearchBtn = IBusinessRoot.findViewById(R.id.ishare_makert_search);
        shareSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SharedBusinessObject> sharedBusinessObject = IMarketAPI.buildSharedBusinessObject(json);
                iMarketBusinessListener.onMarketsListShare(sharedBusinessObject);
            }
        });
    }
}
