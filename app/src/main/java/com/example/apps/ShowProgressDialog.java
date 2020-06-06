package com.example.apps;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.view.Window;

import java.util.Objects;


public class ShowProgressDialog {

    private static ProgressDialog progressDialog;

    public static void Show(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait Data Loading...");
//        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(R.color.white_trance_progress);
        }
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(progressDialog.getWindow()).requestFeature(Window.FEATURE_PROGRESS);
        }
//        progressDialog.setContentView(R.layout.progress_bar_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(progressDialog.getWindow()).setLayout(150, 100);
        }
//        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                progressDialog.dismiss();
//            }
//        });
        progressDialog.show();
        System.out.println(progressDialog + " show called");
    }

    public static void Dismiss() {
        System.out.println(progressDialog + " dismiss called");
        if (progressDialog != null) {
            progressDialog.dismiss();
            System.out.println(progressDialog + " in dialog after dismiss" + Thread.currentThread().getId());
        }
    }

    public static boolean isShowing() {
        boolean isShowing = false;
        if (progressDialog != null) {
            isShowing = progressDialog.isShowing();
        }
        return isShowing;
    }
}
