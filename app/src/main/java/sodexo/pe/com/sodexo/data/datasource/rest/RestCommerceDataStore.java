package sodexo.pe.com.sodexo.data.datasource.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.CommerceDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.retrofit.ApiClient;
import sodexo.pe.com.sodexo.data.model.CommerceEntityData;
import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;

/**
 * Created by RONALD on 10/10/2016.
 */

public class RestCommerceDataStore implements CommerceDataStore {
    @Override
    public void getAllCommerces(final RepositoryCallback repositoryCallback) {
        Call<List<CommerceEntityData>> call = ApiClient.getSodexoApiClient().getCommerce("", 0, 0);
        call.enqueue(new Callback<List<CommerceEntityData>>() {
            @Override
            public void onResponse(Call<List<CommerceEntityData>> call, Response<List<CommerceEntityData>> response) {
                repositoryCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<CommerceEntityData>> call, Throwable t) {
                repositoryCallback.onSuccess("Error");
            }
        });
    }

    @Override
    public void getFilterCommerces(String query, final RepositoryCallback repositoryCallback) {
        Call<List<CommerceEntityData>> call = ApiClient.getSodexoApiClient().getCommerce(query, 0, 0);
        call.enqueue(new Callback<List<CommerceEntityData>>() {
            @Override
            public void onResponse(Call<List<CommerceEntityData>> call, Response<List<CommerceEntityData>> response) {
                repositoryCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<CommerceEntityData>> call, Throwable t) {
                repositoryCallback.onSuccess("Error");
            }
        });
    }

}
