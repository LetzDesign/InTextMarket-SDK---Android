package com.intext.intextmarket2.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.text.emoji.widget.EmojiEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;

import com.intext.intextmarket2.IMarketManager;
import com.intext.intextmarket2.R;
import com.intext.intextmarket2.api.IMarketAPI;
import com.intext.intextmarket2.db.IDBManager;
import com.intext.intextmarket2.dialogs.IMarketDialogs;
import com.intext.intextmarket2.location.IMLocation;
import com.intext.intextmarket2.permissions.IMarketPermission;
import com.intext.intextmarket2.utils.IMUtilities;

import org.json.JSONException;
import org.json.JSONObject;

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

public class IMarketFragment extends Fragment {

    private static final String FRAGMENT_CONTAINER_ARG = "fragment_container";
    private static final String API_TOKEN_ARG = "api_token";

    private View IMarketRoot;
    private int root;
    private String API_TOKEN;
    private IMarketListener iMarketListener;
    private ImageButton sendButton;
    private EmojiEditText emojiEditText;

    public IMarketFragment() {}

    public static IMarketFragment newInstance(int container, String apiToken){
        IMarketFragment iMarketFragment = new IMarketFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_CONTAINER_ARG, container);
        args.putString(API_TOKEN_ARG, apiToken);
        iMarketFragment.setArguments(args);
        return  iMarketFragment;
    }

    public interface IMarketListener{
        void onSendClick(String message);
        void onGetApiToken(String token);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        if (getArguments() != null) {
            root = Objects.requireNonNull(getArguments()).getInt("fragment_container", 0);
            API_TOKEN = getArguments().getString("api_token", "");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        IMarketRoot = inflater.inflate(R.layout.fragment_imarket, container, false);

        IMUtilities.rootViewValidation(IMarketRoot.getContext(), root);

        sendButton = IMarketRoot.findViewById(R.id.send_msg_id);

        initPressAndHoldListener();
        initFunctionsListener();
        initPermissions();

        return IMarketRoot;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IMarketListener) {
            iMarketListener = (IMarketListener) context;
            initDB(context);
        }
        else{
            throw new RuntimeException(context.toString()
                    + " must implement IMarketListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUIElementsAndInterfaceCallbacks(view);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iMarketListener = null;
    }

    private void initFunctionsListener() {
        ImageButton functionsButton = IMarketRoot.findViewById(R.id.functions_msg_id);
        functionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMarketManager.showIMarketFunctions(Objects.requireNonNull(getFragmentManager()), root, API_TOKEN);
            }
        });
    }

    private void initPressAndHoldListener() {
        sendButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                IMarketPermission iMarketPermission = new IMarketPermission(getActivity(), getContext());

                if(iMarketPermission.locationHasPermission()){

                    IMLocation imLocation = new IMLocation(getContext(), getActivity());
                    imLocation.init();

                    if(imLocation.getLatitude() != null && imLocation.getLongitude() != null){
                        try {

                            String msg = emojiEditText.getText().toString();
                            cleanEmojiEditText();
                            JSONObject jsonObject = IMUtilities.createIMarketTextObject(
                                    getActivity(),//TODO is necessary?
                                    getContext(),//TODO is necessary?
                                    msg,
                                    imLocation.getLatitude(),
                                    imLocation.getLongitude()
                            );

                            IMarketAPI iMarketAPI = new IMarketAPI(getContext());
                            iMarketAPI.requestAvailableMarketsByLocation(getFragmentManager() ,jsonObject, msg);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        IMarketDialogs.turnOnLocationAlertIntent(getContext());
                    }
                }else{
                    IMarketDialogs.locationNotHavePermission(getContext(), iMarketPermission);
                }
                return false;
            }
        });
    }

    private void cleanEmojiEditText() {
        emojiEditText.setText("");

        InputMethodManager inputManager = (InputMethodManager) Objects.requireNonNull(getActivity())
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        View currentFocusedView = getActivity().getCurrentFocus();

        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void initUIElementsAndInterfaceCallbacks(View v) {

        sendButton = v.findViewById(R.id.send_msg_id);
        sendButton.setEnabled(false);
        sendButton.setImageAlpha(0x3F);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iMarketListener != null)
                    iMarketListener.onSendClick(emojiEditText.getText().toString());

                cleanEmojiEditText();
            }
        });

        emojiEditText = v.findViewById(R.id.iMarketTextView);
        emojiEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() < 1){
                    sendButton.setEnabled(false);
                    sendButton.setImageAlpha(0x3F);
                }else{
                    sendButton.setEnabled(true);
                    sendButton.setImageAlpha(0xFF);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(!API_TOKEN.equals(""))
            iMarketListener.onGetApiToken(API_TOKEN);
    }

    private void initPermissions() {
        IMarketPermission iMarketPermission = new IMarketPermission(getActivity(), getContext());
        iMarketPermission.checkPermissions();
    }

    private void initDB(Context context){
        IDBManager.init(context);
        //TODO REMOVE IN PRODUCTION - DEV ONLY. lcn
        //IDBManager.deleteAllMarketsRequests();
    }
}
