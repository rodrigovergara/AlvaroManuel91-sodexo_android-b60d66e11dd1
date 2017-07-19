package sodexo.pe.com.sodexo.presentation;

import android.app.Application;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by RONALD on 07/10/2016.
 */

public class SodexoApplication extends Application {
    public static Context context;
    public static LatLng clientLocation;
    public static final String USER_DATA = "DATA";
    public static final String SAVE = "SAVE";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
