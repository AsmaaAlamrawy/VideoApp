package com.sample.videoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogMaker {
        public static AlertDialog makeDialog(Context context, String message,
                                             String positiveStr, DialogInterface.OnClickListener positiveListener,
                                             String negativeStr, DialogInterface.OnClickListener negativeListener) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            return builder.setMessage(message)
                    .setPositiveButton(positiveStr, positiveListener)
                    .setNegativeButton(negativeStr, negativeListener)
                    .setCancelable(false)
                    .create();
        }
}
