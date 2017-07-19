package sodexo.pe.com.sodexo.presentation.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.datasource.services.GpsService;
import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.util.ConnectionUtil;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.activity_splash)
    RelativeLayout activitySplash;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context1, Intent intent) {
            switch (intent.getIntExtra("type", 0)) {
                case 1:
                    SplashActivity.this.stopService(new Intent(SplashActivity.this, GpsService.class));
                    try {
                        SplashActivity.this.unregisterReceiver(mReceiver);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    startActivity(new Intent(SplashActivity.this, PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null ? MainActivity.class : LoginActivity.class));
                    SplashActivity.this.finish();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.registerReceiver(mReceiver, new IntentFilter(this.getString(R.string.intent_filter_broadcast)));
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            verifyPermissions();
        } else {
            validateInternet();
        }
    }

    private void verifyPermissions() {
        verifyPermissionsApplication(activitySplash, PermissionUtils.PERMISSIONS, PermissionUtils.REQUEST, R.string.veryfy_permissions);
    }

    @Override
    protected void onPermissionAccept() {
        validateInternet();
    }

    public void validateInternet() {
        if (ConnectionUtil.isNetworkAvailable(this)) {
            Log.d("validateInternet", "validateInternet");
            validateGps();
        } else {
            showMessage(activitySplash, R.string.internet_conection);
        }
    }

    private void validateGps() {
        LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d("validateGPS", "validate GPS");
            this.startService(new Intent(this, GpsService.class));
        } else {
            Snackbar.make(activitySplash, R.string.message_active_gps, Snackbar.LENGTH_INDEFINITE).setAction("Aceptar", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }).show();
        }
    }

    protected void showMessage(View container, int message) {
        Snackbar snackbar = Snackbar.make(container, message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
}
