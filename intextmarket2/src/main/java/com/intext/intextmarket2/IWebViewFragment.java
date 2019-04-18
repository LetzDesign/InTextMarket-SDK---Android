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

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.intext.intextmarket2.utils.IMUtilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class IWebViewFragment extends DialogFragment {

    private static final String ARG_URL = "business_url";
    private static final String DEFAULT_URL = "http://www.intextwords.com";
    public static String INTEXT_STORES_CLIENT_KEY = "hghDn0jvVPciVz2MAMxYPoP1DJFiIj21$iu$ldhgpnJx$lCwwTrlaW1VHUekWbAicdNkyJ";
    public static String INTEXT_STORES_SECRET_KEY = "7kr0j2Ke0MZyGU6kjXFDgXlnizwFPn0NWAHyWXtsMa4Uds$LGmYsJufUu3i5El6bs";

    private IWebViewFragmentListener iWebViewFragmentListener;
    private String businessUrl;
    private View iWebViewRoot;
    private WebView iWebView;
    private SwipeRefreshLayout iSwipeLayaout;

    public IWebViewFragment() {}

    public static IWebViewFragment newInstance(String businessUrl) {
        IWebViewFragment iWebViewFragment = new IWebViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, businessUrl);
        iWebViewFragment.setArguments(args);
        return iWebViewFragment;
    }

    public interface IWebViewFragmentListener {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            businessUrl = getArguments().getString(ARG_URL, DEFAULT_URL);
        }

        setStyle(DialogFragment.STYLE_NORMAL, R.style.IWebViewSlide);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        iWebViewRoot = inflater.inflate(R.layout.fragment_iweb_view, container, false);

        initIMarketWebView();

        return iWebViewRoot;
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
        iWebView = null;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();

        if(dialog != null){

            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            dialog.getWindow().setLayout(width, height);
        }
    }

    private int getScale(){
        Display display = ((WindowManager) Objects.requireNonNull(getContext())
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int width = display.getWidth();
        Double val = (double) width / 360d;
        val = val * 100d;
        return val.intValue();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initIMarketWebView(){

        iSwipeLayaout = iWebViewRoot.findViewById(R.id.iswipe_layout_id);
        iWebView = iWebViewRoot.findViewById(R.id.iwebview_fragment_id);

        iWebView.getSettings().setJavaScriptEnabled(true);

        iWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                iSwipeLayaout.setRefreshing(false);
            }
        });

        iWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        iWebView.setPadding(0,0,0,0);
        iWebView.getSettings().setUseWideViewPort(true);
        iWebView.getSettings().setLoadWithOverviewMode(true);
        iWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        iWebView.setInitialScale(getScale());

        iWebView.loadUrl(
                IMUtilities.iWebViewUrlFormatter(
                        businessUrl,
                        INTEXT_STORES_SECRET_KEY,
                        INTEXT_STORES_CLIENT_KEY
                )
        );

        iSwipeLayaout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iWebView.reload();
                iWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            }
        });
    }
}
