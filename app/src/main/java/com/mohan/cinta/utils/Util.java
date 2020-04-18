package com.mohan.cinta.utils;

import android.app.AlertDialog;
import android.content.Context;

import com.mohan.cinta.R;

public class Util {

    private static AlertDialog alert;
    private static AlertDialog.Builder builder;

    public static void showMessageDialog(Context context, String message) {
        try {
            if (alert != null) {
                if (alert.isShowing()) {
                    alert.cancel();
                }
            }
            builder = new AlertDialog.Builder(context);
            builder.setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton(context.getResources().getString(R.string.ok), (dialog, which) -> dialog.cancel());
            alert = builder.create();
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
