package sodexo.pe.com.sodexo.domain.repository;

import com.google.android.gms.maps.model.LatLng;

import sodexo.pe.com.sodexo.presentation.model.GetFilterLocalInterface;

/**
 * Created by RONALD on 11/10/2016.
 */

public interface FilterLocalRepository {
    void getFilterLocal(LatLng latLng, int id, GetFilterLocalInterface getFilterLocalInterface);

    void getCommerceByUbigeo(String ubigeoCode, GetFilterLocalInterface getFilterLocalInterface);

    void getCommerceByUbigeo(String ubigeoCode, int typeCommerce, GetFilterLocalInterface getFilterLocalInterface);

    void getCommerceByRouletteOptions(int distance, int categoryId, int stars, GetFilterLocalInterface getFilterLocalInterface);
}
