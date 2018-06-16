package sodexo.pe.com.sodexo.domain.interactor;

import com.google.android.gms.maps.model.LatLng;

import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.presentation.model.GetFilterLocalInterface;

/**
 * Created by RONALD on 11/10/2016.
 */

public interface FilterLocalInteractor {
    void filterLocal(LatLng latLng, int id, GetFilterLocalInterface getFilterLocalInterface);

    void getCommerceByUbigeo(UbigeoEntityData ubigeoData, GetFilterLocalInterface getFilterLocalInterface);

    void getCommerceByUbigeo(UbigeoEntityData ubigeoData, int typeCommerce, GetFilterLocalInterface getFilterLocalInterface);

    void getCommerceByRouletteOptions(int distance, int categoryId, int stars, GetFilterLocalInterface getFilterLocalInterface);
}
