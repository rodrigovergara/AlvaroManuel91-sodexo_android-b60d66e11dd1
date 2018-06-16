package sodexo.pe.com.sodexo.data.datasource.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.PromoDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.retrofit.ApiClient;
import sodexo.pe.com.sodexo.data.model.PromoEntityData;
import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;

/**
 * Created by RONALD on 07/10/2016.
 */

public class RestPromoDataStore implements PromoDataStore {

    public RestPromoDataStore() {
    }

    @Override
    public void getAllPromos(final RepositoryCallback repositoryCallback) {
        Call<List<PromoEntityData>> call = ApiClient.getSodexoApiClient().getPromo("", 0);
        call.enqueue(new Callback<List<PromoEntityData>>() {
            @Override
            public void onResponse(Call<List<PromoEntityData>> call, Response<List<PromoEntityData>> response) {
                repositoryCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<PromoEntityData>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su petición, por favor inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getFilteredPromos(String query, final RepositoryCallback repositoryCallback) {
        Call<List<PromoEntityData>> call = ApiClient.getSodexoApiClient().getPromo(query, 0);
        call.enqueue(new Callback<List<PromoEntityData>>() {
            @Override
            public void onResponse(Call<List<PromoEntityData>> call, Response<List<PromoEntityData>> response) {
                repositoryCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<PromoEntityData>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su petición, por favor inténtelo nuevamente");
            }
        });
    }
}
