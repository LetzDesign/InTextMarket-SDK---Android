package com.intext.intextmarket2.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.intext.intextmarket2.R;

public class IMarketDialogs {

    public static void genericDialog(Context c, String title, String message, IMDialogType type){

        AlertDialog alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setIcon( getAlertIcon(type) );
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private static int getAlertIcon(IMDialogType type){

        int icon = R.drawable.ic_round_help_24px;

        switch (type){
            case ERROR:
                icon = R.drawable.ic_round_error_24px;
                break;
            case WARNING:
                icon = R.drawable.ic_round_warning_24px;
                break;
            case SUCCESS:
                icon = R.drawable.ic_round_check_circle_24px;
                break;
            case HELP:
                break;
        }

        return icon;
    }

    public enum IMDialogType{
        ERROR,
        WARNING,
        SUCCESS,
        HELP,
    }

}
