package sodexo.pe.com.sodexo.data.datasource.rest;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.WishListDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.retrofit.ApiClient;
import sodexo.pe.com.sodexo.data.model.CommerceEntityData;
import sodexo.pe.com.sodexo.data.model.PromoEntityData;
import sodexo.pe.com.sodexo.data.model.ServiceResponse;
import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;

/**
 * Created by RONALD on 14/10/2016.
 */

public class RestWishlistDataStore implements WishListDataStore {
    @Override
    public void addToWishList(String dni, int asociateId, int promo, final RepositoryCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Usuario", dni);
        params.put("PromocionId", String.valueOf(promo));
        params.put("AsociadoId", String.valueOf(asociateId));
        Call<ServiceResponse<Object>> call = ApiClient.getSodexoApiClient().addToWishList(params);
        call.enqueue(new Callback<ServiceResponse<Object>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Object>> call, Response<ServiceResponse<Object>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Ocurrio un error, por favor intenteló nuevamente");
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Object>> call, Throwable t) {
                callback.onError("Ocurrio un error, por favor intenteló nuevamente");
            }
        });
    }

    @Override
    public void deleteFromWishList(String dni, int commerceId, int promoId, final RepositoryCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("usuario", dni);
        params.put("promocionId", String.valueOf(promoId));
        params.put("asociadoId", String.valueOf(commerceId));
        Call<ServiceResponse<Object>> call = ApiClient.getSodexoApiClient().deleteToWishList(params);
        call.enqueue(new Callback<ServiceResponse<Object>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Object>> call, Response<ServiceResponse<Object>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Ocurrio un error, por favor inténtelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Object>> call, Throwable t) {
                callback.onError("Ocurrio un error, por favor inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getPromosFromWishList(String dni, final RepositoryCallback callback) {
        Call<List<PromoEntityData>> call = ApiClient.getSodexoApiClient().getPromoWishList(dni);
//        Call<List<PromoEntityData>> call = ApiClient.getSodexoApiClient().getPromoWishList(dni);
        call.enqueue(new Callback<List<PromoEntityData>>() {
            @Override
            public void onResponse(Call<List<PromoEntityData>> call, Response<List<PromoEntityData>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Ocurrio un error, por favor inténtelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<List<PromoEntityData>> call, Throwable t) {
                callback.onError("Ocurrio un error, por favor inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getCommercesFromWishList(String dni, final RepositoryCallback callback) {
//        Call<List<CommerceEntityData>> call = ApiClient.getSodexoApiClient().getCommerceWishList(dni);
        Call<List<CommerceEntityData>> call = ApiClient.getSodexoApiClient().getCommerceWishList(dni);
        call.enqueue(new Callback<List<CommerceEntityData>>() {
            @Override
            public void onResponse(Call<List<CommerceEntityData>> call, Response<List<CommerceEntityData>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Ocurrio un error, por favor inténtelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<List<CommerceEntityData>> call, Throwable t) {
                callback.onSuccess("Ocurrio un error, por favor inténtelo nuevamente");
            }
        });
    }
}
