package sodexo.pe.com.sodexo.presentation.presenter;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.domain.entity.LocalFilterEntity;

/**
 * Created by RONALD on 11/10/2016.
 */

public interface MainFPresenter {
    void populateFilterOption();

    void showFilterLocal(LatLng latLng, int id);

    void showCommercesFilter(List<LocalFilterEntity> list);

    void getCommercesByUbigeo(UbigeoEntityData ubigeoData);

    void getCommercesByRouletteOptions(int distance, int categoryId, int stars);

    void showFilterLocalByUbigeo(UbigeoEntityData ubigeo, int id);
}