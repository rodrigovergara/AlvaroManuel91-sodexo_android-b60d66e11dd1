package sodexo.pe.com.sodexo.presentation.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class PermissionUtils {
    public static final int REQUEST = 0;
    public static String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};
    public static int[] REQUEST_CODE = {REQUEST};


    public static boolean checkSelfPermission(Context context, String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (ActivityCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static boolean requestCode(int requestCode) {
        for (int i = 0; i < REQUEST_CODE.length; i++) {
            if (requestCode == REQUEST_CODE[i]) {
                return true;
            }
        }
        return false;
    }
}
