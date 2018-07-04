package com.github.masaliev.guardianclient.ui.base;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.masaliev.guardianclient.R;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    private MaterialDialog mProgressDialog;

    public void showProgress(@Nullable String title, @Nullable String content) {
        hideProgress();

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .cancelable(false)
                .progress(true, 0);

        if(title != null){
            builder.title(title);
        }

        if(content != null){
            builder.content(content);
        }
        mProgressDialog = builder.build();
        if(!this.isFinishing()) {
            mProgressDialog.show();
        }
    }

    public void showProgress() {
        showProgress(null, getString(R.string.please_wait));
    }


    public void hideProgress() {
        if(mProgressDialog != null){
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }


    public void handleError(Throwable throwable) {

    }
}
