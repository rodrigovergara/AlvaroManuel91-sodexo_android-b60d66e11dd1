package sodexo.pe.com.sodexo.data.datasource.rest.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RONALD on 07/10/2016.
 */

public class ApiClient {
    private static final String TAG = "ApiClient";
    private static SodexoServiceInterface servicesApiInterface;
    private static SodexoIntranetServiceInterface servicesIntranetApiInterface;

    public static SodexoServiceInterface getSodexoApiClient() {

        if (servicesApiInterface == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit restAdapter = new Retrofit.Builder()
                    //.baseUrl("http://sodexo.bitperfect.pe/api/App/")
                    .baseUrl("http://sodexoclub.com.pe/api/App/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            servicesApiInterface = restAdapter.create(SodexoServiceInterface.class);
        }
        return servicesApiInterface;
    }

    public static SodexoIntranetServiceInterface getSodexoIntranetApiClient() {

        if (servicesIntranetApiInterface == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl("http://201.234.48.227:8080/ws/sodexo/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            servicesIntranetApiInterface = restAdapter.create(SodexoIntranetServiceInterface.class);
        }
        return servicesIntranetApiInterface;
    }
}
