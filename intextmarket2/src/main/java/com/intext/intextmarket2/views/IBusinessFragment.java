package com.intext.intextmarket2.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intext.intextmarket2.R;
import com.intext.intextmarket2.api.pojo.IMBusinessResponse;
import com.intext.intextmarket2.views.adapters.IMarketsAdapter;

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

public class IBusinessFragment extends DialogFragment {

    private IMarketBusinessListener iMarketBusinessListener;
    private View IBusinessRoot;
    private RecyclerView recyclerView;
    private IMarketsAdapter iMarketCategoryAdapter;
    private IMBusinessResponse imBusinessResponse;

    public interface IMarketBusinessListener {}

    public IBusinessFragment() {}

    public void setImBusinessResponse(IMBusinessResponse imBusinessResponse) {
        this.imBusinessResponse = imBusinessResponse;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        IBusinessRoot = inflater.inflate(R.layout.fragment_ibusiness, container, false);

        recyclerView = IBusinessRoot.findViewById(R.id.business_recycle_fragment_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new IMarketsAdapter(getContext(), imBusinessResponse));

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

}
