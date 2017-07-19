package sodexo.pe.com.sodexo.data.datasource.rest.interfaces;

import com.google.android.gms.maps.model.LatLng;

import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;

/**
 * Created by RONALD on 11/10/2016.
 */

public interface FilterLocalDataStore {
    void getFilterLocal(int id, LatLng latLng, String description, int ubligeo, int radio, RepositoryCallback callback);

    void getCommerceByUbigeo(String ubigeoCode, RepositoryCallback repositoryCallback);

    void getCommerceByUbigeo(String ubigeoCode, int typeCommerce, RepositoryCallback repositoryCallback);

    void getCommerceByRouletteOptions(int distance, int categoryId, int stars, RepositoryCallback callback);
}
