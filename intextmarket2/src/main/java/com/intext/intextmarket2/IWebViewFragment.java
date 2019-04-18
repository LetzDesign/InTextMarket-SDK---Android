package com.intext.intextmarket2;

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

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class IWebViewFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private IWebViewFragmentListener iWebViewFragmentListener;

    public IWebViewFragment() {}

    public static IWebViewFragment newInstance() {
        IWebViewFragment iWebViewFragment = new IWebViewFragment();
        Bundle args = new Bundle();
        return iWebViewFragment;
    }

    public interface IWebViewFragmentListener {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_iweb_view, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IWebViewFragmentListener) {
            iWebViewFragmentListener = (IWebViewFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IWebViewFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iWebViewFragmentListener = null;
    }

    public void onButtonPressed(Uri uri) {
        if (iWebViewFragmentListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }
}