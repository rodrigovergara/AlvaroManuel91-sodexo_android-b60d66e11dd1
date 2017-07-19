package sodexo.pe.com.sodexo.presentation.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {


    private View view;
    private int message;

    protected abstract void onPermissionAccept();

    protected void verifyPermissionsApplication(View view, String[] permissions, int requestCode, int message) {
        this.view = view;
        this.message = message;
        if (PermissionUtils.checkSelfPermission(BaseActivity.this, permissions)) {
            if (PermissionUtils.shouldShowRequestPermissionRationale(BaseActivity.this, permissions)) {
                snackBarPermissions(view, permissions, requestCode, message);
            } else {
                ActivityCompat.requestPermissions(BaseActivity.this, permissions, requestCode);
            }
        } else {
            onPermissionAccept();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionUtils.requestCode(requestCode)) {
            if (PermissionUtils.verifyPermissions(grantResults)) {
                onPermissionAccept();
            } else {
                snackBarPermissions(view, permissions, requestCode, message);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    protected void snackBarPermissions(View view, final String[] pemissions, final int requestCode, final int message) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActivityCompat.requestPermissions(BaseActivity.this, pemissions, requestCode);
                    }
                })
                .show();
    }

    protected void showMessage(View container, int message) {
//        Snackbar snackbar = Snackbar.make(container, message, Snackbar.LENGTH_LONG);
//        snackbar.setActionTextColor(Color.RED);
//        View sbView = snackbar.getView();
//        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        snackbar.show();
    }
}
