package com.intext.intextmarket2.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.text.emoji.widget.EmojiEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.intext.intextmarket2.IMarketManager;
import com.intext.intextmarket2.R;
import com.intext.intextmarket2.dialogs.IMarketDialogs;
import com.intext.intextmarket2.utils.IMUtilities;

public class IMarketFragment extends Fragment {

    private View IMarketRoot;
    private int root;
    private IMarketListener iMarketListener;
    private ImageButton sendButton;
    private EmojiEditText emojiEditText;

    public IMarketFragment() {}

    public interface IMarketListener{
        void onSendClick(String message);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        IMarketRoot = inflater.inflate(R.layout.fragment_imarket, container, false);

        Bundle bundle = this.getArguments();
        root = bundle.getInt("fragment_container", 0);

        IMUtilities.rootViewValidation(IMarketRoot.getContext(), root);

        sendButton = IMarketRoot.findViewById(R.id.send_msg_id);

        initPressAndHoldListener();
        initFunctionsListener();

        return IMarketRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(getActivity() instanceof IMarketListener)
            iMarketListener = (IMarketListener)getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInterfaceCallbacks(view);
    }

    private void initFunctionsListener() {
        ImageButton functionsButton = IMarketRoot.findViewById(R.id.functions_msg_id);
        functionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMarketManager.showIMarketFunctions(getFragmentManager(), root);
            }
        });
    }

    private void initPressAndHoldListener() {
        sendButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                IMarketDialogs.genericDialog(
                        IMarketRoot.getContext(),
                        "Press and hold Event trigger...",
                        "Calling API...\nMessage: " + emojiEditText.getText().toString(),
                        IMarketDialogs.IMDialogType.SUCCESS
                );
                cleanEmojiEditText();
                return false;
            }
        });
    }

    private void cleanEmojiEditText() {
        emojiEditText.setText("");
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
    }
}
