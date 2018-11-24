package com.tejachirala.cachecontent.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tejachirala.cachecontent.R;
import com.tejachirala.cachecontent.Utils;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    public interface DialogListener {
        public void onDialogPositiveButtonClicked();
    }

    public void showAlert(String title, String msg, final DialogListener dialogListener) {

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (dialogListener != null) {
                            dialogListener.onDialogPositiveButtonClicked();
                        }
                    }
                })
                .setIcon(Utils.setDrawableColor(this, R.drawable.ic_alert, getResources().getColor(R.color.colorPrimary)))
                .show();

    }

    public void showProgress(String msg, boolean isCancelable) {

        if (!isFinishing()) {
            if (mProgressDialog != null && mProgressDialog.isShowing())
                dismissProgress();

            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setTitle(R.string.app_name);
            mProgressDialog.setMessage(msg);
            mProgressDialog.setCancelable(isCancelable);
            mProgressDialog.show();
        }

    }

    /**
     * Dismisses the progress dialog
     */
    public void dismissProgress() {
        if (!isFinishing() && mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public void showNetworkErrorDialog(DialogListener dialogListener) {
        showAlert(getStringFromResource(R.string.network_error),
                getStringFromResource(R.string.network_message), dialogListener);
    }

    public void showInternalErrorDialog(DialogListener dialogListener) {
        showAlert(getStringFromResource(R.string.internal_error),
                getStringFromResource(R.string.please_try_again), dialogListener);
    }

    public String getStringFromResource(int resId) {
        return getResources().getString(resId);
    }




}
