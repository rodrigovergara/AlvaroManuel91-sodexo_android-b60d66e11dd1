package sodexo.pe.com.sodexo.data.datasource.rest.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RONALD on 10/10/2016.
 */

public class GoogleApiclient {
    private static GoogleServiceInterface googleServiceInterface;

    public static GoogleServiceInterface getGoogleApiClient() {

        if (googleServiceInterface == null) {

            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/geocode/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();

            googleServiceInterface = restAdapter.create(GoogleServiceInterface.class);
        }
        return googleServiceInterface;
    }
}
