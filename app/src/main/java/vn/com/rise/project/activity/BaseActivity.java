package vn.com.rise.project.activity;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;

/**
 * Created by hoanguyen on 3/1/16.
 */
public abstract class BaseActivity extends FragmentActivity {
    private ProgressDialog mProgressDialog;


    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }

        mProgressDialog.setMessage("Loading...");

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void showProgressDialog(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }

        mProgressDialog.setMessage(msg);

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialog == null) {
            return;
        }

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
