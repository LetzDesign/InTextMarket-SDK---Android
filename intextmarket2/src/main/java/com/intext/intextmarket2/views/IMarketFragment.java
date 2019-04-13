package com.intext.intextmarket2.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.text.emoji.widget.EmojiEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;

import com.intext.intextmarket2.IMarketManager;
import com.intext.intextmarket2.R;
import com.intext.intextmarket2.db.IDBManager;
import com.intext.intextmarket2.dialogs.IMarketDialogs;
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

public class IMarketFragment extends Fragment {

    private View IMarketRoot;
    private int root;
    private String API_TOKEN;
    private IMarketListener iMarketListener;
    private ImageButton sendButton;
    private EmojiEditText emojiEditText;

    public IMarketFragment() {}

    public interface IMarketListener{
        void onSendClick(String message);
        void onGetApiToken(String token);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        IMarketRoot = inflater.inflate(R.layout.fragment_imarket, container, false);

        Bundle bundle = this.getArguments();
        root = Objects.requireNonNull(bundle).getInt("fragment_container", 0);
        API_TOKEN = Objects.requireNonNull(bundle).getString("api_token", "");

        IMUtilities.rootViewValidation(IMarketRoot.getContext(), root);

        sendButton = IMarketRoot.findViewById(R.id.send_msg_id);

        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

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
        initInterfaceCallbacks(view);
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
                    IMarketDialogs.genericDialog(
                            IMarketRoot.getContext(),
                            "Press and hold Event trigger...",
                            "Calling API...\nMessage: " + emojiEditText.getText().toString(),
                            IMarketDialogs.IMDialogType.SUCCESS
                    );
                    cleanEmojiEditText();
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

    private void initInterfaceCallbacks(View v) {
        emojiEditText = v.findViewById(R.id.iMarketTextView);

        sendButton = v.findViewById(R.id.send_msg_id);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iMarketListener != null)
                    iMarketListener.onSendClick(emojiEditText.getText().toString());

                cleanEmojiEditText();
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
    }
}
