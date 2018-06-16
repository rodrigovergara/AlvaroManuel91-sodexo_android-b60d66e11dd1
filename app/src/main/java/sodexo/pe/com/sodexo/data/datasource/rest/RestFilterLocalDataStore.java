package sodexo.pe.com.sodexo.data.datasource.rest;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.FilterLocalDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.retrofit.ApiClient;
import sodexo.pe.com.sodexo.data.model.FilterLocalEntityData;
import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;

/**
 * Created by RONALD on 11/10/2016.
 */

public class RestFilterLocalDataStore implements FilterLocalDataStore {
    @Override
    public void getFilterLocal(int id, LatLng latLng, String description, int ubigeo, int radio, final RepositoryCallback callback) {
        Call<List<FilterLocalEntityData>> call = ApiClient.getSodexoApiClient().getFilterLocal(description, id, "0", latLng.latitude, latLng.longitude, radio);
        call.enqueue(new Callback<List<FilterLocalEntityData>>() {
            @Override
            public void onResponse(Call<List<FilterLocalEntityData>> call, Response<List<FilterLocalEntityData>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<FilterLocalEntityData>> call, Throwable t) {
                callback.onError("Ocurrio un error al momento de realizar su petición, por favor inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getCommerceByUbigeo(String ubigeoCode, final RepositoryCallback repositoryCallback) {
        Call<List<FilterLocalEntityData>> call = ApiClient.getSodexoApiClient().getFilterLocal("", 0, ubigeoCode, 0, 0, 1000);
        call.enqueue(new Callback<List<FilterLocalEntityData>>() {
            @Override
            public void onResponse(Call<List<FilterLocalEntityData>> call, Response<List<FilterLocalEntityData>> response) {
                repositoryCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<FilterLocalEntityData>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su petición, por favor inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getCommerceByUbigeo(String ubigeoCode, int typeCommerce, final RepositoryCallback repositoryCallback) {
        Call<List<FilterLocalEntityData>> call = ApiClient.getSodexoApiClient().getFilterLocal("", typeCommerce, ubigeoCode, 0, 0, 1000);
        call.enqueue(new Callback<List<FilterLocalEntityData>>() {
            @Override
            public void onResponse(Call<List<FilterLocalEntityData>> call, Response<List<FilterLocalEntityData>> response) {
                repositoryCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<FilterLocalEntityData>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su petición, por favor inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getCommerceByRouletteOptions(int distance, int categoryId, int stars, final RepositoryCallback callback) {
        Call<List<FilterLocalEntityData>> call = ApiClient.getSodexoApiClient().getFilterLocal("", categoryId, "", SodexoApplication.clientLocation.latitude, SodexoApplication.clientLocation.longitude, distance);
        call.enqueue(new Callback<List<FilterLocalEntityData>>() {
            @Override
            public void onResponse(Call<List<FilterLocalEntityData>> call, Response<List<FilterLocalEntityData>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<FilterLocalEntityData>> call, Throwable t) {
                callback.onError("Ocurrio un error al momento de realizar su petición, por favor inténtelo nuevamente");
            }
        });
    }
}
